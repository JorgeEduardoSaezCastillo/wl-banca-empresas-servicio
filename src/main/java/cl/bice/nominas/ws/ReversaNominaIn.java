
package cl.bice.nominas.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para reversaNominaIn complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="reversaNominaIn">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IN_NUM_NOMINA" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IN_FEC_NOMINA" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "reversaNominaIn", propOrder = {
    "innumnomina",
    "infecnomina"
})
public class ReversaNominaIn {

    @XmlElement(name = "IN_NUM_NOMINA", required = true)
    protected String innumnomina;
    @XmlElement(name = "IN_FEC_NOMINA", required = true)
    protected String infecnomina;

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
     * Obtiene el valor de la propiedad infecnomina.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINFECNOMINA() {
        return infecnomina;
    }

    /**
     * Define el valor de la propiedad infecnomina.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINFECNOMINA(String value) {
        this.infecnomina = value;
    }

}
