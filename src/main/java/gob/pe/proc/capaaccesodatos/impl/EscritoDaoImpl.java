package gob.pe.proc.capaaccesodatos.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.stereotype.Repository;

import gob.pe.proc.capaacccesodatos.EscritoDAO;
import gob.pe.proc.capadatos.Escrito;
@Repository
public class EscritoDaoImpl implements EscritoDAO {
	private HibernateTemplate hibernateTemplate;
	@SuppressWarnings("unused")
	private JdbcTemplate jdbcTemplate;
			
	@Autowired
	public EscritoDaoImpl(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
		this.jdbcTemplate = new JdbcTemplate(SessionFactoryUtils.getDataSource(sessionFactory));
	}

	@SuppressWarnings("unchecked")
	public List<Escrito> obtenerListaEscrito(Escrito escrito) {
		StringBuffer query = new StringBuffer(" from Escrito ");
		
		if(escrito != null && escrito.getDenominacion() != null 
				&& escrito.getDenominacion().length() > 0){
			
			query.append(" where upper(denominacion) like '%").append(escrito.getDenominacion().
					toUpperCase()).append("%' ");
		}
		
		List<Escrito> list = (List<Escrito>)
				  hibernateTemplate.find(query.toString());
		return list;
	}


	public Escrito obtenerEscritoporID(Integer idEscrito) {
		Escrito escrito = (Escrito) hibernateTemplate.get(Escrito.class, idEscrito);
		return escrito;
	}


	public void guardarEscrito(Escrito escrito) {
		hibernateTemplate.saveOrUpdate(escrito);

	}


	public void borrarEscrito(Integer idEscrito) {
		Escrito escrito = obtenerEscritoporID(idEscrito);
		hibernateTemplate.delete(escrito);

	}

}
