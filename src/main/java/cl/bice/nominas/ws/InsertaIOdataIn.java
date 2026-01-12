
package cl.bice.nominas.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para insertaIOdataIn complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="insertaIOdataIn">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IN_COD_SERVICIO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IN_COD_TIPCTA" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IN_MONTO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IN_COD_BANCO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IN_NUM_CTATIT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IN_RUT_TITULAR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IN_NOM_TITUTAL" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IN_COD_MONEDA" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IN_NOM_OFICINA_ORI" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IN_NOM_OFICINA_DES" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IN_NUM_NOMINA" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IN_NOM_LLASER" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IN_NOM_ABOCAR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IN_NUM_NROABO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IN_RUT_ORIGEN" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IN_NOM_ORIGEN" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IN_NUM_CUENTAORI" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IN_GLS_GLOSA" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IN_FEC_FECHA" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IN_FLG_TIPOPAGO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IN_MAIL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "insertaIOdataIn", propOrder = {
    "incodservicio",
    "incodtipcta",
    "inmonto",
    "incodbanco",
    "innumctatit",
    "inruttitular",
    "innomtitutal",
    "incodmoneda",
    "innomoficinaori",
    "innomoficinades",
    "innumnomina",
    "innomllaser",
    "innomabocar",
    "innumnroabo",
    "inrutorigen",
    "innomorigen",
    "innumcuentaori",
    "inglsglosa",
    "infecfecha",
    "inflgtipopago",
    "inmail"
})
public class InsertaIOdataIn {

    @XmlElement(name = "IN_COD_SERVICIO", required = true)
    protected String incodservicio;
    @XmlElement(name = "IN_COD_TIPCTA", required = true)
    protected String incodtipcta;
    @XmlElement(name = "IN_MONTO", required = true)
    protected String inmonto;
    @XmlElement(name = "IN_COD_BANCO", required = true)
    protected String incodbanco;
    @XmlElement(name = "IN_NUM_CTATIT", required = true)
    protected String innumctatit;
    @XmlElement(name = "IN_RUT_TITULAR", required = true)
    protected String inruttitular;
    @XmlElement(name = "IN_NOM_TITUTAL", required = true)
    protected String innomtitutal;
    @XmlElement(name = "IN_COD_MONEDA", required = true)
    protected String incodmoneda;
    @XmlElement(name = "IN_NOM_OFICINA_ORI", required = true)
    protected String innomoficinaori;
    @XmlElement(name = "IN_NOM_OFICINA_DES", required = true)
    protected String innomoficinades;
    @XmlElement(name = "IN_NUM_NOMINA", required = true)
    protected String innumnomina;
    @XmlElement(name = "IN_NOM_LLASER", required = true)
    protected String innomllaser;
    @XmlElement(name = "IN_NOM_ABOCAR", required = true)
    protected String innomabocar;
    @XmlElement(name = "IN_NUM_NROABO", required = true)
    protected String innumnroabo;
    @XmlElement(name = "IN_RUT_ORIGEN", required = true)
    protected String inrutorigen;
    @XmlElement(name = "IN_NOM_ORIGEN", required = true)
    protected String innomorigen;
    @XmlElement(name = "IN_NUM_CUENTAORI", required = true)
    protected String innumcuentaori;
    @XmlElement(name = "IN_GLS_GLOSA", required = true)
    protected String inglsglosa;
    @XmlElement(name = "IN_FEC_FECHA", required = true)
    protected String infecfecha;
    @XmlElement(name = "IN_FLG_TIPOPAGO", required = true)
    protected String inflgtipopago;
    @XmlElement(name = "IN_MAIL")
    protected String inmail;

