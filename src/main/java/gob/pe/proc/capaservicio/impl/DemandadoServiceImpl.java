package gob.pe.proc.capaservicio.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gob.pe.proc.capaacccesodatos.DemandadoDAO;
import gob.pe.proc.capadatos.Demandado;
import gob.pe.proc.capaservicio.DemandadoService;
@Service
public class DemandadoServiceImpl implements DemandadoService {
	@Autowired
	private DemandadoDAO demandadoDAO;

	public List<Demandado> obtenerListaDemandado(Demandado demandado) {
		return demandadoDAO.obtenerListaDemandado(demandado);
	}

	public Demandado obtenerDemandadoporId(Integer idDemandado) {
		return demandadoDAO.obtenerDemandadoporID(idDemandado);
	}

	public void guardarDemandado(Demandado demandado) {
		demandadoDAO.guardarDemandado(demandado);

	}

	public void borrarDemandado(int idDemandado) {
		demandadoDAO.borrarDemandado(idDemandado);

	}

	public String obtenerUltimoProceso() {
		return demandadoDAO.obtenerUltimoProceso();
	}

	public Set<Demandado> obtenerListaDemandadoPorNombre(Demandado demandado) {
		return demandadoDAO.obtenerListaDemandadoPorNombre(demandado);
	}

	public List<Demandado> obtenerListaDemandadoPorNumeroExpediente(String numExpediente) {
		return demandadoDAO.obtenerListaDemandadoPorNumeroExpediente(numExpediente);
	}

	public void eliminarDemandado(int idDemandado) {
		demandadoDAO.eliminarDemandado(idDemandado);
		
	}

}
