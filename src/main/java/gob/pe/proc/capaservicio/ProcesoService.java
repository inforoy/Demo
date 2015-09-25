package gob.pe.proc.capaservicio;


import gob.pe.proc.capadatos.Estado;
import gob.pe.proc.capadatos.Materia;
import gob.pe.proc.capadatos.Naturaleza;
import gob.pe.proc.capadatos.Proceso;
import gob.pe.proc.capadatos.Ubigeo;

import java.util.List;
import java.util.Set;

import net.sf.jasperreports.engine.JRDataSource;

public interface ProcesoService {
	public Set<Proceso> obtenerListaProceso(Proceso proceso);
	public Set<Proceso> obtenerListaProcesoporFolder(Proceso proceso);
	public Proceso obtenerProcesoporId(String nroExpediente);

	public void guardarProceso(Proceso proceso);
	public void borrarProceso(String nroExpediente);
	
	public List<Estado> listarEstado();
	public List<Materia> listarMateria(Integer id);
	public List<Naturaleza> listarNaturaleza();
	public String contarFolderPorAnio(Integer anio);
	public Set<Proceso> obtenerUltimaTupla();
	
	public JRDataSource reporteEstadoProceso(Proceso proceso);
	public JRDataSource reporteMateriaProceso(Proceso proceso);
	public List<Ubigeo> listarUbigeo();
	public void desactivarProceso(String numExpediente);
}
