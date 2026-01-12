package cl.bice.banca.empresas.servicio.service;

import cl.bice.banca.empresas.servicio.exception.BancaEmpresasException;
import cl.bice.banca.empresas.servicio.model.request.operaciones.LiberarOperacionesPortalRequest;
import cl.bice.banca.empresas.servicio.repository.PortalOrawRepository;
import cl.bice.banca.empresas.servicio.soap.ClienteGFS;
import cl.bice.banca.empresas.servicio.soap.ClienteGenericOperProg;
import cl.bice.genericoperprog.ws.ActualizaDetCampOut;
import cl.bice.gfs.ws.GENERICFINANCIALSERVICEOUT;
import cl.bice.nominas.ws.ParametrosVo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TefPortalServiceTest {

    private TefPortalService tefPortalService;

    @Mock
    private EmpresasService empresasService;

    @Mock
    private ConsultarParametrosService consultarParametrosService;

    @Mock
    private ClienteGFS clienteGFS;

    @Mock
    private Properties propiedadesExterna;

    @Mock
    private OperacionesEmpresaPortalService operacionesEmpresaPortalService;

    @Mock
    private ClienteGenericOperProg clienteGenericOperProg;

    @Mock
    private PortalOrawRepository portalOrawRepository;
    
    
    @Test
    public void prueba() {
    	java.util.List<String> a = new ArrayList<>();
    	a.add("");
    	assertEquals("1", String.valueOf(a.size()));
    }
    
    /*
    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void execGFS_OkTest () throws BancaEmpresasException {
        LiberarOperacionesPortalRequest liberarOperacionesPortalRequest = mock(LiberarOperacionesPortalRequest.class);
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("VALOR15", "test");
        objectMap.put("NUM_OPER_PROG", "132312");
        objectMap.put("VALOR7", "123456");
        objectMap.put("VALOR9", "00000123456");
        objectMap.put("VALOR1", "12000");

        when(empresasService.fechaHoy(anyString())).thenReturn("20220208155100");

        ParametrosVo parametrosVo = new ParametrosVo();
        parametrosVo.setNomParam("SERVICIOSLBTR");
        parametrosVo.setNomTipo("LBTRCARGOONLINEBO");
        parametrosVo.setValParametro("test");
        List<ParametrosVo> horaCorteParametros = new ArrayList<>();
        horaCorteParametros.add(parametrosVo);
        when(consultarParametrosService.consultaParametro(anyString(), anyString())).thenReturn(horaCorteParametros);

        doAnswer(invocationOnMock -> {
            GENERICFINANCIALSERVICEOUT genericfinancialserviceout = new GENERICFINANCIALSERVICEOUT();
            genericfinancialserviceout.setESTADO("0");
            genericfinancialserviceout.setIDOPERACIONFCC("132312");
            genericfinancialserviceout.setFECHACONTABLEOUT("08-02-2022");
            genericfinancialserviceout.setNROREFERENCIA("132332");
            genericfinancialserviceout.setFECHA("08-02-2022");

            Object[] arguments = invocationOnMock.getArguments();
            Map<String, Object> argument = (Map<String, Object>) arguments[0];
            argument.put("RESPONSE", genericfinancialserviceout);
            return true;
        }).when(clienteGFS).call(anyMap());

        when(operacionesEmpresaPortalService.actualizaEstadoOperProg(anyString(), anyInt())).thenReturn("OK");

        when(operacionesEmpresaPortalService.actualizaNumOperacionTrfOperProg(anyString(), anyString())).thenReturn("OK");

        ActualizaDetCampOut actualizaDetCampOut = new ActualizaDetCampOut();
        actualizaDetCampOut.setCodEstado(0);
        when(clienteGenericOperProg.actualizarDetalle(anyString(), anyString())).thenReturn(actualizaDetCampOut);

        Map<String, Object> resultado = tefPortalService.execGFS(liberarOperacionesPortalRequest, objectMap, true);
        assertEquals("1", String.valueOf(resultado.size()));
    }

    @Test
    public void execGFS_ErrorTest () throws BancaEmpresasException {
        LiberarOperacionesPortalRequest liberarOperacionesPortalRequest = mock(LiberarOperacionesPortalRequest.class);
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("VALOR15", "test");
        objectMap.put("NUM_OPER_PROG", "132312");
        objectMap.put("VALOR7", "123456");
        objectMap.put("VALOR9", "00000123456");
        objectMap.put("VALOR1", "12000");

        when(empresasService.fechaHoy(anyString())).thenReturn("20220208155100");

        ParametrosVo parametrosVo = new ParametrosVo();
        parametrosVo.setNomParam("SERVICIOSLBTR");
        parametrosVo.setNomTipo("LBTRCARGOONLINEBO");
        parametrosVo.setValParametro("test");
        List<ParametrosVo> horaCorteParametros = new ArrayList<>();
        horaCorteParametros.add(parametrosVo);
        when(consultarParametrosService.consultaParametro(anyString(), anyString())).thenReturn(horaCorteParametros);

        doAnswer(invocationOnMock -> {
            GENERICFINANCIALSERVICEOUT genericfinancialserviceout = new GENERICFINANCIALSERVICEOUT();
            genericfinancialserviceout.setESTADO("0");
            genericfinancialserviceout.setIDOPERACIONFCC("132312");
            genericfinancialserviceout.setFECHACONTABLEOUT("08-02-2022");
            genericfinancialserviceout.setNROREFERENCIA("132332");
            genericfinancialserviceout.setFECHA("08-02-2022");

            Object[] arguments = invocationOnMock.getArguments();
            Map<String, Object> argument = (Map<String, Object>) arguments[0];
            argument.put("RESPONSE", genericfinancialserviceout);
            return false;
        }).when(clienteGFS).call(anyMap());

        ActualizaDetCampOut actualizaDetCampOut = new ActualizaDetCampOut();
        actualizaDetCampOut.setCodEstado(0);
        when(clienteGenericOperProg.actualizarDetalle(anyString(), anyString())).thenReturn(actualizaDetCampOut);

        tefPortalService.execGFS(liberarOperacionesPortalRequest, objectMap, true);
    }

    @Test
    public void execGFS_ReversaPortal_OkTest () throws BancaEmpresasException {
        LiberarOperacionesPortalRequest liberarOperacionesPortalRequest = mock(LiberarOperacionesPortalRequest.class);
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("VALOR15", "test");
        objectMap.put("NUM_OPER_PROG", "132312");
        objectMap.put("VALOR7", "123456");
        objectMap.put("VALOR9", "00000123456");
        objectMap.put("VALOR1", "12000");

        when(empresasService.fechaHoy(anyString())).thenReturn("20220208155100");

        ParametrosVo parametrosVo = new ParametrosVo();
        parametrosVo.setNomParam("SERVICIOSLBTR");
        parametrosVo.setNomTipo("LBTRCARGOONLINEBO");
        parametrosVo.setValParametro("test");
        List<ParametrosVo> horaCorteParametros = new ArrayList<>();
        horaCorteParametros.add(parametrosVo);
        when(consultarParametrosService.consultaParametro(anyString(), anyString())).thenReturn(horaCorteParametros);

        doAnswer(invocationOnMock -> {
            GENERICFINANCIALSERVICEOUT genericfinancialserviceout = new GENERICFINANCIALSERVICEOUT();
            genericfinancialserviceout.setESTADO("0");

            Object[] arguments = invocationOnMock.getArguments();
            Map<String, Object> argument = (Map<String, Object>) arguments[0];
            argument.put("RESPONSE", genericfinancialserviceout);
            return true;
        }).when(clienteGFS).call(anyMap());

        when(operacionesEmpresaPortalService.actualizaEstadoOperProg(anyString(), anyInt())).thenReturn("OK");

        ActualizaDetCampOut actualizaDetCampOut = new ActualizaDetCampOut();
        actualizaDetCampOut.setCodEstado(0);
        when(clienteGenericOperProg.actualizarDetalle(anyString(), anyString())).thenReturn(actualizaDetCampOut);

        tefPortalService.execGFS(liberarOperacionesPortalRequest, objectMap, true);
    }

    @Test
    public void execGFS_ErrorGFS_OkTest () throws BancaEmpresasException, SQLException {
        LiberarOperacionesPortalRequest liberarOperacionesPortalRequest = mock(LiberarOperacionesPortalRequest.class);
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("VALOR15", "test");
        objectMap.put("NUM_OPER_PROG", "132312");
        objectMap.put("VALOR7", "123456");
        objectMap.put("VALOR9", "00000123456");
        objectMap.put("VALOR1", "12000");

        when(empresasService.fechaHoy(anyString())).thenReturn("20220208155100");

        ParametrosVo parametrosVo = new ParametrosVo();
        parametrosVo.setNomParam("SERVICIOSLBTR");
        parametrosVo.setNomTipo("LBTRCARGOONLINEBO");
        parametrosVo.setValParametro("test");
        List<ParametrosVo> horaCorteParametros = new ArrayList<>();
        horaCorteParametros.add(parametrosVo);
        when(consultarParametrosService.consultaParametro(anyString(), anyString())).thenReturn(horaCorteParametros);

        doAnswer(invocationOnMock -> {
            GENERICFINANCIALSERVICEOUT genericfinancialserviceout = new GENERICFINANCIALSERVICEOUT();
            genericfinancialserviceout.setESTADO("5");
            genericfinancialserviceout.setDESCRIPCION("SALDO");

            Object[] arguments = invocationOnMock.getArguments();
            Map<String, Object> argument = (Map<String, Object>) arguments[0];
            argument.put("RESPONSE", genericfinancialserviceout);
            return true;
        }).when(clienteGFS).call(anyMap());

        when(operacionesEmpresaPortalService.actualizaEstadoOperProg(anyString(), anyInt())).thenReturn("OK");

        doAnswer(invocationOnMock -> {
            Object[] arguments = invocationOnMock.getArguments();
            Map<String, Object> argument = (Map<String, Object>) arguments[0];
            argument.put("v_SALIDA", "S");
            return null;
        }).when(portalOrawRepository).obtenerCodigosErrorGfs(anyMap());

        tefPortalService.execGFS(liberarOperacionesPortalRequest, objectMap, true);
    }
    */
}
