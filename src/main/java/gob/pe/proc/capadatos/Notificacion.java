package gob.pe.proc.capadatos;


import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Table(name="notificacion")
public class Notificacion implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="nro_notificacion",nullable=false)
	private String nroNotificacion;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="proceso_nro_expediente",nullable=false)
	private Proceso proceso;
	
	@Column(name="nro_resolucion",nullable=false)
	private String nroResolucion;
	
	@Column(name="nro_fojas",nullable=false)
	private String nroFojas;
	
	@Column(name="anexo",nullable=false)
	private String anexo;
	
	@Column(name="fecha_resolucion",nullable=false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date fechaResolucion;
	
	@Column(name="fecha_ingreso",nullable=false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date fechaIngreso;
	
	@OneToMany(mappedBy="notificacion")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<Audiencia> audiencias;
	
	@OneToMany(mappedBy="notificacion")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<Escrito> escritos;
	
	@Column(name="ind_eliminado")
	private boolean ind_eliminadoNotificacion;
	
	@Transient
	private String componenteUnoNroNotificacion;
	
	@Transient
	private String componenteDosNroNotificacion;
	
	@Transient
	private String componenteTresNroNotificacion;
	
	@Transient
	private String componenteCuatroNroNotificacion;
	
	
	public Notificacion() {
	}

	public Notificacion(Proceso proceso) {
		this.proceso = proceso;
	}

	public boolean isInd_eliminadoNotificacion() {
		return ind_eliminadoNotificacion;
	}

	public void setInd_eliminadoNotificacion(boolean ind_eliminadoNotificacion) {
		this.ind_eliminadoNotificacion = ind_eliminadoNotificacion;
	}

	public Set<Audiencia> getAudiencias() {
		return audiencias;
	}

	public void setAudiencias(Set<Audiencia> audiencias) {
		this.audiencias = audiencias;
	}

	public Set<Escrito> getEscritos() {
		return escritos;
	}

	public void setEscritos(Set<Escrito> escritos) {
		this.escritos = escritos;
	}

	public Proceso getProceso() {
		return this.proceso;
	}

	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}

	public String getNroNotificacion() {
		return this.nroNotificacion;
	}

	public void setNroNotificacion(String nroNotificacion) {
		this.nroNotificacion = nroNotificacion;
	}

	public String getNroResolucion() {
		return this.nroResolucion;
	}

	public void setNroResolucion(String nroResolucion) {
		this.nroResolucion = nroResolucion;
	}

	public String getNroFojas() {
		return this.nroFojas;
	}

	public void setNroFojas(String nroFojas) {
		this.nroFojas = nroFojas;
	}

	public String getAnexo() {
		return this.anexo;
	}

	public void setAnexo(String anexo) {
		this.anexo = anexo;
	}

	public Date getFechaResolucion() {
		return this.fechaResolucion;
	}

	public void setFechaResolucion(Date fechaResolucion) {
		this.fechaResolucion = fechaResolucion;
	}

	public Date getFechaIngreso() {
		return this.fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	/**********************************************************************************************/
	
	@Transient
	private int tipoDoc;



	public int getTipoDoc() {
		return tipoDoc;
	}

	public void setTipoDoc(int tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	public String getComponenteUnoNroNotificacion() {
		return componenteUnoNroNotificacion;
	}

	public void setComponenteUnoNroNotificacion(String componenteUnoNroNotificacion) {
		this.componenteUnoNroNotificacion = componenteUnoNroNotificacion;
	}

	public String getComponenteDosNroNotificacion() {
		return componenteDosNroNotificacion;
	}

	public void setComponenteDosNroNotificacion(String componenteDosNroNotificacion) {
		this.componenteDosNroNotificacion = componenteDosNroNotificacion;
	}

	public String getComponenteTresNroNotificacion() {
		return componenteTresNroNotificacion;
	}

	public void setComponenteTresNroNotificacion(
			String componenteTresNroNotificacion) {
		this.componenteTresNroNotificacion = componenteTresNroNotificacion;
	}

	public String getComponenteCuatroNroNotificacion() {
		return componenteCuatroNroNotificacion;
	}

	public void setComponenteCuatroNroNotificacion(
			String componenteCuatroNroNotificacion) {
		this.componenteCuatroNroNotificacion = componenteCuatroNroNotificacion;
	}
}
