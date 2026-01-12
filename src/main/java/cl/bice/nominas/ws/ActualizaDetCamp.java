
package cl.bice.nominas.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Actualiza_Det_Camp complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Actualiza_Det_Camp">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="actualizaDetCamp" type="{http://ws.nominas.bice.cl/}actualizaDetCampIn"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Actualiza_Det_Camp", propOrder = {
    "actualizaDetCamp"
})
public class ActualizaDetCamp {

    @XmlElement(required = true)
    protected ActualizaDetCampIn actualizaDetCamp;

    /**
     * Obtiene el valor de la propiedad actualizaDetCamp.
     * 
     * @return
     *     possible object is
     *     {@link ActualizaDetCampIn }
     *     
     */
    public ActualizaDetCampIn getActualizaDetCamp() {
        return actualizaDetCamp;
    }

    /**
     * Define el valor de la propiedad actualizaDetCamp.
     * 
     * @param value
     *     allowed object is
     *     {@link ActualizaDetCampIn }
     *     
     */
    public void setActualizaDetCamp(ActualizaDetCampIn value) {
        this.actualizaDetCamp = value;
    }

}
