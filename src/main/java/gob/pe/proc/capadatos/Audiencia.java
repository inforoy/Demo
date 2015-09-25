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

import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Table(name="audiencia")
public class Audiencia implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="incrementar")
	@SequenceGenerator(name="incrementar",sequenceName="incrementar_id_audiencia",initialValue=1,allocationSize=1)
	@Column(name="id_audiencia",nullable=false)
	private int idAudiencia;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="notificacion_nro_notificacion",nullable=false)
	private Notificacion notificacion;
	
	@Column(name="diligencia",nullable=false)
	private String diligencia;
	
	@Column(name="hora",nullable=false)
	private String hora;
	
	@Column(name="fecha_audiencia",nullable=false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date fechaAudiencia;
	
	@Column(name="fecha_registro",nullable=false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date fechaRegistro;
	
	@Column(name="asistencia",nullable=false)
	private boolean asistencia;
	
	@Column(name="observacion")
	private String observacion;

	@Column(name="ind_eliminado")
	private boolean ind_eliminadoAudiencia;
	
	public int getIdAudiencia() {
		return this.idAudiencia;
	}

	public void setIdAudiencia(int idAudiencia) {
		this.idAudiencia = idAudiencia;
	}

	public Notificacion getNotificacion() {
		return this.notificacion;
	}

	public void setNotificacion(Notificacion notificacion) {
		this.notificacion = notificacion;
	}

	public String getDiligencia() {
		return this.diligencia;
	}

	public void setDiligencia(String diligencia) {
		this.diligencia = diligencia;
	}

	public String getHora() {
		return this.hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public Date getFechaAudiencia() {
		return this.fechaAudiencia;
	}

	public void setFechaAudiencia(Date fechaAudiencia) {
		this.fechaAudiencia = fechaAudiencia;
	}

	public Date getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public boolean isAsistencia() {
		return this.asistencia;
	}

	public void setAsistencia(boolean asistencia) {
		this.asistencia = asistencia;
	}

	public String getObservacion() {
		return this.observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public boolean isInd_eliminadoAudiencia() {
		return ind_eliminadoAudiencia;
	}

	public void setInd_eliminadoAudiencia(boolean ind_eliminadoAudiencia) {
		this.ind_eliminadoAudiencia = ind_eliminadoAudiencia;
	}

	
}
