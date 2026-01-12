
package cl.bice.genericoperprog.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para registraApoOpIn complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="registraApoOpIn">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IN_RUT_APODERADO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IN_NUM_OPER_PROG" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IN_NOM_APODERADO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IN_DATO_FIRMA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "registraApoOpIn", propOrder = {
    "inrutapoderado",
    "innumoperprog",
    "innomapoderado",
    "indatofirma"
})
public class RegistraApoOpIn {

    @XmlElement(name = "IN_RUT_APODERADO", required = true)
    protected String inrutapoderado;
    @XmlElement(name = "IN_NUM_OPER_PROG", required = true)
    protected String innumoperprog;
    @XmlElement(name = "IN_NOM_APODERADO")
    protected String innomapoderado;
    @XmlElement(name = "IN_DATO_FIRMA")
    protected String indatofirma;

    /**
     * Obtiene el valor de la propiedad inrutapoderado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINRUTAPODERADO() {
        return inrutapoderado;
    }

    /**
     * Define el valor de la propiedad inrutapoderado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINRUTAPODERADO(String value) {
        this.inrutapoderado = value;
    }

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
     * Obtiene el valor de la propiedad innomapoderado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINNOMAPODERADO() {
        return innomapoderado;
    }

    /**
     * Define el valor de la propiedad innomapoderado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINNOMAPODERADO(String value) {
        this.innomapoderado = value;
    }

    /**
     * Obtiene el valor de la propiedad indatofirma.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINDATOFIRMA() {
        return indatofirma;
    }

    /**
     * Define el valor de la propiedad indatofirma.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINDATOFIRMA(String value) {
        this.indatofirma = value;
    }

}
