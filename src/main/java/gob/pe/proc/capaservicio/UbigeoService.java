package gob.pe.proc.capaservicio;


import gob.pe.proc.capadatos.Ubigeo;

import java.util.List;

public interface UbigeoService {
	public List<Ubigeo> obtenerListaUbigeo(Ubigeo ubigeo);
	public Ubigeo obtenerUbigeoporId(String idUbigeo);
	public void guardarUbigeo(Ubigeo ubigeo);
	public void borrarUbigeo(String idUbigeo);
	public List<Ubigeo> listarUbigeo();
}
