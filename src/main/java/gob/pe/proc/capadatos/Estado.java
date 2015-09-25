package gob.pe.proc.capadatos;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name="estado")
public class Estado implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="incrementar")
	@SequenceGenerator(name="incrementar",sequenceName="incrementar_id_estado",initialValue=1,allocationSize=1)
	@Column(name="id_estado",nullable=false)
	
	private int idEstado;
	@Column(name="denominacion",nullable=false)
	private String denominacion;

	public int getIdEstado() {
		return this.idEstado;
	}

	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}

	public String getDenominacion() {
		return this.denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

}
