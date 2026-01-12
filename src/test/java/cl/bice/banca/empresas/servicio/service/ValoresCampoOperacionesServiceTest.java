package cl.bice.banca.empresas.servicio.service;

import cl.bice.banca.empresas.servicio.exception.BancaEmpresasException;
import cl.bice.banca.empresas.servicio.model.operaciones.MapOperaciones;
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
import java.util.Properties;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ValoresCampoOperacionesServiceTest {
    @InjectMocks
    private ValoresCampoOperacionesService valoresCampoOperacionesService;

    @Mock
    private OperacionesEmpresaService operacionesEmpresaService;

    @Mock
    private Properties propiedadesExterna;

    @Mock
    private OperacionesEmpresaPortalService operacionesEmpresaPortalService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void obtenerMapOperaciones_OkTest () throws BancaEmpresasException {
        Map<String, Object> map = new HashMap<>();
        map.put("test", "test");
        List<Map<String, Object>> mapList = new ArrayList<>();
        mapList.add(map);
        when(operacionesEmpresaService.obtenerOperacionesAprobarLiberar(
                anyString(),
                anyString(),
                anyString(),
                eq(true),
                eq(null))).thenReturn(mapList);

        when(propiedadesExterna.getProperty(anyString())).thenReturn("5");

        valoresCampoOperacionesService.obtenerMapOperaciones("23423", "23423", "935");
        assertEquals("1", String.valueOf(map.size()));
    }

    @Test
    public void getValorCampoOperacion_OkTest () throws BancaEmpresasException {
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
        map1.put("2323423", "2323423");
        Map<String, Map<String, String>> map = new HashMap<>();
        map.put("2323423", map1);
        MapOperaciones mapOperaciones = new MapOperaciones();
        mapOperaciones.setMapOperaciones(map);
        mapOperaciones.setMapOutputSP(outputSP);

        valoresCampoOperacionesService.getValorCampoOperacion(mapOperaciones, "2323423", "test", eq(true));
        assertEquals("1", String.valueOf(map1.size()));
    }

    @Test
    public void obtenerMapOperacionesCompleto_OkTest() throws BancaEmpresasException {
        Map<String, Object> map = new HashMap<>();
        map.put("test", "test");
        List<Map<String, Object>> mapList = new ArrayList<>();
        mapList.add(map);
        when(operacionesEmpresaService.obtenerOperacionesAprobarLiberar(
                anyString(),
                anyString(),
                anyString(),
                eq(true))).thenReturn(mapList);
        when(propiedadesExterna.getProperty(anyString())).thenReturn("5");
        valoresCampoOperacionesService.obtenerMapOperacionesCompleto("11221", "32134", "935", true);
        assertEquals("1", String.valueOf(map.size()));
    }
    
    /*
    @Test
    public void obtenerMapOperacionesPortal_OkTest() throws BancaEmpresasException {
        Map<String, Object> map = new HashMap<>();
        map.put("test", "test");
        List<Map<String, Object>> mapList = new ArrayList<>();
        mapList.add(map);
        when(operacionesEmpresaPortalService.obtenerDetalleOperacionesAprobarLiberarPortal(
                anyString(),
                anyString(),
                anyString(),
                eq(true),
                anyString(),
                anyString())).thenReturn(mapList);
        when(propiedadesExterna.getProperty(anyString())).thenReturn("5");
        valoresCampoOperacionesService.obtenerMapOperacionesPortal("11221", "32134", "935", "IE", "3233", true);
        assertEquals("1", String.valueOf(map.size()));
    }
    */
}
