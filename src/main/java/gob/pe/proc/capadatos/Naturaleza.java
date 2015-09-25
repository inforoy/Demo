package gob.pe.proc.capadatos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="naturaleza")
public class Naturaleza implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="incrementar")
	@SequenceGenerator(name="incrementar",sequenceName="incrementar_id_naturaleza",initialValue=1,allocationSize=1)
	@Column(name="id_naturaleza",nullable=false)
	private int idNaturaleza;
	
	@Column(name="denominacion",nullable=false)
	private String denominacion;
	
	
	@OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy="naturaleza")
	private List<Materia> materias=new ArrayList<Materia>();
	
	
	
	public List<Materia> getMaterias() {
		return materias;
	}

	public void setMaterias(List<Materia> materias) {
		this.materias = materias;
	}

	public int getIdNaturaleza() {
		return this.idNaturaleza;
	}

	public void setIdNaturaleza(int idNaturaleza) {
		this.idNaturaleza = idNaturaleza;
	}

	public String getDenominacion() {
		return this.denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	

}
