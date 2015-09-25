package gob.pe.proc.capaservicio;


import gob.pe.proc.capadatos.Escrito;

import java.util.List;

public interface EscritoService {
	public List<Escrito> obtenerListaEscrito(Escrito escrito);
	public Escrito obtenerEscritoporId(Integer idEscrito);
	public void guardarEscrito(Escrito escrito);
	public void borrarEscrito(Integer idEscrito);
}
