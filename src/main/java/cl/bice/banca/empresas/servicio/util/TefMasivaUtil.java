package cl.bice.banca.empresas.servicio.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import cl.bice.banca.empresas.servicio.model.common.Constantes;
import cl.bice.banca.empresas.servicio.model.operaciones.MapOperaciones;


/**
 * Clase utilitaria para validaciones en el proceso de Tef Masiva
 *
 * @author Marco Bello
 * @version 1.0
 */
public final class TefMasivaUtil {

	/**
	 * Constructor Privado.
	 */
	private TefMasivaUtil() {
		super();
	}


	public static String getMensajeErrorAprobar(boolean resultOperProg, boolean resultFirma)  {
		String msgError = "";
		if (!resultOperProg) {
			msgError = Constantes.GLS_ERROR_UPD_OPER_PROG;
		}
		else {
			if (!resultFirma) {
				msgError = Constantes.GLS_ERROR_INS_FIRMA_APO;
			}
		}
		
		return msgError;
	}
	
	public static String getHoraFormatoFront(String horario) {
		try {
			
			horario = (horario != null && horario.length() == 4) ? (horario.substring(0,2) + ":" + horario.substring(2,4)) : ""; 
		}
		catch (Exception e) {
			horario = "";
		}
		
		return horario;
	}
	
	public static String aplicaCargoOnlineTodos(List<Map<String, Object>> listaParametros) {
		String resultado = "";
		
		try {
			if (listaParametros != null && !listaParametros.isEmpty()) {
				resultado = obtenerValorParametro(listaParametros, Constantes.NOM_TIPO_CARGO_ONLINE_TODOS);
			}
			else {
				resultado = "ERROR";
			}
		}
		catch (Exception e) {
			resultado = "ERROR";
		}
		
		return resultado;
	}

	
	public static String obtenerValorParametro(List<Map<String, Object>> listaParametros, String nomTipo) {
		String valor = "";
		
		if (listaParametros != null) {
			for (Map<String, Object> parametro : listaParametros) {
				if (nomTipo.equals(parametro.get("NOM_TIPO"))) {
					valor = (String)parametro.get("VAL_PARAMETRO");
					break;
				}
			}
		}
		
		return valor;
	}
	
	public static boolean cumplePertenencia(String numOperProg, List<String> listaOperPertenencia) {
		Optional<String> optOperProg = listaOperPertenencia.stream()
			    .filter(s -> s.equals(numOperProg))
			    .findFirst();
		
		return (optOperProg.isPresent() ? Boolean.TRUE.booleanValue() : Boolean.FALSE.booleanValue());
	}
	
	
	public static boolean isOperacionCCLV(String codServicio, String rutAbono, String numCuentaAbono, String nombreBancoAbono, String rutCCLV) {
		return (Constantes.SERVICIO_OPERACION_EMPRESA_COD_LBTR.equals(codServicio) &&
				rutAbono.equals(rutCCLV) &&
				"0".equals(validaNumero(numCuentaAbono)) &&
				Constantes.GLS_BANCO_CENTRAL.equalsIgnoreCase(eliminaEspacios(nombreBancoAbono)));
	}
	
	
	public static boolean isTefLbtrSis(	String rutCliente, String referencia, List<String> listaCiaSeguro, List<String> listaTipoPago, List<String> listaNumeroContrato) {
		boolean existeCiaSeguro;
		boolean existeTipoPago;
		boolean existeNumContrato;

		referencia = referencia == null ? "" : referencia;
		final String referenciaTipoPago = referencia.length() >= 3 ? referencia.substring(0,3) : referencia;
		final String referenciaNumContrato = referencia.length() >= 19 ? referencia.substring(15,19) : referencia;

		existeCiaSeguro   = listaCiaSeguro.stream().anyMatch(c -> c.equals(formaRutBice(rutCliente)));
		existeTipoPago    = listaTipoPago.stream().anyMatch(d -> d.equals(referenciaTipoPago));
		existeNumContrato = listaNumeroContrato.stream().anyMatch(e -> e.equals(referenciaNumContrato));
		
		return (existeCiaSeguro && existeTipoPago && existeNumContrato);

	}	
	
	
	public static List<String> getListaValoresParametroSis(List<Map<String, Object>> listaParametrosSis, String nomTipo) {
		List<String> datos = new ArrayList<>();
		String valParametro = "";
		
		if (listaParametrosSis != null) {
			for (Map<String, Object> parametro : listaParametrosSis) {
				valParametro = agregaParametro(nomTipo, parametro.get("NOM_TIPO").toString(), (String)parametro.get("VAL_PARAMETRO"));
				if (!"".equals(valParametro)) {
					datos.add(valParametro);
				}
			}
		}
		
		return datos;
	}
	
