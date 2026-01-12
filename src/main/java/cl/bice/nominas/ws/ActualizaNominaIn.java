
package cl.bice.nominas.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para actualizaNominaIn complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="actualizaNominaIn">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IN_NUM_NOMINA" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IN_COD_ESTADO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IN_COD_ACCION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IN_NOM_FILE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IN_FORMATO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IN_TIP_INGRESO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IN_FEC_CURSE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IN_FEC_REGISTRO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IN_NUM_CTA_CARGO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IN_NUM_REG_CARGO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IN_SRV_INGRESO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IN_SRV_CIERRE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IN_MAIL_ORDENANTE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "actualizaNominaIn", propOrder = {
    "innumnomina",
    "incodestado",
    "incodaccion",
    "innomfile",
    "informato",
    "intipingreso",
    "infeccurse",
    "infecregistro",
    "innumctacargo",
    "innumregcargo",
    "insrvingreso",
    "insrvcierre",
    "inmailordenante"
})
@XmlRootElement(name = "ActualizaNominaIn")
public class ActualizaNominaIn {

    @XmlElement(name = "IN_NUM_NOMINA", required = true)
    protected String innumnomina;
    @XmlElement(name = "IN_COD_ESTADO")
    protected String incodestado;
    @XmlElement(name = "IN_COD_ACCION")
    protected String incodaccion;
    @XmlElement(name = "IN_NOM_FILE")
    protected String innomfile;
    @XmlElement(name = "IN_FORMATO")
    protected String informato;
    @XmlElement(name = "IN_TIP_INGRESO")
    protected String intipingreso;
    @XmlElement(name = "IN_FEC_CURSE")
    protected String infeccurse;
    @XmlElement(name = "IN_FEC_REGISTRO")
    protected String infecregistro;
    @XmlElement(name = "IN_NUM_CTA_CARGO")
    protected String innumctacargo;
    @XmlElement(name = "IN_NUM_REG_CARGO")
    protected String innumregcargo;
    @XmlElement(name = "IN_SRV_INGRESO")
    protected String insrvingreso;
    @XmlElement(name = "IN_SRV_CIERRE")
    protected String insrvcierre;
    @XmlElement(name = "IN_MAIL_ORDENANTE")
    protected String inmailordenante;

    /**
     * Obtiene el valor de la propiedad innumnomina.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINNUMNOMINA() {
        return innumnomina;
    }

    /**
     * Define el valor de la propiedad innumnomina.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINNUMNOMINA(String value) {
        this.innumnomina = value;
    }

    /**
     * Obtiene el valor de la propiedad incodestado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINCODESTADO() {
        return incodestado;
    }

    /**
     * Define el valor de la propiedad incodestado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINCODESTADO(String value) {
        this.incodestado = value;
    }

    /**
     * Obtiene el valor de la propiedad incodaccion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINCODACCION() {
        return incodaccion;
    }

    /**
     * Define el valor de la propiedad incodaccion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINCODACCION(String value) {
        this.incodaccion = value;
    }

    /**
     * Obtiene el valor de la propiedad innomfile.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINNOMFILE() {
        return innomfile;
    }

    /**
     * Define el valor de la propiedad innomfile.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINNOMFILE(String value) {
        this.innomfile = value;
    }

    /**
     * Obtiene el valor de la propiedad informato.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINFORMATO() {
        return informato;
    }

    /**
     * Define el valor de la propiedad informato.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINFORMATO(String value) {
        this.informato = value;
    }

    /**
     * Obtiene el valor de la propiedad intipingreso.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINTIPINGRESO() {
        return intipingreso;
    }

    /**
     * Define el valor de la propiedad intipingreso.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINTIPINGRESO(String value) {
        this.intipingreso = value;
    }

    /**
     * Obtiene el valor de la propiedad infeccurse.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINFECCURSE() {
        return infeccurse;
    }

    /**
     * Define el valor de la propiedad infeccurse.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINFECCURSE(String value) {
        this.infeccurse = value;
    }

    /**
     * Obtiene el valor de la propiedad infecregistro.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINFECREGISTRO() {
        return infecregistro;
    }

    /**
     * Define el valor de la propiedad infecregistro.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINFECREGISTRO(String value) {
        this.infecregistro = value;
    }

    /**
     * Obtiene el valor de la propiedad innumctacargo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINNUMCTACARGO() {
        return innumctacargo;
    }

    /**
     * Define el valor de la propiedad innumctacargo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINNUMCTACARGO(String value) {
        this.innumctacargo = value;
    }

    /**
     * Obtiene el valor de la propiedad innumregcargo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINNUMREGCARGO() {
        return innumregcargo;
    }

    /**
     * Define el valor de la propiedad innumregcargo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINNUMREGCARGO(String value) {
        this.innumregcargo = value;
    }

    /**
     * Obtiene el valor de la propiedad insrvingreso.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINSRVINGRESO() {
        return insrvingreso;
    }

    /**
     * Define el valor de la propiedad insrvingreso.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINSRVINGRESO(String value) {
        this.insrvingreso = value;
    }

    /**
     * Obtiene el valor de la propiedad insrvcierre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINSRVCIERRE() {
        return insrvcierre;
    }

    /**
     * Define el valor de la propiedad insrvcierre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINSRVCIERRE(String value) {
        this.insrvcierre = value;
    }

    /**
     * Obtiene el valor de la propiedad inmailordenante.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINMAILORDENANTE() {
        return inmailordenante;
    }

    /**
     * Define el valor de la propiedad inmailordenante.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINMAILORDENANTE(String value) {
        this.inmailordenante = value;
    }

}
