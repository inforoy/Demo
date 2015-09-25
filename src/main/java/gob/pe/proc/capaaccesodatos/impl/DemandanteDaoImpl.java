package gob.pe.proc.capaaccesodatos.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.stereotype.Repository;

import gob.pe.proc.capaacccesodatos.DemandanteDAO;
import gob.pe.proc.capadatos.Demandante;
import gob.pe.proc.capadatos.Proceso;
@Repository
public class DemandanteDaoImpl implements DemandanteDAO {
	private HibernateTemplate hibernateTemplate;
	@SuppressWarnings("unused")
	private JdbcTemplate jdbcTemplate;
			
	@Autowired
	public DemandanteDaoImpl(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
		this.jdbcTemplate = new JdbcTemplate(SessionFactoryUtils.getDataSource(sessionFactory));
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<Demandante> obtenerListaDemandante(Demandante demandante) {
		StringBuffer query = new StringBuffer(" from Demandante where ind_eliminadoDemandante=true ");
		
		if(demandante != null && demandante.getDenominacion() != null 
				&& demandante.getDenominacion().length() > 0){
			
			query.append(" and upper(denominacion) like '%").append(demandante.getDenominacion().
					toUpperCase()).append("%' ");
		}
		
		List<Demandante> list = (List<Demandante>)
				  hibernateTemplate.find(query.toString());
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<Demandante> obtenerListaDemandantePorNumeroExpediente(String numExpediente) {
		StringBuffer query = new StringBuffer(" from Demandante where ind_eliminadoDemandante is not true ");
		
		if(numExpediente != null && !numExpediente.equals("") && numExpediente.length() > 0){
			
			query.append(" and upper(proceso.nroExpediente) like '%").append(numExpediente).append("%' ");
		}
		List<Demandante> list = (List<Demandante>)hibernateTemplate.find(query.toString());
		return list;
	}
	@Override
	public Demandante obtenerDemandanteporID(Integer idDemandante) {
		Demandante demandante = (Demandante) hibernateTemplate.get(Demandante.class, idDemandante);
		return demandante;
	}

	@Override
	public void guardarDemandante(Demandante demandante) {
		hibernateTemplate.saveOrUpdate(demandante);

	}

	public void borrarDemandante(Integer idDemandante) {
		Demandante demandante = obtenerDemandanteporID(idDemandante);
		hibernateTemplate.delete(demandante);

	}
	@SuppressWarnings("rawtypes")
	public String obtenerUltimoProceso() {
		String ultimoProceso="";
		DetachedCriteria criteria=DetachedCriteria.forClass(Proceso.class);
		criteria.setProjection(Projections.max("nroExpediente"));
		List resultado=hibernateTemplate.findByCriteria(criteria);
		ultimoProceso=(String)resultado.get(0);
		return ultimoProceso;
	}
	@SuppressWarnings("unchecked")
	public Set<Demandante> obtenerListaDemandantePorNombre(Demandante demandante) {
		StringBuffer query = new StringBuffer(" from Demandante d left join fetch d.proceso where d.ind_eliminadoDemandante is not true ");
		
		if(demandante != null && demandante.getDenominacion() != null && demandante.getDenominacion().length() > 0){
			
			query.append(" and upper(d.denominacion) like '").append(demandante.getDenominacion().toUpperCase()).append("%' ");
		}
		List<Demandante> list = (List<Demandante>) hibernateTemplate.find(query.toString());
		Set<Demandante> lista=new HashSet<Demandante>();
		lista.addAll(list);
		return lista;
	}
	public void eliminarDemandante(Integer idDemandante) {
		Demandante demandante=obtenerDemandanteporID(idDemandante);
		demandante.setInd_eliminadoDemandante(false);
		guardarDemandante(demandante);
	}

}
