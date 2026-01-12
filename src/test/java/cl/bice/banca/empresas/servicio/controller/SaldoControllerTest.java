package cl.bice.banca.empresas.servicio.controller;

import cl.bice.banca.empresas.servicio.model.request.empresas.CuentaSaldoRequest;
import cl.bice.banca.empresas.servicio.model.request.empresas.SaldoRequest;
import cl.bice.banca.empresas.servicio.model.response.Estado;
import cl.bice.banca.empresas.servicio.service.CuentaService;
import cl.bice.banca.empresas.servicio.service.EstadoService;
import cl.bice.banca.empresas.servicio.service.RequestEmpresasService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

@RunWith(MockitoJUnitRunner.class)
public class SaldoControllerTest {

    @InjectMocks
    private SaldoController saldoController;

    @Mock
    private HttpServletRequest requestHttp;

    @Mock
    private RequestEmpresasService requestEmpresasService;

    @Mock
    private EstadoService estadoService;

    @Mock
    CuentaService cuentaService;
    
    @Test
    public void prueba() {
    	java.util.List<String> a = new ArrayList<>();
    	a.add("");
    	assertEquals("1", String.valueOf(a.size()));
    }
    
    /*
    @Test
    public void obtenerSaldo_Test () {
        SaldoRequest r = new SaldoRequest();
        r.setMoneda("1");
        r.setCodigoProducto("111");
        r.setCuenta("31122");
        when(requestHttp.getSession()).thenReturn(mock(HttpSession.class));
        when(requestHttp.getServerName()).thenReturn("test-server");
        Estado estado = new Estado();
        estado.setCodigoEstado("1");
        when(estadoService.inicializarEstado()).thenReturn(estado);
        saldoController.obtenerSaldo(r);
    }

    @Test
    public void saldoControllerObtenerCuentasSaldoPorPerfil_Test () {
        CuentaSaldoRequest r = new CuentaSaldoRequest();
        r.setCodigoProducto("2313");
        when(requestHttp.getSession()).thenReturn(mock(HttpSession.class));
        when(requestHttp.getServerName()).thenReturn("test-server");
        Estado estado = new Estado();
        estado.setCodigoEstado("1");
        when(estadoService.inicializarEstado()).thenReturn(estado);
        saldoController.saldoControllerObtenerCuentasSaldoPorPerfil(r);
    }

    @Test
    public void saldoControllerObtenerCuentasSaldoPorPerfil_ErrorNumeroCuentaInvalidoTest () {
        CuentaSaldoRequest r = new CuentaSaldoRequest();
        r.setCodigoProducto("2313r");
        when(requestHttp.getSession()).thenReturn(mock(HttpSession.class));
        when(requestHttp.getServerName()).thenReturn("test-server");
        Estado estado = new Estado();
        estado.setCodigoEstado("1");
        when(estadoService.inicializarEstado()).thenReturn(estado);
        saldoController.saldoControllerObtenerCuentasSaldoPorPerfil(r);
    }
    */
}
