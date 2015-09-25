package gob.pe.proc.capaservicio.test;

import static org.mockito.Mockito.*;

import java.util.HashSet;
import java.util.Set;

import junit.framework.Assert;
import gob.pe.proc.capaacccesodatos.NotificacionDAO;
import gob.pe.proc.capadatos.Notificacion;
import gob.pe.proc.capaservicio.NotificacionService;
import gob.pe.proc.capaservicio.impl.NotificacionServiceImpl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.util.CollectionUtils;
@RunWith(MockitoJUnitRunner.class)
public class NotificacionServiceTest {

	private Notificacion notificacion;
	
	@InjectMocks
	private NotificacionService notificacionService=new NotificacionServiceImpl();
	
	@Mock
	private NotificacionDAO notificacionDAO;
	
	@Before
	public void inicio(){
		notificacion=new Notificacion();
		notificacion.setNroNotificacion("2015-568978-JR-CI");
		
		Set<Notificacion> todasNotificaciones= new HashSet<Notificacion>();
		todasNotificaciones.add(notificacion);
		
		when(notificacionDAO.obtenerListaNotificacion((Notificacion)anyObject())).thenReturn(todasNotificaciones);
		when(notificacionDAO.obtenerNotificacionporID(anyString())).thenReturn(notificacion);
	}
	
	@Test
	public void obtenerListaNotificacionTest(){
		Set<Notificacion> todasNotificaciones=notificacionService.obtenerListaNotificacion(notificacion);
		
		Assert.assertFalse(CollectionUtils.isEmpty(todasNotificaciones));		
	}
	
	@Test
	public void obtenerNotificacionporIdTest(){
		Notificacion resultadoNotificacion=notificacionService.obtenerNotificacionporId("2015-568978-JR-CI");
		
		Assert.assertTrue(resultadoNotificacion.getNroNotificacion().equalsIgnoreCase("2015-568978-JR-CI"));
	}
	
	@Test
	public void guardarNotificacionTest(){
		notificacionService.guardarNotificacion(notificacion);
		
		verify(notificacionDAO,times(1)).guardarNotificacion(notificacion);
	}
}