	private static String agregaParametro(String nomTipo, String nomTipoBD, String valorBD) {
		String salida = "";
		
		switch (nomTipo) {
		case Constantes.VALOR_NOM_TIPO_RUT_CIA:
			if (nomTipo.equals(getValor(nomTipoBD,6))) {
				salida = valorBD;
			}
			break;
		case Constantes.VALOR_NOM_TIPO_TIPO_PAGO:
			if (nomTipo.equals(getValor(nomTipoBD,8))) {
				salida = valorBD;
			}
			break;
		case Constantes.VALOR_NOM_TIPO_NUM_CONTRATO:
			if (nomTipo.equals(nomTipoBD)) {
				salida = valorBD;
			}
			break;

		default:
			break;
		}
		
		return salida;

	}

	private static String getValor(String dato, int largo) {
		return (dato.length() >= largo ? dato.substring(0, largo) : dato);
	}
	
    private static String formaRutBice(String rut) {
    	return StringUtils.leftPad(rut, 10, "0").toUpperCase();
    }
    
    public static String getGlosaTipoLbtr(List<String> datos, List<String> listaCiaSeguro, List<String> listaTipoPago, List<String> listaNumeroContrato) {
    	String tipoLbtr = "";
    	
    	String rutCliente = datos.get(0);
    	String codServicio = datos.get(1);
    	String referencia = datos.get(2);
    	String metodoPago = datos.get(3);
    	String rutAbono = datos.get(4);
    	String numCuentaAbono = datos.get(5);
    	String nombreBancoAbono = datos.get(6);
    	String rutCCLV = datos.get(7);
    	
		if (Constantes.SERVICIO_OPERACION_EMPRESA_COD_LBTR_SOMA.equals(codServicio)) {
			tipoLbtr = Constantes.SERVICIO_OPERACION_EMPRESA_TIPO_SOMA;
		}
		else {
			if (TefMasivaUtil.isTefLbtrSis(rutCliente, referencia, listaCiaSeguro, listaTipoPago, listaNumeroContrato)) {
				tipoLbtr = Constantes.SERVICIO_OPERACION_EMPRESA_TIPO_SIS;
			}
			else {
				if (Constantes.SERVICIO_OPERACION_EMPRESA_TIPO_SIMPLE.equals(metodoPago)) {
					tipoLbtr = getGlosaTipoSimple(codServicio, rutAbono, numCuentaAbono, nombreBancoAbono, rutCCLV);
				}
				else {
					if (Constantes.SERVICIO_OPERACION_EMPRESA_TIPO_DVP.equals(metodoPago)) {
						tipoLbtr = Constantes.SERVICIO_OPERACION_EMPRESA_TIPO_DVP;
					}
				}
			}
		}
		
		return tipoLbtr;

    }
    
    private static String getGlosaTipoSimple(String codServicio, String rutAbono, String numCuentaAbono, String nombreBancoAbono, String rutCCLV) {
    	String tipoLbtr = "";
    	if (TefMasivaUtil.isOperacionCCLV(codServicio, rutAbono, numCuentaAbono, nombreBancoAbono, rutCCLV)) {
			tipoLbtr = Constantes.SERVICIO_OPERACION_EMPRESA_TIPO_CCLV;
		}
		else {
			tipoLbtr = Constantes.SERVICIO_OPERACION_EMPRESA_TIPO_SIMPLE;
		}
    	return tipoLbtr;
    } 
    
    public static boolean isListasOk(List<Map<String, Object>> listaParametros, List<Map<String, Object>> listaParamSis) {
    	return (listaParametros != null && !listaParametros.isEmpty() && listaParamSis != null && !listaParamSis.isEmpty()); 
    }
    
    
	public static String getCodServicioOperProgDesdeBD(MapOperaciones mapOperaciones, String numOperProgParam) {
		String numOperProgBD = "";
		String codServicioBD = "";
		String codServicio = "";
		
		if (mapOperaciones != null) {
			for (Map<String, Object> mapa : mapOperaciones.getMapOutputSP()) {
				numOperProgBD = mapa.get("NUM_OPER_PROG").toString();
				codServicioBD = mapa.get("COD_SERVICIO").toString();
				if (numOperProgParam.equals(numOperProgBD)) {
					codServicio = codServicioBD;
					break;
				}
			}
		}
		
		return codServicio;
	}
	
	public static String eliminaEspacios(String dato) {
		return (dato !=null ? dato.trim() : dato);
	}
	
	private static String validaNumero(String numero) {
		return (esNumero(numero) ? String.valueOf(Long.parseLong(numero)) : "");
	}
	
	public static boolean esNumero(String valor) {
		return valor.matches("[0-9]*");
	}
	
	public static boolean isOperacionSOMA(MapOperaciones mapOperaciones, String numOperProgParam) {
		return (Constantes.SERVICIO_OPERACION_EMPRESA_COD_LBTR_SOMA.equals(getCodServicioOperProgDesdeBD(mapOperaciones, numOperProgParam)) ? 
				Boolean.TRUE.booleanValue() : Boolean.FALSE.booleanValue() );
	}
	
}
