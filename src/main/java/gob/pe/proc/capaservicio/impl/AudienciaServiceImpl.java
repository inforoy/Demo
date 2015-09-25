package gob.pe.proc.capaservicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gob.pe.proc.capaacccesodatos.AudienciaDAO;
import gob.pe.proc.capadatos.Audiencia;
import gob.pe.proc.capaservicio.AudienciaService;
@Service
public class AudienciaServiceImpl implements AudienciaService {
	@Autowired
	private AudienciaDAO audienciaDAO;

	public List<Audiencia> obtenerListaAudiencia(Audiencia audiencia) {
		return audienciaDAO.obtenerListaAudiencia(audiencia);
	}

	public Audiencia obtenerAudienciaporId(Integer idAudiencia) {
		return audienciaDAO.obtenerAudienciaporID(idAudiencia);
	}

	public void guardarAudiencia(Audiencia audiencia) {
		audienciaDAO.guardarAudiencia(audiencia);
	}

	public void borrarAudiencia(Integer idAudiencia) {
		audienciaDAO.borrarAudiencia(idAudiencia);

	}

}
