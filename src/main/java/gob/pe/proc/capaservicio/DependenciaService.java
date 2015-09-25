package gob.pe.proc.capaservicio;

import java.util.List;

import gob.pe.proc.capadatos.Dependencia;

public interface DependenciaService {
	public Dependencia buscarDependeciaID(Integer idDependencia);
	public List<Dependencia> obtenerListaDistritosJudicial(Dependencia dependencia);
	public void guardarDistritoJudicial(Dependencia dependencia);
	public void borrarDistritoJudicial(Integer idDistritoJudicial);
	public Dependencia obtenerDependenciaporId(Integer idDependencia);
}
