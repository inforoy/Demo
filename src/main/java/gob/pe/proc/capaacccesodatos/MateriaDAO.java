package gob.pe.proc.capaacccesodatos;

import gob.pe.proc.capadatos.Materia;
import gob.pe.proc.capadatos.Naturaleza;

import java.util.List;

public interface MateriaDAO {
	public List<Materia> obtenerListaMateria(Materia materia);
	public Materia obtenerMateriaporID(Integer idMateria);
	public void guardarMateria(Materia materia);
	public void borrarMateria(Integer idMateria);
	public List<Naturaleza> obtenerListaNaturaleza();
}
