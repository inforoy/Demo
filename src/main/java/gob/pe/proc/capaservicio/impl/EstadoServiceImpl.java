package gob.pe.proc.capaservicio.impl;

import gob.pe.proc.capaacccesodatos.EstadoDAO;
import gob.pe.proc.capadatos.Estado;
import gob.pe.proc.capaservicio.EstadoService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class EstadoServiceImpl implements EstadoService {
	@Autowired
	private EstadoDAO estadoDAO; 
	
	public EstadoServiceImpl(){
		
	}
	public List<Estado> obtenerListaEstado(Estado estado) {
		return estadoDAO.obtenerListaEstado(estado);
	}
	
	public void guardarEstado(Estado estado) {
		estadoDAO.guardarEstado(estado);

	}
	
	@Override
	public void actualizarEstado(Estado estado) {
		estadoDAO.actualizarEstado(estado);
		
	}

	public Estado obtenerEstado(int idEstado){
		return estadoDAO.obtenerEstado(idEstado);		 
	}
	
	public void eliminarEstado(int idEstado){
		estadoDAO.eliminarEstado(idEstado);
	}
}
