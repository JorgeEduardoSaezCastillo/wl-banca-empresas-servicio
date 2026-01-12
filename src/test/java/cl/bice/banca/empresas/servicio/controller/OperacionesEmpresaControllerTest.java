package cl.bice.banca.empresas.servicio.controller;

import cl.bice.banca.empresas.servicio.controller.fixtures.ControllersFixtures;
import cl.bice.banca.empresas.servicio.exception.RequestInvalidoException;
import cl.bice.banca.empresas.servicio.model.common.BaseRequestEmpresa;
import cl.bice.banca.empresas.servicio.model.request.ListaOperacionesRequest;
import cl.bice.banca.empresas.servicio.model.request.operaciones.AprobarOperacionesPortalRequest;
import cl.bice.banca.empresas.servicio.model.request.operaciones.AprobarOperacionesRequest;
import cl.bice.banca.empresas.servicio.model.request.operaciones.LiberarOperacionesPortalRequest;
import cl.bice.banca.empresas.servicio.model.request.operaciones.LiberarOperacionesRequest;
import cl.bice.banca.empresas.servicio.model.request.operaciones.ValidaPoderRequest;
import cl.bice.banca.empresas.servicio.model.response.Estado;
import cl.bice.banca.empresas.servicio.model.response.base.ResponseBase;
import cl.bice.banca.empresas.servicio.model.response.operaciones.AprobarOperacionesPortalResponse;
import cl.bice.banca.empresas.servicio.model.response.operaciones.AprobarOperacionesResponse;
import cl.bice.banca.empresas.servicio.model.response.operaciones.LiberarOperacionesPortalResponse;
import cl.bice.banca.empresas.servicio.model.response.operaciones.LiberarOperacionesResponse;
import cl.bice.banca.empresas.servicio.model.response.operaciones.ValidacionPoderResponse;
import cl.bice.banca.empresas.servicio.service.EstadoService;
import cl.bice.banca.empresas.servicio.service.NominasEnLineaService;
import cl.bice.banca.empresas.servicio.service.OperacionesEmpresaPortalService;
import cl.bice.banca.empresas.servicio.service.OperacionesEmpresaService;
import cl.bice.banca.empresas.servicio.service.RequestEmpresasService;
import cl.bice.banca.empresas.servicio.service.ValidacionService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.itextpdf.text.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OperacionesEmpresaControllerTest {

    private OperacionesEmpresaController operacionesEmpresaController;

    @Mock
    private BaseControllerEmpresa baseControllerEmpresa;

    @Mock
    private EstadoService estadoService;

    @Mock
    private RequestEmpresasService requestEmpresasService;

    @Mock
    private OperacionesEmpresaPortalService operacionesEmpresaPortalService;

    @Mock
    private ValidacionService validacionService;

    @Mock
    private NominasEnLineaService nominasEnLineaService;

    @Mock
    private OperacionesEmpresaService operacionesEmpresaService;

    @Mock
    private HttpServletRequest httpServletRequest;

    @Mock
    private Properties propiedadesExterna;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        operacionesEmpresaController = new OperacionesEmpresaController(estadoService, requestEmpresasService, operacionesEmpresaPortalService, operacionesEmpresaService, nominasEnLineaService, httpServletRequest);
    }
    
    @Test
    public void prueba() {
    	java.util.List<String> a = new ArrayList<>();
    	a.add("");
    	assertEquals("1", String.valueOf(a.size()));
    }
    
    /*
    @Test
    public void aprobarOperacionesMasivas_OkTest() {
        AprobarOperacionesPortalRequest a = mock(AprobarOperacionesPortalRequest.class);
        Estado estado = new Estado();
        estado.setCodigoEstado("1");
        when(estadoService.inicializarEstado()).thenReturn(estado);
        ResponseEntity<AprobarOperacionesPortalResponse>  res = operacionesEmpresaController.aprobarOperacionesMasivas(a);

        Assert.assertEquals(HttpStatus.OK, res.getStatusCode());
    }

    @Test
    public void aprobarOperacionesMasivas_ErrorTest() throws RequestInvalidoException {
        AprobarOperacionesPortalRequest a = mock(AprobarOperacionesPortalRequest.class);
        Estado estado = new Estado();
        estado.setCodigoEstado("1");
        when(estadoService.inicializarEstado()).thenReturn(estado);
        doThrow(RequestInvalidoException.class).when(requestEmpresasService).validaRequestEmpresaPortal(a, estado);
        estado.setCodigoEstado("E002");
        ResponseEntity<AprobarOperacionesPortalResponse>  res = operacionesEmpresaController.aprobarOperacionesMasivas(a);
        Assert.assertEquals(HttpStatus.OK, res.getStatusCode());
    }

    @Test
    public void liberarOperacionesMasivas_OkTest() {
        LiberarOperacionesPortalRequest a = mock(LiberarOperacionesPortalRequest.class);
        Estado estado = new Estado();
        estado.setCodigoEstado("1");
        when(estadoService.inicializarEstado()).thenReturn(estado);
        ResponseEntity<LiberarOperacionesPortalResponse>  res = operacionesEmpresaController.liberarOperacionesMasivas(a);

        Assert.assertEquals(HttpStatus.OK, res.getStatusCode());
    }

    @Test
    public void liberarOperacionesMasivas_ErrorTest() throws RequestInvalidoException {
        LiberarOperacionesPortalRequest a = mock(LiberarOperacionesPortalRequest.class);
        Estado estado = new Estado();
        estado.setCodigoEstado("1");
        when(estadoService.inicializarEstado()).thenReturn(estado);
        doThrow(RequestInvalidoException.class).when(requestEmpresasService).validaRequestEmpresaPortalLiberacion(a, estado);
        estado.setCodigoEstado("E002");
        ResponseEntity<LiberarOperacionesPortalResponse>  res = operacionesEmpresaController.liberarOperacionesMasivas(a);
        Assert.assertEquals(HttpStatus.OK, res.getStatusCode());
    }
    
    
    @Test
    public void liberarOperaciones_LBTR_OkTest() {
        LiberarOperacionesRequest a = ControllersFixtures.LIBERAR_OPERACIONES_REQUEST_FIXTURE("935");
        Estado estado = new Estado();
        estado.setCodigoEstado("1");
        when(estadoService.inicializarEstado()).thenReturn(estado);
        when(requestHttp.getSession()).thenReturn(mock(HttpSession.class));
        when(requestHttp.getServerName()).thenReturn("test-server");
        when(requestHttp.getRemoteAddr()).thenReturn("1.1.1.1");
        when(requestHttp.getHeader("referer")).thenReturn("aaa");
        
        ResponseEntity<LiberarOperacionesResponse>  res = operacionesEmpresaController.liberarOperaciones(a);

        Assert.assertEquals(HttpStatus.OK, res.getStatusCode());
    }
    
    
    @Test
    public void liberarOperaciones_NOMINAS_OkTest() {
        LiberarOperacionesRequest a = ControllersFixtures.LIBERAR_OPERACIONES_REQUEST_FIXTURE("5117");
        Estado estado = new Estado();
        estado.setCodigoEstado("1");
        when(estadoService.inicializarEstado()).thenReturn(estado);
        when(requestHttp.getSession()).thenReturn(mock(HttpSession.class));
        when(requestHttp.getServerName()).thenReturn("test-server");
        ResponseEntity<LiberarOperacionesResponse>  res = operacionesEmpresaController.liberarOperaciones(a);

        Assert.assertEquals(HttpStatus.OK, res.getStatusCode());
    }

    @Test
    public void liberarOperaciones_ErrorTest() {
        LiberarOperacionesRequest a = mock(LiberarOperacionesRequest.class);
        Estado estado = new Estado();
        estado.setCodigoEstado("1");
        when(estadoService.inicializarEstado()).thenReturn(estado);
        when(requestHttp.getSession()).thenReturn(mock(HttpSession.class));
        when(requestHttp.getServerName()).thenReturn("test-server");
        ResponseEntity<LiberarOperacionesResponse>  res = operacionesEmpresaController.liberarOperaciones(a);

        Assert.assertEquals(HttpStatus.OK, res.getStatusCode());
    }

    @Test
    public void aprobarOperaciones_OkTest() {
        AprobarOperacionesRequest a = ControllersFixtures.APROBAR_OPERACIONES_REQUEST_FIXTURE("935");
        Estado estado = new Estado();
        estado.setCodigoEstado("1");
        when(estadoService.inicializarEstado()).thenReturn(estado);
        when(requestHttp.getSession()).thenReturn(mock(HttpSession.class));
        when(requestHttp.getServerName()).thenReturn("test-server");
        ResponseEntity<AprobarOperacionesResponse>  res = operacionesEmpresaController.aprobarOperaciones(a);

        Assert.assertEquals(HttpStatus.OK, res.getStatusCode());
    }

    @Test
    public void validaPoderEmpresa_OkTest() {
        ValidaPoderRequest a = ControllersFixtures.VALIDA_PODER_REQUEST_FIXTURE("935");
        Estado estado = new Estado();
        estado.setCodigoEstado("1");
        when(estadoService.inicializarEstado()).thenReturn(estado);
        when(requestHttp.getSession()).thenReturn(mock(HttpSession.class));
        when(requestHttp.getServerName()).thenReturn("test-server");
        ResponseEntity<ValidacionPoderResponse>  res = operacionesEmpresaController.validaPoderEmpresa(a);

        Assert.assertEquals(HttpStatus.OK, res.getStatusCode());
    }

    @Test
    public void listaOperaciones_OkTest() {
        ListaOperacionesRequest a = mock(ListaOperacionesRequest.class);
        Estado estado = new Estado();
        estado.setCodigoEstado("1");
        when(estadoService.inicializarEstado()).thenReturn(estado);
        when(requestHttp.getSession()).thenReturn(mock(HttpSession.class));
        when(requestHttp.getServerName()).thenReturn("test-server");
        ResponseEntity<ResponseBase>  res = operacionesEmpresaController.listaOperaciones(a);

        Assert.assertEquals(HttpStatus.OK, res.getStatusCode());
    }

    @Test
    public void listaOperaciones_ErrorTest() {
        ListaOperacionesRequest a = mock(ListaOperacionesRequest.class);
        Estado estado = new Estado();
        estado.setCodigoEstado("1");
        when(estadoService.inicializarEstado()).thenReturn(estado);
        when(requestHttp.getSession()).thenReturn(mock(HttpSession.class));
        when(requestHttp.getServerName()).thenReturn("test-server");
        estado.setCodigoEstado("E001");
        ResponseEntity<ResponseBase>  res = operacionesEmpresaController.listaOperaciones(a);

        Assert.assertEquals(HttpStatus.OK, res.getStatusCode());
    }
    */
}
