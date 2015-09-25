package gob.pe.proc.capaaccesodatos.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.stereotype.Repository;

import gob.pe.proc.capaacccesodatos.UbigeoDAO;
import gob.pe.proc.capadatos.Ubigeo;
@Repository
public class UbigeoDaoImpl implements UbigeoDAO {
	private HibernateTemplate hibernateTemplate;
	@SuppressWarnings("unused")
	private JdbcTemplate jdbcTemplate;
			
	@Autowired
	public UbigeoDaoImpl(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
		this.jdbcTemplate = new JdbcTemplate(SessionFactoryUtils.getDataSource(sessionFactory));
	}

	@SuppressWarnings("unchecked")
	public List<Ubigeo> obtenerListaUbigeo(Ubigeo ubigeo) {
		StringBuffer query = new StringBuffer(" from Ubigeo ");
		
		if(ubigeo != null && ubigeo.getDenominacion() != null 
				&& ubigeo.getDenominacion().length() > 0){
			
			query.append(" where upper(denominacion) like '%").append(ubigeo.getDenominacion().
					toUpperCase()).append("%' ");
		}
		
		List<Ubigeo> list = (List<Ubigeo>)
				  hibernateTemplate.find(query.toString());
		return list;
	}
	public Ubigeo obtenerUbigeoporID(String idUbigeo) {
		Ubigeo ubigeo = (Ubigeo) hibernateTemplate.get(Ubigeo.class, idUbigeo);
		return ubigeo;
	}

	public void guardarUbigeo(Ubigeo ubigeo) {
		hibernateTemplate.saveOrUpdate(ubigeo);

	}

	public void borrarUbigeo(String idUbigeo) {
		Ubigeo ubigeo = obtenerUbigeoporID(idUbigeo);
		hibernateTemplate.delete(ubigeo);

	}
	@SuppressWarnings("unchecked")
	public List<Ubigeo> listarUbigeo() {
		StringBuffer query = new StringBuffer(" from Ubigeo ");
		List<Ubigeo> list = (List<Ubigeo>)hibernateTemplate.find(query.toString());
		return list;
	}
}
