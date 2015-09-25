package gob.pe.proc.capaaccesodatos.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.stereotype.Repository;

import gob.pe.proc.capaacccesodatos.EstadoDAO;
import gob.pe.proc.capadatos.Estado;
@Repository
public class EstadoDaoImpl implements EstadoDAO{
	private HibernateTemplate hibernateTemplate;
	@SuppressWarnings("unused")
	private JdbcTemplate jdbcTemplate;
			
	@Autowired
	public EstadoDaoImpl(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
		this.jdbcTemplate = new JdbcTemplate(SessionFactoryUtils.getDataSource(sessionFactory));
	}

	public void guardarEstado(Estado estado) {
		hibernateTemplate.save(estado);		
	}
	
	public void actualizarEstado(Estado estado) {
		hibernateTemplate.saveOrUpdate(estado);		
	}
	
	@SuppressWarnings("unchecked")
	public List<Estado> obtenerListaEstado(Estado estado) {
		StringBuffer query = new StringBuffer(" from Estado ");
		
		if(estado != null && estado.getDenominacion() != null 
				&& estado.getDenominacion().length() > 0){
			
			query.append(" where upper(denominacion) like '%").append(estado.getDenominacion().
					toUpperCase()).append("%' ");
		}		
		List<Estado> list = (List<Estado>)hibernateTemplate.find(query.toString());
		return list;
	}

	public Estado obtenerEstado(int idEstado){
		Estado estado = (Estado) hibernateTemplate.get(Estado.class, idEstado);		
		return estado;
	}
	
	public void eliminarEstado(int idEstado){
		Estado estado = obtenerEstado(idEstado);		
		hibernateTemplate.delete(estado);
	}
	
	
	
}
