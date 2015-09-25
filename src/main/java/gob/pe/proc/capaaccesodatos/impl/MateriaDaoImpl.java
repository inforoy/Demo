package gob.pe.proc.capaaccesodatos.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.stereotype.Repository;

import gob.pe.proc.capaacccesodatos.MateriaDAO;
import gob.pe.proc.capadatos.Materia;
import gob.pe.proc.capadatos.Naturaleza;
@Repository
public class MateriaDaoImpl implements MateriaDAO{

	private HibernateTemplate hibernateTemplate;
	@SuppressWarnings("unused")
	private JdbcTemplate jdbcTemplate;
			
	@Autowired
	public MateriaDaoImpl(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
		this.jdbcTemplate = new JdbcTemplate(SessionFactoryUtils.getDataSource(sessionFactory));
	}

	@SuppressWarnings("unchecked")
	public List<Materia> obtenerListaMateria(Materia materia) {
		StringBuffer query = new StringBuffer(" from Materia ");
		if(materia != null && materia.getDenominacion() != null 
				&& materia.getDenominacion().length() > 0){
			
			query.append(" where upper(denominacion) like '%").append(materia.getDenominacion().
					toUpperCase()).append("%' ");
		}
		List<Materia> list = (List<Materia>)hibernateTemplate.find(query.toString());
		return list;
	}


	public Materia obtenerMateriaporID(Integer idMateria) {
		Materia materia = (Materia) hibernateTemplate.get(Materia.class, idMateria);
		return materia;
	}


	public void guardarMateria(Materia materia) {
		hibernateTemplate.save(materia);
		
	}

	
	public void borrarMateria(Integer idMateria) {
		Materia materia = obtenerMateriaporID(idMateria);
		hibernateTemplate.delete(materia);
		
	}
	@SuppressWarnings("unchecked")
	public List<Naturaleza> obtenerListaNaturaleza(){
		List<Naturaleza> listarNaturaleza=(List<Naturaleza>)hibernateTemplate.find("from Naturaleza");
		return listarNaturaleza;
	}

}
