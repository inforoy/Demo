package gob.pe.proc.capaservicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gob.pe.proc.capaacccesodatos.DependenciaDAO;
import gob.pe.proc.capadatos.Dependencia;
import gob.pe.proc.capaservicio.DependenciaService;
@Service
public class DependenciaServiceImpl implements DependenciaService {
	@Autowired
	private DependenciaDAO dependenciaDAO;
	public Dependencia buscarDependeciaID(Integer idDependencia) {
		return dependenciaDAO.buscarDependenciaporID(idDependencia);
	}
	public List<Dependencia> obtenerListaDistritosJudicial(Dependencia dependencia) {

		return dependenciaDAO.obtenerListaDependencia(dependencia);
	}
	public void guardarDistritoJudicial(Dependencia dependencia) {
		dependenciaDAO.guardarDependencia(dependencia);
		
	}
	public void borrarDistritoJudicial(Integer idDistritoJudicial) {
		dependenciaDAO.borrarDependencia(idDistritoJudicial);
		
	}
	public Dependencia obtenerDependenciaporId(Integer idDependencia) {
		return dependenciaDAO.buscarDependenciaporID(idDependencia);
	}

}
