package cl.bice.banca.empresas.servicio.repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import cl.bice.banca.empresas.servicio.exception.BancaEmpresasException;
import cl.bice.banca.empresas.servicio.exception.ErrorStoredProcedureException;
import cl.bice.banca.empresas.servicio.exception.NoEncontradoException;
import cl.bice.banca.empresas.servicio.mapper.bicecomex.BiceComexMapper;
import cl.bice.banca.empresas.servicio.model.bicecomex.ConsultaGeneral;
import cl.bice.banca.empresas.servicio.model.common.Constantes;
import cl.bice.banca.empresas.servicio.model.bicecomex.AprobacionBiceComex;
import cl.bice.banca.empresas.servicio.service.EmpresasService;

/**
 * Created by .
 */
@Repository("BiceComexRepository")
public class BiceComexRepository implements BiceComexMapper {
	private static final Logger LOGGER = LoggerFactory.getLogger(BiceComexRepository.class);

	/**
	 * Template SQL Server.
	 */
	@Autowired
	@Qualifier("sqlSessionTemplateBiceComexMapper")
	private SqlSessionTemplate sqlSessionTemplate;
	
	/**
	 * Template SQL ORACLE.
	 */
	@Autowired
	@Qualifier("sqlSessionTemplatePortalOraw")
	private SqlSessionTemplate sqlSessionTemplateOra;
	
	@Autowired
	private EmpresasService empresasService;

	/**
	 * Constructor por defecto.
	 */
	public BiceComexRepository() {
		super();
	}

	/**
	 * Mapper de la BD SQLServer bicepaso
	 */
	private BiceComexMapper getBiceComexMapper() {
		return this.sqlSessionTemplate.getMapper(BiceComexMapper.class);
	}

	/**
	 * Permite obtener el valor del atributo sqlSessionTemplate.
	 *
	 * @return el valor sqlSessionTemplate.
	 */
	public SqlSessionTemplate getSqlSessionTemplate() {
		return this.sqlSessionTemplate;
	}

	/**
	 *
	 * @param sqlSessionTemplate nuevo valor para el atributo sqlSessionTemplate.
	 */
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	@Override
	public void consultarOperaciones(Map<String, Object> params) throws SQLException {
		this.getBiceComexMapper().consultarOperaciones(params);
	}

