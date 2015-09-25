package gob.pe.proc.capaacccesodatos;


import gob.pe.proc.capadatos.Ubigeo;

import java.util.List;

public interface UbigeoDAO {
	public List<Ubigeo> obtenerListaUbigeo(Ubigeo ubigeo);
	public Ubigeo obtenerUbigeoporID(String idUbigeo);
	public void guardarUbigeo(Ubigeo ubigeo);
	public void borrarUbigeo(String idUbigeo);
	public List<Ubigeo> listarUbigeo();
}
