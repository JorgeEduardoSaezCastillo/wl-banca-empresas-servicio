
package cl.bice.nominas.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Reversa_Nomina complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Reversa_Nomina">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="reversaNomina" type="{http://ws.nominas.bice.cl/}reversaNominaIn"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Reversa_Nomina", propOrder = {
    "reversaNomina"
})
public class ReversaNomina {

    @XmlElement(required = true)
    protected ReversaNominaIn reversaNomina;

    /**
     * Obtiene el valor de la propiedad reversaNomina.
     * 
     * @return
     *     possible object is
     *     {@link ReversaNominaIn }
     *     
     */
    public ReversaNominaIn getReversaNomina() {
        return reversaNomina;
    }

    /**
     * Define el valor de la propiedad reversaNomina.
     * 
     * @param value
     *     allowed object is
     *     {@link ReversaNominaIn }
     *     
     */
    public void setReversaNomina(ReversaNominaIn value) {
        this.reversaNomina = value;
    }

}
