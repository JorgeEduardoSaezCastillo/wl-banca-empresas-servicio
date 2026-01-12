package cl.bice.banca.empresas.servicio.controller;

import cl.bice.banca.empresas.servicio.exception.RequestInvalidoException;
import cl.bice.banca.empresas.servicio.model.request.operaciones.PdfAutorizacionRequest;
import cl.bice.banca.empresas.servicio.model.response.Estado;
import cl.bice.banca.empresas.servicio.model.response.base.ResponseBase;
import cl.bice.banca.empresas.servicio.service.DetalleAutorizacionService;
import cl.bice.banca.empresas.servicio.service.EstadoService;
import cl.bice.banca.empresas.servicio.service.RequestEmpresasService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DetalleAutorizacionControllerTest {

    @InjectMocks
    private DetalleAutorizacionController detalleAutorizacionController;

    @Mock
    private HttpServletRequest requestHttp;

    @Mock
    private EstadoService estadoService;

    @Mock
    private RequestEmpresasService reqEmpresasService;

    @Mock
    private DetalleAutorizacionService detalleAutorizacionService;

    @Test
    public void obtenerPdfAutorizacion_OkTest() {
        PdfAutorizacionRequest r = new PdfAutorizacionRequest();
        r.setCodigoServicio("935");
        r.setListaOperaciones(new ArrayList<>());
        when(requestHttp.getSession()).thenReturn(mock(HttpSession.class));
        when(requestHttp.getServerName()).thenReturn("test-server");
        Estado estado = new Estado();
        estado.setCodigoEstado("1");
        when(estadoService.inicializarEstado()).thenReturn(estado);
        ResponseEntity<ResponseBase> res = detalleAutorizacionController.obtenerPdfAutorizacion(r);
        Assert.assertEquals(HttpStatus.OK, res.getStatusCode());
    }

    @Test
    public void obtenerPdfAutorizacion_ErrorTest() {
        PdfAutorizacionRequest r = new PdfAutorizacionRequest();
        r.setCodigoServicio("");
        r.setListaOperaciones(null);
        when(requestHttp.getSession()).thenReturn(mock(HttpSession.class));
        when(requestHttp.getServerName()).thenReturn("test-server");
        Estado estado = new Estado();
        estado.setCodigoEstado("1");
        when(estadoService.inicializarEstado()).thenReturn(estado);
        ResponseEntity<ResponseBase> res = detalleAutorizacionController.obtenerPdfAutorizacion(r);
        Assert.assertEquals(HttpStatus.OK, res.getStatusCode());
    }
}
