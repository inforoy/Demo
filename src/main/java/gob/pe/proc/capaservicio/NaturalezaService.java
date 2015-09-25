package gob.pe.proc.capaservicio;

import gob.pe.proc.capadatos.Naturaleza;

import java.util.List;

public interface NaturalezaService {
	public List<Naturaleza> obtenerListaNaturaleza(Naturaleza naturaleza);
	public Naturaleza obtenerNaturalezaporID(Integer idNaturaleza);
	public void guardarNaturaleza(Naturaleza naturaleza);
	public void borrarNaturaleza(Integer idNaturaleza);
}
