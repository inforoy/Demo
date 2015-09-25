package gob.pe.proc.capaservicio.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gob.pe.proc.capaacccesodatos.NotificacionDAO;
import gob.pe.proc.capadatos.Notificacion;
import gob.pe.proc.capaservicio.NotificacionService;
@Service
public class NotificacionServiceImpl implements NotificacionService {
	@Autowired
	private NotificacionDAO notificacionDAO;

	public Set<Notificacion> obtenerListaNotificacion(Notificacion notificacion) {
		return notificacionDAO.obtenerListaNotificacion(notificacion);
	}

	public Notificacion obtenerNotificacionporId(String nroNotificacion) {
		return notificacionDAO.obtenerNotificacionporID(nroNotificacion);
	}

	public void guardarNotificacion(Notificacion notificacion) {
		if(!notificacion.isInd_eliminadoNotificacion()){
			notificacion.setInd_eliminadoNotificacion(true);
		}
		notificacionDAO.guardarNotificacion(notificacion);

	}

	public void borrarNotificacion(String nroNotificacion) {
		notificacionDAO.borrarNotificacion(nroNotificacion);

	}

	public void desactivarNotificacion(String idNotificacion) {
		notificacionDAO.desactivarNotificacion(idNotificacion);
	}

	public Set<Notificacion> obtenerListaNotificacionPorNumeroExpediente(Notificacion notificacion) {
		return notificacionDAO.obtenerListaNotificacionPorNumeroExpediente(notificacion);
	}

}
