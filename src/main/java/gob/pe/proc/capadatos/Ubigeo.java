package gob.pe.proc.capadatos;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name="ubigeo")
public class Ubigeo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id_ubigeo",nullable=false)
	private String idUbigeo;
	
	@Column(name="denominacion",nullable=false)
	private String denominacion;
	
	@OneToMany(mappedBy="ubigeo")
	private Set<Dependencia> dependecias;
	
	public Set<Dependencia> getDependecias() {
		return dependecias;
	}

	public void setDependecias(Set<Dependencia> dependecias) {
		this.dependecias = dependecias;
	}

	public String getIdUbigeo() {
		return this.idUbigeo;
	}

	public void setIdUbigeo(String idUbigeo) {
		this.idUbigeo = idUbigeo;
	}

	public String getDenominacion() {
		return this.denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

}
