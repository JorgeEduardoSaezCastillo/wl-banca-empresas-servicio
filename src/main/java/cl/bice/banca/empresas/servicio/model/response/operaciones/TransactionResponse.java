package cl.bice.banca.empresas.servicio.model.response.operaciones;

import lombok.Data;

@Data
public class TransactionResponse {
    private boolean success;
    private String message;
}
