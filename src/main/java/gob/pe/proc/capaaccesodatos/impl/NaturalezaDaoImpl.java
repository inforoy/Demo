package gob.pe.proc.capaaccesodatos.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.stereotype.Repository;

import gob.pe.proc.capaacccesodatos.NaturalezaDAO;
import gob.pe.proc.capadatos.Naturaleza;
@Repository
public class NaturalezaDaoImpl implements NaturalezaDAO {
	private HibernateTemplate hibernateTemplate;
	@SuppressWarnings("unused")
	private JdbcTemplate jdbcTemplate;
			
	@Autowired
	public NaturalezaDaoImpl(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
		this.jdbcTemplate = new JdbcTemplate(SessionFactoryUtils.getDataSource(sessionFactory));
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<Naturaleza> obtenerListaNaturaleza(Naturaleza naturaleza) {
		StringBuffer query = new StringBuffer(" from Naturaleza ");
		
		if(naturaleza != null && naturaleza.getDenominacion() != null 
				&& naturaleza.getDenominacion().length() > 0){
			
			query.append(" where upper(denominacion) like '%").append(naturaleza.getDenominacion().
					toUpperCase()).append("%' ");
		}
		
		List<Naturaleza> list = (List<Naturaleza>)
				  hibernateTemplate.find(query.toString());
		return list;
	}

	@Override
	public Naturaleza obtenerNaturalezaporID(Integer idNaturaleza) {
		Naturaleza naturaleza = (Naturaleza) hibernateTemplate.get(Naturaleza.class, idNaturaleza);
		return naturaleza;
	}

	@Override
	public void guardarNaturaleza(Naturaleza naturaleza) {
		hibernateTemplate.saveOrUpdate(naturaleza);

	}

	@Override
	public void borrarNaturaleza(Integer idNaturaleza) {
		Naturaleza naturaleza = obtenerNaturalezaporID(idNaturaleza);
		hibernateTemplate.delete(naturaleza);

	}

}