	@Override
	public List<Map<String, Object>>  contarOperaciones(Map<String, Object> params) throws SQLException {
		return this.getBiceComexMapper().contarOperaciones(params);
	}
	/**
     * Método encargado de consultar operaciones BICE-COMEX SQL Server
     * @param rutUsuario
     * @param rutempresa
     * @param producto
     * @param tipoOp
     * @param estado
     * @param fechaDesde
     * @param fechaHasta
     * @return
     */
    public List<ConsultaGeneral> consultaOperaciones(String rutUsuario,String rutempresa,String producto,String tipoOp,String estado,String fechaDesde,String fechaHasta){
        List<ConsultaGeneral> data = new ArrayList<>();
        java.util.Date Dinicial_;
        java.util.Date Dfinal_;
        
        String query = "{ call Srv_firmas_consulta_general(?,?,?,?,?,?,?) }";
        try (Connection con = this.sqlSessionTemplate.getSqlSessionFactory().openSession().getConnection();
				CallableStatement pSmt = con.prepareCall(query)){
        	
        	String fechaHoy = empresasService.fechaHoy(Constantes.FORMAT_YYYYMMDD);

			SimpleDateFormat formateador = new SimpleDateFormat(Constantes.FORMAT_YYYYMMDD);
			Date hoy = formateador.parse(fechaHoy);

			Date fechaDesdeDate = new Date();
			Date fechaHastaDate = new Date();

			if (fechaDesde == null || fechaDesde.isEmpty())
				fechaDesdeDate = hoy;
			else
				fechaDesdeDate = formateador.parse(fechaDesde);

			if (fechaHasta == null || fechaHasta.isEmpty())
				fechaHastaDate = hoy;
			else
				fechaHastaDate = formateador.parse(fechaHasta);

            Dinicial_ = fechaDesdeDate;
            Dfinal_ = fechaHastaDate;
            pSmt.setString(1, rutUsuario);
            pSmt.setString(2, rutempresa);
            pSmt.setString(3, producto);
            pSmt.setString(4, tipoOp);
            pSmt.setString(5, estado);
            java.sql.Date sqlDateInicial = new java.sql.Date(Dinicial_.getTime()); 
            pSmt.setDate(6,sqlDateInicial);
            java.sql.Date sqlDateFinal = new java.sql.Date(Dfinal_.getTime());
            pSmt.setDate(7,sqlDateFinal);
            try (ResultSet rs = pSmt.executeQuery()){
                
                while (rs.next()){
                    ConsultaGeneral consulta = new ConsultaGeneral();
                    consulta.setProducto((rs.getString(1))); 
                    consulta.setTipoOperacion((rs.getString(2)));
                    consulta.setReferenciaOrdenante((rs.getString(3)));
                    consulta.setMoneda((rs.getString(4)));
                    consulta.setMonto((rs.getString(5)));
                    consulta.setBeneficiarioTema((rs.getString(6)));
                    consulta.setFecha((rs.getString(7)));
                    consulta.setEstado((rs.getString(8)));
                    consulta.setIdentificacionOperacion((rs.getString(9))); 
                    consulta.setFechaModificacion((rs.getString(10)));  
                    consulta.setFirmadaPorApoderado((rs.getString(11)));
                    consulta.setTimeStampOPRecibidas((rs.getString(12))); 
                    consulta.setCorrelativo((rs.getString(13)));              
                    data.add(consulta);
                }
            }catch(SQLException e){
                LOGGER.info("BiceComexRepository.ConsultaOperaciones - SQLException =" + e.getMessage());
                LOGGER.info("SQLException =" + e.getMessage());
            } 
            
        }catch(Exception e){
            LOGGER.info("BiceComexRepository.ConsultaOperaciones :  " +String.valueOf(e));
            return data;
        }
        return data;
    }

    /**
     * Método encargado de Aprobar una operación BICE-COMEX SQL Server
     * @param id
     * @param correlativo
     * @param last_changed
     * @param time_stamp
     * @param producto
     * @param rut_empresa
     * @param apoderado
     */
	public void AprobarOperaciones(int id, int correlativo, String last_changed, String time_stamp, String producto,
			String rut_empresa, String apoderado) throws SQLTimeoutException {

		String query = "{ call Srv_firmas_aprobar_operacion(?,?,?,?,?,?,?) }";
		try (Connection con = this.sqlSessionTemplate.getSqlSessionFactory().openSession().getConnection();
				CallableStatement pSmt = con.prepareCall(query)){
			LOGGER.info("BiceComexRepository.AprobarOperaciones : id[" + id + "] correlativo[" + correlativo + "] last_changed["
					+ last_changed + "] time_Stamp[" + time_stamp + "] producto[" + producto + "] rut_empresa["
					+ rut_empresa + "] apoderado[" + apoderado + "]");

			pSmt.setQueryTimeout(120);
			pSmt.setInt(1, id);
			pSmt.setInt(2, correlativo);
			pSmt.setString(3, last_changed);
			pSmt.setString(4, time_stamp);
			pSmt.setString(5, producto);
			pSmt.setString(6, apoderado);
			pSmt.setString(7, rut_empresa);
			pSmt.execute();

			pSmt.close();
		} catch (SQLTimeoutException e) {
			e.printStackTrace();
			LOGGER.info("BiceComexRepository.AprobarOperaciones - SQLTimeoutException =" + e.getMessage());
			LOGGER.info("SQLTimeoutException =" + e.getMessage());
			throw new SQLTimeoutException();
		} catch (SQLException e) {
			e.printStackTrace();
			LOGGER.info("BiceComexRepository.AprobarOperaciones - SQLException =" + e.getMessage());
			LOGGER.info("SQLException =" + e.getMessage());
		} catch (Exception e) {
			LOGGER.info("BiceComexRepository.AprobarOperaciones :  " + String.valueOf(e));
			return;
		}
		return;
	}
	
