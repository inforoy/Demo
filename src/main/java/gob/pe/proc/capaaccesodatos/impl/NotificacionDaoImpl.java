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

import gob.pe.proc.capaacccesodatos.NotificacionDAO;
import gob.pe.proc.capadatos.Notificacion;

@Repository
public class NotificacionDaoImpl implements NotificacionDAO {
	private HibernateTemplate hibernateTemplate;
	@SuppressWarnings("unused")
	private JdbcTemplate jdbcTemplate;
			
	@Autowired
	public NotificacionDaoImpl(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
		this.jdbcTemplate = new JdbcTemplate(SessionFactoryUtils.getDataSource(sessionFactory));
	}

	@SuppressWarnings("unchecked")
	public Set<Notificacion> obtenerListaNotificacion(Notificacion notificacion) {
		StringBuffer query = new StringBuffer(" from Notificacion n where n.ind_eliminadoNotificacion is true ");
		
		if(notificacion != null && notificacion.getNroNotificacion() != null && notificacion.getNroNotificacion().length() > 0){			
			query.append(" and upper(n.nroNotificacion) like '%").append(notificacion.getNroNotificacion().toUpperCase()).append("%' ");			
		}
		List<Notificacion> list = (List<Notificacion>)hibernateTemplate.find(query.toString());
		Set<Notificacion> lista = new HashSet<Notificacion>();
		lista.addAll(list);
		return lista;
	}

	public Notificacion obtenerNotificacionporID(String idNotificacion) {
		Notificacion notificacion = (Notificacion) hibernateTemplate.get(Notificacion.class, idNotificacion);
		return notificacion;
	}

	public void guardarNotificacion(Notificacion notificacion) {
		hibernateTemplate.saveOrUpdate(notificacion);

	}

	public void borrarNotificacion(String idNotificacion) {
		Notificacion notificacion = obtenerNotificacionporID(idNotificacion);
		hibernateTemplate.delete(notificacion);

	}
	
	public void desactivarNotificacion(String idNotificacion) {
		Notificacion notificacion = obtenerNotificacionporID(idNotificacion);
		notificacion.setInd_eliminadoNotificacion(false);
		hibernateTemplate.saveOrUpdate(notificacion);

	}

	public Set<Notificacion> obtenerListaNotificacionPorNumeroExpediente(Notificacion notificacion) {
		StringBuffer query = new StringBuffer(" from Notificacion n where n.ind_eliminadoNotificacion is true ");
		
		if(notificacion != null && notificacion.getProceso() != null && notificacion.getProceso().getNroExpediente()!=null){			
			query.append(" and upper(n.proceso.nroExpediente) like '%").append(notificacion.getProceso().getNroExpediente().toUpperCase()).append("%' ");			
		}
		List<Notificacion> list = (List<Notificacion>)hibernateTemplate.find(query.toString());
		Set<Notificacion> lista = new HashSet<Notificacion>();
		lista.addAll(list);
		return lista;
	}

}
