package gob.pe.proc.capaservicio.impl;

import gob.pe.proc.capaacccesodatos.UsuarioDAO;
import gob.pe.proc.capadatos.Usuario;
import gob.pe.proc.capaservicio.UsuarioService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	@Autowired
	private UsuarioDAO usuarioDAO;

	public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException {
		Usuario verificarUsuario=usuarioDAO.verificarUsuario(username);
		  if(verificarUsuario==null)throw new UsernameNotFoundException("Usuario no existe.");
		  else{
			  verificarUsuario.setRol(usuarioDAO.verificarUsuario(username).getRol());
			  List<GrantedAuthority> perfiles=new ArrayList<GrantedAuthority>();
			  perfiles.add(usuarioDAO.verificarUsuario(username).getRol());
			  verificarUsuario.setPerfiles(perfiles);
			  return verificarUsuario;
		  }
	}


	public Usuario validarUsuario(Usuario usuario) {
		return usuarioDAO.validarUsuario(usuario);
	}


}
