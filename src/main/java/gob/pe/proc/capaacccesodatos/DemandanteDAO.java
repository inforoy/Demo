package gob.pe.proc.capaacccesodatos;

import gob.pe.proc.capadatos.Demandante;

import java.util.List;
import java.util.Set;

public interface DemandanteDAO {
	public List<Demandante> obtenerListaDemandante(Demandante demandante);
	public Demandante obtenerDemandanteporID(Integer idDemandante);
	public String obtenerUltimoProceso();
	public void guardarDemandante(Demandante demandante);
	public void borrarDemandante(Integer idDemandante);
	public List<Demandante> obtenerListaDemandantePorNumeroExpediente(String numExpediente);
	public Set<Demandante>obtenerListaDemandantePorNombre(Demandante demandante);
	public void eliminarDemandante(Integer idDemandante);
	
}
