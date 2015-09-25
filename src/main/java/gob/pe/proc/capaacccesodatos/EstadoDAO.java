package gob.pe.proc.capaacccesodatos;


import gob.pe.proc.capadatos.Estado;

import java.util.List;

public interface EstadoDAO {
	public List<Estado> obtenerListaEstado(Estado estado);
	public void guardarEstado(Estado estado);
	public void actualizarEstado(Estado estado);
	public Estado obtenerEstado(int idEstado);
	public void eliminarEstado(int idEstado);
}
