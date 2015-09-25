package gob.pe.proc.capaaccesodatos.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.stereotype.Repository;

import gob.pe.proc.capaacccesodatos.UsuarioDAO;
import gob.pe.proc.capadatos.Usuario;
@Repository
public class UsuarioDaoImpl implements UsuarioDAO {
	private HibernateTemplate hibernateTemplate;
	@SuppressWarnings("unused")
	private JdbcTemplate jdbcTemplate;
			
	@Autowired
	public UsuarioDaoImpl(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
		this.jdbcTemplate = new JdbcTemplate(SessionFactoryUtils.getDataSource(sessionFactory));
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> obtenerListaUsuario(Usuario usuario) {
		StringBuffer query = new StringBuffer(" from Usuario ");
		if(usuario.isEstadoLaboral() || !usuario.isEstadoLaboral()){
			query.append(" where estadoLaboral=").append(usuario.isEstadoLaboral());
		}
		
		if(usuario != null && usuario.getNombres() != null 	&& usuario.getNombres().length() > 0
				&& usuario.getApaterno() != null && usuario.getApaterno().length() > 0
				&& usuario.getAmaterno() != null && usuario.getAmaterno().length() > 0){
			
			query.append(" and upper(nombres) like '%").append(usuario.getNombres().toUpperCase()).append("%' ")
					.append(" and upper(apaterno) like '%").append(usuario.getApaterno().toUpperCase()).append("%' ")
					.append(" and upper(amaterno) like '%").append(usuario.getAmaterno().toUpperCase()).append("%' ");
		}
		
		List<Usuario> list = (List<Usuario>)
				  hibernateTemplate.find(query.toString());
		return list;
	}

	public Usuario obtenerUsuarioporID(String dni) {
		Usuario usuario = (Usuario) hibernateTemplate.get(Usuario.class, dni);
		return usuario;
	}

	public void guardarUsuario(Usuario usuario) {
		hibernateTemplate.saveOrUpdate(usuario);

	}

	public void borrarUsuario(String dni) {
		Usuario usuario = obtenerUsuarioporID(dni);
		hibernateTemplate.delete(usuario);

	}
	
	public Usuario validarUsuario(Usuario usuario) {
		boolean flag = false;
		Usuario usuarioEncontrado = null;
		try {
			String query="from Usuario where username=?";
	        usuarioEncontrado = (Usuario) hibernateTemplate.find(query, usuario.getUsername()).get(0);
	        
			if (usuarioEncontrado != null) {
				if(usuarioEncontrado.getPassname().equals(usuario.getPassname())){
					flag = true;
				}
			}else{
				System.out.println("No se pudo Iniciar Sesión");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(!flag){
			usuarioEncontrado = null;
		}
		return usuarioEncontrado;
	}
	public Usuario verificarUsuario(String username){
		Usuario usuarioEncontrado = null;
		String query="from Usuario where username=?";
        usuarioEncontrado = (Usuario) hibernateTemplate.find(query, username).get(0);

		 if (usuarioEncontrado!=null) {
			 return usuarioEncontrado;
		 }
	return null;
	}	

}
