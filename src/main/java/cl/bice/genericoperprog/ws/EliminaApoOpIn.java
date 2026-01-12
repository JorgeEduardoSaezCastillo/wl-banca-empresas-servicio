
package cl.bice.genericoperprog.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para eliminaApoOpIn complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="eliminaApoOpIn">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IN_NUM_OPER_PROG" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IN_RUT_APODERADO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "eliminaApoOpIn", propOrder = {
    "innumoperprog",
    "inrutapoderado"
})
public class EliminaApoOpIn {

    @XmlElement(name = "IN_NUM_OPER_PROG", required = true)
    protected String innumoperprog;
    @XmlElement(name = "IN_RUT_APODERADO")
    protected String inrutapoderado;

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

}
