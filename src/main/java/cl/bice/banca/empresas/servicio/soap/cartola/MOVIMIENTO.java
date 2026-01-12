//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación
// de la referencia de enlace (JAXB) XML v2.2.11
// Visite <a
// href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve
// a compilar el esquema de origen.
// Generado el: 2017.09.27 a las 09:26:39 AM CLST
//

package cl.bice.banca.empresas.servicio.soap.cartola;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Clase Java para anonymous complex type.
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que
 * haya en esta clase.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://www.bice.cl/wsdl}FECHA"/&gt;
 *         &lt;element ref="{http://www.bice.cl/wsdl}FECHACONTABLE"/&gt;
 *         &lt;element ref="{http://www.bice.cl/wsdl}CODTRN"/&gt;
 *         &lt;element ref="{http://www.bice.cl/wsdl}DESCRIPCION"/&gt;
 *         &lt;element ref="{http://www.bice.cl/wsdl}NARRATIVA"/&gt;
 *         &lt;element ref="{http://www.bice.cl/wsdl}REMARKS"/&gt;
 *         &lt;element ref="{http://www.bice.cl/wsdl}SUCMOV"/&gt;
 *         &lt;element ref="{http://www.bice.cl/wsdl}NROINST" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.bice.cl/wsdl}TIPOMOV"/&gt;
 *         &lt;element ref="{http://www.bice.cl/wsdl}MONTO"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "fecha",
    "fechacontable",
    "codtrn",
    "descripcion",
    "narrativa",
    "remarks",
    "sucmov",
    "nroinst",
    "tipomov",
    "monto"
})
@XmlRootElement(name = "MOVIMIENTO")
public class MOVIMIENTO {

    @XmlElement(name = "FECHA", required = true)
    protected String fecha;
    @XmlElement(name = "FECHACONTABLE", required = true)
    protected String fechacontable;
    @XmlElement(name = "CODTRN", required = true)
    protected String codtrn;
    @XmlElement(name = "DESCRIPCION", required = true)
    protected String descripcion;
    @XmlElement(name = "NARRATIVA", required = true)
    protected String narrativa;
    @XmlElement(name = "REMARKS", required = true)
    protected String remarks;
    @XmlElement(name = "SUCMOV", required = true)
    protected String sucmov;
    @XmlElement(name = "NROINST")
    protected String nroinst;
    @XmlElement(name = "TIPOMOV", required = true)
    protected String tipomov;
    @XmlElement(name = "MONTO", required = true)
    protected String monto;
    @XmlTransient
    private String tipo;

    @XmlTransient
    private String documento;

    @XmlTransient
    private boolean muestraLink;

    @XmlTransient
    private String fechaDesplegada;

    @XmlTransient
    private String saldoIntermedio;

    @XmlTransient
    private String saldoIntermedioAux;

    @XmlTransient
    private String montoCargo;

    @XmlTransient
    private String montoAbono;

    /**
     * Permite obtener el valor del atributo saldoIntermedio.
     *
     * @return retorna el valor del atributo saldoIntermedio.
     */
    public String getSaldoIntermedio() {
        return saldoIntermedio;
    }

    /**
     * Permite setear el valor del atributo saldoIntermedio.
     *
     * @param saldoIntermedio nuevo valor para el atributo saldoIntermedio.
     */
    public void setSaldoIntermedio(String saldoIntermedio) {
        this.saldoIntermedio = saldoIntermedio;
    }

    /**
     * Permite obtener el valor del atributo saldoIntermedio.
     *
     * @return retorna el valor del atributo saldoIntermedio.
     */
    public String getSaldoIntermedioAux() {
        return saldoIntermedioAux;
    }

    /**
     * Permite setear el valor del atributo saldoIntermedio.
     *
     * @param saldoIntermedio nuevo valor para el atributo saldoIntermedio.
     */
    public void setSaldoIntermedioAux(String saldoIntermedioAux) {
        this.saldoIntermedioAux = saldoIntermedioAux;
    }

    /**
     * Permite obtener el valor del atributo montoCargo.
     *
     * @return retorna el valor del atributo montoCargo.
     */
    public String getMontoCargo() {
        return montoCargo;
    }

    /**
     * Permite setear el valor del atributo montoCargo.
     *
     * @param montoCargo nuevo valor para el atributo montoCargo.
     */
    public void setMontoCargo(String montoCargo) {
        this.montoCargo = montoCargo;
    }

    /**
     * Permite obtener el valor del atributo montoAbono.
     *
     * @return retorna el valor del atributo montoAbono.
     */
    public String getMontoAbono() {
        return montoAbono;
    }

    /**
     * Permite setear el valor del atributo montoAbono.
     *
     * @param montoAbono nuevo valor para el atributo montoAbono.
     */
    public void setMontoAbono(String montoAbono) {
        this.montoAbono = montoAbono;
    }

    /**
     * Obtiene el valor de la propiedad fecha.
     * 
     * @return possible object is {@link String }
     */
    public String getFECHA() {
        return fecha;
    }

