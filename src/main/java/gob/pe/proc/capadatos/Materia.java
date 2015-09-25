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
@Table(name="materia")
public class Materia implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="incrementar")
	@SequenceGenerator(name="incrementar",sequenceName="incrementar_id_materia",initialValue=1,allocationSize=1)
	@Column(name="id_materia",nullable=false)
	private int idMateria;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="naturaleza_id_naturaleza",nullable=false)
	private Naturaleza naturaleza;
	
	@Column(name="denominacion",nullable=false)
	private String denominacion;
	

	
	public int getIdMateria() {
		return this.idMateria;
	}

	public void setIdMateria(int idMateria) {
		this.idMateria = idMateria;
	}

	public Naturaleza getNaturaleza() {
		return this.naturaleza;
	}

	public void setNaturaleza(Naturaleza naturaleza) {
		this.naturaleza = naturaleza;
	}

	public String getDenominacion() {
		return this.denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

}
