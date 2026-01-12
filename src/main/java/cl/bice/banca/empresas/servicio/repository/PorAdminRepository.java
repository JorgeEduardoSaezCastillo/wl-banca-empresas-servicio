package cl.bice.banca.empresas.servicio.repository;

import java.util.Date;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import cl.bice.banca.empresas.servicio.mapper.poradmin.BancaDigitalMapper;

/**
 * Created by lbasso on 23-05-17.
 */
@Repository("PorAdminRepository")
public class PorAdminRepository implements BancaDigitalMapper {

	/**
	 * Template SQL.
	 */
	@Autowired
	@Qualifier("sqlSessionTemplatePorAdmin")
	private SqlSessionTemplate sqlSessionTemplate;

	/**
	 * Constructor por defecto.
	 */
	public PorAdminRepository() {
		super();
	}

	/**
	 * Mapper del package Banca digital.
	 */
	private BancaDigitalMapper getBancaDigitalMapper() {
		return this.sqlSessionTemplate.getMapper(BancaDigitalMapper.class);
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
	public void obtenerFechaPKG(Map<String, Date> params) {
		this.getBancaDigitalMapper().obtenerFechaPKG(params);
	}
	
	@Override
	public void obtenerFechaContable(Map<String, Object> params) {
		this.getBancaDigitalMapper().obtenerFechaContable(params);
	}

	@Override
	public void obtenerBancos(Map<String, Object> params) {
		this.getBancaDigitalMapper().obtenerBancos(params);		
	}
}
