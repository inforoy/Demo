package gob.pe.proc.capadatos;

import java.util.Date;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="asignado")
@AssociationOverrides({
	@AssociationOverride(name = "id.usuario", joinColumns = @JoinColumn(name = "usuario_dni")),
	@AssociationOverride(name = "id.proceso", joinColumns = @JoinColumn(name = "proceso_nro_expediente")) })
public class Asignado implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private AsignadoId id;
	
	@Column(name="fecha_asignado",nullable=false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date fechaAsignado;
	
	@Column(name="observacion")
	private String observacion;
	
	@Column(name="ind_eliminado")
	private boolean ind_eliminadoAsignado;
	
	public AsignadoId getId() {
		return this.id;
	}

	public void setId(AsignadoId id) {
		this.id = id;
	}

	public Date getFechaAsignado() {
		return this.fechaAsignado;
	}

	public void setFechaAsignado(Date fechaAsignado) {
		this.fechaAsignado = fechaAsignado;
	}

	public String getObservacion() {
		return this.observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	
	
	public boolean isInd_eliminadoAsignado() {
		return ind_eliminadoAsignado;
	}

	public void setInd_eliminadoAsignado(boolean ind_eliminadoAsignado) {
		this.ind_eliminadoAsignado = ind_eliminadoAsignado;
	}

	@Transient
	public Usuario getUsuario(){
		return getId().getUsuario();
	}

	public void setUsuario(Usuario usuario){
		getId().setUsuario(usuario);
	}
	@Transient
	public Proceso getProceso(){
		return getId().getProceso();
	}
	public void setProceso(Proceso proceso){
		getId().setProceso(proceso);
	}
	
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Asignado that = (Asignado) o;

		if (getId() != null ? !getId().equals(that.getId())
				: that.getId() != null)
			return false;

		return true;
	}

	public int hashCode() {
		return (getId() != null ? getId().hashCode() : 0);
	}
}
