package gob.pe.proc.capaacccesodatos;

import gob.pe.proc.capadatos.Audiencia;
import java.util.List;

public interface AudienciaDAO {
	public List<Audiencia> obtenerListaAudiencia(Audiencia audiencia);
	public Audiencia obtenerAudienciaporID(Integer idAudiencia);
	public void guardarAudiencia(Audiencia audiencia);
	public void borrarAudiencia(Integer idAudiencia);
}
