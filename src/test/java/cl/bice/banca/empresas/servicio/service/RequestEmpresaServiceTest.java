package cl.bice.banca.empresas.servicio.service;

import cl.bice.banca.empresas.servicio.exception.BancaEmpresasException;
import cl.bice.banca.empresas.servicio.model.common.BaseRequestEmpresa;
import cl.bice.banca.empresas.servicio.model.request.operaciones.AprobarOperacionesPortalRequest;
import cl.bice.banca.empresas.servicio.model.request.operaciones.LiberarOperacionesPortalRequest;
import cl.bice.banca.empresas.servicio.model.response.Estado;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RequestEmpresaServiceTest {

    @InjectMocks
    private RequestEmpresasService requestEmpresasService;

    @Mock
    private EstadoService estadoService;

    @Mock
    private ConsultarParametrosService consultarParametrosService;

    @Mock
    private OperacionesEmpresaPortalService operacionesEmpresaPortalService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void requestEmpresasUtilValidarRequestBaseEmpresa_OkTest () throws BancaEmpresasException {
        BaseRequestEmpresa request = new BaseRequestEmpresa();
        request.setRutEmpresa("0173590377");
        request.setRut("0173590377");
        request.setCanal("IE");
        request.setDispositivo("gfgsdfg5424523");
        request.setToken("token");

        when(consultarParametrosService.validarRegla(anyString(), anyString(), anyString(), anyInt())).thenReturn(true);

        requestEmpresasService.requestEmpresasUtilValidarRequestBaseEmpresa(request, mock(Estado.class));
        assertEquals("IE", request.getCanal());
    }

    @Test
    public void validaRequestEmpresaPortal_OkTest () throws BancaEmpresasException {
        List<String> listaOperaciones = new ArrayList<>();
        listaOperaciones.add("2343232");
        AprobarOperacionesPortalRequest request = new AprobarOperacionesPortalRequest();
        request.setRutEmpresa("097080000K");
        request.setRutApoderado("0126405944");
        request.setCanal("IE");
        request.setCodigoServicio("935");
        request.setListaOperaciones(listaOperaciones);

        Map<String, Object> datosUsuarioClienteMap = new HashMap<>();
        datosUsuarioClienteMap.put("FLG_APODERADO", 1);
        datosUsuarioClienteMap.put("FLG_ADM_DELEGADO", 1);
        List<Map<String, Object>> datosUsuarioClienteMapList = new ArrayList<>();
        datosUsuarioClienteMapList.add(datosUsuarioClienteMap);

        when(operacionesEmpresaPortalService.obtenerDatosUsuarioCliente(anyString(), anyString())).thenReturn(datosUsuarioClienteMapList);

        requestEmpresasService.validaRequestEmpresaPortal(request, mock(Estado.class));
        assertEquals("IE", request.getCanal());
    }

    @Test
    public void validaRequestEmpresaPortal_ErrorRutTest () throws BancaEmpresasException {
        List<String> listaOperaciones = new ArrayList<>();
        listaOperaciones.add("2343232");
        AprobarOperacionesPortalRequest request = new AprobarOperacionesPortalRequest();
        request.setRutEmpresa("097080000K");
        request.setRutApoderado("533424");
        request.setCanal("IE");
        request.setCodigoServicio("935");
        request.setListaOperaciones(listaOperaciones);

        requestEmpresasService.validaRequestEmpresaPortal(request, mock(Estado.class));
        assertEquals("IE", request.getCanal());
    }

    @Test
    public void validaRequestEmpresaPortal_ErrorTest () throws BancaEmpresasException {
        List<String> listaOperaciones = new ArrayList<>();
        listaOperaciones.add("2343232");
        AprobarOperacionesPortalRequest request = new AprobarOperacionesPortalRequest();
        request.setRutEmpresa("097080000K");
        request.setRutApoderado("0126405944");
        request.setCanal("IE");
        request.setCodigoServicio("935");
        request.setListaOperaciones(listaOperaciones);

        requestEmpresasService.validaRequestEmpresaPortal(request, mock(Estado.class));
        assertEquals("IE", request.getCanal());
    }

    @Test
    public void validaRequestEmpresaPortalLiberacion_OkTest () throws BancaEmpresasException {
        List<String> listaOperaciones = new ArrayList<>();
        listaOperaciones.add("2343232");
        LiberarOperacionesPortalRequest request = new LiberarOperacionesPortalRequest();
        request.setRutEmpresa("097080000K");
        request.setRutApoderado("0126405944");
        request.setCanal("IE");
        request.setCodigoServicio("935");
        request.setListaOperaciones(listaOperaciones);

        requestEmpresasService.validaRequestEmpresaPortalLiberacion(request, mock(Estado.class));
        assertEquals("IE", request.getCanal());
    }
}
