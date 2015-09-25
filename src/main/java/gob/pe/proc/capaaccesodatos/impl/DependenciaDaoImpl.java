package gob.pe.proc.capaaccesodatos.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.stereotype.Repository;

import gob.pe.proc.capaacccesodatos.DependenciaDAO;
import gob.pe.proc.capadatos.Dependencia;
@Repository
public class DependenciaDaoImpl implements DependenciaDAO {
	private HibernateTemplate hibernateTemplate;
	@SuppressWarnings("unused")
	private JdbcTemplate jdbcTemplate;
			
	@Autowired
	public DependenciaDaoImpl(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
		this.jdbcTemplate = new JdbcTemplate(SessionFactoryUtils.getDataSource(sessionFactory));
	}

	@SuppressWarnings("unchecked")
	public List<Dependencia> obtenerListaDependencia(Dependencia dependencia) {
		StringBuffer query = new StringBuffer(" from Dependencia ");
		
		if(dependencia != null && dependencia.getDenominacion() != null 
				&& dependencia.getDenominacion().length() > 0){
			
			query.append(" where upper(denominacion) like '%").append(dependencia.getDenominacion().
					toUpperCase()).append("%' ");
		}
		
		List<Dependencia> list = (List<Dependencia>)
				  hibernateTemplate.find(query.toString());
		return list;
	}

	public Dependencia obtenerDependenciaporID(Integer idOrganoJudicial,String idUbigeo) {
		Dependencia dependencia = (Dependencia) hibernateTemplate.get(Dependencia.class, idOrganoJudicial);
		return dependencia;
	}

	public void guardarDependencia(Dependencia dependencia) {
		hibernateTemplate.saveOrUpdate(dependencia);

	}
	
	public void borrarDependencia(Integer idOrganoJudicial) {
		Dependencia dependencia = buscarDependenciaporID(idOrganoJudicial);
		hibernateTemplate.delete(dependencia);

	}

	public Dependencia buscarDependenciaporID(Integer idDependencia) {
		Dependencia dependencia=(Dependencia)hibernateTemplate.get(Dependencia.class, idDependencia);
		return dependencia;
	}

}
