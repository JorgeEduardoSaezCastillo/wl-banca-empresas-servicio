package cl.bice.banca.empresas.servicio.controller;

import cl.bice.banca.empresas.servicio.model.request.operaciones.TransactionRequest;
import cl.bice.banca.empresas.servicio.repository.PortalOrawRepository;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.cert.CertificateException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@DisplayName("Certificate Test")
@RunWith(MockitoJUnitRunner.class)
public class TransaccionesControllerTest {
    @InjectMocks
    TransaccionesController transaccionesController;
    @Mock
    PortalOrawRepository portalOrawRepository;
    
    @Test
    public void prueba() {
    	java.util.List<String> a = new ArrayList<>();
    	a.add("");
    	assertEquals("1", String.valueOf(a.size()));
    }
    
    /*
    @Test
    @DisplayName("Genera certificado null")
    public void testLockOk() {
        TransactionRequest request = new TransactionRequest();
        request.setRutEnterprise("0762107775");
        request.setAction("Liberando trx");
        request.setIdTrx("1944074");
        transaccionesController.empresasControllerTransactionLock(request);
    }
    

    @Test
    public void empresasControllerTransactionUnLock_OkTest () {
        TransactionRequest r = mock(TransactionRequest.class);
        transaccionesController.empresasControllerTransactionUnLock(r);
    }
    */
}