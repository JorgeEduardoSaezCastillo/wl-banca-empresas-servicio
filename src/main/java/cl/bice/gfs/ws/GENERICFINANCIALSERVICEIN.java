
package cl.bice.gfs.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CANAL" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SUCURSAL" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MODOTRX" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MODOINVOCACION" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RUTCLIENTE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TRACENUMBER" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FECHACONTABLE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MONEDACARGO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="BANCOCARGO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CUENTACARGO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TIPOCUENTACARGO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MONTOCARGO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RUTABONO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NOMBREABONO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MONEDAABONO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CUENTABONO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TIPOCUENTABONO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IDSERVICIO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="REFERENCIA" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DOCCARGO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DOCABONO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "canal",
    "sucursal",
    "modotrx",
    "modoinvocacion",
    "rutcliente",
    "tracenumber",
    "fechacontable",
    "monedacargo",
    "bancocargo",
    "cuentacargo",
    "tipocuentacargo",
    "montocargo",
    "rutabono",
    "nombreabono",
    "monedaabono",
    "cuentabono",
    "tipocuentabono",
    "idservicio",
    "referencia",
    "doccargo",
    "docabono"
})
@XmlRootElement(name = "GENERICFINANCIALSERVICE_IN")
public class GENERICFINANCIALSERVICEIN {

    @XmlElement(name = "CANAL", required = true)
    protected String canal;
    @XmlElement(name = "SUCURSAL", required = true)
    protected String sucursal;
    @XmlElement(name = "MODOTRX", required = true)
    protected String modotrx;
    @XmlElement(name = "MODOINVOCACION", required = true)
    protected String modoinvocacion;
    @XmlElement(name = "RUTCLIENTE", required = true)
    protected String rutcliente;
    @XmlElement(name = "TRACENUMBER", required = true)
    protected String tracenumber;
    @XmlElement(name = "FECHACONTABLE", required = true)
    protected String fechacontable;
    @XmlElement(name = "MONEDACARGO", required = true)
    protected String monedacargo;
    @XmlElement(name = "BANCOCARGO", required = true)
    protected String bancocargo;
    @XmlElement(name = "CUENTACARGO", required = true)
    protected String cuentacargo;
    @XmlElement(name = "TIPOCUENTACARGO", required = true)
    protected String tipocuentacargo;
    @XmlElement(name = "MONTOCARGO", required = true)
    protected String montocargo;
    @XmlElement(name = "RUTABONO", required = true)
    protected String rutabono;
    @XmlElement(name = "NOMBREABONO", required = true)
    protected String nombreabono;
    @XmlElement(name = "MONEDAABONO", required = true)
    protected String monedaabono;
    @XmlElement(name = "CUENTABONO", required = true)
    protected String cuentabono;
    @XmlElement(name = "TIPOCUENTABONO", required = true)
    protected String tipocuentabono;
    @XmlElement(name = "IDSERVICIO", required = true)
    protected String idservicio;
    @XmlElement(name = "REFERENCIA", required = true)
    protected String referencia;
    @XmlElement(name = "DOCCARGO", required = true)
    protected String doccargo;
    @XmlElement(name = "DOCABONO", required = true)
    protected String docabono;

