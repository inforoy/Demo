package gob.pe.proc.capaacccesodatos;

import gob.pe.proc.capadatos.Dependencia;
import gob.pe.proc.capadatos.Instancia;
import gob.pe.proc.capadatos.InstanciaId;
import gob.pe.proc.capadatos.Organojudicial;

import java.util.List;
import java.util.Set;

public interface InstanciaDAO {
	public List<Instancia> obtenerListaInstancia(Instancia instancia);
	public Instancia obtenerInstanciaporID(InstanciaId instanciaId);
	public void guardarInstancia(Instancia instancia);
	public void borrarInstancia(InstanciaId instanciaId);
	

	public List<Dependencia> listarDependencia(int id);
	public List<Organojudicial> listarOrganosJudiciales();
	public Set<Instancia> reporteInstanciaUbigeo(Instancia instancia);
	public Set<Instancia> reporteDependenciaInstancia(Instancia instancia);
}
