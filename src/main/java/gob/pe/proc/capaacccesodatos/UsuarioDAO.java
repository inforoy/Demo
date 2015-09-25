package gob.pe.proc.capaacccesodatos;


import gob.pe.proc.capadatos.Usuario;

import java.util.List;

public interface UsuarioDAO {
	public List<Usuario> obtenerListaUsuario(Usuario usuario);
	public Usuario obtenerUsuarioporID(String dni);
	public void guardarUsuario(Usuario usuario);
	public void borrarUsuario(String dni);

	
	public Usuario validarUsuario(Usuario usuario);
	public Usuario verificarUsuario(String username);
}
