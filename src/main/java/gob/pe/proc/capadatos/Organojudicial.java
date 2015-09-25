package gob.pe.proc.capadatos;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
@Entity
@Table(name="organojudicial")
public class Organojudicial implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="incrementar")
	@SequenceGenerator(name="incrementar",sequenceName="incrementar_id_organojudicial",initialValue=1,allocationSize=1)
	@Column(name="id_organojudicial",nullable=false)
	private int idOrganojudicial;
	
	
	@Column(name="denominacion",nullable=false)
	private String denominacion;
	
	@OneToMany(mappedBy="organojudicial")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<Dependencia> dependecias;
	
	public Set<Dependencia> getDependecias() {
		return dependecias;
	}

	public void setDependecias(Set<Dependencia> dependecias) {
		this.dependecias = dependecias;
	}

	public int getIdOrganojudicial() {
		return this.idOrganojudicial;
	}

	public void setIdOrganojudicial(int idOrganojudicial) {
		this.idOrganojudicial = idOrganojudicial;
	}

	public String getDenominacion() {
		return this.denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

}
