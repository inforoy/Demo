package gob.pe.proc.capaaccesodatos.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.stereotype.Repository;

import gob.pe.proc.capaacccesodatos.PerfilDAO;
import gob.pe.proc.capadatos.Rol;
@Repository
public class PerfilDaoImpl implements PerfilDAO {
	private HibernateTemplate hibernateTemplate;
	@SuppressWarnings("unused")
	private JdbcTemplate jdbcTemplate;
			
	@Autowired
	public PerfilDaoImpl(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
		this.jdbcTemplate = new JdbcTemplate(SessionFactoryUtils.getDataSource(sessionFactory));
	}

	@SuppressWarnings("unchecked")
	public List<Rol> obtenerListaPerfil(Rol perfil) {
		StringBuffer query = new StringBuffer(" from Rol ");
		
		if(perfil != null && perfil.getDenominacion() != null 
				&& perfil.getDenominacion().length() > 0){
			
			query.append(" where upper(denominacion) like '%").append(perfil.getDenominacion().
					toUpperCase()).append("%' ");
		}
		
		List<Rol> list = (List<Rol>)
				  hibernateTemplate.find(query.toString());
		return list;
	}

	public Rol obtenerPerfilporID(Integer idPerfil) {
		Rol perfil = (Rol) hibernateTemplate.get(Rol.class, idPerfil);
		return perfil;
	}

	public void guardarPerfil(Rol perfil) {
		hibernateTemplate.saveOrUpdate(perfil);
	}

	public void borrarPerfil(Integer idPerfil) {
		Rol perfil = obtenerPerfilporID(idPerfil);
		hibernateTemplate.delete(perfil);

	}

	@SuppressWarnings("unchecked")
	public List<Rol> listarPerfiles() {
		return (List<Rol>)hibernateTemplate.find("from Rol");
	}
}