    /**
     * Define el valor de la propiedad fecha.
     * 
     * @param value allowed object is {@link String }
     */
    public void setFECHA(String value) {
        this.fecha = value;
    }

    /**
     * Obtiene el valor de la propiedad fechacontable.
     * 
     * @return possible object is {@link String }
     */
    public String getFECHACONTABLE() {
        return fechacontable;
    }

    /**
     * Define el valor de la propiedad fechacontable.
     * 
     * @param value allowed object is {@link String }
     */
    public void setFECHACONTABLE(String value) {
        this.fechacontable = value;
    }

    /**
     * Obtiene el valor de la propiedad codtrn.
     * 
     * @return possible object is {@link String }
     */
    public String getCODTRN() {
        return codtrn;
    }

    /**
     * Define el valor de la propiedad codtrn.
     * 
     * @param value allowed object is {@link String }
     */
    public void setCODTRN(String value) {
        this.codtrn = value;
    }

    /**
     * Obtiene el valor de la propiedad descripcion.
     * 
     * @return possible object is {@link String }
     */
    public String getDESCRIPCION() {
        return descripcion;
    }

    /**
     * Define el valor de la propiedad descripcion.
     * 
     * @param value allowed object is {@link String }
     */
    public void setDESCRIPCION(String value) {
        this.descripcion = value;
    }

    /**
     * Obtiene el valor de la propiedad narrativa.
     * 
     * @return possible object is {@link String }
     */
    public String getNARRATIVA() {
        return narrativa;
    }

    /**
     * Define el valor de la propiedad narrativa.
     * 
     * @param value allowed object is {@link String }
     */
    public void setNARRATIVA(String value) {
        this.narrativa = value;
    }

    /**
     * Obtiene el valor de la propiedad remarks.
     * 
     * @return possible object is {@link String }
     */
    public String getREMARKS() {
        return remarks;
    }

    /**
     * Define el valor de la propiedad remarks.
     * 
     * @param value allowed object is {@link String }
     */
    public void setREMARKS(String value) {
        this.remarks = value;
    }

    /**
     * Obtiene el valor de la propiedad sucmov.
     * 
     * @return possible object is {@link String }
     */
    public String getSUCMOV() {
        return sucmov;
    }

    /**
     * Define el valor de la propiedad sucmov.
     * 
     * @param value allowed object is {@link String }
     */
    public void setSUCMOV(String value) {
        this.sucmov = value;
    }

    /**
     * Obtiene el valor de la propiedad nroinst.
     * 
     * @return possible object is {@link String }
     */
    public String getNROINST() {
        return nroinst;
    }

    /**
     * Define el valor de la propiedad nroinst.
     * 
     * @param value allowed object is {@link String }
     */
    public void setNROINST(String value) {
        this.nroinst = value;
    }

    /**
     * Obtiene el valor de la propiedad tipomov.
     * 
     * @return possible object is {@link String }
     */
    public String getTIPOMOV() {
        return tipomov;
    }

    /**
     * Define el valor de la propiedad tipomov.
     * 
     * @param value allowed object is {@link String }
     */
    public void setTIPOMOV(String value) {
        this.tipomov = value;
    }

    /**
     * Obtiene el valor de la propiedad monto.
     * 
     * @return possible object is {@link String }
     */
    public String getMONTO() {
        return monto;
    }

    /**
     * Define el valor de la propiedad monto.
     * 
     * @param value allowed object is {@link String }
     */
    public void setMONTO(String value) {
        this.monto = value;
    }

    /**
     * Permite obtener el valor del atributo tipo.
     *
     * @return retorna el valor del atributo tipo.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Permite setear el valor del atributo tipo.
     *
     * @param tipo nuevo valor para el atributo tipo.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Permite obtener el valor del atributo documento.
     *
     * @return retorna el valor del atributo documento.
     */
    public String getDocumento() {
        return documento;
    }

    /**
     * Permite setear el valor del atributo documento.
     *
     * @param documento nuevo valor para el atributo documento.
     */
    public void setDocumento(String documento) {
        this.documento = documento;
    }

    /**
     * Permite obtener el valor del atributo muestraLink.
     *
     * @return retorna el valor del atributo muestraLink.
     */
    public boolean isMuestraLink() {
        return muestraLink;
    }

    /**
     * Permite setear el valor del atributo muestraLink.
     *
     * @param muestraLink nuevo valor para el atributo muestraLink.
     */
    public void setMuestraLink(boolean muestraLink) {
        this.muestraLink = muestraLink;
    }

    /**
     * Permite obtener el valor del atributo fechaDesplegada.
     *
     * @return retorna el valor del atributo fechaDesplegada.
     */
    public String getFechaDesplegada() {
        return fechaDesplegada;
    }

    /**
     * Permite setear el valor del atributo fechaDesplegada.
     *
     * @param fechaDesplegada nuevo valor para el atributo fechaDesplegada.
     */
    public void setFechaDesplegada(String fechaDesplegada) {
        this.fechaDesplegada = fechaDesplegada;
    }

}
