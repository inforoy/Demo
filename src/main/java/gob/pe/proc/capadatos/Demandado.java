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
@Table(name="demandado")
public class Demandado implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="incrementar")
	@SequenceGenerator(name="incrementar",sequenceName="incrementar_id_demandado",initialValue=1,allocationSize=1)
	@Column(name="id_demandado",nullable=false)
	private Integer idDemandado;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="proceso_nro_expediente",nullable=false)
	private Proceso proceso;
	
	@Column(name="denominacion",nullable=false)
	private String denominacion;
	
	@Column(name="ind_eliminado")
	private boolean ind_eliminadoDemandado;
	
	


	public boolean isInd_eliminadoDemandado() {
		return ind_eliminadoDemandado;
	}

	public void setInd_eliminadoDemandado(boolean ind_eliminadoDemandado) {
		this.ind_eliminadoDemandado = ind_eliminadoDemandado;
	}

	public Integer getIdDemandado() {
		return idDemandado;
	}

	public void setIdDemandado(Integer idDemandado) {
		this.idDemandado = idDemandado;
	}

	public Proceso getProceso() {
		return this.proceso;
	}

	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}

	public String getDenominacion() {
		return this.denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

}
