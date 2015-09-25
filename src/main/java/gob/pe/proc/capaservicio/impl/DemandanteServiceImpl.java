package gob.pe.proc.capaservicio.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gob.pe.proc.capaacccesodatos.DemandanteDAO;
import gob.pe.proc.capadatos.Demandante;
import gob.pe.proc.capaservicio.DemandanteService;
@Service
public class DemandanteServiceImpl implements DemandanteService {
	@Autowired
	private DemandanteDAO demandanteDAO;

	public List<Demandante> obtenerListaDemandante(Demandante demandante) {
		return demandanteDAO.obtenerListaDemandante(demandante);
	}

	public Demandante obtenerDemandanteporId(Integer idDemandante) {
		return demandanteDAO.obtenerDemandanteporID(idDemandante);
	}

	public void guardarDemandante(Demandante demandante) {
		demandanteDAO.guardarDemandante(demandante);

	}

	public void borrarDemandante(Integer idDemandante) {
		demandanteDAO.borrarDemandante(idDemandante);

	}

	public String obtenerUltimoProceso() {
		return demandanteDAO.obtenerUltimoProceso();
	}

	public Set<Demandante> obtenerListaDemandantePorNombre(Demandante demandante) {
		return demandanteDAO.obtenerListaDemandantePorNombre(demandante);
	}

	public List<Demandante> obtenerListaDemandantePorNumeroExpediente(String numExpediente) {
		return demandanteDAO.obtenerListaDemandantePorNumeroExpediente(numExpediente);
	}

	public void eliminarDemandante(Integer idDemandante) {
		demandanteDAO.eliminarDemandante(idDemandante);
	}

}