	/** Método encargado de consultar estado de una aprobación
     * @param id
     * @param correlativo
     * @param last_changed
     * @param time_stamp
     * @param producto
     * @param rut_empresa
     * @param apoderado
     * @return
     */
    public AprobacionBiceComex AprobarOperaciones_resultado(int id, int correlativo, String last_changed,String time_stamp, String producto, String rut_empresa, String apoderado){
        AprobacionBiceComex apro = new AprobacionBiceComex();
        String resultado = new String();
        
        String query = "{ call Srv_firmas_aprobar_operacion_res(?,?,?,?,?,?,?) }";
        try (Connection con = this.sqlSessionTemplate.getSqlSessionFactory().openSession().getConnection();
				CallableStatement pSmt = con.prepareCall(query)){
        	
        	LOGGER.info("BiceComexRepository.AprobarOperaciones_resultado : id["+id+"] correlativo["+correlativo+"] last_changed["+last_changed+"] time_Stamp["+time_stamp+"] producto["+producto+"] rut_empresa["+rut_empresa+"] apoderado["+apoderado+"]");
        	
            pSmt.setInt(1,id);
            pSmt.setInt(2,correlativo);
            pSmt.setString(3, last_changed);
            pSmt.setString(4, time_stamp);
            pSmt.setString(5, producto);    
            pSmt.setString(6, apoderado);
            pSmt.setString(7, rut_empresa);
            
            try (ResultSet rs = pSmt.executeQuery()){
            	while (rs.next()){
                    resultado = rs.getString(1);
                    if(resultado.length()>2){
                        apro.setReturn_code(Integer.parseInt(resultado.substring(0,2).trim()));
                        apro.setReturn_msg(resultado.substring(3,(resultado.length()-1)));
                    }
                }
            }catch(SQLException e){
                LOGGER.info("BiceComexRepository.AprobarOperaciones_resultado - SQLException =" + e.getMessage());
                LOGGER.info("SQLException =" + e.getMessage());
            }
        }catch(Exception e){
        	LOGGER.info("BiceComexRepository.AprobarOperaciones_resultado :  " +String.valueOf(e));
            return apro;
        }
        return apro;
    }
    
    /** 
	 * Metodo encargado de obtener los estados por aprobar para operaciones BiceComex desde SQLServer
	 * @param listaOperaciones
	 * @param esConsulta
	 */
    public List<String> obtenerEstadosPorAprobar(){
    	List<String> data = new ArrayList<>();
        
        String query = "{ call Srv_firmas_estados_apr() }";
        
        try(Connection con = this.sqlSessionTemplate.getSqlSessionFactory().openSession().getConnection();
				CallableStatement pSmt = con.prepareCall(query)){
            
            try (ResultSet rs = pSmt.executeQuery()){
                
                while (rs.next()){
                    String estado = "";
                    estado = rs.getObject(1).toString().trim();
                    data.add(estado);
                }
            }catch(SQLException e){
                LOGGER.info("BiceComexRepository.obtenerEstadosPorAprobar - SQLException =" + e.getMessage());
                LOGGER.info("SQLException =" + e.getMessage());
            } 
           
        }catch(Exception e){
            LOGGER.info("obtenerEstadosPorAprobar.obtenerEstadosPorAprobar :  " +String.valueOf(e));
            return data;
        }
        return data;
    }
    
