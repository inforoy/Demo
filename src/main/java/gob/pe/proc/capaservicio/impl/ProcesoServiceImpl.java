package gob.pe.proc.capaservicio.impl;

import java.util.List;
import java.util.Set;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import gob.pe.proc.capaacccesodatos.EstadoDAO;
import gob.pe.proc.capaacccesodatos.ProcesoDAO;
import gob.pe.proc.capadatos.Estado;
import gob.pe.proc.capadatos.Materia;
import gob.pe.proc.capadatos.Naturaleza;
import gob.pe.proc.capadatos.Proceso;
import gob.pe.proc.capadatos.Ubigeo;
import gob.pe.proc.capaservicio.ProcesoService;
@Service
public class ProcesoServiceImpl implements ProcesoService {
	@Autowired
	private ProcesoDAO procesoDAO;
	@Autowired
	private EstadoDAO estadoDAO;
	

	public Set<Proceso> obtenerListaProceso(Proceso proceso) {
		return procesoDAO.obtenerListaProceso(proceso);
	}
	public Proceso obtenerProcesoporId(String nroExpediente) {
		return procesoDAO.obtenerProcesoporID(nroExpediente);
	}
	public void guardarProceso(Proceso proceso) {
		if(proceso.getSentencia()!=null && !proceso.getSentencia().equalsIgnoreCase("")){
			Estado estadoConcluido=estadoDAO.obtenerEstado(2);
			proceso.setEstado(estadoConcluido);
		}
		procesoDAO.guardarProceso(proceso);


	}
	public void borrarProceso(String nroExpediente) {
		procesoDAO.borrarProceso(nroExpediente);

	}
	public List<Estado> listarEstado() {
		return procesoDAO.listarEstado();
	}
	public List<Materia> listarMateria(Integer id) {
		return procesoDAO.listarMateria(id);
	}
	public String contarFolderPorAnio(Integer anio) {
		Integer cantidadFolder=procesoDAO.contarFolderPorAnio(anio);
		cantidadFolder++;
		String numeroFolder=""+anio+"-"+cantidadFolder;
		return numeroFolder;
	}
	public Set<Proceso> obtenerListaProcesoporFolder(Proceso proceso) {
		return procesoDAO.obtenerListaProcesoporFolder(proceso);
	}
	public Set<Proceso> obtenerUltimaTupla() {
		return procesoDAO.obtenerUltimaTupla();
	}

	public List<Naturaleza> listarNaturaleza() {
		return procesoDAO.listarNaturaleza();
	}

	public JRDataSource reporteEstadoProceso(Proceso proceso) {
		JRDataSource ds=null;
		Set<Proceso> reporteEstadoProceso=procesoDAO.reporteEstadoProceso(proceso);
		if(!CollectionUtils.isEmpty(reporteEstadoProceso)){
			ds=new JRBeanCollectionDataSource(reporteEstadoProceso);
		}
		return ds;
	}

	public JRDataSource reporteMateriaProceso(Proceso proceso) {
		JRDataSource ds=null;
		Set<Proceso> reporteMateriaProceso=procesoDAO.reporteMateriaProceso(proceso);
		if(!CollectionUtils.isEmpty(reporteMateriaProceso)){
			ds=new JRBeanCollectionDataSource(reporteMateriaProceso);	
		}
		return ds;
	}

	public List<Ubigeo> listarUbigeo() {
		return procesoDAO.listarUbigeo();
	}

	public void desactivarProceso(String numExpediente) {
		procesoDAO.desactivarProceso(numExpediente);
		
	}


}
