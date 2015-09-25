package gob.pe.proc.capaacccesodatos;

import gob.pe.proc.capadatos.Escrito;

import java.util.List;

public interface EscritoDAO {
	public List<Escrito> obtenerListaEscrito(Escrito escrito);
	public Escrito obtenerEscritoporID(Integer idEscrito);
	public void guardarEscrito(Escrito escrito);
	public void borrarEscrito(Integer idEscrito);
}
