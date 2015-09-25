package gob.pe.proc.capaaccesodatos.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.stereotype.Repository;

import gob.pe.proc.capaacccesodatos.OrganoJudicialDAO;
import gob.pe.proc.capadatos.Organojudicial;
@Repository
public class OrganoJudicialDaoImpl implements OrganoJudicialDAO{
	private HibernateTemplate hibernateTemplate;
	@SuppressWarnings("unused")
	private JdbcTemplate jdbcTemplate;
			
	@Autowired
	public OrganoJudicialDaoImpl(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
		this.jdbcTemplate = new JdbcTemplate(SessionFactoryUtils.getDataSource(sessionFactory));
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<Organojudicial> obtenerListaOrganoJudicial(Organojudicial organoJudicial) {
		StringBuffer query = new StringBuffer(" from Organojudicial ");
		
		if(organoJudicial != null && organoJudicial.getDenominacion() != null 
				&& organoJudicial.getDenominacion().length() > 0){
			
			query.append(" where upper(denominacion) like '%").append(organoJudicial.getDenominacion().
					toUpperCase()).append("%' ");
		}
		
		List<Organojudicial> list = (List<Organojudicial>)
				  hibernateTemplate.find(query.toString());
		return list;
	}

	@Override
	public Organojudicial obtenerOrganoJudicialporID(Integer idOrganoJudicial) {
		Organojudicial organoJudicial = (Organojudicial) hibernateTemplate.get(Organojudicial.class, idOrganoJudicial);
		return organoJudicial;
	}

	@Override
	public void guardarOrganoJudicial(Organojudicial organoJudicial) {
		hibernateTemplate.saveOrUpdate(organoJudicial);
		
	}

	@Override
	public void borrarOrganoJudicial(Integer idOrganoJudicial) {
		Organojudicial organoJudicial = obtenerOrganoJudicialporID(idOrganoJudicial);
		hibernateTemplate.delete(organoJudicial);
		
	}

}
