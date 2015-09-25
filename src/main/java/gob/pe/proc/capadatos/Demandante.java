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
@Table(name="demandante")
public class Demandante implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="incrementar")
	@SequenceGenerator(name="incrementar",sequenceName="incrementar_id_demandante",initialValue=1,allocationSize=1)
	@Column(name="id_demandante",nullable=false)
	private int idDemandante;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="proceso_nro_expediente",nullable=false)
	private Proceso proceso;
	
	@Column(name="denominacion",nullable=false)
	private String denominacion;

	@Column(name="ind_eliminado")
	private boolean ind_eliminadoDemandante;
	

	public boolean isInd_eliminadoDemandante() {
		return ind_eliminadoDemandante;
	}

	public void setInd_eliminadoDemandante(boolean ind_eliminadoDemandante) {
		this.ind_eliminadoDemandante = ind_eliminadoDemandante;
	}

	public int getIdDemandante() {
		return this.idDemandante;
	}

	public void setIdDemandante(int idDemandante) {
		this.idDemandante = idDemandante;
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
