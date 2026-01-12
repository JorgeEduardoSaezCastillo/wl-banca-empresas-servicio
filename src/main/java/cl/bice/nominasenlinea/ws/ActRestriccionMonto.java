
package cl.bice.nominasenlinea.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Act_Restriccion_Monto complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Act_Restriccion_Monto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ActRestriccionMonto" type="{http://portal.ws.bice.cl/}actRestriccionMontoIn"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Act_Restriccion_Monto", propOrder = {
    "actRestriccionMonto"
})
public class ActRestriccionMonto {

    @XmlElement(name = "ActRestriccionMonto", required = true)
    protected ActRestriccionMontoIn actRestriccionMonto;

    /**
     * Obtiene el valor de la propiedad actRestriccionMonto.
     * 
     * @return
     *     possible object is
     *     {@link ActRestriccionMontoIn }
     *     
     */
    public ActRestriccionMontoIn getActRestriccionMonto() {
        return actRestriccionMonto;
    }

    /**
     * Define el valor de la propiedad actRestriccionMonto.
     * 
     * @param value
     *     allowed object is
     *     {@link ActRestriccionMontoIn }
     *     
     */
    public void setActRestriccionMonto(ActRestriccionMontoIn value) {
        this.actRestriccionMonto = value;
    }

}
