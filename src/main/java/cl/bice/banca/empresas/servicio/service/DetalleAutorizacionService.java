package cl.bice.banca.empresas.servicio.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.TabSettings;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.Base64;

import cl.bice.banca.empresas.servicio.exception.BancaEmpresasException;
import cl.bice.banca.empresas.servicio.exception.DocumentPdfException;
import cl.bice.banca.empresas.servicio.exception.EntradaInvalidaException;
import cl.bice.banca.empresas.servicio.exception.NoEncontradoException;
import cl.bice.banca.empresas.servicio.exception.RequestInvalidoException;
import cl.bice.banca.empresas.servicio.model.common.Banco;
import cl.bice.banca.empresas.servicio.model.common.BaseRequestEmpresa;
import cl.bice.banca.empresas.servicio.model.common.Constantes;
import cl.bice.banca.empresas.servicio.model.common.ConstantesPdfAutorizacion;
import cl.bice.banca.empresas.servicio.model.nominas.NominaEnLinea;
import cl.bice.banca.empresas.servicio.model.operaciones.MapOperaciones;
import cl.bice.banca.empresas.servicio.model.request.operaciones.PdfAutorizacionRequest;
import cl.bice.banca.empresas.servicio.model.response.Estado;
import cl.bice.banca.empresas.servicio.util.EstadoEnum;
import cl.bice.banca.empresas.servicio.util.FormateadorUtil;

/**
 * Clase con los métodos para generar un PDF con el detalle de autorización de
 * las operaciones que se van a aprobar.
 * 
 * @author Fibacache
 *
 */
@Service
public class DetalleAutorizacionService {

	/**
	 * Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(DetalleAutorizacionService.class);
	/**
	 * Propiedades Externas.
	 */
	@Autowired
	private Properties propiedadesExterna;

	/**
	 * Logo banco
	 */
	private static final String LOGO_BANCO = "/logo_bice.jpg";
	/**
	 * Fuente Arial
	 */
	private static final String FUENTE_ARIAL = "/ARIAL.TTF";

	/**
	 * String mesajes
	 */
	private static final String ARCHIVO_BASE_ELIMINADO_OK = "Archivo base eliminado ok";

	/**
	 * Clave Map NumeroOperacion
	 */
	private static final String REGISTROS_CLAVE_MAP_NRO_OPERACION = "NumeroOperacion";

	private static final String TIPO_OPERACION_NOMINA = "nominas";

	private static final String NOMBRE_VALOR_MONTO = "Monto";

	/**
	 * Fuentes
	 */
	private Font font7;
	private Font fontb7;

	private Font font10;
	private Font fontb10;

	private Font fontb11;
	private Font font11;

	/**
	 * Document
	 */
	private Document document = null;

	/**
	 * Contador de registros que se van imprimiendo en el archivo PDF.
	 * 
	 */
	private int numUltimoRegistroDocumento = 0;

	/**
	 * Operaciones empresa service
	 */
	@Autowired
	OperacionesEmpresaService operacionesEmpresaService;
	/**
	 * Valores campos operaciones service
	 */
	@Autowired
	ValoresCampoOperacionesService valoresCampoOperacionesService;
	/**
	 * Empresas service
	 */
	@Autowired
	EmpresasService empresasService;
	/**
	 * Validacion service
	 */
	@Autowired
	ValidacionService validacionService;
	/**
	 * Estado service
	 */
	@Autowired
	EstadoService estadoService;

	@Autowired
	NominasEnLineaService nominasEnLineaService;

	@Autowired
	BancosService bancosService;
	

	@Autowired
	BiceComexService biceComexService;

	/**
	 * Obtiene el nombre de las columnas a mostrar en la tabla con el detalle de las
	 * operaciones autorizadas.
	 * 
	 * @return
	 */
	private String[] obtenerNombresValoresColumnas() {
		return propiedadesExterna.getProperty("servicios.pdf.autorizacion.tabla.operaciones.nombres.valores.columnas")
				.split(",");
	}

	/**
	 * Agrega a el map de registros el valor de un campo de una operación
	 * determinada.
	 * 
	 * @param reg
	 * @param nroOperacion
	 * @param nombreValor
	 * @throws BancaEmpresasException
	 */
	private void agregarRegistroValorCampoOperacion(Map<Object, Object> reg, String nroOperacion, String nombreValor,
			MapOperaciones mapOperaciones) throws BancaEmpresasException {

		if (REGISTROS_CLAVE_MAP_NRO_OPERACION.equals(nombreValor)) {
			reg.put(nombreValor, nroOperacion);
		} else {
			String val = operacionesEmpresaService.valoresCampoOperacionesService.getValorCampoOperacion(mapOperaciones,
					nroOperacion, nombreValor, true);
			if ("NroCuentaCargo".equals(nombreValor))
				val = FormateadorUtil.formatoProducto(val,
						Integer.parseInt(propiedadesExterna.getProperty("servicios.lbtr.codigo.producto")));
			if (NOMBRE_VALOR_MONTO.equals(nombreValor))
				val = FormateadorUtil.formatearMonto(val,
						propiedadesExterna.getProperty("servicios.lbtr.codigo.moneda"));
			reg.put(nombreValor, val);

		}
	}

