package gob.pe.proc.capaservicio.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.HashSet;
import java.util.Set;

import junit.framework.Assert;
import gob.pe.proc.capaacccesodatos.AsignadoDAO;
import gob.pe.proc.capadatos.Asignado;
import gob.pe.proc.capadatos.AsignadoId;
import gob.pe.proc.capadatos.Proceso;
import gob.pe.proc.capadatos.Usuario;
import gob.pe.proc.capaservicio.AsignacionService;
import gob.pe.proc.capaservicio.impl.AsignacionServiceImpl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.util.CollectionUtils;
@RunWith(MockitoJUnitRunner.class)
public class AsignacionServiceTest {
	private Asignado asignado;
	private AsignadoId asignadoId;
	private Proceso proceso;
	private Usuario usuario;
	
	@InjectMocks
	private AsignacionService asignacionService=new AsignacionServiceImpl();
	@Mock
	private AsignadoDAO asignadoDAO;
	
	@Before
	public void inicio(){
		proceso=new Proceso();
		proceso.setNroExpediente("2013-01030-0-0501-JR-CA-1");
		
		usuario=new Usuario();
		usuario.setDni("43610836");
		
		asignadoId=new AsignadoId();
		asignadoId.setProceso(proceso);
		asignadoId.setUsuario(usuario);
		
		asignado=new Asignado();
		asignado.setId(asignadoId);		
		
		Set<Proceso> listaAsignaciones=new HashSet<Proceso>();
		listaAsignaciones.add(proceso);
		
		when(asignadoDAO.obtenerListaProcesosNoAsignados((Asignado)anyObject())).thenReturn(listaAsignaciones);
		when(asignadoDAO.obtenerAsignadoporID(anyString(), anyString())).thenReturn(asignado);
	}
	
	@Test
	public void obtenerListaProcesosNoAsignadosTest() {
		Set<Proceso> listaAsignaciones= asignacionService.obtenerListaProcesosNoAsignados(asignado);
		
		Assert.assertFalse(CollectionUtils.isEmpty(listaAsignaciones));
	}

	@Test
	public void obtenerAsignadoporIDTest(){
		Asignado resultadoAsignado=asignacionService.obtenerAsignadoporID("2013-01030-0-0501-JR-CA-1", "43610836");
		
		Assert.assertTrue(resultadoAsignado.getId().getUsuario().getDni().equals("43610836"));
		Assert.assertTrue(resultadoAsignado.getId().getProceso().getNroExpediente().equals("2013-01030-0-0501-JR-CA-1"));
	}
	
	@Test
	public void guardarAsignadoTest(){
		asignacionService.guardarAsignado(asignado);
		
		verify(asignadoDAO,times(1)).guardarAsignado(asignado);
	}
	
	
}
