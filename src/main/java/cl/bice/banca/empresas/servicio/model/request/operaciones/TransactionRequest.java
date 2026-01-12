package cl.bice.banca.empresas.servicio.model.request.operaciones;

import lombok.Data;

@Data
public class TransactionRequest {
    private String idTrx;
    private String rutEnterprise;
    private String action;
}