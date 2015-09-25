package gob.pe.proc.capadatos;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="perfil")
public class Perfil implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="id_perfil",nullable=false)
	private int idPerfil;
	@Column(name="denominacion",nullable=false)
	private String denominacion;


	
	public int getIdPerfil() {
		return this.idPerfil;
	}

	public void setIdPerfil(int idPerfil) {
		this.idPerfil = idPerfil;
	}

	public String getDenominacion() {
		return this.denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}



}
