package gob.pe.proc.capaservicio.test;

import static org.mockito.Mockito.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.util.CollectionUtils;

import gob.pe.proc.capaacccesodatos.EstadoDAO;
import gob.pe.proc.capaacccesodatos.ProcesoDAO;
import gob.pe.proc.capadatos.Estado;
import gob.pe.proc.capadatos.Proceso;
import gob.pe.proc.capaservicio.ProcesoService;
import gob.pe.proc.capaservicio.impl.ProcesoServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ProcesoServiceTest {
	
	private Proceso proceso;
	
	@InjectMocks
	private ProcesoService procesoService=new ProcesoServiceImpl();  
	
	@Mock
	private ProcesoDAO procesoDAO;
	
	@Mock
	private EstadoDAO estadoDAO;
	
	@Before
	public void inicio(){
		proceso=new Proceso();
		proceso.setNroExpediente("2013-01030-0-0501-JR-CA-1");
		proceso.setSentencia("Sentenciado");
		
		Set<Proceso> todosLosProceso=new HashSet<Proceso>();
		todosLosProceso.add(proceso);
		
		Estado estado=new Estado();
		estado.setIdEstado(2);
		estado.setDenominacion("Concluido");
		
		when(procesoDAO.obtenerListaProceso((Proceso)anyObject())).thenReturn(todosLosProceso);
		when(procesoDAO.obtenerProcesoporID(anyString())).thenReturn(proceso);
		when(estadoDAO.obtenerEstado(2)).thenReturn(estado);
		
	}
	
	@Test
	public void obtenerListaProcesoTest() {
		Set<Proceso> todosLosProceso=procesoService.obtenerListaProceso(proceso);
		
		Assert.assertNotNull(todosLosProceso);
		Assert.assertFalse(CollectionUtils.isEmpty(todosLosProceso));
	}
	
	@Test
	public void obtenerProcesoporIdTest(){
		Proceso resultadoProceso=procesoService.obtenerProcesoporId("2013-01030-0-0501-JR-CA-1");
		
		Assert.assertTrue(resultadoProceso.getNroExpediente().equals("2013-01030-0-0501-JR-CA-1"));
	}

	@Test
	public void guardarProcesoTest(){
		//procesoService.guardarProceso(proceso);
		//verify(estadoDAO,times(1)).obtenerEstado(3);
		//verify(procesoDAO,times(1)).guardarProceso(proceso);
	}
	
	@Test
	public void borrarProcesoTest(){
		procesoService.borrarProceso("2013-01030-0-0501-JR-CA-1");
		verify(procesoDAO, times(1)).borrarProceso("2013-01030-0-0501-JR-CA-1");
	}
}