	/**
	 * Crea una lista, donde cada elemento es un registro que se identifica por
	 * número de operación y estos registros contienen los datos de dichas
	 * operaciones.
	 * 
	 * @param request
	 * @param codigoServicio
	 * @param listOperaciones
	 * @return
	 * @throws BancaEmpresasException
	 */
	private List<Map<Object, Object>> crearRegistros(List<String> listOperaciones, MapOperaciones mapOperaciones)
			throws BancaEmpresasException {
		Map<Object, Object> reg = null;
		List registros = new ArrayList<>();
		String[] nombresValoresColumnas = obtenerNombresValoresColumnas();

		for (String nroOperacion : listOperaciones) {
			reg = new HashMap<>();
			for (String nombreValor : nombresValoresColumnas) {
				agregarRegistroValorCampoOperacion(reg, nroOperacion, nombreValor, mapOperaciones);
			}
			registros.add(reg);
		}

		return registros;
	}

	/**
	 * Este método recibe una lista con número de operaciones y obtiene el monto
	 * total de todas ellas.
	 * 
	 * @param listOperaciones
	 * @return
	 * @throws BancaEmpresasException
	 */
	private String obtenerMontoTotalOperaciones(List<String> listOperaciones, MapOperaciones mapOperaciones)
			throws BancaEmpresasException {
		String resultado = "";
		long suma = 0;

		try {
			for (String nroOperacion : listOperaciones) {
				suma = suma + Long.parseLong(valoresCampoOperacionesService.getValorCampoOperacion(mapOperaciones,
						nroOperacion, NOMBRE_VALOR_MONTO, true));
			}
			resultado = Long.toString(suma);
		} catch (NumberFormatException e) {
			throw new BancaEmpresasException();
		}

		return resultado;
	}

