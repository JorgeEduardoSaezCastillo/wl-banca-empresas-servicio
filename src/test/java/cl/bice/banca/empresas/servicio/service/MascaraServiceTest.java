package cl.bice.banca.empresas.servicio.service;

import cl.bice.banca.empresas.servicio.exception.BancaEmpresasException;
import cl.bice.banca.empresas.servicio.exception.NoEncontradoException;
import cl.bice.banca.empresas.servicio.model.operaciones.MapOperaciones;
import cl.bice.banca.empresas.servicio.model.response.ms.mascara.EnmascararSalidaTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

public class MascaraServiceTest {
	
	private static final String NUM_OPER_PROG = "151617";
	private static final String NUM_CTA_CARGO = "123456";
	private static final String DATA_REQUEST = NUM_CTA_CARGO+","+NUM_OPER_PROG;
	private static final String DATO_ENMASCARADO = "ax1wzx";

	
    private MascaraService mascaraService;

    @Mock
    private ValoresCampoOperacionesService valoresCampoOperacionesService;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private Properties propiedadesExterna;
    
    
    @Before
    public void init () throws Exception {
        MockitoAnnotations.initMocks(this);
        mascaraService = new MascaraService(restTemplate, propiedadesExterna, valoresCampoOperacionesService);
     }
    
     @Test
    public void getDataRequestTest() throws NoEncontradoException {
        List<Map<String, Object>> outputSP = new ArrayList<>();
        Map<String, Object> outPutSPMap = new HashMap<>();
        outPutSPMap.put("NUM_OPER_PROG", NUM_OPER_PROG);
        outPutSPMap.put("COD_SERVICIO", "935");
        outPutSPMap.put("TIPO", "DVP");
        outPutSPMap.put("VALOR3", "01-02-2022");
        outPutSPMap.put("VALOR9", "000000000001255606");
        outPutSPMap.put("NroCuentaCargo", NUM_CTA_CARGO);
        outputSP.add(outPutSPMap);

        Map<String, String> map1 = new HashMap<>();
        map1.put("dato1", "valor");
        Map<String, Map<String, String>> map = new HashMap<>();
        map.put("dato1", map1);
        MapOperaciones mapOperaciones = new MapOperaciones();
        mapOperaciones.setMapOperaciones(map);
        mapOperaciones.setMapOutputSP(outputSP);
        
        when(valoresCampoOperacionesService.getValorCampoOperacion(mapOperaciones, NUM_OPER_PROG, "NroCuentaCargo", true)).thenReturn(NUM_CTA_CARGO);

        String dataRequestService = mascaraService.getDataRequest(mapOperaciones, NUM_OPER_PROG);
        assertEquals(DATA_REQUEST, dataRequestService);
        
    }
    
    
    @Test
    public void encriptarDatoTest() {
        when(propiedadesExterna.getProperty(anyString())).thenReturn("http://localhost");

        EnmascararSalidaTO expectedResponse = new EnmascararSalidaTO();
        expectedResponse.setCodigoSalida("200");
        expectedResponse.setMascara(DATO_ENMASCARADO);
        String dato = NUM_CTA_CARGO+","+NUM_OPER_PROG;
        String datoEnmascarado = "";
        
        ResponseEntity<EnmascararSalidaTO> responseEntity = new ResponseEntity<>(expectedResponse, HttpStatus.OK);
        /*
        when(restTemplate.exchange(
                anyString(),
                any(HttpMethod.class),
                any(HttpEntity.class),
                eq(EnmascararSalidaTO.class)
                )).thenReturn(responseEntity);
        */
 		try {
			datoEnmascarado = mascaraService.encriptarDato(dato, "0060623538");
		} catch (BancaEmpresasException e) {
			e.printStackTrace();
		}
        assertEquals("", datoEnmascarado);

    }
    

    
}
