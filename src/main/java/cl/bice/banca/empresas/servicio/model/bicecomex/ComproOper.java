package cl.bice.banca.empresas.servicio.model.bicecomex;

import java.io.Serializable;

public class ComproOper extends ConsultaGeneral

implements Serializable{

    /**
     * @param args
     */
    String enviadoMsj;
    
    public String getEnviadoMsj() {
        return enviadoMsj;
    }
    
    public void setEnviadoMsj(String enviadoMsj) {
        this.enviadoMsj = enviadoMsj;
    }
    
}
