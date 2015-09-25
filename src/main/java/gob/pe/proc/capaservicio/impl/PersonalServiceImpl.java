package gob.pe.proc.capaservicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gob.pe.proc.capaacccesodatos.UsuarioDAO;
import gob.pe.proc.capadatos.Usuario;
import gob.pe.proc.capaservicio.PersonalService;
@Service
public class PersonalServiceImpl implements PersonalService {
	@Autowired
	private UsuarioDAO usuarioDAO;
	public List<Usuario> obtenerListaPersonal(Usuario usuario) {
		return usuarioDAO.obtenerListaUsuario(usuario);
	}

	public Usuario obtenerPersonalporId(String dni) {
		return usuarioDAO.obtenerUsuarioporID(dni);
	}

	public void guardarPersonal(Usuario usuario) {
		usuarioDAO.guardarUsuario(usuario);
	}

	public void borrarPersonal(String dni) {
		usuarioDAO.borrarUsuario(dni);
	}

}
