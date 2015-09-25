package gob.pe.proc.capadatos;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Table(name="escrito")
public class Escrito implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="incrementar")
	@SequenceGenerator(name="incrementar",sequenceName="incrementar_id_escrito",initialValue=1,allocationSize=1)
	@Column(name="id_escrito",nullable=false)
	private int idEscrito;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="notificacion_nro_notificacion",nullable=false)
	private Notificacion notificacion;
	
	@NotNull(message="Denominacion Requerido")
	@Column(name="denominacion",nullable=false)
	private String denominacion;
	
	@Column(name="sumilla",nullable=false)
	private String sumilla;
	
	@Column(name="nro_folios",nullable=false)
	private String nroFolios;
	
	@NotNull(message="Fecha Requerido")
	@Column(name="fecha_emision",nullable=false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date fechaEmision;
	
	@Column(name="observacion")
	private String observacion;

	@Column(name="ind_eliminado")
	private boolean ind_eliminadoEscrito;
	

	public boolean isInd_eliminadoEscrito() {
		return ind_eliminadoEscrito;
	}

	public void setInd_eliminadoEscrito(boolean ind_eliminadoEscrito) {
		this.ind_eliminadoEscrito = ind_eliminadoEscrito;
	}

	public int getIdEscrito() {
		return this.idEscrito;
	}

	public void setIdEscrito(int idEscrito) {
		this.idEscrito = idEscrito;
	}

	public Notificacion getNotificacion() {
		return this.notificacion;
	}

	public void setNotificacion(Notificacion notificacion) {
		this.notificacion = notificacion;
	}

	public String getDenominacion() {
		return this.denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	public String getSumilla() {
		return this.sumilla;
	}

	public void setSumilla(String sumilla) {
		this.sumilla = sumilla;
	}

	public String getNroFolios() {
		return this.nroFolios;
	}

	public void setNroFolios(String nroFolios) {
		this.nroFolios = nroFolios;
	}

	public Date getFechaEmision() {
		return this.fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public String getObservacion() {
		return this.observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

}
