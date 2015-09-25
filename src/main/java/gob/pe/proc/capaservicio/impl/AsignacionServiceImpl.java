package gob.pe.proc.capaservicio.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gob.pe.proc.capaacccesodatos.AsignadoDAO;
import gob.pe.proc.capadatos.Asignado;
import gob.pe.proc.capadatos.Proceso;
import gob.pe.proc.capadatos.Usuario;
import gob.pe.proc.capaservicio.AsignacionService;
@Service
public class AsignacionServiceImpl implements AsignacionService {
	@Autowired
	private AsignadoDAO asignadoDAO;

	public Set<Proceso> obtenerListaProcesosNoAsignados(Asignado asignado) {
		return asignadoDAO.obtenerListaProcesosNoAsignados(asignado);
	}
	public Asignado obtenerAsignadoporID(String nroExpediente, String nrodni) {
		return asignadoDAO.obtenerAsignadoporID(nroExpediente, nrodni);
	}
	public void guardarAsignado(Asignado asignado) {
		asignadoDAO.guardarAsignado(asignado);
	}
	public void borrarAsignado(String nroExpediente, String nrodni) {
		asignadoDAO.borrarAsignado(nroExpediente, nrodni);
	}
	public List<Usuario> listaAbogados() {
		return asignadoDAO.listaAbogados();
	}
	public List<Asignado> listaAbogadosProceso(Asignado asignado) {
		return asignadoDAO.listaAbogadosProceso(asignado);
	}

}
