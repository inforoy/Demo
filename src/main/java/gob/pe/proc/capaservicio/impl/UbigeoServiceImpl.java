package gob.pe.proc.capaservicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gob.pe.proc.capaacccesodatos.UbigeoDAO;
import gob.pe.proc.capadatos.Ubigeo;
import gob.pe.proc.capaservicio.UbigeoService;
@Service
public class UbigeoServiceImpl implements UbigeoService {
	@Autowired
	private UbigeoDAO ubigeoDAO;

	public List<Ubigeo> obtenerListaUbigeo(Ubigeo ubigeo) {
		return ubigeoDAO.obtenerListaUbigeo(ubigeo);
	}

	public Ubigeo obtenerUbigeoporId(String idUbigeo) {
		return ubigeoDAO.obtenerUbigeoporID(idUbigeo);
	}

	public void guardarUbigeo(Ubigeo ubigeo) {
		ubigeoDAO.guardarUbigeo(ubigeo);

	}

	public void borrarUbigeo(String idUbigeo) {
		ubigeoDAO.borrarUbigeo(idUbigeo);

	}

	public List<Ubigeo> listarUbigeo() {
		return ubigeoDAO.listarUbigeo();
	}

}
