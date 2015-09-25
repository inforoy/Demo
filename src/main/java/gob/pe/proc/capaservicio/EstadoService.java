package gob.pe.proc.capaservicio;

import gob.pe.proc.capadatos.Estado;

import java.util.List;

public interface EstadoService {
	public List<Estado> obtenerListaEstado(Estado estado);
	public void guardarEstado(Estado estado);
	public void actualizarEstado(Estado estado);
	public Estado obtenerEstado(int idEstado);
	public void eliminarEstado(int idEstado);
}
