package gob.pe.proc.model;

import gob.pe.proc.capadatos.Demandado;
import gob.pe.proc.capadatos.Demandante;
import gob.pe.proc.capadatos.Instancia;
import gob.pe.proc.capadatos.Proceso;

public class CrearExpedienteBean {
	private Proceso proceso;
	private Demandado demandado;
	private Demandante demandante;
	private Instancia instancia;
	public Proceso getProceso() {
		return proceso;
	}
	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}
	public Demandado getDemandado() {
		return demandado;
	}
	public void setDemandado(Demandado demandado) {
		this.demandado = demandado;
	}
	public Demandante getDemandante() {
		return demandante;
	}
	public void setDemandante(Demandante demandante) {
		this.demandante = demandante;
	}
	public Instancia getInstancia() {
		return instancia;
	}
	public void setInstancia(Instancia instancia) {
		this.instancia = instancia;
	}
	
	
}
