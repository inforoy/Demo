package gob.pe.proc.capadatos;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="dependencia")
public class Dependencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="incrementar")
	@SequenceGenerator(name="incrementar",sequenceName="incrementar_id_dependencia",initialValue=1,allocationSize=1)
	@Column(name="id_dependencia",nullable=false)
	private Integer idDependencia;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="organojudicial_id_organojudicial",nullable=false)
	private Organojudicial organojudicial;
	
	@Column(name="denominacion",nullable=false)
	private String denominacion;
	
	@Column(name="magistrado",nullable=false)
	private String magistrado;
	
	@Column(name="secretario",nullable=false)
	private String secretario;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="ubigeo_id_ubigeo",nullable=false)
	private Ubigeo ubigeo;

	
	public Integer getIdDependencia() {
		return idDependencia;
	}

	public void setIdDependencia(Integer idDependencia) {
		this.idDependencia = idDependencia;
	}

	public Ubigeo getUbigeo() {
		return ubigeo;
	}

	public void setUbigeo(Ubigeo ubigeo) {
		this.ubigeo = ubigeo;
	}

	public Organojudicial getOrganojudicial() {
		return this.organojudicial;
	}

	public void setOrganojudicial(Organojudicial organojudicial) {
		this.organojudicial = organojudicial;
	}

	public String getDenominacion() {
		return this.denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	public String getMagistrado() {
		return this.magistrado;
	}

	public void setMagistrado(String magistrado) {
		this.magistrado = magistrado;
	}

	public String getSecretario() {
		return this.secretario;
	}

	public void setSecretario(String secretario) {
		this.secretario = secretario;
	}

}
