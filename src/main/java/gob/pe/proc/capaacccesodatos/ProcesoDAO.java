package gob.pe.proc.capaacccesodatos;

import gob.pe.proc.capadatos.Estado;
import gob.pe.proc.capadatos.Materia;
import gob.pe.proc.capadatos.Naturaleza;
import gob.pe.proc.capadatos.Proceso;
import gob.pe.proc.capadatos.Ubigeo;

import java.util.List;
import java.util.Set;

public interface ProcesoDAO {
	public Set<Proceso> obtenerListaProceso(Proceso proceso);
	public Set<Proceso> obtenerListaProcesoporFolder(Proceso proceso);
	public Proceso obtenerProcesoporID(String nroExpediente);
	public void guardarProceso(Proceso proceso);
	public void borrarProceso(String nroExpediente);
	
	public List<Materia> listarMateria(Integer id);
	public List<Ubigeo> listarUbigeo();
	public List<Estado> listarEstado();
	public List<Naturaleza> listarNaturaleza();
	public int contarFolderPorAnio(Integer anio);
	public Set<Proceso> obtenerUltimaTupla();

	public Set<Proceso> reporteEstadoProceso(Proceso proceso);
	public Set<Proceso> reporteMateriaProceso(Proceso proceso);
	public void desactivarProceso(String numExpediente);
}
