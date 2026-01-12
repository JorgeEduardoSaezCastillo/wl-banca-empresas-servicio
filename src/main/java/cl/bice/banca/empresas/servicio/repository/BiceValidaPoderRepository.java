package cl.bice.banca.empresas.servicio.repository;

import java.sql.SQLException;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import cl.bice.banca.empresas.servicio.mapper.validapoder.BiceValidaPoderMapper;

/**
 * Created by .
 */
@Repository("BiceValidaPoderRepository")
public class BiceValidaPoderRepository implements BiceValidaPoderMapper {

	/**
	 * Template SQL.
	 */
	@Autowired
	@Qualifier("sqlSessionTemplateBiceValidaPoderMapper")
	private SqlSessionTemplate sqlSessionTemplate;

	/**
	 * Constructor por defecto.
	 */
	public BiceValidaPoderRepository() {
		super();
	}

	/**
	 * Mapper de la BD SQLServer db_legal
	 */
	private BiceValidaPoderMapper getBiceValidaPoderMapper() {
		return this.sqlSessionTemplate.getMapper(BiceValidaPoderMapper.class);
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
		this.getBiceValidaPoderMapper().validaPoder(params);
	}

}
