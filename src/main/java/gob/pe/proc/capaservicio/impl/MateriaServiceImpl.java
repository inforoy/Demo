package gob.pe.proc.capaservicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gob.pe.proc.capaacccesodatos.MateriaDAO;
import gob.pe.proc.capadatos.Materia;
import gob.pe.proc.capadatos.Naturaleza;
import gob.pe.proc.capaservicio.MateriaService;
@Service
public class MateriaServiceImpl implements MateriaService {
	@Autowired
	private MateriaDAO materiaDAO;
	@Override
	public List<Materia> obtenerListaMateria(Materia materia) {
		return materiaDAO.obtenerListaMateria(materia);
	}

	@Override
	public Materia obtenerMateriaporId(Integer idMateria) {
		return materiaDAO.obtenerMateriaporID(idMateria);
	}

	@Override
	public void guardarMateria(Materia materia) {
		materiaDAO.guardarMateria(materia);

	}

	@Override
	public void borrarMateria(Integer idMateria) {
		materiaDAO.borrarMateria(idMateria);

	}
	public List<Naturaleza> obtenerListaNaturaleza(){
		return materiaDAO.obtenerListaNaturaleza();
	}

}
