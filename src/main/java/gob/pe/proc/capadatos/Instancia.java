package gob.pe.proc.capadatos;


import java.util.Comparator;
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
@SuppressWarnings("rawtypes")
@Entity
@Table(name="instancia")
@AssociationOverrides({
	@AssociationOverride(name = "id.dependencia", joinColumns = @JoinColumn(name = "dependencia_id_dependencia")),
	@AssociationOverride(name = "id.proceso", joinColumns = @JoinColumn(name = "proceso_nro_expediente")) })
public class Instancia  implements Comparator {

	@EmbeddedId
	private InstanciaId id;

	@Column(name="fecha_cambio")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date fechaCambio;
	
	@Column(name="ind_eliminado")
	private boolean ind_eliminadoInstancia;
	

	public boolean isInd_eliminadoInstancia() {
		return ind_eliminadoInstancia;
	}

	public void setInd_eliminadoInstancia(boolean ind_eliminadoInstancia) {
		this.ind_eliminadoInstancia = ind_eliminadoInstancia;
	}

	public InstanciaId getId() {
		return this.id;
	}

	public void setId(InstanciaId id) {
		this.id = id;
	}

	public Date getFechaCambio() {
		return this.fechaCambio;
	}

	public void setFechaCambio(Date fechaCambio) {
		this.fechaCambio = fechaCambio;
	}
	@Transient
	public Dependencia getDependencia(){
		return getId().getDependencia();
	}
	public void setDependencia(Dependencia dependencia){
		getId().setDependencia(dependencia);
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

		Instancia that = (Instancia) o;

		if (getId() != null ? !getId().equals(that.getId())
				: that.getId() != null)
			return false;

		return true;
	}

	public int hashCode() {
		return (getId() != null ? getId().hashCode() : 0);
	}

	public int compare(Object o1, Object o2) {
		Instancia instancia1=(Instancia)o1;
		Instancia instancia2=(Instancia)o2;
		return instancia1.getFechaCambio().compareTo(instancia2.getFechaCambio());
	}

}

