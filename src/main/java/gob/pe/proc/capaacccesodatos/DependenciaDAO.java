package gob.pe.proc.capaacccesodatos;
import gob.pe.proc.capadatos.Dependencia;

import java.util.List;

public interface DependenciaDAO {
	public List<Dependencia> obtenerListaDependencia(Dependencia dependencia);
	public void guardarDependencia(Dependencia dependencia);
	public void borrarDependencia(Integer idOrganoJudicial);
	public Dependencia buscarDependenciaporID(Integer idDependencia);

}
