package gob.pe.proc.capaservicio;

import gob.pe.proc.capadatos.Audiencia;

import java.util.List;

public interface AudienciaService {
	public List<Audiencia> obtenerListaAudiencia(Audiencia audiencia);
	public Audiencia obtenerAudienciaporId(Integer idAudiencia);
	public void guardarAudiencia(Audiencia audiencia);
	public void borrarAudiencia(Integer idAudiencia);
}
