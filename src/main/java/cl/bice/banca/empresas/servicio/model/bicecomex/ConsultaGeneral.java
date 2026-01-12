package cl.bice.banca.empresas.servicio.model.bicecomex;

import java.io.Serializable;
import java.sql.Timestamp;


public class ConsultaGeneral

implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 7838947897100545362L;
	
	/**
     * @param args
     */
    public void ConsultaGeneral() {}
    
    private String Producto;                //char (20)
    private String TipoOperacion;           //char (20)
    private String ReferenciaOrdenante;     //char (20)
    private String Moneda;                  //char (03)
    private String Monto;
    private String BeneficiarioTema;        //varchar (35)
    private String Fecha;                   //char (10) DD/MM/AAAA
    private String Estado;                  //char (20)
    private String IdentificacionOperacion; //int
    private String FechaModificacion;       //datetime 
    private Timestamp last_changed;
    private String FirmadaPorApoderado;     //char (02) 
    private String TimeStampOPRecibidas;    //char (30)
    private String Correlativo;

    
    
    
    
    public Timestamp getLast_changed() {
        return last_changed;
    }
    


    public void setLast_changed(Timestamp last_changed) {
        this.last_changed = last_changed;
    }
    


    public String getCorrelativo() {
        return Correlativo;
    }
    

    public void setCorrelativo(String correlativo) {
        Correlativo = correlativo;
    }
    

    public String getBeneficiarioTema() {
        return BeneficiarioTema;
    }
    
    public void setBeneficiarioTema(String beneficiarioTema) {
        BeneficiarioTema = beneficiarioTema;
    }
    
    public String getEstado() {
        return Estado;
    }
    
    public void setEstado(String estado) {
        Estado = estado;
    }
    
    public String getFecha() {
        return Fecha;
    }
    
    public void setFecha(String fecha) {
        Fecha = fecha;
    }
    
    public String getFechaModificacion() {
        return FechaModificacion;
    }
    
    public void setFechaModificacion(String fechaModificacion) {
        FechaModificacion = fechaModificacion;
    }
    
    public String getFirmadaPorApoderado() {
        return FirmadaPorApoderado;
    }
    
    public void setFirmadaPorApoderado(String firmadaPorApoderado) {
        FirmadaPorApoderado = firmadaPorApoderado;
    }
    
    public String getIdentificacionOperacion() {
        return IdentificacionOperacion;
    }
    
    public void setIdentificacionOperacion(String identificacionOperacion) {
        IdentificacionOperacion = identificacionOperacion;
    }
    
    public String getMoneda() {
        return Moneda;
    }
    
    public void setMoneda(String moneda) {
        Moneda = moneda;
    }
    
    public String getMonto() {
        return Monto;
    }
    
    public void setMonto(String monto) {
        Monto = monto;
    }
    
    public String getProducto() {
        return Producto;
    }
    
    public void setProducto(String producto) {
        Producto = producto;
    }
    
    public String getReferenciaOrdenante() {
        return ReferenciaOrdenante;
    }
    
    public void setReferenciaOrdenante(String referenciaOrdenante) {
        ReferenciaOrdenante = referenciaOrdenante;
    }
    
    public String getTimeStampOPRecibidas() {
        return TimeStampOPRecibidas;
    }
    
    public void setTimeStampOPRecibidas(String timeStampOPRecibidas) {
        TimeStampOPRecibidas = timeStampOPRecibidas;
    }
    
    public String getTipoOperacion() {
        return TipoOperacion;
    }
    
    public void setTipoOperacion(String tipoOperacion) {
        TipoOperacion = tipoOperacion;
    }
    
    

    



    
    
    
    
    

}
