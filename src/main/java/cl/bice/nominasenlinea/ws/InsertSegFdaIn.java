
package cl.bice.nominasenlinea.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para insertSegFdaIn complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="insertSegFdaIn">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IN_COD_OPER" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IN_RUT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IN_ORIGEN" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="IN_NUM_OPER" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IN_EMPRESA" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IN_USER_AGENT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "insertSegFdaIn", propOrder = {
    "incodoper",
    "inrut",
    "inorigen",
    "innumoper",
    "inempresa",
    "inuseragent"
})
public class InsertSegFdaIn {

    @XmlElement(name = "IN_COD_OPER", required = true)
    protected String incodoper;
    @XmlElement(name = "IN_RUT", required = true)
    protected String inrut;
    @XmlElement(name = "IN_ORIGEN")
    protected int inorigen;
    @XmlElement(name = "IN_NUM_OPER", required = true)
    protected String innumoper;
    @XmlElement(name = "IN_EMPRESA", required = true)
    protected String inempresa;
    @XmlElement(name = "IN_USER_AGENT", required = true)
    protected String inuseragent;

    /**
     * Obtiene el valor de la propiedad incodoper.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINCODOPER() {
        return incodoper;
    }

    /**
     * Define el valor de la propiedad incodoper.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINCODOPER(String value) {
        this.incodoper = value;
    }

    /**
     * Obtiene el valor de la propiedad inrut.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINRUT() {
        return inrut;
    }

    /**
     * Define el valor de la propiedad inrut.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINRUT(String value) {
        this.inrut = value;
    }

    /**
     * Obtiene el valor de la propiedad inorigen.
     * 
     */
    public int getINORIGEN() {
        return inorigen;
    }

    /**
     * Define el valor de la propiedad inorigen.
     * 
     */
    public void setINORIGEN(int value) {
        this.inorigen = value;
    }

    /**
     * Obtiene el valor de la propiedad innumoper.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINNUMOPER() {
        return innumoper;
    }

    /**
     * Define el valor de la propiedad innumoper.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINNUMOPER(String value) {
        this.innumoper = value;
    }

    /**
     * Obtiene el valor de la propiedad inempresa.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINEMPRESA() {
        return inempresa;
    }

    /**
     * Define el valor de la propiedad inempresa.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINEMPRESA(String value) {
        this.inempresa = value;
    }

    /**
     * Obtiene el valor de la propiedad inuseragent.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINUSERAGENT() {
        return inuseragent;
    }

    /**
     * Define el valor de la propiedad inuseragent.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINUSERAGENT(String value) {
        this.inuseragent = value;
    }

}
