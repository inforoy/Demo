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

import gob.pe.proc.capaacccesodatos.DemandadoDAO;
import gob.pe.proc.capadatos.Demandado;
import gob.pe.proc.capadatos.Proceso;
@Repository
public class DemandadoDaoImpl implements DemandadoDAO {
	private HibernateTemplate hibernateTemplate;
	@SuppressWarnings("unused")
	private JdbcTemplate jdbcTemplate;
			
	@Autowired
	public DemandadoDaoImpl(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
		this.jdbcTemplate = new JdbcTemplate(SessionFactoryUtils.getDataSource(sessionFactory));
	}

	@SuppressWarnings("unchecked")
	public List<Demandado> obtenerListaDemandado(Demandado demandado) {
		StringBuffer query = new StringBuffer(" from Demandado where ind_eliminadoDemandado is not true ");
		
		if(demandado != null && demandado.getDenominacion() != null && demandado.getDenominacion().length() > 0){
			
			query.append(" and upper(denominacion) like '%").append(demandado.getDenominacion().toUpperCase()).append("%' ");
		}
		List<Demandado> list = (List<Demandado>)hibernateTemplate.find(query.toString());
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<Demandado> obtenerListaDemandadoPorNumeroExpediente(String numExpediente) {
		StringBuffer query = new StringBuffer(" from Demandado where ind_eliminadoDemandado is not true ");
		
		if(numExpediente != null && !numExpediente.equals("") && numExpediente.length() > 0){
			
			query.append(" and upper(proceso.nroExpediente) like '%").append(numExpediente).append("%' ");
		}
		List<Demandado> list = (List<Demandado>)hibernateTemplate.find(query.toString());
		return list;
	}
	public Demandado obtenerDemandadoporID(Integer idDemandado) {
		Demandado demandado = (Demandado) hibernateTemplate.get(Demandado.class, idDemandado);
		return demandado;
	}

	public void guardarDemandado(Demandado demandado) {
		hibernateTemplate.saveOrUpdate(demandado);

	}

	public void borrarDemandado(int idDemandado) {
		Demandado demandado = obtenerDemandadoporID(idDemandado);
		hibernateTemplate.delete(demandado);

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
	public Set<Demandado> obtenerListaDemandadoPorNombre(Demandado demandado) {
		StringBuffer query = new StringBuffer(" from Demandado d left join fetch d.proceso where d.ind_eliminadoDemandado is not true ");
		
		if(demandado != null && demandado.getDenominacion() != null && demandado.getDenominacion().length() > 0){
			
			query.append(" and upper(d.denominacion) like '%").append(demandado.getDenominacion().toUpperCase()).append("%' ");
		}
		List<Demandado> list = (List<Demandado>) hibernateTemplate.find(query.toString());
		Set<Demandado> lista=new HashSet<Demandado>();
		lista.addAll(list);
		return lista;
	}

	public void eliminarDemandado(int idDemandado) {
		Demandado demandado=obtenerDemandadoporID(idDemandado);
		demandado.setInd_eliminadoDemandado(false);
		guardarDemandado(demandado);
	}
}
