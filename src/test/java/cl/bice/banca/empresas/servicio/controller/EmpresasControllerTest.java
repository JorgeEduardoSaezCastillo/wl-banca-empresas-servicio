package cl.bice.banca.empresas.servicio.controller;

import cl.bice.banca.empresas.servicio.model.common.BaseRequestEmpresa;
import cl.bice.banca.empresas.servicio.model.request.empresas.OperacionesAprobarLiberarRequest;
import cl.bice.banca.empresas.servicio.model.request.empresas.ResumenMovimientoRequest;
import cl.bice.banca.empresas.servicio.model.response.Estado;
import cl.bice.banca.empresas.servicio.model.response.empresas.CuentasPorPerfilResponse;
import cl.bice.banca.empresas.servicio.model.response.empresas.EmpresasResponse;
import cl.bice.banca.empresas.servicio.model.response.empresas.MovimientosResponse;
import cl.bice.banca.empresas.servicio.model.response.empresas.OperacionesAprobarLiberarResponse;
import cl.bice.banca.empresas.servicio.model.response.empresas.OperacionesPendientesResponse;
import cl.bice.banca.empresas.servicio.repository.PortalOrawRepository;
import cl.bice.banca.empresas.servicio.service.CuentaService;
import cl.bice.banca.empresas.servicio.service.EstadoService;
import cl.bice.banca.empresas.servicio.service.RequestEmpresasService;
import cl.bice.banca.empresas.servicio.soap.cartola.cliente.CartolaCliente;
import cl.bice.banca.empresas.servicio.util.MapperUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EmpresasControllerTest {

    @InjectMocks
    private EmpresasController empresasController;

    @Mock
    private HttpServletRequest requestHttp;

    @Mock
    private EstadoService estadoService;

    @Mock
    private RequestEmpresasService reqEmpresasService;

    @Mock
    private PortalOrawRepository portalOrawRepository;

    @Mock
    private CuentaService cuentasService;

    @Mock
    private CartolaCliente cartolaCliente;

    @Test
    public void empresasControllerObtenerEmpresas_Test () {
        BaseRequestEmpresa r = mock(BaseRequestEmpresa.class);
        when(requestHttp.getSession()).thenReturn(mock(HttpSession.class));
        when(requestHttp.getServerName()).thenReturn("test-server");
        Estado estado = new Estado();
        estado.setCodigoEstado("1");
        when(estadoService.inicializarEstado()).thenReturn(estado);
        ResponseEntity<EmpresasResponse> res = empresasController.empresasControllerObtenerEmpresas(r);
        Assert.assertEquals(HttpStatus.OK, res.getStatusCode());
    }

    @Test
    public void empresasControllerObtenerResumenOperaciones_Test () {
        BaseRequestEmpresa r = mock(BaseRequestEmpresa.class);
        when(requestHttp.getSession()).thenReturn(mock(HttpSession.class));
        when(requestHttp.getServerName()).thenReturn("test-server");
        Estado estado = new Estado();
        estado.setCodigoEstado("1");
        when(estadoService.inicializarEstado()).thenReturn(estado);
        ResponseEntity<OperacionesPendientesResponse> res =  empresasController.empresasControllerObtenerResumenOperaciones(r);
        Assert.assertEquals(HttpStatus.OK, res.getStatusCode());
    }

    @Test
    public void empresasControllerObtenerCuentasPorPerfil_Test () {
        BaseRequestEmpresa r = mock(BaseRequestEmpresa.class);
        when(requestHttp.getSession()).thenReturn(mock(HttpSession.class));
        when(requestHttp.getServerName()).thenReturn("test-server");
        Estado estado = new Estado();
        estado.setCodigoEstado("1");
        when(estadoService.inicializarEstado()).thenReturn(estado);
        ResponseEntity<CuentasPorPerfilResponse> res =  empresasController.empresasControllerObtenerCuentasPorPerfil(r);
        Assert.assertEquals(HttpStatus.OK, res.getStatusCode());
    }

    @Test
    public void listaResumenMovimiento_Test () {
        ResumenMovimientoRequest r = new ResumenMovimientoRequest();
        r.setCuenta("423433");
        when(requestHttp.getSession()).thenReturn(mock(HttpSession.class));
        when(requestHttp.getServerName()).thenReturn("test-server");
        Estado estado = new Estado();
        estado.setCodigoEstado("1");
        when(estadoService.inicializarEstado()).thenReturn(estado);
        ResponseEntity<MovimientosResponse> res =  empresasController.listaResumenMovimiento(r);
        Assert.assertEquals(HttpStatus.OK, res.getStatusCode());
    }
}
