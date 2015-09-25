package gob.pe.proc.capadatos;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;


@Embeddable
public class AsignadoId implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	private Proceso proceso;
	@ManyToOne(cascade=CascadeType.PERSIST)
	private Usuario usuario;
	
	public Proceso getProceso() {
		return proceso;
	}

	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AsignadoId that = (AsignadoId) o;

        if (usuario != null ? !usuario.equals(that.usuario) : that.usuario != null) return false;
        if (proceso != null ? !proceso.equals(that.proceso) : that.proceso != null)
            return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (usuario != null ? usuario.hashCode() : 0);
        result = 31 * result + (proceso != null ? proceso.hashCode() : 0);
        return result;
    }

}
