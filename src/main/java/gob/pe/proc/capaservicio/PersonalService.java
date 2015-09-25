package gob.pe.proc.capaservicio;

import gob.pe.proc.capadatos.Usuario;

import java.util.List;

public interface PersonalService {
	public List<Usuario> obtenerListaPersonal(Usuario usuario);
	public Usuario obtenerPersonalporId(String dni);
	public void guardarPersonal(Usuario usuario);
	public void borrarPersonal(String dni);
}
