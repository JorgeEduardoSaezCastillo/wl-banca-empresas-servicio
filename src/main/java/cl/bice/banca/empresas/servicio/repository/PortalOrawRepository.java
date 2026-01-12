package cl.bice.banca.empresas.servicio.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import cl.bice.banca.empresas.servicio.mapper.portaloraw.EmpresasMapper;

/**
 * Created by Cristian Pais on 27-02-19.
 */
@Repository("PortalOrawRepository")
public class PortalOrawRepository implements EmpresasMapper {

	/**
	 * Template SQL.
	 */
	@Autowired
	@Qualifier("sqlSessionTemplatePortalOraw")
	private SqlSessionTemplate sqlSessionTemplate;

	/**
	 * Constructor por defecto.
	 */
	public PortalOrawRepository() {
		super();
	}

	/**
	 * Mapper del package Lista TEF Reporte.
	 */
	private EmpresasMapper getEmpresasMapper() {
		return this.sqlSessionTemplate.getMapper(EmpresasMapper.class);
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
	 * Permite settear el valor del atributo sqlSessionTemplate.
	 *
	 * @param sqlSessionTemplate nuevo valor para el atributo sqlSessionTemplate.
	 */
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	@Override
	public void obtenerOperacionesPendientesDeFirma(Map<String, Object> params) throws SQLException {
		this.getEmpresasMapper().obtenerOperacionesPendientesDeFirma(params);
	}

	@Override
	public void obtenerListaEmpresas(Map<String, Object> params) throws SQLException {
		this.getEmpresasMapper().obtenerListaEmpresas(params);
	}

	@Override
	public void obtenerResumenOperaciones(Map<String, Object> params) throws SQLException {
		this.getEmpresasMapper().obtenerResumenOperaciones(params);
	}

	@Override
	public void obtenerCuentasPorPerfil(Map<String, Object> params) throws SQLException {
		this.getEmpresasMapper().obtenerCuentasPorPerfil(params);
	}

	@Override
	public void obtenerOperacionesAprobarLiberar(Map<String, Object> params) throws SQLException {
		this.getEmpresasMapper().obtenerOperacionesAprobarLiberar(params);
	}

	@Override
	public void obtieneListaOperacionesDia(Map<String, Object> params) throws SQLException {
		this.getEmpresasMapper().obtieneListaOperacionesDia(params);
	}

	@Override
	public void existeFacultad(Map<String, Object> params) throws SQLException {
		this.getEmpresasMapper().existeFacultad(params);
	}

	@Override
	public void registraLogFacultad(Map<String, Object> params) throws SQLException {
		this.getEmpresasMapper().registraLogFacultad(params);
	}

	@Override
	public void validarPertenenciaNumOperacionRutEmpresa(Map<String, Object> params) throws SQLException {
		this.getEmpresasMapper().validarPertenenciaNumOperacionRutEmpresa(params);
	}

	@Override
	public void enviarMailCodGlsError(Map<String, Object> params) throws SQLException {
		this.getEmpresasMapper().enviarMailCodGlsError(params);
	}

	@Override
	public void consultarDetalleOperacion(Map<String, Object> params) throws SQLException {
		this.getEmpresasMapper().consultarDetalleOperacion(params);
	}

	@Override
	public void obtenerNombreDelegado(Map<String, Object> params) throws SQLException {
		this.getEmpresasMapper().obtenerNombreDelegado(params);
	}

	@Override
	public void obtenerNombreCliente(Map<String, Object> params) throws SQLException {
		this.getEmpresasMapper().obtenerNombreCliente(params);
	}

	@Override
	public void registraContratoFDA(Map<String, Object> params) throws SQLException {
		this.getEmpresasMapper().registraContratoFDA(params);
	}

	@Override
	public void insertarFDASeguimiento(Map<String, Object> params) throws SQLException {
		this.getEmpresasMapper().insertarFDASeguimiento(params);
	}
	
	@Override
	public void obtenerOperacionesAprobarLiberarPortal(Map<String, Object> params) throws SQLException {
		this.getEmpresasMapper().obtenerOperacionesAprobarLiberarPortal(params);
	}

	@Override
	public void obtenerFirmasNominaEnLinea(Map<String, Object> params) throws SQLException {
		this.getEmpresasMapper().obtenerFirmasNominaEnLinea(params);
	}
	
	public void obtenerNominaEnLinea(Map<String, Object> params) throws SQLException {
		this.getEmpresasMapper().obtenerNominaEnLinea(params);
	}

	@Override
	public void obtenerNombreTipoNomina(Map<String, Object> params) throws SQLException {
		this.getEmpresasMapper().obtenerNombreTipoNomina(params);
	}
	
	@Override
	public void empresaTieneCargoEnLineaLbtr(Map<String, Object> params) throws SQLException {
		this.getEmpresasMapper().empresaTieneCargoEnLineaLbtr(params);
	}
	
	@Override
	public void empresaTieneCuentaParaguaLbtr(Map<String, Object> params) throws SQLException {
		this.getEmpresasMapper().empresaTieneCuentaParaguaLbtr(params);
	}
	
	@Override
	public void obtenerCodigosErrorGfs(Map<String, Object> params) throws SQLException {
		this.getEmpresasMapper().obtenerCodigosErrorGfs(params);
	}
	
	@Override
	public void obtenerCuentasColaborativas(Map<String, Object> params) throws SQLException {
		this.getEmpresasMapper().obtenerCuentasColaborativas(params);
	}
	
	@Override
	public void obtenerDatosCclv(Map<String, Object> params) throws SQLException {
		this.getEmpresasMapper().obtenerDatosCclv(params);
	}
	
	@Override
	public void obtenerFirmasNominaDiferida(Map<String, Object> params) throws SQLException {
		this.getEmpresasMapper().obtenerFirmasNominaDiferida(params);
	}
	
	@Override
	public void obtenerDatosNominaDiferida(Map<String, Object> params) throws SQLException {
		this.getEmpresasMapper().obtenerDatosNominaDiferida(params);
	}
	
	@Override
	public void obtenerEmailEjecutivo(Map<String, Object> params) throws SQLException {
		this.getEmpresasMapper().obtenerEmailEjecutivo(params);
	}

	@Override
	public void obtenerEstadosNominasEnLinea(Map<String, Object> params) throws SQLException {
		this.getEmpresasMapper().obtenerEstadosNominasEnLinea(params);
	}

	@Override
	public void eliminarEstadoNominaEnLinea(Map<String, Object> params) throws SQLException {
		this.getEmpresasMapper().eliminarEstadoNominaEnLinea(params);
	}

	@Override
	public void validarMaxFechaLiberarNomLin(Map<String, Object> params) throws SQLException {
		this.getEmpresasMapper().validarMaxFechaLiberarNomLin(params);
	}

	@Override
	public void obtenerFechaContableNomLin(Map<String, Object> params) throws SQLException {
		this.getEmpresasMapper().obtenerFechaContableNomLin(params);
	}

	@Override
	public void registrarCargoNominasEnLinea(Map<String, Object> params) throws SQLException {
		this.getEmpresasMapper().registrarCargoNominasEnLinea(params);
	}

	@Override
	public void obtenerParametrosNomLin(Map<String, Object> params) throws SQLException {
		this.getEmpresasMapper().obtenerParametrosNomLin(params);
	}

	@Override
	public void obtenerCorrelativoNomLin(Map<String, Object> params) throws SQLException {
		this.getEmpresasMapper().obtenerCorrelativoNomLin(params);
	}

	@Override
	public void insertarCorrelativoNomLin(Map<String, Object> params) throws SQLException {
		this.getEmpresasMapper().insertarCorrelativoNomLin(params);
	}

	@Override
	public void consultarCorrelativoNomLin(Map<String, Object> params) throws SQLException {
		this.getEmpresasMapper().consultarCorrelativoNomLin(params);
	}

	@Override
	public void actualizarCorrelativoNomLin(Map<String, Object> params) throws SQLException {
		this.getEmpresasMapper().actualizarCorrelativoNomLin(params);
	}
	
	@Override
	public void getLisResumenCC(Map<String, Object> params) throws SQLException {
		this.getEmpresasMapper().getLisResumenCC(params);
	}
	
	@Override
	public void obtenerFechaIodata(Map<String, Object> params) throws SQLException {
		this.getEmpresasMapper().obtenerFechaIodata(params);
	}
	
	@Override
	public void validaDiaHabil(Map<String, Object> params) throws SQLException {
		this.getEmpresasMapper().validaDiaHabil(params);
	}

	@Override
	public void obtenerDetalleOperacion(Map<String, Object> params) throws SQLException {
		this.getEmpresasMapper().obtenerDetalleOperacion(params);
	}
	
	@Override
	public void registraSegBiceComex(Map<String, Object> params) throws SQLException {
		this.getEmpresasMapper().registraSegBiceComex(params);
	}

	@Override
	public void validaBloqueoOperaciones(Map<String, Object> params) throws SQLException {
		this.getEmpresasMapper().validaBloqueoOperaciones(params);
	}

	@Override
	public void consultarTblUsuarioCliente(Map<String, Object> params) throws SQLException {
		this.getEmpresasMapper().consultarTblUsuarioCliente(params);		
	}

	@Override
	public void consultarTblValidaPoderes(Map<String, Object> params) throws SQLException {
		this.getEmpresasMapper().consultarTblValidaPoderes(params);		
	}

	@Override
	public void obtenerOperacionesAprobarLiberarGenericoPortal(Map<String, Object> params) throws SQLException {
		this.getEmpresasMapper().obtenerOperacionesAprobarLiberarGenericoPortal(params);
	}
	
	@Override
	public void registraContratoFDADesactivado(Map<String, Object> params) throws SQLException {
		this.getEmpresasMapper().registraContratoFDADesactivado(params);
	}
	
	@Override
	public void consultaContratosFDA(Map<String, Object> params) throws SQLException {
		this.getEmpresasMapper().consultaContratosFDA(params);
	}

	@Override
	public void lockTransaction(Map<String, Object> params) throws SQLException {
		this.getEmpresasMapper().lockTransaction(params);
	}

	@Override
	public void unLockTransaction(Map<String, Object> params) throws SQLException {
		this.getEmpresasMapper().unLockTransaction(params);
	}
	
	@Override
    public List<Map<String, Object>> obtenerListaOperacionesAprobarLiberarPortal(Map<String, Object> params) throws SQLException {
        return this.getEmpresasMapper().obtenerListaOperacionesAprobarLiberarPortal(params);
	}

	@Override
    public void actualizaDetalleCampPortal(Map<String, Object> params) throws SQLException {
		this.getEmpresasMapper().actualizaDetalleCampPortal(params);
	}
	
    @Override
    public void actualizaEstadoOperProg(Map<String, Object> params) throws SQLException {
        this.getEmpresasMapper().actualizaEstadoOperProg(params);
    }
    
    @Override
    public String getNombreApoderadoClaveAcceso(Map<String, Object> params) throws SQLException {
        return this.getEmpresasMapper().getNombreApoderadoClaveAcceso(params);
    }    
	
    @Override
    public String getNombreCliente(Map<String, Object> params) throws SQLException {
        return this.getEmpresasMapper().getNombreCliente(params);
    }
    
    @Override
    public Map<String, Object> getDatosOperProg(Map<String, Object> params) throws SQLException {
        return this.getEmpresasMapper().getDatosOperProg(params);
    }
    
    @Override
    public List<Map<String, Object>> getParametrosValidacion(Map<String, Object> params) throws SQLException {
    	return this.getEmpresasMapper().getParametrosValidacion(params);
    }

    @Override
    public List<Map<String, Object>> getFirmasOperProg(Map<String, Object> params) throws SQLException {
        return this.getEmpresasMapper().getFirmasOperProg(params);
    }
    
    @Override
    public List<Map<String, Object>> getDatosUsuarioCliente(Map<String, Object> params) throws SQLException {
        return this.getEmpresasMapper().getDatosUsuarioCliente(params);
    }    
    
	@Override
	public void actualizaNumOperacionTrfOperProg(Map<String, Object> params) throws SQLException {
		this.getEmpresasMapper().actualizaNumOperacionTrfOperProg(params);
	}
	
	@Override
	public void eliminaFirmaApoderado(Map<String, Object> params) throws SQLException {
		this.getEmpresasMapper().eliminaFirmaApoderado(params);
	}
	
	@Override
    public void agregaCampoDetalleCampPortal(Map<String, Object> params) throws SQLException {
        this.getEmpresasMapper().agregaCampoDetalleCampPortal(params);
	}
	
	@Override
	public void registraFirmaApoderado(Map<String, Object> params) throws SQLException {
		this.getEmpresasMapper().registraFirmaApoderado(params);
	}
	
	@Override
	public void consultarUsuarioExcepcionado(Map<String, Object> params) throws SQLException {
		this.getEmpresasMapper().consultarUsuarioExcepcionado(params);
		
	}
	
}