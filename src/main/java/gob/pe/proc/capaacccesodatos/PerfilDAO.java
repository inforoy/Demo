package gob.pe.proc.capaacccesodatos;


import gob.pe.proc.capadatos.Rol;

import java.util.List;

public interface PerfilDAO {
	public List<Rol> obtenerListaPerfil(Rol perfil);
	public Rol obtenerPerfilporID(Integer idPerfil);
	public void guardarPerfil(Rol perfil);
	public void borrarPerfil(Integer idPerfil);
	public List<Rol> listarPerfiles();
}
