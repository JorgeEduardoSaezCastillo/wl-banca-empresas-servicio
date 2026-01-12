
package cl.bice.nominas.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para consultaApoOpIn complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="consultaApoOpIn">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IN_NUM_OPER_PROG" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultaApoOpIn", propOrder = {
    "innumoperprog"
})
@XmlRootElement(name = "ConsultaApoOpIn")
public class ConsultaApoOpIn {

    @XmlElement(name = "IN_NUM_OPER_PROG", required = true)
    protected String innumoperprog;

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

}
