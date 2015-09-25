package gob.pe.proc.capaservicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gob.pe.proc.capaacccesodatos.PerfilDAO;
import gob.pe.proc.capadatos.Rol;
import gob.pe.proc.capaservicio.PerfilService;
@Service
public class PerfilServiceImpl implements PerfilService {
	@Autowired
	private PerfilDAO perfilDAO;

	public List<Rol> obtenerListaPerfil(Rol perfil) {
		return perfilDAO.obtenerListaPerfil(perfil);
	}

	public Rol obtenerPerfilporId(Integer idPerfil) {
		return perfilDAO.obtenerPerfilporID(idPerfil);
	}

	public void guardarPerfil(Rol perfil) {
		perfilDAO.guardarPerfil(perfil);

	}

	public void borrarPerfil(Integer idPerfil) {
		perfilDAO.borrarPerfil(idPerfil);

	}

	public List<Rol> listarPerfiles() {
		return perfilDAO.listarPerfiles();
	}

}
