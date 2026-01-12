
package cl.bice.nominas.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para actualizaOpIn complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="actualizaOpIn">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IN_NUM_OPER_PROG" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IN_ESTADO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IN_ACCION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IN_NOM_REGISTRO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IN_RUT_REGISTRO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IN_FEC_REGISTRO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IN_HR_REGISTRO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IN_FEC_CURSE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IN_HR_CURSE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IN_FEC_PRESCRIPCION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IN_HR_PRESCRIPCION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IN_RUT_CLIENTE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IN_NOM_CLIENTE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IN_NUM_OPER_TRF" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IN_NUM_NOMINA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IN_NUM_OP_ANULA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IN_COD_EJECUTOR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "actualizaOpIn", propOrder = {
    "innumoperprog",
    "inestado",
    "inaccion",
    "innomregistro",
    "inrutregistro",
    "infecregistro",
    "inhrregistro",
    "infeccurse",
    "inhrcurse",
    "infecprescripcion",
    "inhrprescripcion",
    "inrutcliente",
    "innomcliente",
    "innumopertrf",
    "innumnomina",
    "innumopanula",
    "incodejecutor"
})
@XmlRootElement(name = "ActualizaOpIn")
public class ActualizaOpIn {

    @XmlElement(name = "IN_NUM_OPER_PROG", required = true)
    protected String innumoperprog;
    @XmlElement(name = "IN_ESTADO")
    protected String inestado;
    @XmlElement(name = "IN_ACCION")
    protected String inaccion;
    @XmlElement(name = "IN_NOM_REGISTRO")
    protected String innomregistro;
    @XmlElement(name = "IN_RUT_REGISTRO")
    protected String inrutregistro;
    @XmlElement(name = "IN_FEC_REGISTRO")
    protected String infecregistro;
    @XmlElement(name = "IN_HR_REGISTRO")
    protected String inhrregistro;
    @XmlElement(name = "IN_FEC_CURSE")
    protected String infeccurse;
    @XmlElement(name = "IN_HR_CURSE")
    protected String inhrcurse;
    @XmlElement(name = "IN_FEC_PRESCRIPCION")
    protected String infecprescripcion;
    @XmlElement(name = "IN_HR_PRESCRIPCION")
    protected String inhrprescripcion;
    @XmlElement(name = "IN_RUT_CLIENTE")
    protected String inrutcliente;
    @XmlElement(name = "IN_NOM_CLIENTE")
    protected String innomcliente;
    @XmlElement(name = "IN_NUM_OPER_TRF")
    protected String innumopertrf;
    @XmlElement(name = "IN_NUM_NOMINA")
    protected String innumnomina;
    @XmlElement(name = "IN_NUM_OP_ANULA")
    protected String innumopanula;
    @XmlElement(name = "IN_COD_EJECUTOR")
    protected String incodejecutor;

    /**
     * Obtiene el valor de la propiedad innumoperprog.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINNUMOPERPROG() {
        return innumoperprog;
    }

    /**
     * Define el valor de la propiedad innumoperprog.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINNUMOPERPROG(String value) {
        this.innumoperprog = value;
    }

    /**
     * Obtiene el valor de la propiedad inestado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINESTADO() {
        return inestado;
    }

    /**
     * Define el valor de la propiedad inestado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINESTADO(String value) {
        this.inestado = value;
    }

    /**
     * Obtiene el valor de la propiedad inaccion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINACCION() {
        return inaccion;
    }

    /**
     * Define el valor de la propiedad inaccion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINACCION(String value) {
        this.inaccion = value;
    }

    /**
     * Obtiene el valor de la propiedad innomregistro.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINNOMREGISTRO() {
        return innomregistro;
    }

    /**
     * Define el valor de la propiedad innomregistro.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINNOMREGISTRO(String value) {
        this.innomregistro = value;
    }

    /**
     * Obtiene el valor de la propiedad inrutregistro.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINRUTREGISTRO() {
        return inrutregistro;
    }

    /**
     * Define el valor de la propiedad inrutregistro.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINRUTREGISTRO(String value) {
        this.inrutregistro = value;
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
     * Obtiene el valor de la propiedad inhrregistro.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINHRREGISTRO() {
        return inhrregistro;
    }

    /**
     * Define el valor de la propiedad inhrregistro.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINHRREGISTRO(String value) {
        this.inhrregistro = value;
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
     * Obtiene el valor de la propiedad inhrcurse.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINHRCURSE() {
        return inhrcurse;
    }

    /**
     * Define el valor de la propiedad inhrcurse.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINHRCURSE(String value) {
        this.inhrcurse = value;
    }

    /**
     * Obtiene el valor de la propiedad infecprescripcion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINFECPRESCRIPCION() {
        return infecprescripcion;
    }

    /**
     * Define el valor de la propiedad infecprescripcion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINFECPRESCRIPCION(String value) {
        this.infecprescripcion = value;
    }

    /**
     * Obtiene el valor de la propiedad inhrprescripcion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINHRPRESCRIPCION() {
        return inhrprescripcion;
    }

    /**
     * Define el valor de la propiedad inhrprescripcion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINHRPRESCRIPCION(String value) {
        this.inhrprescripcion = value;
    }

    /**
     * Obtiene el valor de la propiedad inrutcliente.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINRUTCLIENTE() {
        return inrutcliente;
    }

    /**
     * Define el valor de la propiedad inrutcliente.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINRUTCLIENTE(String value) {
        this.inrutcliente = value;
    }

    /**
     * Obtiene el valor de la propiedad innomcliente.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINNOMCLIENTE() {
        return innomcliente;
    }

    /**
     * Define el valor de la propiedad innomcliente.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINNOMCLIENTE(String value) {
        this.innomcliente = value;
    }

    /**
     * Obtiene el valor de la propiedad innumopertrf.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINNUMOPERTRF() {
        return innumopertrf;
    }

    /**
     * Define el valor de la propiedad innumopertrf.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINNUMOPERTRF(String value) {
        this.innumopertrf = value;
    }

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
     * Obtiene el valor de la propiedad innumopanula.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINNUMOPANULA() {
        return innumopanula;
    }

    /**
     * Define el valor de la propiedad innumopanula.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINNUMOPANULA(String value) {
        this.innumopanula = value;
    }

    /**
     * Obtiene el valor de la propiedad incodejecutor.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINCODEJECUTOR() {
        return incodejecutor;
    }

    /**
     * Define el valor de la propiedad incodejecutor.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINCODEJECUTOR(String value) {
        this.incodejecutor = value;
    }

}
