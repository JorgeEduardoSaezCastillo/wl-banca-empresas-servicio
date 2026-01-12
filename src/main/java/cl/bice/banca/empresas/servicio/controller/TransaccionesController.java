package cl.bice.banca.empresas.servicio.controller;

import cl.bice.banca.empresas.servicio.model.common.Constantes;
import cl.bice.banca.empresas.servicio.model.request.operaciones.TransactionRequest;
import cl.bice.banca.empresas.servicio.model.response.operaciones.TransactionResponse;
import cl.bice.banca.empresas.servicio.repository.PortalOrawRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/transaction")
public class TransaccionesController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TransaccionesController.class);

    @Autowired
    PortalOrawRepository portalOrawRepository;
    private static final int COD_RESULT_SUCCESS = 1;

    /**
     * Método que inserta en la tabla TBL_BLOQUEAR_TRX un registro que representa un estado de bloqueo
     * para la transacción solicitada en el request.
     */
    @PostMapping(value = "/lock", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionResponse> empresasControllerTransactionLock(
            @RequestBody TransactionRequest request) {
        TransactionResponse resp = new TransactionResponse();
        LOGGER.info(Constantes.PARAMETRO_EMPRESAS_LOG_INICIO, request.getIdTrx(), request.getRutEnterprise());

        try {
            Map<String, Object> params = new HashMap<>();
            params.put("I_TRANSACCION", request.getIdTrx());
            params.put("I_RUT_EMPRESA", request.getRutEnterprise());
            params.put("I_ACCION", request.getAction());
            params.put("O_COD_RESULT,", null);
            params.put("O_MSG_RESULT,", null);
            portalOrawRepository.lockTransaction(params);
            LOGGER.info("Salida SP: [{}] {}", params.get(Constantes.O_COD_RESULT), params.get(Constantes.O_MSG_RESULT));
            if (Objects.equals(params.get(Constantes.O_COD_RESULT), BigDecimal.valueOf(COD_RESULT_SUCCESS))) {
                LOGGER.info(Constantes.TRX_LOCK_SUCESS, request.getIdTrx(), request.getRutEnterprise());
                resp.setSuccess(true);
                resp.setMessage(Constantes.VALIDAR_DESAFIO_ESTADO_EXITO);
            } else {
                String message = (String) params.get(Constantes.O_MSG_RESULT);
                LOGGER.error(message);
                resp.setMessage(Constantes.TRX_LOCK_ERROR + message);
            }

        } catch (SQLException e) {
            LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e.getMessage());
            resp.setMessage(Constantes.TRX_LOCK_ERROR + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
        }
        return ResponseEntity.ok(resp);
    }

    /**
     * Método que elimina en la tabla TBL_BLOQUEAR_TRX un registro que representa un estado de bloqueo
     * para la transacción solicitada en el request.
     */
    @PostMapping(value = "/unlock", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionResponse> empresasControllerTransactionUnLock(
            @RequestBody TransactionRequest request) {
        TransactionResponse resp = new TransactionResponse();
        LOGGER.info(Constantes.PARAMETRO_EMPRESAS_LOG_INICIO, request.getIdTrx(), request.getRutEnterprise());

        try {
            Map<String, Object> params = new HashMap<>();
            params.put("I_TRANSACCION", request.getIdTrx());
            params.put("I_RUT_EMPRESA", request.getRutEnterprise());
            params.put("O_COD_RESULT,", null);
            params.put("O_MSG_RESULT,", null);
            portalOrawRepository.unLockTransaction(params);
            LOGGER.info("Salida SP: [{}] {}", params.get(Constantes.O_COD_RESULT), params.get(Constantes.O_MSG_RESULT));
            if (Objects.equals(params.get(Constantes.O_COD_RESULT), BigDecimal.valueOf(COD_RESULT_SUCCESS))) {
                LOGGER.info(Constantes.TRX_UNLOCK_SUCESS, request.getIdTrx(), request.getRutEnterprise());
                resp.setSuccess(true);
                resp.setMessage(Constantes.VALIDAR_DESAFIO_ESTADO_EXITO);
            } else {
                String message = (String) params.get(Constantes.O_MSG_RESULT);
                LOGGER.error(message);
                resp.setMessage(Constantes.TRX_UNLOCK_ERROR + message);
            }
        } catch (SQLException e) {
            LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e.getMessage());
            resp.setMessage(Constantes.TRX_UNLOCK_ERROR + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
        }
        return ResponseEntity.ok(resp);
    }
}