    /**
     * Obtiene el valor de la propiedad canal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCANAL() {
        return canal;
    }

    /**
     * Define el valor de la propiedad canal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCANAL(String value) {
        this.canal = value;
    }

    /**
     * Obtiene el valor de la propiedad sucursal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSUCURSAL() {
        return sucursal;
    }

    /**
     * Define el valor de la propiedad sucursal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSUCURSAL(String value) {
        this.sucursal = value;
    }

    /**
     * Obtiene el valor de la propiedad modotrx.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMODOTRX() {
        return modotrx;
    }

    /**
     * Define el valor de la propiedad modotrx.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMODOTRX(String value) {
        this.modotrx = value;
    }

    /**
     * Obtiene el valor de la propiedad modoinvocacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMODOINVOCACION() {
        return modoinvocacion;
    }

    /**
     * Define el valor de la propiedad modoinvocacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMODOINVOCACION(String value) {
        this.modoinvocacion = value;
    }

    /**
     * Obtiene el valor de la propiedad rutcliente.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRUTCLIENTE() {
        return rutcliente;
    }

    /**
     * Define el valor de la propiedad rutcliente.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRUTCLIENTE(String value) {
        this.rutcliente = value;
    }

    /**
     * Obtiene el valor de la propiedad tracenumber.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTRACENUMBER() {
        return tracenumber;
    }

    /**
     * Define el valor de la propiedad tracenumber.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTRACENUMBER(String value) {
        this.tracenumber = value;
    }

    /**
     * Obtiene el valor de la propiedad fechacontable.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFECHACONTABLE() {
        return fechacontable;
    }

    /**
     * Define el valor de la propiedad fechacontable.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFECHACONTABLE(String value) {
        this.fechacontable = value;
    }

    /**
     * Obtiene el valor de la propiedad monedacargo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMONEDACARGO() {
        return monedacargo;
    }

    /**
     * Define el valor de la propiedad monedacargo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMONEDACARGO(String value) {
        this.monedacargo = value;
    }

    /**
     * Obtiene el valor de la propiedad bancocargo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBANCOCARGO() {
        return bancocargo;
    }

    /**
     * Define el valor de la propiedad bancocargo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBANCOCARGO(String value) {
        this.bancocargo = value;
    }

    /**
     * Obtiene el valor de la propiedad cuentacargo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCUENTACARGO() {
        return cuentacargo;
    }

    /**
     * Define el valor de la propiedad cuentacargo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCUENTACARGO(String value) {
        this.cuentacargo = value;
    }

    /**
     * Obtiene el valor de la propiedad tipocuentacargo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTIPOCUENTACARGO() {
        return tipocuentacargo;
    }

    /**
     * Define el valor de la propiedad tipocuentacargo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTIPOCUENTACARGO(String value) {
        this.tipocuentacargo = value;
    }

    /**
     * Obtiene el valor de la propiedad montocargo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMONTOCARGO() {
        return montocargo;
    }

    /**
     * Define el valor de la propiedad montocargo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMONTOCARGO(String value) {
        this.montocargo = value;
    }

    /**
     * Obtiene el valor de la propiedad rutabono.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRUTABONO() {
        return rutabono;
    }

    /**
     * Define el valor de la propiedad rutabono.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRUTABONO(String value) {
        this.rutabono = value;
    }

    /**
     * Obtiene el valor de la propiedad nombreabono.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNOMBREABONO() {
        return nombreabono;
    }

    /**
     * Define el valor de la propiedad nombreabono.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNOMBREABONO(String value) {
        this.nombreabono = value;
    }

    /**
     * Obtiene el valor de la propiedad monedaabono.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMONEDAABONO() {
        return monedaabono;
    }

    /**
     * Define el valor de la propiedad monedaabono.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMONEDAABONO(String value) {
        this.monedaabono = value;
    }

    /**
     * Obtiene el valor de la propiedad cuentabono.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCUENTABONO() {
        return cuentabono;
    }

    /**
     * Define el valor de la propiedad cuentabono.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCUENTABONO(String value) {
        this.cuentabono = value;
    }

    /**
     * Obtiene el valor de la propiedad tipocuentabono.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTIPOCUENTABONO() {
        return tipocuentabono;
    }

    /**
     * Define el valor de la propiedad tipocuentabono.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTIPOCUENTABONO(String value) {
        this.tipocuentabono = value;
    }

    /**
     * Obtiene el valor de la propiedad idservicio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIDSERVICIO() {
        return idservicio;
    }

    /**
     * Define el valor de la propiedad idservicio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIDSERVICIO(String value) {
        this.idservicio = value;
    }

    /**
     * Obtiene el valor de la propiedad referencia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getREFERENCIA() {
        return referencia;
    }

    /**
     * Define el valor de la propiedad referencia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setREFERENCIA(String value) {
        this.referencia = value;
    }

    /**
     * Obtiene el valor de la propiedad doccargo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDOCCARGO() {
        return doccargo;
    }

    /**
     * Define el valor de la propiedad doccargo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDOCCARGO(String value) {
        this.doccargo = value;
    }

    /**
     * Obtiene el valor de la propiedad docabono.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDOCABONO() {
        return docabono;
    }

    /**
     * Define el valor de la propiedad docabono.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDOCABONO(String value) {
        this.docabono = value;
    }

}