    /**
     * Obtiene el valor de la propiedad incodservicio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINCODSERVICIO() {
        return incodservicio;
    }

    /**
     * Define el valor de la propiedad incodservicio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINCODSERVICIO(String value) {
        this.incodservicio = value;
    }

    /**
     * Obtiene el valor de la propiedad incodtipcta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINCODTIPCTA() {
        return incodtipcta;
    }

    /**
     * Define el valor de la propiedad incodtipcta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINCODTIPCTA(String value) {
        this.incodtipcta = value;
    }

    /**
     * Obtiene el valor de la propiedad inmonto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINMONTO() {
        return inmonto;
    }

    /**
     * Define el valor de la propiedad inmonto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINMONTO(String value) {
        this.inmonto = value;
    }

    /**
     * Obtiene el valor de la propiedad incodbanco.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINCODBANCO() {
        return incodbanco;
    }

    /**
     * Define el valor de la propiedad incodbanco.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINCODBANCO(String value) {
        this.incodbanco = value;
    }

    /**
     * Obtiene el valor de la propiedad innumctatit.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINNUMCTATIT() {
        return innumctatit;
    }

    /**
     * Define el valor de la propiedad innumctatit.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINNUMCTATIT(String value) {
        this.innumctatit = value;
    }

    /**
     * Obtiene el valor de la propiedad inruttitular.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINRUTTITULAR() {
        return inruttitular;
    }

    /**
     * Define el valor de la propiedad inruttitular.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINRUTTITULAR(String value) {
        this.inruttitular = value;
    }

    /**
     * Obtiene el valor de la propiedad innomtitutal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINNOMTITUTAL() {
        return innomtitutal;
    }

    /**
     * Define el valor de la propiedad innomtitutal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINNOMTITUTAL(String value) {
        this.innomtitutal = value;
    }

    /**
     * Obtiene el valor de la propiedad incodmoneda.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINCODMONEDA() {
        return incodmoneda;
    }

    /**
     * Define el valor de la propiedad incodmoneda.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINCODMONEDA(String value) {
        this.incodmoneda = value;
    }

    /**
     * Obtiene el valor de la propiedad innomoficinaori.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINNOMOFICINAORI() {
        return innomoficinaori;
    }

    /**
     * Define el valor de la propiedad innomoficinaori.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINNOMOFICINAORI(String value) {
        this.innomoficinaori = value;
    }

    /**
     * Obtiene el valor de la propiedad innomoficinades.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINNOMOFICINADES() {
        return innomoficinades;
    }

    /**
     * Define el valor de la propiedad innomoficinades.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINNOMOFICINADES(String value) {
        this.innomoficinades = value;
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
     * Obtiene el valor de la propiedad innomllaser.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINNOMLLASER() {
        return innomllaser;
    }

    /**
     * Define el valor de la propiedad innomllaser.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINNOMLLASER(String value) {
        this.innomllaser = value;
    }

    /**
     * Obtiene el valor de la propiedad innomabocar.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINNOMABOCAR() {
        return innomabocar;
    }

    /**
     * Define el valor de la propiedad innomabocar.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINNOMABOCAR(String value) {
        this.innomabocar = value;
    }

    /**
     * Obtiene el valor de la propiedad innumnroabo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINNUMNROABO() {
        return innumnroabo;
    }

    /**
     * Define el valor de la propiedad innumnroabo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINNUMNROABO(String value) {
        this.innumnroabo = value;
    }

    /**
     * Obtiene el valor de la propiedad inrutorigen.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINRUTORIGEN() {
        return inrutorigen;
    }

    /**
     * Define el valor de la propiedad inrutorigen.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINRUTORIGEN(String value) {
        this.inrutorigen = value;
    }

    /**
     * Obtiene el valor de la propiedad innomorigen.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINNOMORIGEN() {
        return innomorigen;
    }

    /**
     * Define el valor de la propiedad innomorigen.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINNOMORIGEN(String value) {
        this.innomorigen = value;
    }

    /**
     * Obtiene el valor de la propiedad innumcuentaori.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINNUMCUENTAORI() {
        return innumcuentaori;
    }

    /**
     * Define el valor de la propiedad innumcuentaori.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINNUMCUENTAORI(String value) {
        this.innumcuentaori = value;
    }

    /**
     * Obtiene el valor de la propiedad inglsglosa.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINGLSGLOSA() {
        return inglsglosa;
    }

    /**
     * Define el valor de la propiedad inglsglosa.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINGLSGLOSA(String value) {
        this.inglsglosa = value;
    }

    /**
     * Obtiene el valor de la propiedad infecfecha.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINFECFECHA() {
        return infecfecha;
    }

    /**
     * Define el valor de la propiedad infecfecha.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINFECFECHA(String value) {
        this.infecfecha = value;
    }

    /**
     * Obtiene el valor de la propiedad inflgtipopago.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINFLGTIPOPAGO() {
        return inflgtipopago;
    }

    /**
     * Define el valor de la propiedad inflgtipopago.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINFLGTIPOPAGO(String value) {
        this.inflgtipopago = value;
    }

    /**
     * Obtiene el valor de la propiedad inmail.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINMAIL() {
        return inmail;
    }

    /**
     * Define el valor de la propiedad inmail.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINMAIL(String value) {
        this.inmail = value;
    }

}
