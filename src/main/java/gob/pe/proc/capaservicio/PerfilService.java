package gob.pe.proc.capaservicio;

import gob.pe.proc.capadatos.Rol;

import java.util.List;

public interface PerfilService {
	public List<Rol> obtenerListaPerfil(Rol perfil);
	public Rol obtenerPerfilporId(Integer idPerfil);
	public void guardarPerfil(Rol perfil);
	public void borrarPerfil(Integer idPerfil);
	public List<Rol> listarPerfiles();
}