	/**
	 * Método encargado de controlar posibles errores al generar el documento con el
	 * detalle de la autorizaci&oacute;n para las operaciones de tipo LBTR y nominas
	 * en linea. La finalidad de este métdo es controlar errores para que el
	 * controller luego pueda enviarlos al front.
	 * 
	 * @param request
	 * @param codigoServicio
	 * @param listaOperaciones
	 * @param estado
	 * @return
	 * @throws BancaEmpresasException
	 */
	public String obtenerPDFDetalleAutorizacion(PdfAutorizacionRequest request, String codigoServicio,
			List<String> listaOperaciones, Estado estado) {
		String response = "";

		try {
			if (Constantes.CODIGO_SERVICIO_NOMINAS_EN_LINEA.equals(request.getCodigoServicio())) {
				List<NominaEnLinea> listNominas = nominasEnLineaService.obtenerListaNominasEnLinea(listaOperaciones);
				if (!validacionService.isPertenenciaNominaValida(request, codigoServicio, listNominas))
					throw new RequestInvalidoException();

				response = generaDocPdfOperacionesNominasEnLinea(request, listNominas);
			} else {
				MapOperaciones mapOperaciones = valoresCampoOperacionesService.obtenerMapOperaciones(request.getRut(),
						request.getRutEmpresa(), codigoServicio);
				if (!validacionService.isPertenenciaValida(request, codigoServicio, listaOperaciones, mapOperaciones,
						true))
					throw new RequestInvalidoException();
				if (Constantes.CODIGO_SERVICIO_LBTR.equals(request.getCodigoServicio())) {
					response = generaDocPdfOperacionesLBTR(request, listaOperaciones, mapOperaciones);
				} else if (Constantes.CODIGO_SERVICIO_SPOTWEB.equals(request.getCodigoServicio())
						|| Constantes.CODIGO_SERVICIO_TEF_MX.equals(request.getCodigoServicio())) {
					response = generaDocPdfOperacionesMultiMoneda(request, listaOperaciones, mapOperaciones,
							request.getCodigoServicio());
				}

				mapOperaciones.clearMap();
			}
		} catch (DocumentPdfException e) {
			LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
			estadoService.setErrorEstado(EstadoEnum.ERROR_E003_EMPRESAS_DETALLE_AUTORIZACION, estado);
		} catch (RequestInvalidoException | EntradaInvalidaException e) {
			LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
			estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_REQUEST_INVALIDO, estado);
		} catch (NoEncontradoException e) {
			LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
			estadoService.setErrorEstado(EstadoEnum.ERROR_E002_EMPRESAS_DETALLE_AUTORIZACION, estado);
		} catch (BancaEmpresasException e) {
			LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
			estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_DETALLE_AUTORIZACION, estado);
		}

		return response;
	}

	/**
	 * Inicializa fuentes.
	 * 
	 * @throws DocumentException
	 * @throws IOException
	 */
	private void inicializarFuentes() throws DocumentException, IOException {
		BaseFont bf = BaseFont.createFont(
				propiedadesExterna.getProperty("servicios.pdf.autorizacion.ruta.fuente") + FUENTE_ARIAL,
				BaseFont.CP1252, BaseFont.EMBEDDED);

		Font font = new Font(bf, 5);
		font.setColor(BaseColor.WHITE);
		Font fontb = new Font(bf, 7);
		fontb.setColor(BaseColor.WHITE);
		font7 = new Font(bf, 7);
		fontb7 = new Font(bf, 7, Font.BOLD);

		font10 = new Font(bf, 10);
		fontb10 = new Font(bf, 10, Font.BOLD);

		fontb11 = new Font(bf, 11, Font.BOLD);
		font11 = new Font(bf, 11);
	}

	/**
	 * Genera el documento con el detalle de autorizaci&oacute;n para operaciones de
	 * tipo LBTR.
	 * 
	 * @param request
	 * @param listOperaciones
	 * @param mapOperaciones
	 * @return
	 * @throws BancaEmpresasException
	 */
	public String generaDocPdfOperacionesLBTR(BaseRequestEmpresa request, List<String> listOperaciones,
			MapOperaciones mapOperaciones) throws BancaEmpresasException {
		List<Map<Object, Object>> registros = crearRegistros(listOperaciones, mapOperaciones);

		String montoTotal = obtenerMontoTotalOperaciones(listOperaciones, mapOperaciones);
		String[] nombresValoresColumnas = obtenerNombresValoresColumnas();

		String subtitulo = propiedadesExterna.getProperty("servicios.pdf.autorizacion.subtitulo");
		return generaDocPdf(request, listOperaciones.size(), registros, montoTotal, nombresValoresColumnas, subtitulo,
				"", Constantes.CODIGO_SERVICIO_LBTR);
	}

	/**
	 * Genera el documento con el detalle de autorizaci&oacute;n para operaciones de
	 * tipo NOMINAS.
	 * 
	 * @param request
	 * @param listNominas
	 * @return
	 * @throws BancaEmpresasException
	 */
	public String generaDocPdfOperacionesNominasEnLinea(BaseRequestEmpresa request, List<NominaEnLinea> listNominas)
			throws BancaEmpresasException {
		List<Map<Object, Object>> registros = crearRegistrosNominas(listNominas);

		String montoTotal = String.valueOf(nominasEnLineaService.obtenerMontoTotalNominas(listNominas));
		String[] nombresValoresColumnas = propiedadesExterna
				.getProperty("servicios.pdf.autorizacion.tabla.nominas.nombres.valores.columnas").split(",");

		String subtitulo = propiedadesExterna.getProperty("servicios.pdf.autorizacion.nominas.en.linea.subtitulo");
		String paramTipoOperacionNomina = "." + TIPO_OPERACION_NOMINA;
		return generaDocPdf(request, listNominas.size(), registros, montoTotal, nombresValoresColumnas, subtitulo,
				paramTipoOperacionNomina, Constantes.CODIGO_SERVICIO_NOMINAS_EN_LINEA);
	}

	/**
	 * Método principal que crea el PDF llamando a los demás métodos auxiliares.
	 * 
	 * @param request
	 * @param listOperaciones
	 * @return
	 * @throws BancaEmpresasException
	 */
	public String generaDocPdf(BaseRequestEmpresa request, int totalOperaciones, List<Map<Object, Object>> registros,
			String montoTotal, String[] nombresValoresColumnas, String subtitulo, String paramTipoOperacion,
			String codigoServicio) throws BancaEmpresasException {

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHmmssSSSS");
			String date = sdf.format(new Date());
			String nombreArchivoPDF = propiedadesExterna.getProperty("servicios.pdf.autorizacion.ruta.pdf")
					+ "DetalleAutorizacion" + "_" + date + ".pdf";

			File archivoPdf = new File(nombreArchivoPDF);

			document = new Document(PageSize.LETTER);
			LOGGER.info("Configuracion de documento Finalizada");

			PdfWriter.getInstance(document, new FileOutputStream(archivoPdf));

			if (!document.isOpen()) {
				document.open();
			}

			inicializarFuentes();

			// Carga de imagenes
			Image logoBice = Image.getInstance(
					propiedadesExterna.getProperty("servicios.pdf.autorizacion.ruta.imagenes") + LOGO_BANCO);
			// dimension de la imagen y posicion del logo
			logoBice.scaleAbsolute(ConstantesPdfAutorizacion.DIMENSION_IMG_X,
					ConstantesPdfAutorizacion.DIMENSION_IMG_Y);
			logoBice.setAbsolutePosition(ConstantesPdfAutorizacion.POSICION_IMG_X,
					ConstantesPdfAutorizacion.POSICION_IMG_Y);

			document.add(logoBice);

			crearEncabezadoDocumento(request, totalOperaciones, montoTotal, subtitulo);

			crearCuerpoDocumento(logoBice, registros, nombresValoresColumnas, paramTipoOperacion, codigoServicio);

			if (document.isOpen()) {
				document.close();
			}

			PdfReader reader = new PdfReader(archivoPdf.getPath());
			String pathFinal = archivoPdf.getAbsolutePath().replace(".pdf", "_final.pdf");
			int totalPaginas = reader.getNumberOfPages();

			// stamper para copiar pdf y asignar numeros de pagina
			PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(pathFinal));
			PdfContentByte pagecontent;
			for (int i = 1; i < totalPaginas; i++) {
				pagecontent = stamper.getOverContent(i);
				ColumnText.showTextAligned(pagecontent, Element.ALIGN_RIGHT,
						new Phrase(propiedadesExterna.getProperty("servicios.pdf.autorizacion.titulo") + " - Página "
								+ i + " de " + totalPaginas, font10),
						550, 30, 0);
			}
			stamper.close();
			reader.close();
			Path path = Paths.get(pathFinal);
			byte[] data = Files.readAllBytes(path);

			String archivoBase64 = Base64.encodeBytes(data, Base64.DONT_BREAK_LINES);
			if (Files.deleteIfExists(path) && Files.deleteIfExists(Paths.get(archivoPdf.getAbsolutePath()))) {
				LOGGER.debug(ARCHIVO_BASE_ELIMINADO_OK);
			}

			return archivoBase64;
		} catch (IOException | DocumentException e) {
			throw new DocumentPdfException(e);
		}
	}

	/**
	 * Crea el encabezado del documento PDF.
	 * 
	 * @param request
	 * @param codigoServicio
	 * @param listOperaciones
	 * @throws DocumentException
	 * @throws BancaEmpresasException
	 */
	private void crearEncabezadoDocumento(BaseRequestEmpresa request, int totalOperaciones, String montoTotal,
			String subtitulo) throws DocumentException, BancaEmpresasException {

		String titulo = propiedadesExterna.getProperty("servicios.pdf.autorizacion.titulo");

		document.add(new Paragraph(new Chunk("")));
		document.add(Chunk.NEWLINE);
		Chunk chunk = new Chunk(titulo, fontb11);

		Paragraph parrafoTitulo = new Paragraph(chunk);
		parrafoTitulo.setAlignment(Element.ALIGN_CENTER);
		document.add(parrafoTitulo);

		chunk = new Chunk(subtitulo, font11);

		Paragraph parrafoSubTitulo = new Paragraph(chunk);
		parrafoSubTitulo.setAlignment(Element.ALIGN_CENTER);
		document.add(parrafoSubTitulo);

		document.add(Chunk.NEWLINE);
		document.add(Chunk.NEWLINE);

		Paragraph encabezado = new Paragraph();
		encabezado.setTabSettings(new TabSettings(56f));
		encabezado.add(new Chunk(
				propiedadesExterna.getProperty("servicios.pdf.autorizacion.encabezado.item.empresa") + ":", fontb10));
		encabezado.add(Chunk.TABBING);
		encabezado.add(Chunk.TABBING);
		encabezado.add(Chunk.TABBING);
		encabezado.add(new Chunk(empresasService.obtenerNombreCliente(request.getRutEmpresa()), font10));
		encabezado.add(Chunk.NEWLINE);
		encabezado.add(new Chunk(
				propiedadesExterna.getProperty("servicios.pdf.autorizacion.encabezado.item.apoderado") + ":", fontb10));
		encabezado.add(Chunk.TABBING);
		encabezado.add(Chunk.TABBING);
		encabezado.add(new Chunk(empresasService.obtenerNombreApoderado(request.getRut()), font10));
		encabezado.add(Chunk.NEWLINE);
		encabezado.add(new Chunk(
				propiedadesExterna.getProperty("servicios.pdf.autorizacion.encabezado.item.fechahora") + ":", fontb10));
		encabezado.add(Chunk.TABBING);
		encabezado.add(new Chunk(empresasService.fechaHoy("dd/MM/yyyy HH:mm"), font10));

		if (null != montoTotal) {
			encabezado.add(Chunk.NEWLINE);
			encabezado.add(new Chunk(
					propiedadesExterna.getProperty("servicios.pdf.autorizacion.encabezado.item.montototal") + ":",
					fontb10));
			encabezado.add(Chunk.TABBING);
			encabezado.add(Chunk.TABBING);
			encabezado.add(new Chunk(FormateadorUtil.formatearMonto(montoTotal,
					propiedadesExterna.getProperty("servicios.lbtr.codigo.moneda")), font10));
		}

		encabezado.add(Chunk.NEWLINE);
		encabezado.add(new Chunk(
				propiedadesExterna.getProperty("servicios.pdf.autorizacion.encabezado.item.totaloperaciones") + ":",
				fontb10));
		encabezado.add(Chunk.TABBING);
		encabezado.add(Chunk.TABBING);
		encabezado.add(new Chunk(String.valueOf(totalOperaciones), font10));
		encabezado.add(Chunk.NEWLINE);

		encabezado.setAlignment(Element.ALIGN_JUSTIFIED);
		document.add(encabezado);

		document.add(Chunk.NEWLINE);
		document.add(Chunk.NEWLINE);

	}

	/**
	 * Inicializa la tabla que contiene los detalles de las operaciones.
	 * 
	 * @return
	 * @throws DocumentException
	 */
	private PdfPTable inicializarTablaDetalleOperaciones(String paramTipoOperacion, String codigoServicio)
			throws DocumentException {
		// Tabla detalle operaciones
		Integer cantidadColumnasTablaOperaciones = Integer.valueOf(propiedadesExterna.getProperty(
				"servicios.pdf.autorizacion.tabla.operaciones" + paramTipoOperacion + ".cantidad.columnas"));
		PdfPTable table = new PdfPTable(cantidadColumnasTablaOperaciones);
		table.setWidthPercentage(ConstantesPdfAutorizacion.LARGO_TABLA);

		if (Constantes.CODIGO_SERVICIO_SPOTWEB.equals(codigoServicio)
				|| Constantes.CODIGO_SERVICIO_TEF_MX.equals(codigoServicio)
				|| Constantes.CODIGO_SERVICIO_BICECOMEX.equals(codigoServicio)) {
			String[] anchosColumnas = propiedadesExterna
					.getProperty("servicios.pdf.autorizacion.tabla" + paramTipoOperacion + ".anchos.columnas").split(",");
			float[] widths = new float[cantidadColumnasTablaOperaciones];
			int i = 0;
			for (String s : anchosColumnas) {
				widths[i] = Float.parseFloat(s);
				i++;
			}
			table.setWidths(widths);
		} else {
			table.setWidths(new float[] { 5, 7, 6, 7, 6, 5, 5, 5 });
		}

		table.getDefaultCell().setPadding(ConstantesPdfAutorizacion.ESPACIADO_TABLA);
		table.getDefaultCell().setUseAscender(true);
		table.getDefaultCell().setUseDescender(true);
		table.getDefaultCell().setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

		for (int numColumna = 1; numColumna <= cantidadColumnasTablaOperaciones.intValue(); numColumna++) {
			table.addCell(new Phrase(propiedadesExterna.getProperty(
					"servicios.pdf.autorizacion.tabla.operaciones" + paramTipoOperacion + ".columna" + numColumna),
					fontb7));
		}

		return table;
	}

	/**
	 * Agrega una celda a la tabla de detalles de las operaciones.
	 * 
	 * @param table
	 * @param valorCelda
	 */
	private void agregarCeldaTablaDocumento(PdfPTable table, String valorCelda) {
		PdfPCell cell = new PdfPCell(new Paragraph(valorCelda, font7));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setFixedHeight(35f);
		table.addCell(cell);
	}

	/**
	 * Agrega la tabla de detalles de las operaciones al documento PDF.
	 * 
	 * @param registros
	 * @param nombresValoresColumnas
	 * @param cantidadRegistros
	 * @throws DocumentException
	 */
	private void agregarTablaDocumento(List<Map<Object, Object>> registros, String[] nombresValoresColumnas,
			int cantidadRegistros, String paramTipoOperacion, String codigoServicio) throws DocumentException {
		PdfPTable table = inicializarTablaDetalleOperaciones(paramTipoOperacion, codigoServicio);

		for (int i = numUltimoRegistroDocumento; i < registros.size(); i++) {
			if (i < cantidadRegistros) {
				for (String nombreValor : nombresValoresColumnas) {
					agregarCeldaTablaDocumento(table, (String) registros.get(i).get(nombreValor));
					numUltimoRegistroDocumento = i;
				}
			} else {
				break;
			}
		}
		document.add(table);
	}

	/**
	 * Agrega el párrafo en el pié de página del documento PDF.
	 * 
	 * @throws DocumentException
	 */
	private void agregarParrafoPiePagina() throws DocumentException {
		Paragraph parrafo = new Paragraph();
		parrafo.add(new Chunk(propiedadesExterna.getProperty("servicios.pdf.autorizacion.pie.pagina"), font10));
		parrafo.setAlignment(Element.ALIGN_JUSTIFIED);
		document.add(parrafo);
	}

	/**
	 * Crea el cuerpo del documento PDF.
	 * 
	 * @param logoBice
	 * @param registros
	 * @throws DocumentException
	 */
	private void crearCuerpoDocumento(Image logoBice, List<Map<Object, Object>> registros,
			String[] nombresValoresColumnas, String paramTipoOperacion, String codigoServicio)
			throws DocumentException {

		int regTotal = registros.size();
		int regPag1 = Integer
				.parseInt(propiedadesExterna.getProperty("servicios.pdf.autorizacion.registros.pagina.portada"));
		int regPagSiguientes = Integer
				.parseInt(propiedadesExterna.getProperty("servicios.pdf.autorizacion.registros.pagina.siguientes"));

		Paragraph titloTabla = new Paragraph(new Chunk(
				propiedadesExterna.getProperty("servicios.pdf.autorizacion.tabla.operaciones.titulo"), fontb10));
		titloTabla.setAlignment(Element.ALIGN_LEFT);
		document.add(titloTabla);
		document.add(Chunk.NEWLINE);

		numUltimoRegistroDocumento = 0;

		agregarTablaDocumento(registros, nombresValoresColumnas, regPag1, paramTipoOperacion, codigoServicio);

		document.add(Chunk.NEWLINE);

		if (regTotal <= regPag1) {
			agregarParrafoPiePagina();
			document.add(Chunk.NEWLINE);
		}

		if (regTotal > regPag1) {
			int aux = regTotal - regPag1;
			float resultado = (float) aux / (float) regPagSiguientes;
			int totalPaginas = (int) Math.ceil(resultado);
			int pag = 0;

			for (int k = 0; k < totalPaginas; k++) {
				document.newPage();
				document.add(logoBice);
				document.add(Chunk.NEWLINE);
				pag++;
				int limite = pag * regPagSiguientes;
				limite = limite + regPag1;
				numUltimoRegistroDocumento = numUltimoRegistroDocumento + 1;
				agregarTablaDocumento(registros, nombresValoresColumnas, limite, paramTipoOperacion, codigoServicio);

				document.add(Chunk.NEWLINE);

				if (pag == totalPaginas) {
					agregarParrafoPiePagina();
				}

			}
		}
	}

	/**
	 * Recibe el nombre del valor que se quiere obtener del objeto de tipo
	 * NominaEnLinea y retorna dicho valor.
	 *
	 * @param nomina
	 * @param nombreValor
	 * @return
	 * @throws BancaEmpresasException
	 */
	private String obtenerValorCampoNomina(NominaEnLinea nomina, String nombreValor) {

		String val = "";
		if ("FechaCarga".equalsIgnoreCase(nombreValor))
			val = nomina.getFechaCarga().substring(0, 10);
		if ("FechaPago".equalsIgnoreCase(nombreValor))
			val = nomina.getFechaPago().substring(0, 10);
		if ("MailOrdenante".equalsIgnoreCase(nombreValor))
			val = nomina.getMailOrdenante();
		if ("NomArchivo".equalsIgnoreCase(nombreValor))
			val = nomina.getNomArchivo();
		if ("NombreEmpresa".equalsIgnoreCase(nombreValor))
			val = nomina.getNombreEmpresa();
		if ("Referencia".equalsIgnoreCase(nombreValor))
			val = nomina.getReferencia();
		if ("RutEmpresa".equalsIgnoreCase(nombreValor))
			val = nomina.getRutEmpresa();
		if ("SecuenciaCCA".equalsIgnoreCase(nombreValor))
			val = nomina.getSecuenciaCCA();
		if ("TipoNomina".equalsIgnoreCase(nombreValor))
			val = nomina.getTipoNomina();
		if ("CodNomina".equalsIgnoreCase(nombreValor))
			val = String.valueOf(nomina.getCodNomina());
		if ("CodTipoNomina".equalsIgnoreCase(nombreValor))
			val = String.valueOf(nomina.getCodTipoNomina());
		if ("NroCuentaCargo".equalsIgnoreCase(nombreValor)) {
			val = String.valueOf(nomina.getCuentaCargo());
			val = FormateadorUtil.formatoProducto(val,
					Integer.parseInt(propiedadesExterna.getProperty("servicios.nominas.codigo.producto")));
		}
		if (NOMBRE_VALOR_MONTO.equalsIgnoreCase(nombreValor)) {
			val = String.valueOf(nomina.getMonto());
			val = FormateadorUtil.formatearMonto(val,
					propiedadesExterna.getProperty("servicios.nominas.codigo.moneda"));
		}
		if (REGISTROS_CLAVE_MAP_NRO_OPERACION.equalsIgnoreCase(nombreValor))
			val = String.valueOf(nomina.getNumOperProg());
		if ("TotalPagos".equalsIgnoreCase(nombreValor))
			val = String.valueOf(nomina.getTotalPagos());

		return val;
	}

	/**
	 * Crea una lista de maps que contienen los datos necesarios de las nominas para
	 * crear el documento con el detalle de autorizaci&oacute;n.
	 * 
	 * @param listNominas
	 * @return
	 * @throws BancaEmpresasException
	 */
	private List<Map<Object, Object>> crearRegistrosNominas(List<NominaEnLinea> listNominas) {
		Map<Object, Object> reg = null;
		List registros = new ArrayList<>();
		String[] nombresValoresColumnas = propiedadesExterna
				.getProperty("servicios.pdf.autorizacion.tabla.nominas.nombres.valores.columnas").split(",");

		for (NominaEnLinea nomina : listNominas) {
			reg = new HashMap<>();
			for (String nombreValor : nombresValoresColumnas) {
				reg.put(nombreValor, obtenerValorCampoNomina(nomina, nombreValor));
			}
			registros.add(reg);
		}

		return registros;
	}

	/**
	 * Genera el documento con el detalle de autorización para operaciones de tipo
	 * Nóminas Diferidas.
	 * 
	 * @param request
	 * @param listOperaciones
	 * @param mapOperaciones
	 * @return
	 * @throws BancaEmpresasException
	 */
	public String generaDocPdfOperacionesNominasDiferidas(BaseRequestEmpresa request, List<String> listOperaciones,
			MapOperaciones mapOperaciones, String codigoServicio) throws BancaEmpresasException {
		List<Map<Object, Object>> registros = crearRegistrosNomDiferidas(listOperaciones, mapOperaciones);

		String montoTotal = obtenerMontoTotalOperaciones(listOperaciones, mapOperaciones);
		String[] nombresValoresColumnas = propiedadesExterna
				.getProperty("servicios.pdf.autorizacion.tabla.nominas.diferidas.nombres.valores.columnas").split(",");

		String subtitulo = propiedadesExterna.getProperty("servicios.pdf.autorizacion.nominas.diferidas.subtitulo");
		return generaDocPdf(request, listOperaciones.size(), registros, montoTotal, nombresValoresColumnas, subtitulo,
				".nominas.diferidas", codigoServicio);
	}

	/**
	 * Crea una lista, donde cada elemento es un registro que se identifica por
	 * número de operación y estos registros contienen los datos de dichas
	 * operaciones.
	 * 
	 * @param request
	 * @param codigoServicio
	 * @param listOperaciones
	 * @return
	 * @throws BancaEmpresasException
	 */
	private List<Map<Object, Object>> crearRegistrosNomDiferidas(List<String> listOperaciones,
			MapOperaciones mapOperaciones) throws BancaEmpresasException {
		Map<Object, Object> reg = null;
		List registros = new ArrayList<>();
		String[] nombresValoresColumnas = propiedadesExterna
				.getProperty("servicios.pdf.autorizacion.tabla.nominas.diferidas.nombres.valores.columnas").split(",");

		for (String nroOperacion : listOperaciones) {
			reg = new HashMap<>();
			for (String nombreValor : nombresValoresColumnas) {
				agregarRegistroValorCampoOperacionNomDiferidas(reg, nroOperacion, nombreValor, mapOperaciones);
			}
			registros.add(reg);
		}

		return registros;
	}

	/**
	 * Agrega a el map de registros el valor de un campo de una operación
	 * determinada.
	 * 
	 * @param reg
	 * @param nroOperacion
	 * @param nombreValor
	 * @throws BancaEmpresasException
	 */
	private void agregarRegistroValorCampoOperacionNomDiferidas(Map<Object, Object> reg, String nroOperacion,
			String nombreValor, MapOperaciones mapOperaciones) throws BancaEmpresasException {

		if (REGISTROS_CLAVE_MAP_NRO_OPERACION.equals(nombreValor)) {
			reg.put(nombreValor, nroOperacion);
		} else {
			String val = operacionesEmpresaService.valoresCampoOperacionesService.getValorCampoOperacion(mapOperaciones,
					nroOperacion, nombreValor, true);
			if ("NomDifNroCuentaCargo".equals(nombreValor))
				val = FormateadorUtil.formatoProducto(val, Integer
						.parseInt(propiedadesExterna.getProperty("servicios.nominas.diferidas.codigo.producto")));
			if ("NomDifMonto".equals(nombreValor))
				val = FormateadorUtil.formatearMonto(val,
						propiedadesExterna.getProperty("servicios.nominas.diferidas.codigo.moneda"));
			reg.put(nombreValor, val);

		}
	}

	/**
	 * Agrega a el map de registros el valor de un campo de una operación
	 * determinada.
	 * 
	 * @param reg
	 * @param nroOperacion
	 * @param nombreValor
	 * @throws BancaEmpresasException
	 */
	private void agregarRegistroValorCampoOperacionMultiMoneda(Map<Object, Object> reg, String nroOperacion,
			String nombreValor, MapOperaciones mapOperaciones, String codigoServicio) throws BancaEmpresasException {
		LOGGER.info(
				"DetalleAutorizacionService agregarRegistroValorCampoOperacionMultiMoneda: nroOperacion [{}] nombreValor [{}]",
				nroOperacion, nombreValor);
		if (REGISTROS_CLAVE_MAP_NRO_OPERACION.equals(nombreValor)) {
			reg.put(nombreValor, nroOperacion);
		} else {
			String val = operacionesEmpresaService.valoresCampoOperacionesService.getValorCampoOperacion(mapOperaciones,
					nroOperacion, nombreValor, true);
			if ("SpotWebNroCuentaCargo".equals(nombreValor) ||
					"TefMxNroCuentaCargo".equals(nombreValor)) {
				String codigoProductoStr = operacionesEmpresaService.obtenerValorCampoOperacion(nroOperacion, "30");
				int codigoProducto;
				try {
					codigoProducto = Integer.parseInt(codigoProductoStr);
				} catch (NumberFormatException e) {
					LOGGER.info(
							"DetalleAutorizacionService agregarRegistroValorCampoOperacionMultiMoneda: ERROR al obtener codigo de producto: ",
							e);
					codigoProducto = 0;
				}
				val = FormateadorUtil.formatoProducto(val, codigoProducto);
			}
			if ("SpotWebMonto".equals(nombreValor) ||
					"TefMxMonto".equals(nombreValor)) {
				String codigoMoneda = operacionesEmpresaService.obtenerValorCampoOperacion(nroOperacion,
						(codigoServicio.equals(Constantes.CODIGO_SERVICIO_SPOTWEB) ? "22" : "6"));
				if (null != codigoMoneda) {
					val = FormateadorUtil.formatearMontoOperacionMultiMoneda(val, codigoMoneda);
				} else {
					LOGGER.info(
							"DetalleAutorizacionService agregarRegistroValorCampoOperacionMultiMoneda: ERROR al obtener codigo de moneda");
				}
			}

			if ("SpotWebBancoBeneficiario".equals(nombreValor) ||
					"TefMxBancoBeneficiario".equals(nombreValor)) {
				Banco banco = bancosService.obtenerBanco(val);
				if (null != banco) {
					val = banco.getNomBanco();
					val = (null == val ? "" : val);
				} else {
					val = "";
				}
			}

			reg.put(nombreValor, val);

		}
	}

	/**
	 * Crea una lista, donde cada elemento es un registro que se identifica por
	 * número de operación y estos registros contienen los datos de dichas
	 * operaciones.
	 * 
	 * @param request
	 * @param codigoServicio
	 * @param listOperaciones
	 * @return
	 * @throws BancaEmpresasException
	 */
	private List<Map<Object, Object>> crearRegistrosMultiMoneda(List<String> listOperaciones,
			MapOperaciones mapOperaciones, String codigoServicio) throws BancaEmpresasException {
		Map<Object, Object> reg = null;
		List registros = new ArrayList<>();
		String[] nombresValoresColumnas;

		if (Constantes.CODIGO_SERVICIO_SPOTWEB.equals(codigoServicio))
			nombresValoresColumnas = propiedadesExterna
					.getProperty("servicios.pdf.autorizacion.tabla.spot.web.nombres.valores.columnas").split(",");
		else {
			nombresValoresColumnas = propiedadesExterna
					.getProperty("servicios.pdf.autorizacion.tabla.tef.mx.nombres.valores.columnas").split(",");
		}

		for (String nroOperacion : listOperaciones) {
			reg = new HashMap<>();
			for (String nombreValor : nombresValoresColumnas) {
				agregarRegistroValorCampoOperacionMultiMoneda(reg, nroOperacion, nombreValor, mapOperaciones,
						codigoServicio);
			}
			registros.add(reg);
		}

		return registros;
	}

	/**
	 * Genera el documento con el detalle de autorizaci&oacute;n para operaciones de
	 * tipo SpotWeb.
	 * 
	 * @param request
	 * @param listOperaciones
	 * @param mapOperaciones
	 * @return
	 * @throws BancaEmpresasException
	 */
	public String generaDocPdfOperacionesMultiMoneda(BaseRequestEmpresa request, List<String> listOperaciones,
			MapOperaciones mapOperaciones, String codigoServicio) throws BancaEmpresasException {
		List<Map<Object, Object>> registros = crearRegistrosMultiMoneda(listOperaciones, mapOperaciones,
				codigoServicio);

		String[] nombresValoresColumnas;
		
		String subtitulo = "";
		String paramTipoOperacion = "";
		if (Constantes.CODIGO_SERVICIO_SPOTWEB.equals(codigoServicio)) {
			subtitulo = propiedadesExterna.getProperty("servicios.pdf.autorizacion.spot.web.subtitulo");
			nombresValoresColumnas = propiedadesExterna.getProperty("servicios.pdf.autorizacion.tabla.spot.web.nombres.valores.columnas").split(",");
			paramTipoOperacion = ".spot.web";
		}else {
			subtitulo = propiedadesExterna.getProperty("servicios.pdf.autorizacion.tef.mx.subtitulo");
			nombresValoresColumnas = propiedadesExterna.getProperty("servicios.pdf.autorizacion.tabla.tef.mx.nombres.valores.columnas").split(",");
			paramTipoOperacion = ".tef.mx";
		}
			
		
		return generaDocPdf(request, listOperaciones.size(), registros, null, nombresValoresColumnas, subtitulo,
				paramTipoOperacion, codigoServicio);
	}
	
	/**
	 * Genera el documento con el detalle de autorizaci&oacute;n para operaciones de
	 * tipo BiceComex.
	 * 
	 * @param request
	 * @param listOperaciones
	 * @param mapOperaciones
	 * @return
	 * @throws BancaEmpresasException
	 */
	public String generaDocPdfOperacionesBiceComex(BaseRequestEmpresa request, List<String> listOperaciones,
			MapOperaciones mapOperaciones, String codigoServicio) throws BancaEmpresasException {
		List<Map<Object, Object>> registros = crearRegistrosBiceComex(listOperaciones, mapOperaciones,
				codigoServicio);

		String[] nombresValoresColumnas = propiedadesExterna
				.getProperty("servicios.pdf.autorizacion.tabla.bice.comex.nombres.valores.columnas.sin.moneda").split(",");

		String subtitulo = propiedadesExterna.getProperty("servicios.pdf.autorizacion.bice.comex.subtitulo");
		return generaDocPdf(request, listOperaciones.size(), registros, null, nombresValoresColumnas, subtitulo,
				".bice.comex", codigoServicio);
	}
	
	/**
	 * Crea una lista, donde cada elemento es un registro que se identifica por
	 * número de operación y estos registros contienen los datos de dichas
	 * operaciones.
	 * 
	 * @param request
	 * @param codigoServicio
	 * @param listOperaciones
	 * @return
	 * @throws BancaEmpresasException
	 */
	private List<Map<Object, Object>> crearRegistrosBiceComex(List<String> listOperaciones,
			MapOperaciones mapOperaciones, String codigoServicio) throws BancaEmpresasException {
		Map<Object, Object> reg = null;
		List registros = new ArrayList<>();
		String[] nombresValoresColumnas = propiedadesExterna
				.getProperty("servicios.pdf.autorizacion.tabla.bice.comex.nombres.valores.columnas").split(",");

		for (String nroOperacion : listOperaciones) {
			reg = new HashMap<>();
			for (String nombreValor : nombresValoresColumnas) {
				agregarRegistroValorCampoOperacionBiceComex(reg, nroOperacion, nombreValor, mapOperaciones,
						codigoServicio);
			}
			registros.add(reg);
		}

		return registros;
	}
	
	/**
	 * Agrega a el map de registros el valor de un campo de una operación
	 * determinada.
	 * 
	 * @param reg
	 * @param nroOperacion
	 * @param nombreValor
	 * @throws BancaEmpresasException
	 */
	private void agregarRegistroValorCampoOperacionBiceComex(Map<Object, Object> reg, String nroOperacion,
			String nombreValor, MapOperaciones mapOperaciones, String codigoServicio) throws BancaEmpresasException {
		LOGGER.info(
				"DetalleAutorizacionService agregarRegistroValorCampoOperacionBiceComex: nroOperacion [{}] nombreValor [{}]",
				nroOperacion, nombreValor);
		if (REGISTROS_CLAVE_MAP_NRO_OPERACION.equals(nombreValor)) {
			reg.put(nombreValor, nroOperacion);
		} else {
			String val = operacionesEmpresaService.valoresCampoOperacionesService.getValorCampoOperacion(mapOperaciones,
					nroOperacion, nombreValor, true);
			if ("BiceComexMonto".equals(nombreValor)) {
				String glosaMoneda = operacionesEmpresaService.valoresCampoOperacionesService.getValorCampoOperacion(mapOperaciones,
						nroOperacion, "BiceComexMoneda", true);
				String codigoMoneda = biceComexService.obtenerCodigoMoneda(glosaMoneda);
				if (null != codigoMoneda) {
					val = FormateadorUtil.formatearMontoOperacionBiceComex(val, codigoMoneda);
				} else {
					LOGGER.info(
							"DetalleAutorizacionService agregarRegistroValorCampoOperacionBiceComex: ERROR al obtener codigo de moneda");
				}
			}

			if(!"BiceComexMoneda".equals(nombreValor))
				reg.put(nombreValor, val);

		}
	}
}
