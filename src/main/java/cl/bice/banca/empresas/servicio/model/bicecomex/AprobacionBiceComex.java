package cl.bice.banca.empresas.servicio.model.bicecomex;

import java.io.Serializable;

public class AprobacionBiceComex

implements Serializable{

    /**
     * @param args
     */
    int return_code;
    String return_msg;
    String envia;
    String chk;
        
    public String getChk() {
        return chk;
    }
    
    public void setChk(String chk) {
        this.chk = chk;
    }
    
    public String getEnvia() {
        return envia;
    }

    public void setEnvia(String envia) {
        this.envia = envia;
    }

    public AprobacionBiceComex(){
        this.return_code = 101;
        this.return_msg = "Error de Sistema.";
    }
    
    public int getReturn_code() {
        return return_code;
    }
    
    public void setReturn_code(int return_code) {
        this.return_code = return_code;
    }
    
    public String getReturn_msg() {
        return return_msg;
    }
    
    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }
    
}
