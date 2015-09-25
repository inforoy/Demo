package gob.pe.proc.capaservicio;

import gob.pe.proc.capadatos.Asignado;
import gob.pe.proc.capadatos.Proceso;
import gob.pe.proc.capadatos.Usuario;

import java.util.List;
import java.util.Set;

public interface AsignacionService {
	public Set<Proceso> obtenerListaProcesosNoAsignados(Asignado asignado);
	public Asignado obtenerAsignadoporID(String nroExpediente,String nrodni);
	public void guardarAsignado(Asignado asignado);
	public void borrarAsignado(String nroExpediente,String nrodni);
	
	public List<Usuario> listaAbogados();
	public List<Asignado> listaAbogadosProceso(Asignado asignado);
}
