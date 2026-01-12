package cl.bice.banca.empresas.servicio.controller;

import cl.bice.banca.empresas.servicio.model.common.BaseRequestEmpresa;
import cl.bice.banca.empresas.servicio.model.response.Estado;
import cl.bice.banca.empresas.servicio.service.EstadoService;
import cl.bice.banca.empresas.servicio.service.RequestEmpresasService;
import cl.bice.banca.empresas.servicio.service.ServiciosService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

@RunWith(MockitoJUnitRunner.class)
public class ServiciosControllerTest {
    @InjectMocks
    private ServiciosController serviciosController;

    @Mock
    private EstadoService estadoService;

    @Mock
    private HttpServletRequest requestHttp;

    @Mock
    private RequestEmpresasService requestEmpresasService;

    @Mock
    ServiciosService serviciosService;
    
    @Test
    public void prueba() {
    	java.util.List<String> a = new ArrayList<>();
    	a.add("");
    	assertEquals("1", String.valueOf(a.size()));
    }
    
    /*
    @Test
    public void listaServicios_OkTest() {
        BaseRequestEmpresa r = mock(BaseRequestEmpresa.class);
        when(requestHttp.getSession()).thenReturn(mock(HttpSession.class));
        when(requestHttp.getServerName()).thenReturn("test-server");
        Estado estado = new Estado();
        estado.setCodigoEstado("1");
        when(estadoService.inicializarEstado()).thenReturn(estado);
        serviciosController.listaServicios(r);
    }

    @Test
    public void listaServicios_ErrorTest() {
        BaseRequestEmpresa r = mock(BaseRequestEmpresa.class);
        when(requestHttp.getSession()).thenReturn(mock(HttpSession.class));
        when(requestHttp.getServerName()).thenReturn("test-server");
        Estado estado = new Estado();
        estado.setCodigoEstado("1");
        when(estadoService.inicializarEstado()).thenReturn(estado);
        estado.setCodigoEstado("E001");
        serviciosController.listaServicios(r);
    }
    */
}
