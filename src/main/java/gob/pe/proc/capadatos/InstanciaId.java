package gob.pe.proc.capadatos;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class InstanciaId implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	private Proceso proceso;
	@ManyToOne(cascade=CascadeType.PERSIST)
	private Dependencia dependencia;

	public Proceso getProceso() {
		return proceso;
	}

	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}

	public Dependencia getDependencia() {
		return dependencia;
	}

	public void setDependencia(Dependencia dependencia) {
		this.dependencia = dependencia;
	}

	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InstanciaId that = (InstanciaId) o;

        if (dependencia != null ? !dependencia.equals(that.dependencia) : that.dependencia != null) return false;
        if (proceso != null ? !proceso.equals(that.proceso) : that.proceso != null)
            return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (dependencia != null ? dependencia.hashCode() : 0);
        result = 31 * result + (proceso != null ? proceso.hashCode() : 0);
        return result;
    }

}
