package gob.pe.proc.capadatos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class DependenciaId implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Column(name="organojudicial_id_organojudicial",nullable=false)
	private Integer organojudicialIdOrganojudicial;
	
	@Column(name="ubigeo_id_ubigeo",nullable=false)
	private String ubigeoIdUbigeo;
	
	public int getOrganojudicialIdOrganojudicial() {
		return organojudicialIdOrganojudicial;
	}
	public void setOrganojudicialIdOrganojudicial(int organojudicialIdOrganojudicial) {
		this.organojudicialIdOrganojudicial = organojudicialIdOrganojudicial;
	}
	public String getUbigeoIdUbigeo() {
		return ubigeoIdUbigeo;
	}
	public void setUbigeoIdUbigeo(String ubigeoIdUbigeo) {
		this.ubigeoIdUbigeo = ubigeoIdUbigeo;
	}

	
}
