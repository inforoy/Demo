package gob.pe.proc.capaservicio.impl;

import java.util.List;
import java.util.Set;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import gob.pe.proc.capaacccesodatos.InstanciaDAO;
import gob.pe.proc.capadatos.Dependencia;
import gob.pe.proc.capadatos.Instancia;
import gob.pe.proc.capadatos.InstanciaId;
import gob.pe.proc.capadatos.Organojudicial;
import gob.pe.proc.capaservicio.InstanciaService;
@Service
public class InstanciaServiceImpl implements InstanciaService {
	@Autowired
	private InstanciaDAO instanciaDAO;
	
	public List<Instancia> obtenerListaInstancia(Instancia instancia) {
		return instanciaDAO.obtenerListaInstancia(instancia);
	}

	public Instancia obtenerInstanciaporID(InstanciaId instanciaId) {
		return instanciaDAO.obtenerInstanciaporID(instanciaId);
	}

	public void guardarInstancia(Instancia instancia) {
		instanciaDAO.guardarInstancia(instancia);

	}

	public void borrarInstancia(InstanciaId instanciaId) {
		borrarInstancia(instanciaId);

	}

	public List<Dependencia> listarDependencia(int id) {
		return instanciaDAO.listarDependencia(id);
	}

	public List<Organojudicial> listarOrganosJudiciales() {
		return instanciaDAO.listarOrganosJudiciales();
	}

	public JRDataSource reporteInstanciaUbigeo(Instancia instancia) {
		JRDataSource ds=null;
		Set<Instancia> reporteInstanciaUbigeo=instanciaDAO.reporteInstanciaUbigeo(instancia);
		if(!CollectionUtils.isEmpty(reporteInstanciaUbigeo)){
			ds=new JRBeanCollectionDataSource(reporteInstanciaUbigeo);	
		}
		return ds;
	}

	public JRDataSource reporteDependenciaInstancia(Instancia instancia) {
		JRDataSource ds=null;
		Set<Instancia> reporteInstanciaDependencia=instanciaDAO.reporteDependenciaInstancia(instancia);
		if(!CollectionUtils.isEmpty(reporteInstanciaDependencia)){
			ds=new JRBeanCollectionDataSource(reporteInstanciaDependencia);	
		}
		return ds;
	}

}