	/** 
	 * Metodo encargado de registrar intenciones y actualizar resultado de aprobaciones BiceComex en BD Oracle
	 * @param datosOper
	 * @param rutCliente
	 * @param rutApoderado
	 * @param infoOp
	 * @param idSesion
	 * @param estado
	 */
	public boolean registraSegBiceComex(ConsultaGeneral datosOper, String rutCliente,String rutApoderado, String infoOp, String idSesion, String estado) throws BancaEmpresasException{
		
		LOGGER.info("BiceComexService.registraSegBiceComex INI");
		
		SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat dt2= new SimpleDateFormat("dd-MMM-yyyy");
		
		boolean retorno = false; 
		
	    String log = "identificador:["+datosOper.getIdentificacionOperacion()+"] rutCliente:["+rutCliente+"] rutApoderado:["+rutApoderado+"] producto:["+datosOper.getProducto()+"] tipoOperacion:["+datosOper.getTipoOperacion()+"] "
				+ "refOrdenante:["+datosOper.getReferenciaOrdenante()+"] moneda:["+datosOper.getMoneda()+"] monto:["+datosOper.getMonto()+"] beneficiario:["+datosOper.getBeneficiarioTema()+"] fechaIngreso:["+datosOper.getFecha()+"] "
						+ "estadoFirma:["+(estado != null && !estado.isEmpty() ? estado : datosOper.getEstado())+"] infoOp:["+infoOp+"]";
	    
	    LOGGER.info("BiceComexService.registraSegBiceComex parametros : " + log);

			String query = "{? = call por_admin.por_fun_registra_op_comex(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			
			try (Connection con = this.sqlSessionTemplateOra.getSqlSessionFactory().openSession().getConnection();
					CallableStatement pSmt = con.prepareCall(query)){
				
				pSmt.registerOutParameter(1, java.sql.Types.NUMERIC);
				pSmt.setLong(2, Long.parseLong(datosOper.getIdentificacionOperacion()));
				pSmt.setString(3, rutCliente);
				pSmt.setString(4, rutApoderado);
				pSmt.setString(5, datosOper.getProducto());
				pSmt.setString(6, datosOper.getTipoOperacion());
				pSmt.setString(7, (datosOper.getReferenciaOrdenante() != null) ? datosOper.getReferenciaOrdenante().trim() : datosOper.getReferenciaOrdenante());
				pSmt.setString(8, datosOper.getMoneda());
				pSmt.setString(9,  datosOper.getMonto());
				pSmt.setString(10, (datosOper.getBeneficiarioTema() != null) ? datosOper.getBeneficiarioTema().trim() : datosOper.getBeneficiarioTema());
				pSmt.setString(11, dt2.format(dt.parse(datosOper.getFecha())));
				LOGGER.info("Fecha: {}", dt2.format(dt.parse(datosOper.getFecha())));
				pSmt.setString(12, estado != null && !estado.isEmpty() ? estado : (datosOper.getEstado() != null) ? datosOper.getEstado().trim() : datosOper.getEstado());		
				pSmt.setString(13, infoOp);
				pSmt.setString(14, idSesion);
				
				pSmt.execute();
				//Mayor 0 : exito , < error.
				int status = pSmt.getInt(1);
				LOGGER.info("Status: {}", String.valueOf(status));
				
				//Mayor 0 : exito , < error.
				if(status > 0){
					retorno = true;
					LOGGER.info("BiceComexService.registraSegBiceComex Registro exitoso operacion: {}", datosOper.getIdentificacionOperacion());
				}else{
					LOGGER.info("BiceComexService.registraSegBiceComex NO Registro operacion: {}", datosOper.getIdentificacionOperacion());
				}
				
				pSmt.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
				LOGGER.info("BiceComexService.registraSegBiceComex - SQLException =" + e.getMessage());
				LOGGER.info("SQLException =" + e.getMessage());
			} catch (Exception e) {
				LOGGER.info("BiceComexService.registraSegBiceComex  :  " + String.valueOf(e));
			}

			return retorno;
	}
	
}
