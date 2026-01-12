package cl.bice.banca.empresas.servicio.repository;

import java.sql.SQLException;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import cl.bice.banca.empresas.servicio.mapper.mdp.MdpMapper;

/**
 * Created by .
 */
@Repository("MdpRepository")
public class MdpRepository implements MdpMapper {

	/**
	 * Template SQL.
	 */
	@Autowired
	@Qualifier("sqlSessionTemplateMdpMapper")
	private SqlSessionTemplate sqlSessionTemplate;

	/**
	 * Constructor por defecto.
	 */
	public MdpRepository() {
		super();
	}

	/**
	 * Mapper de la BD SQLServer db_legal
	 */
	private MdpMapper getMdpMapper() {
		return this.sqlSessionTemplate.getMapper(MdpMapper.class);
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
	public void validaPoder(Map<String, Object> params) throws SQLException {
		this.getMdpMapper().validaPoder(params);
	}

	@Override
	public void obtenerAbonosCclv(Map<String, Object> params) throws SQLException {
		this.getMdpMapper().obtenerAbonosCclv(params);
	}
	
	@Override
	public void obtenerPagosCclv(Map<String, Object> params) throws SQLException {
		this.getMdpMapper().obtenerPagosCclv(params);
	}
	
}
