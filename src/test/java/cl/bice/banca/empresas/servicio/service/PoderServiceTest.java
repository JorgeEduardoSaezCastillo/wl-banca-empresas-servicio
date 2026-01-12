package cl.bice.banca.empresas.servicio.service;

import cl.bice.banca.empresas.servicio.exception.NoEncontradoException;
import cl.bice.banca.empresas.servicio.exception.RequestInvalidoException;
import cl.bice.banca.empresas.servicio.model.operaciones.MapOperaciones;
import cl.bice.banca.empresas.servicio.model.operaciones.RespuestaAprob;
import cl.bice.banca.empresas.servicio.model.request.ms.poderes.PoderRequest;
import cl.bice.banca.empresas.servicio.model.request.operaciones.AprobarOperacionesPortalRequest;
import cl.bice.banca.empresas.servicio.model.response.ms.poderes.PoderResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PoderServiceTest {

    @InjectMocks
    private PoderService poderService;

    @Mock
    private ValoresCampoOperacionesService valoresCampoOperacionesService;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private Properties propiedadesExterna;

    @Before
    public void init () {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getPoderRequest_OkTest() throws RequestInvalidoException, NoEncontradoException {
        List<String> listaOperaciones = new ArrayList<>();
        listaOperaciones.add("3245345");
        AprobarOperacionesPortalRequest aprobarOperacionesPortalRequest = new AprobarOperacionesPortalRequest();
        aprobarOperacionesPortalRequest.setRutEmpresa("121232");
        aprobarOperacionesPortalRequest.setCodigoServicio("935");
        aprobarOperacionesPortalRequest.setRutApoderado("74657");
        aprobarOperacionesPortalRequest.setCanal("IE");
        aprobarOperacionesPortalRequest.setListaOperaciones(listaOperaciones);

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

        when(valoresCampoOperacionesService.getValorCampoOperacion(any(MapOperaciones.class), anyString(), anyString(), anyBoolean())).thenReturn("000002242232");

        PoderRequest poderRequest = poderService.getPoderRequest(aprobarOperacionesPortalRequest, "223434", mapOperaciones);
        assertEquals("935", poderRequest.getCodigoServicio());
    }

    @Test
    public void validaPoder_OkTest() {
        PoderRequest poderRequest = mock(PoderRequest.class);

        when(propiedadesExterna.getProperty(anyString())).thenReturn("test");

        PoderResponse poderResponse = new PoderResponse();
        poderResponse.setCodigoSalida("0");
        String maskedData = "xwgabc123";

        when(restTemplate.exchange(
                anyString(),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(PoderResponse.class),
                anyMap())).thenReturn(new ResponseEntity<>(poderResponse, HttpStatus.OK));
        RespuestaAprob respuestaAprob = poderService.validaPoder(poderRequest, maskedData);
        assertEquals("30", String.valueOf(respuestaAprob.getCodEstado()));
        
        
        
    }
}
