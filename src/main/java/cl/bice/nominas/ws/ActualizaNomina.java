
package cl.bice.nominas.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Actualiza_Nomina complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Actualiza_Nomina">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="actualizaNomina" type="{http://ws.nominas.bice.cl/}actualizaNominaIn"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Actualiza_Nomina", propOrder = {
    "actualizaNomina"
})
public class ActualizaNomina {

    @XmlElement(required = true)
    protected ActualizaNominaIn actualizaNomina;

    /**
     * Obtiene el valor de la propiedad actualizaNomina.
     * 
     * @return
     *     possible object is
     *     {@link ActualizaNominaIn }
     *     
     */
    public ActualizaNominaIn getActualizaNomina() {
        return actualizaNomina;
    }

    /**
     * Define el valor de la propiedad actualizaNomina.
     * 
     * @param value
     *     allowed object is
     *     {@link ActualizaNominaIn }
     *     
     */
    public void setActualizaNomina(ActualizaNominaIn value) {
        this.actualizaNomina = value;
    }

}
