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

import gob.pe.proc.capaacccesodatos.AsignadoDAO;
import gob.pe.proc.capadatos.Asignado;
import gob.pe.proc.capadatos.Proceso;
import gob.pe.proc.capadatos.Usuario;

@Repository
public class AsignadoDaoImpl implements AsignadoDAO{
	private HibernateTemplate hibernateTemplate;
	@SuppressWarnings("unused")
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public AsignadoDaoImpl(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
		this.jdbcTemplate = new JdbcTemplate(SessionFactoryUtils.getDataSource(sessionFactory));
	}
	@SuppressWarnings("unchecked")
	public Set<Proceso> obtenerListaProcesosNoAsignados(Asignado asignado) {
		StringBuffer query = new StringBuffer("from Proceso p left join fetch p.asignados a where a.id.usuario=null");
		List<Proceso> list = (List<Proceso>)hibernateTemplate.find(query.toString());
		Set<Proceso> lista=new HashSet<Proceso>();
		lista.addAll(list);
		return lista;
	}

	public Asignado obtenerAsignadoporID(String nroExpediente, String nrodni) {
		Asignado asignado = (Asignado) hibernateTemplate.get(Asignado.class, nroExpediente);
		return asignado;
	}

	public void guardarAsignado(Asignado asignado) {
		
		hibernateTemplate.saveOrUpdate(asignado);
		
	}

	public void borrarAsignado(String nroExpediente, String nrodni) {
		Asignado asignado = obtenerAsignadoporID(nroExpediente, nrodni);
		hibernateTemplate.delete(asignado);
		
	}
	@SuppressWarnings("unchecked")
	public List<Usuario> listaAbogados() {
		StringBuffer query = new StringBuffer("from Usuario u where u.rol.idRol=1 or u.rol.idRol=2");
		List<Usuario> list = (List<Usuario>)hibernateTemplate.find(query.toString());
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<Asignado> listaAbogadosProceso(Asignado asignado) {
		StringBuffer query=new StringBuffer("from Asignado a ");
		if(asignado!=null && asignado.getUsuario() != null && asignado.getId().getUsuario().getDni().length()>0){
			System.out.println("ENTRO AL IF");
			query.append(" where a.id.usuario.dni like '%").append(asignado.getUsuario().getDni()).append("%'");
		}
		List<Asignado> list=(List<Asignado>)hibernateTemplate.find(query.toString());
		return list;
	}

}
