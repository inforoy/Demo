package gob.pe.proc.capaservicio;

import gob.pe.proc.capadatos.Notificacion;

import java.util.Set;

public interface NotificacionService {
	public Set<Notificacion> obtenerListaNotificacion(Notificacion notificacion);
	public Notificacion obtenerNotificacionporId(String nroNotificacion);
	public void guardarNotificacion(Notificacion notificacion);
	public void borrarNotificacion(String nroNotificacion);
	public void desactivarNotificacion(String idNotificacion);
	public Set<Notificacion> obtenerListaNotificacionPorNumeroExpediente(Notificacion notificacion);
}
