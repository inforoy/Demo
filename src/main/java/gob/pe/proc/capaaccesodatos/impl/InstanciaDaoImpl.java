package gob.pe.proc.capaaccesodatos.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.stereotype.Repository;

import gob.pe.proc.capaacccesodatos.InstanciaDAO;
import gob.pe.proc.capadatos.Dependencia;
import gob.pe.proc.capadatos.Instancia;
import gob.pe.proc.capadatos.InstanciaId;
import gob.pe.proc.capadatos.Organojudicial;

@Repository
public class InstanciaDaoImpl implements InstanciaDAO {
	private HibernateTemplate hibernateTemplate;
	@SuppressWarnings("unused")
	private JdbcTemplate jdbcTemplate;
			
	@Autowired
	public InstanciaDaoImpl(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
		this.jdbcTemplate = new JdbcTemplate(SessionFactoryUtils.getDataSource(sessionFactory));
	}

	@SuppressWarnings("unchecked")
	public List<Instancia> obtenerListaInstancia(Instancia instancia) {
		StringBuffer query = new StringBuffer(" from Instancia ");
		
		if(instancia != null){
			
			query.append(" where upper(denominacion) like '%").append(instancia.getFechaCambio()).append("%' ");
		}
		
		List<Instancia> list = (List<Instancia>)
				  hibernateTemplate.find(query.toString());
		return list;
	}

	public Instancia obtenerInstanciaporID(InstanciaId instanciaId) {
		Instancia instancia = (Instancia) hibernateTemplate.get(Instancia.class, instanciaId);
		return instancia;
	}

	public void guardarInstancia(Instancia instancia) {
		hibernateTemplate.saveOrUpdate(instancia);

	}

	public void borrarInstancia(InstanciaId instanciaId) {
		Instancia instancia = obtenerInstanciaporID(instanciaId);
		hibernateTemplate.delete(instancia);

	}

	@SuppressWarnings("unchecked")
	public List<Dependencia> listarDependencia(int id) {
		StringBuffer query = new StringBuffer(" from Dependencia d where d.organojudicial.idOrganojudicial=");
		query.append(id);
		List<Dependencia> list = (List<Dependencia>)hibernateTemplate.find(query.toString());
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Organojudicial> listarOrganosJudiciales() {
		StringBuffer query = new StringBuffer(" from Organojudicial ");
		List<Organojudicial> list = (List<Organojudicial>)hibernateTemplate.find(query.toString());
		return list;
	}

	@SuppressWarnings("unchecked")
	public Set<Instancia> reporteInstanciaUbigeo(Instancia instancia) {
		StringBuffer query=new StringBuffer("from Instancia i where i.id.dependencia.ubigeo.idUbigeo='");
		query.append(instancia.getDependencia().getUbigeo().getIdUbigeo())
		.append("' and i.id.proceso.materia.idMateria=")
		.append(instancia.getProceso().getMateria().getIdMateria())
		.append("and i.id.proceso.estado.idEstado=")
		.append(instancia.getProceso().getEstado().getIdEstado()).
		append("and i.id.proceso.fechaInicio between '").append(instancia.getProceso().getFechaInicio()).append("' and '").append(instancia.getProceso().getFechaFin()).append("'");
		List<Instancia> list=(List<Instancia>)hibernateTemplate.find(query.toString());
		Set<Instancia> lista=new HashSet<Instancia>();
		lista.addAll(list);
		return lista;
	}

	@SuppressWarnings("unchecked")
	public Set<Instancia> reporteDependenciaInstancia(Instancia instancia) {
		StringBuffer query=new StringBuffer("from Instancia i where i.id.dependencia.idDependencia=");
		query.append(instancia.getDependencia().getIdDependencia())
		.append("and i.id.proceso.estado.idEstado=")
		.append(instancia.getProceso().getEstado().getIdEstado()).
		append("and i.id.proceso.fechaInicio between '").append(instancia.getProceso().getFechaInicio()).append("' and '").append(instancia.getProceso().getFechaFin()).append("'");
		List<Instancia> list=(List<Instancia>)hibernateTemplate.find(query.toString());
		Set<Instancia> lista=new HashSet<Instancia>();
		lista.addAll(list);
		return lista;
	}

}
