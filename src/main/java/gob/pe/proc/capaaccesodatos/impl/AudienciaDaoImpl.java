package gob.pe.proc.capaaccesodatos.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.stereotype.Repository;

import gob.pe.proc.capaacccesodatos.AudienciaDAO;
import gob.pe.proc.capadatos.Audiencia;
@Repository
public class AudienciaDaoImpl implements AudienciaDAO {
	private HibernateTemplate hibernateTemplate;
	@SuppressWarnings("unused")
	private JdbcTemplate jdbcTemplate;
			
	@Autowired
	public AudienciaDaoImpl(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
		this.jdbcTemplate = new JdbcTemplate(SessionFactoryUtils.getDataSource(sessionFactory));
	}

	@SuppressWarnings("unchecked")
	public List<Audiencia> obtenerListaAudiencia(Audiencia audiencia) {
		StringBuffer query = new StringBuffer(" from Audiencia ");
		
		if(audiencia != null && audiencia.getDiligencia() != null 
				&& audiencia.getDiligencia().length() > 0){
			
			query.append(" where upper(diligencia) like '%").append(audiencia.getDiligencia().
					toUpperCase()).append("%' ");
		}
		
		
		List<Audiencia> list = (List<Audiencia>)hibernateTemplate.find(query.toString());
		return list;
	}


	public Audiencia obtenerAudienciaporID(Integer idAudiencia) {
		Audiencia audiencia=(Audiencia)hibernateTemplate.get(Audiencia.class, idAudiencia);
		return audiencia;
	}

	public void guardarAudiencia(Audiencia audiencia) {
		hibernateTemplate.saveOrUpdate(audiencia);

	}


	public void borrarAudiencia(Integer idAudiencia) {
		Audiencia audiencia=obtenerAudienciaporID(idAudiencia);
		hibernateTemplate.delete(audiencia);
	}

}
