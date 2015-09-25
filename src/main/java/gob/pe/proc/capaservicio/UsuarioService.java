package gob.pe.proc.capaservicio;

import gob.pe.proc.capadatos.Usuario;


import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsuarioService extends UserDetailsService {
	
	
	public Usuario validarUsuario(Usuario usuario);

}
