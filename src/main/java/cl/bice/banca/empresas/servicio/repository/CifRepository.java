package cl.bice.banca.empresas.servicio.repository;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import cl.bice.banca.empresas.servicio.mapper.cif.CifMapper;

/**
 * Created by lbasso on 23-05-17.
 */
@Repository("CifRepository")
public class CifRepository implements CifMapper {

	/**
	 * Template SQL.
	 */
	@Autowired
	@Qualifier("sqlSessionTemplateCif")
	private SqlSessionTemplate sqlSessionTemplateCif;

	/**
	 * Constructor por defecto.
	 */
	public CifRepository() {
		super();
	}

	/**
	 * Mapper del package Integracion Canales.
	 *
	 * @return mapper.
	 */
	private CifMapper getCifMapper() {
		return this.sqlSessionTemplateCif.getMapper(CifMapper.class);
	}

	/**
	 * Permite obtener el valor del atributo sqlSessionTemplate.
	 *
	 * @return el valor sqlSessionTemplate.
	 */
	public SqlSessionTemplate getSqlSessionTemplate() {
		return this.sqlSessionTemplateCif;
	}

	/**
	 * Permite settear el valor del atributo sqlSessionTemplate.
	 *
	 * @param sqlSessionTemplate nuevo valor para el atributo sqlSessionTemplate.
	 */
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplateCif = sqlSessionTemplate;
	}

	@Override
	public void validaProductoPKG(Map<String, Object> params) {
		getCifMapper().validaProductoPKG(params);
	}

}
