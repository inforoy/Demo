package gob.pe.proc.capaservicio;

import gob.pe.proc.capadatos.Dependencia;
import gob.pe.proc.capadatos.Instancia;
import gob.pe.proc.capadatos.InstanciaId;
import gob.pe.proc.capadatos.Organojudicial;

import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;

public interface InstanciaService {
	public List<Instancia> obtenerListaInstancia(Instancia instancia);
	public Instancia obtenerInstanciaporID(InstanciaId instanciaId);
	public void guardarInstancia(Instancia instancia);
	public void borrarInstancia(InstanciaId instanciaId);
	
	public List<Dependencia> listarDependencia(int id);
	public List<Organojudicial> listarOrganosJudiciales();
	public JRDataSource reporteInstanciaUbigeo(Instancia instancia);
	public JRDataSource reporteDependenciaInstancia(Instancia instancia);
}
