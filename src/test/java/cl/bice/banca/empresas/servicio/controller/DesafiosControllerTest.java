package cl.bice.banca.empresas.servicio.controller;

import cl.bice.banca.empresas.servicio.controller.fixtures.ControllersFixtures;
import cl.bice.banca.empresas.servicio.exception.BancaEmpresasException;
import cl.bice.banca.empresas.servicio.model.common.BaseRequestEmpresa;
import cl.bice.banca.empresas.servicio.model.operaciones.MapOperaciones;
import cl.bice.banca.empresas.servicio.model.request.desafios.EstadoValidacionMobileSignerRequest;
import cl.bice.banca.empresas.servicio.model.request.desafios.ListarCrearDesafiosRequest;
import cl.bice.banca.empresas.servicio.model.request.desafios.ValidarEstadoConfirmacionRequest;
import cl.bice.banca.empresas.servicio.model.request.desafios.ValidarMobileSignerRequest;
import cl.bice.banca.empresas.servicio.model.response.Estado;
import cl.bice.banca.empresas.servicio.model.response.base.ResponseBase;
import cl.bice.banca.empresas.servicio.model.response.desafios.ListarCrearDesafiosResponse;
import cl.bice.banca.empresas.servicio.model.response.desafios.ValidarEstadoConfirmacionResponse;
import cl.bice.banca.empresas.servicio.repository.PortalOrawRepository;
import cl.bice.banca.empresas.servicio.service.BiceComexService;
import cl.bice.banca.empresas.servicio.service.CuentaService;
import cl.bice.banca.empresas.servicio.service.DesafiosServices;
import cl.bice.banca.empresas.servicio.service.EmpresasService;
import cl.bice.banca.empresas.servicio.service.EstadoService;
import cl.bice.banca.empresas.servicio.service.NominasEnLineaService;
import cl.bice.banca.empresas.servicio.service.OperacionesEmpresaPortalService;
import cl.bice.banca.empresas.servicio.service.OperacionesEmpresaService;
import cl.bice.banca.empresas.servicio.service.RequestEmpresasService;
import cl.bice.banca.empresas.servicio.service.ValidacionService;
import cl.bice.banca.empresas.servicio.service.ValoresCampoOperacionesService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.Properties;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DesafiosControllerTest {
    @InjectMocks
    private DesafiosController desafiosController;

    @Mock
    private HttpServletRequest requestHttp;

    @Mock
    private BiceComexService biceComexService;

    @Mock
    private EstadoService estadoService;

    @Mock
    private RequestEmpresasService reqEmpresasService;

    @Mock
    private DesafiosServices desafiosServices;

    @Mock
    private ValoresCampoOperacionesService valoresCampoOperacionesService;

    @Mock
    private ValidacionService validacionService;

    @Mock
    private Properties propiedadesExterna;

    @Mock
    private OperacionesEmpresaService operacionesEmpresaService;

    @Mock
    private OperacionesEmpresaPortalService operacionesEmpresaPortalService;

    @Mock
    private EmpresasService empresasService;

    @Mock
    private CuentaService cuentaService;

    @Mock
    private PortalOrawRepository portalOrawRepository;

    @Mock
    private NominasEnLineaService nominasEnLineaService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void listarCrearDesafios_OkTest (){
        ListarCrearDesafiosRequest r = ControllersFixtures.LISTAR_CREAR_DESAFIOS_REQUEST_FIXTURE();
        when(requestHttp.getSession()).thenReturn(mock(HttpSession.class));
        when(requestHttp.getServerName()).thenReturn("test-server");
        Estado estado = new Estado();
        estado.setCodigoEstado("1");
        when(estadoService.inicializarEstado()).thenReturn(estado);
        ResponseEntity<ListarCrearDesafiosResponse> res =  desafiosController.listarCrearDesafios(r);
        Assert.assertEquals(HttpStatus.OK, res.getStatusCode());
    }

    @Test
    public void validarEstadoDesafio_OkTest () throws BancaEmpresasException {
        ValidarEstadoConfirmacionRequest r = ControllersFixtures.VALIDAR_ESTADO_CONFIRMACION_REQUEST_FIXTURE();
        when(requestHttp.getSession()).thenReturn(mock(HttpSession.class));
        when(requestHttp.getServerName()).thenReturn("test-server");
        Estado estado = new Estado();
        estado.setCodigoEstado("1");
        when(estadoService.inicializarEstado()).thenReturn(estado);
        when(valoresCampoOperacionesService.obtenerMapOperaciones(
                anyString(), anyString(), anyString(), anyString(),
                eq(true), anyString(), anyString())).thenReturn(mock(MapOperaciones.class));
        when(validacionService.isPertenenciaValida(any(BaseRequestEmpresa.class), anyString(),
                anyList(), any(MapOperaciones.class), eq(true))).thenReturn(true);
        ResponseEntity<ValidarEstadoConfirmacionResponse> res =  desafiosController.validarEstadoDesafio(r);
        Assert.assertEquals(HttpStatus.OK, res.getStatusCode());
    }

    @Test
    public void validarEstadoDesafio_ErrorTest () throws BancaEmpresasException {
        ValidarEstadoConfirmacionRequest r = ControllersFixtures.VALIDAR_ESTADO_CONFIRMACION_REQUEST_FIXTURE();
        when(requestHttp.getSession()).thenReturn(mock(HttpSession.class));
        when(requestHttp.getServerName()).thenReturn("test-server");
        Estado estado = new Estado();
        estado.setCodigoEstado("1");
        when(estadoService.inicializarEstado()).thenReturn(estado);
        when(valoresCampoOperacionesService.obtenerMapOperaciones(
                anyString(), anyString(), anyString(), anyString(),
                eq(true), anyString(), anyString())).thenReturn(mock(MapOperaciones.class));
        ResponseEntity<ValidarEstadoConfirmacionResponse> res =  desafiosController.validarEstadoDesafio(r);
        Assert.assertEquals(HttpStatus.OK, res.getStatusCode());
    }

    @Test
    public void isClienteEnroladoMobisigner_OkTest () {
        BaseRequestEmpresa r = mock(BaseRequestEmpresa.class);
        when(requestHttp.getSession()).thenReturn(mock(HttpSession.class));
        when(requestHttp.getServerName()).thenReturn("test-server");
        Estado estado = new Estado();
        estado.setCodigoEstado("1");
        when(estadoService.inicializarEstado()).thenReturn(estado);
        ResponseEntity<ResponseBase> res =  desafiosController.isClienteEnroladoMobisigner(r);
        Assert.assertEquals(HttpStatus.OK, res.getStatusCode());
    }

    @Test
    public void validarMobileSigner_OkTest () {
        ValidarMobileSignerRequest r = mock(ValidarMobileSignerRequest.class);
        when(requestHttp.getSession()).thenReturn(mock(HttpSession.class));
        when(requestHttp.getServerName()).thenReturn("test-server");
        Estado estado = new Estado();
        estado.setCodigoEstado("1");
        when(estadoService.inicializarEstado()).thenReturn(estado);
        ResponseEntity<ResponseBase> res =  desafiosController.validarMobileSigner(r);
        Assert.assertEquals(HttpStatus.OK, res.getStatusCode());
    }

    @Test
    public void estadoValidacionMobileSigner_OkTest () {
        EstadoValidacionMobileSignerRequest r = mock(EstadoValidacionMobileSignerRequest.class);
        when(requestHttp.getSession()).thenReturn(mock(HttpSession.class));
        when(requestHttp.getServerName()).thenReturn("test-server");
        Estado estado = new Estado();
        estado.setCodigoEstado("1");
        when(estadoService.inicializarEstado()).thenReturn(estado);
        ResponseEntity<ResponseBase> res =  desafiosController.estadoValidacionMobileSigner(r);
        Assert.assertEquals(HttpStatus.OK, res.getStatusCode());
    }
}
