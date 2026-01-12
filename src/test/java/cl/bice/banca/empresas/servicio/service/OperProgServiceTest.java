package cl.bice.banca.empresas.servicio.service;

import cl.bice.banca.empresas.servicio.exception.NoEncontradoException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class OperProgServiceTest {
	
	private static final String NUM_OPER_PROG = "151617";
	private static final String RESULT_OK = "OK";
	private static final String RESULT_ERROR = "ERROR";
	private static final String RUT_USUARIO = "0060623538";

    private OperProgService operProgService;

    @Mock
    private OperacionesEmpresaPortalService operacionesEmpresaPortalService;

    
    @Before
    public void init() throws Exception {
        MockitoAnnotations.initMocks(this);
        operProgService = new OperProgService();
     }    
    
    

    @Test
    public void getDataRequestTest() throws NoEncontradoException {
        String resultado = operProgService.validaProcesoOperProg(NUM_OPER_PROG, RUT_USUARIO);
        assertEquals(RESULT_ERROR, resultado);
        
    }
    
    
 
    
}
