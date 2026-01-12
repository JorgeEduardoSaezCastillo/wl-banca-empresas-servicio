package cl.bice.banca.empresas.servicio.service;

import cl.bice.banca.empresas.servicio.exception.BancaEmpresasException;
import cl.bice.banca.empresas.servicio.model.operaciones.MapOperaciones;
import cl.bice.banca.empresas.servicio.model.request.operaciones.AprobarOperacionesPortalRequest;
import cl.bice.banca.empresas.servicio.repository.PortalOrawRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ValidacionPortalServiceTest {
	
	
    private ValidacionPortalService validacionPortalService;

    @Mock
    private ValoresCampoOperacionesService valoresCampoOperacionesService;

    @Mock
    private CuentaService cuentaService;

    @Mock
    private EmpresasService empresasService;

    @Mock
    private OperacionesEmpresaService operacionesEmpresaService;

    @Mock
    private PortalOrawRepository portalOrawRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        validacionPortalService = new ValidacionPortalService(valoresCampoOperacionesService, cuentaService, empresasService, operacionesEmpresaService, portalOrawRepository);
    }

    @Test
    public void isPertenenciaValida_OkTest() throws BancaEmpresasException {
        AprobarOperacionesPortalRequest request = new AprobarOperacionesPortalRequest();
        request.setRutApoderado("2314312");
        request.setRutEmpresa("2314312");

        List<String> listaOperaciones = new ArrayList<>();
        listaOperaciones.add("233234");

        List<Map<String, Object>> outputSP = new ArrayList<>();
        Map<String, Object> outPutSPMap = new HashMap<>();
        outPutSPMap.put("NUM_OPER_PROG", "2323423");
        outPutSPMap.put("COD_SERVICIO", "935");
        outPutSPMap.put("TIPO", "DVP");
        outPutSPMap.put("VALOR3", "01-02-2022");
        outPutSPMap.put("VALOR9", "000000000001255606");
        outPutSPMap.put("VALOR1", "12000");
        outputSP.add(outPutSPMap);

        Map<String, String> map1 = new HashMap<>();
        map1.put("dato1", "valor");
        Map<String, Map<String, String>> map = new HashMap<>();
        map.put("dato1", map1);
        MapOperaciones mapOperaciones = new MapOperaciones();
        mapOperaciones.setMapOperaciones(map);
        mapOperaciones.setMapOutputSP(outputSP);

        when(valoresCampoOperacionesService.getValorCampoOperacion(
                any(MapOperaciones.class),
                anyString(),
                anyString(),
                anyBoolean())).thenReturn("00004233232");

        when(cuentaService.validaPertenenciaCuentaRutUsuarioRutEmpresa(
                anyString(), anyString(), anyString())).thenReturn(true);

        when(empresasService.perteneceUsuarioEmpresa(anyString(), anyString())).thenReturn(true);

        when(operacionesEmpresaService.isPertenenciaCorrectaNumOperacionRutEmpresa(anyString(), anyString(),
                anyString())).thenReturn(true);

        List<String> datos = validacionPortalService.isPertenenciaValida(request, "935", listaOperaciones, mapOperaciones, true);
        assertEquals("1", String.valueOf(datos.size()));
    }

    @Test
    public void empresaTieneCargoEnLineaLbtr_OkTest() throws SQLException {

        doAnswer(invocationOnMock -> {
            Object[] arguments = invocationOnMock.getArguments();
            Map<String, Object> argument = (Map<String, Object>) arguments[0];
            argument.put("RESULT", "0");
            return null;
        }).when(portalOrawRepository).empresaTieneCargoEnLineaLbtr(anyMap());

        String tieneCargoLinea = validacionPortalService.empresaTieneCargoEnLineaLbtr("3242332");
        assertEquals("NO", tieneCargoLinea);
    }

    @Test
    public void empresaOperaCuentaParaguaLbtr_OkTest() throws SQLException, BancaEmpresasException {

        doAnswer(invocationOnMock -> {
            Object[] arguments = invocationOnMock.getArguments();
            Map<String, Object> argument = (Map<String, Object>) arguments[0];
            argument.put("RESULT", "0");
            return null;
        }).when(portalOrawRepository).empresaTieneCuentaParaguaLbtr(anyMap());

        boolean operaCtaParagua = validacionPortalService.empresaOperaCuentaParaguaLbtr("3242332");
        assertEquals("NO", operaCtaParagua == true ? "SI" : "NO");
    }

    @Test
    public void validaOperProgLib_OkTest() throws SQLException, BancaEmpresasException {
        List<Map<String, Object>> outputSP = new ArrayList<>();
        Map<String, Object> outPutSPMap = new HashMap<>();
        outPutSPMap.put("NUM_OPER_PROG", "3242332");
        outPutSPMap.put("COD_SERVICIO", "935");
        outPutSPMap.put("TIPO", "DVP");
        outPutSPMap.put("VALOR3", "01-02-2022");
        outPutSPMap.put("VALOR9", "000000000001255606");
        outPutSPMap.put("VALOR1", "12000");
        outputSP.add(outPutSPMap);

        Map<String, String> map1 = new HashMap<>();
        map1.put("dato1", "valor");
        Map<String, Map<String, String>> map = new HashMap<>();
        map.put("dato1", map1);
        MapOperaciones mapOperaciones = new MapOperaciones();
        mapOperaciones.setMapOperaciones(map);
        mapOperaciones.setMapOutputSP(outputSP);

        boolean valida = validacionPortalService.validaOperProgLib("3242332", mapOperaciones, "NUM_OPER_PROG");
        assertEquals("SI", valida == true ? "SI" : "NO");
    }
    

    
}
