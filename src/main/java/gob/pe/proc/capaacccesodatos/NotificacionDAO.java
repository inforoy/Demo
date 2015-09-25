package gob.pe.proc.capaacccesodatos;

import gob.pe.proc.capadatos.Notificacion;

import java.util.Set;

public interface NotificacionDAO {
	public Set<Notificacion> obtenerListaNotificacion(Notificacion notificacion);
	public Notificacion obtenerNotificacionporID(String idNotificacion);
	public void guardarNotificacion(Notificacion notificacion);
	public void borrarNotificacion(String idNotificacion);
	public void desactivarNotificacion(String idNotificacion);
	public Set<Notificacion> obtenerListaNotificacionPorNumeroExpediente(Notificacion notificacion);
}
