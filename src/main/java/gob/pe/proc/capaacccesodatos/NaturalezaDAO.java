package gob.pe.proc.capaacccesodatos;

import gob.pe.proc.capadatos.Naturaleza;

import java.util.List;

public interface NaturalezaDAO {
	public List<Naturaleza> obtenerListaNaturaleza(Naturaleza naturaleza);
	public Naturaleza obtenerNaturalezaporID(Integer idNaturaleza);
	public void guardarNaturaleza(Naturaleza naturaleza);
	public void borrarNaturaleza(Integer idNaturaleza);
}
