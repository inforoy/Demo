package gob.pe.proc.capaservicio;

import gob.pe.proc.capadatos.Demandado;

import java.util.List;
import java.util.Set;

public interface DemandadoService {
	public List<Demandado> obtenerListaDemandado(Demandado demandado);
	public Demandado obtenerDemandadoporId(Integer idDemandado);
	public String obtenerUltimoProceso();
	public void guardarDemandado(Demandado demandado);
	public void borrarDemandado(int idDemandado);
	public List<Demandado> obtenerListaDemandadoPorNumeroExpediente(String numExpediente);
	public Set<Demandado> obtenerListaDemandadoPorNombre(Demandado demandado);
	public void eliminarDemandado(int idDemandado);
}
