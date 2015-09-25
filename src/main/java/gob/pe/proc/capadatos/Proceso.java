package gob.pe.proc.capadatos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;
import java.util.List;

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
@Table(name="proceso")
public class Proceso implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="nro_expediente",nullable=false)
	private String nroExpediente;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="materia_id_materia",nullable=false)
	private Materia materia;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="estado_id_estado",nullable=false)
	private Estado estado;
	
	@Column(name="nro_folder",nullable=false)
	private String nroFolder;
	
	@Column(name="anio",nullable=false)
	private Integer anio;
	
	@Column(name="cuantia",nullable=false)
	private String cuantia;
	
	@Column(name="pretension",nullable=false)
	private String pretension;
	
	@Column(name="sentencia")
	private String sentencia;
	
	@Column(name="fecha_inicio")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date fechaInicio;
	
	@Column(name="fecha_fin")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date fechaFin;
	
	@Column(name="cuaderno",nullable=false)
	private Integer cuaderno;
	
	@OneToMany(mappedBy="proceso",cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<Demandado> demandado;
	
	@OneToMany(mappedBy="proceso",cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<Demandante> demandante;
	
	@OneToMany(mappedBy="id.proceso",cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<Asignado> asignados;
	
	@OneToMany(mappedBy="proceso",cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<Notificacion> notificaciones;
	
	@OneToMany(mappedBy="id.proceso",cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<Instancia> instancias;
	
	//variables que componen el numero de expediente ####-####-#-####-#####
	@Transient
	private String opcion;
	
	@Transient
	private String txtBuscqueda;
	
	@Transient
	private String componenteUnoNroExpediente;
	
	@Transient
	private String componenteDosNroExpediente;
	
	@Transient
	private String componenteTresNroExpediente;
	
	@Transient
	private String componenteCuatroNroExpediente;
	
	@Transient
	private String componenteCincoNroExpediente;
	
	@Transient
	private String componenteSeisNroExpediente;
	
	@Transient
	private String componenteSieteNroExpediente;
	
	@Transient
	private List<Demandado> nuevoDemandado=new ArrayList<Demandado>();
	
	@Transient
	private List<Demandante> nuevoDemandante=new ArrayList<Demandante>();

	
	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Set<Asignado> getAsignados() {
		return asignados;
	}

	public void setAsignados(Set<Asignado> asignados) {
		this.asignados = asignados;
	}

	public Set<Notificacion> getNotificaciones() {
		return notificaciones;
	}

	public void setNotificaciones(Set<Notificacion> notificaciones) {
		this.notificaciones = notificaciones;
	}

	public Set<Instancia> getInstancias() {
		return instancias;
	}

	public void setInstancias(Set<Instancia> instancias) {
		this.instancias = instancias;
	}

	public Set<Demandante> getDemandante() {
		return demandante;
	}

	public void setDemandante(Set<Demandante> demandante) {
		this.demandante = demandante;
	}

	public Set<Demandado> getDemandado() {
		return demandado;
	}

	public void setDemandado(Set<Demandado> demandado) {
		this.demandado = demandado;
	}

	public Integer getCuaderno() {
		return cuaderno;
	}

	public void setCuaderno(Integer cuaderno) {
		this.cuaderno = cuaderno;
	}

	public String getNroExpediente() {
		return this.nroExpediente;
	}

	public void setNroExpediente(String nroExpediente) {
		this.nroExpediente = nroExpediente;
	}

	public Materia getMateria() {
		return this.materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	public String getNroFolder() {
		return this.nroFolder;
	}

	public void setNroFolder(String nroFolder) {
		this.nroFolder = nroFolder;
	}

	public Integer getAnio() {
		return this.anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	public String getCuantia() {
		return this.cuantia;
	}

	public void setCuantia(String cuantia) {
		this.cuantia = cuantia;
	}

	public String getPretension() {
		return this.pretension;
	}

	public void setPretension(String pretension) {
		this.pretension = pretension;
	}

	public String getSentencia() {
		return this.sentencia;
	}

	public void setSentencia(String sentencia) {
		this.sentencia = sentencia;
	}

	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getComponenteUnoNroExpediente() {
		return componenteUnoNroExpediente;
	}

	public void setComponenteUnoNroExpediente(String componenteUnoNroExpediente) {
		this.componenteUnoNroExpediente = componenteUnoNroExpediente;
	}

	public String getComponenteDosNroExpediente() {
		return componenteDosNroExpediente;
	}

	public void setComponenteDosNroExpediente(String componenteDosNroExpediente) {
		this.componenteDosNroExpediente = componenteDosNroExpediente;
	}

	public String getComponenteTresNroExpediente() {
		return componenteTresNroExpediente;
	}

	public void setComponenteTresNroExpediente(String componenteTresNroExpediente) {
		this.componenteTresNroExpediente = componenteTresNroExpediente;
	}

	public String getComponenteCuatroNroExpediente() {
		return componenteCuatroNroExpediente;
	}

	public void setComponenteCuatroNroExpediente(
			String componenteCuatroNroExpediente) {
		this.componenteCuatroNroExpediente = componenteCuatroNroExpediente;
	}

	public String getComponenteCincoNroExpediente() {
		return componenteCincoNroExpediente;
	}

	public void setComponenteCincoNroExpediente(String componenteCincoNroExpediente) {
		this.componenteCincoNroExpediente = componenteCincoNroExpediente;
	}
	
	
	public String getComponenteSeisNroExpediente() {
		return componenteSeisNroExpediente;
	}

	public void setComponenteSeisNroExpediente(String componenteSeisNroExpediente) {
		this.componenteSeisNroExpediente = componenteSeisNroExpediente;
	}

	public String getComponenteSieteNroExpediente() {
		return componenteSieteNroExpediente;
	}

	public void setComponenteSieteNroExpediente(String componenteSieteNroExpediente) {
		this.componenteSieteNroExpediente = componenteSieteNroExpediente;
	}

	public List<Demandado> getNuevoDemandado() {
		return nuevoDemandado;
	}

	public void setNuevoDemandado(List<Demandado> nuevoDemandado) {
		this.nuevoDemandado = nuevoDemandado;
	}

	public List<Demandante> getNuevoDemandante() {
		return nuevoDemandante;
	}

	public void setNuevoDemandante(List<Demandante> nuevoDemandante) {
		this.nuevoDemandante = nuevoDemandante;
	}
		
	public String getOpcion() {
		return opcion;
	}

	public void setOpcion(String opcion) {
		this.opcion = opcion;
	}

	public String getTxtBuscqueda() {
		return txtBuscqueda;
	}

	public void setTxtBuscqueda(String txtBuscqueda) {
		this.txtBuscqueda = txtBuscqueda;
	}
	
}
