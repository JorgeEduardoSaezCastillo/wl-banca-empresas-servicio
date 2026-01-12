
package cl.bice.nominasenlinea.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Cons_Rest_Monto complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Cons_Rest_Monto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ConsRestMonto" type="{http://portal.ws.bice.cl/}consRestriccionMontoIn"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Cons_Rest_Monto", propOrder = {
    "consRestMonto"
})
public class ConsRestMonto {

    @XmlElement(name = "ConsRestMonto", required = true)
    protected ConsRestriccionMontoIn consRestMonto;

    /**
     * Obtiene el valor de la propiedad consRestMonto.
     * 
     * @return
     *     possible object is
     *     {@link ConsRestriccionMontoIn }
     *     
     */
    public ConsRestriccionMontoIn getConsRestMonto() {
        return consRestMonto;
    }

    /**
     * Define el valor de la propiedad consRestMonto.
     * 
     * @param value
     *     allowed object is
     *     {@link ConsRestriccionMontoIn }
     *     
     */
    public void setConsRestMonto(ConsRestriccionMontoIn value) {
        this.consRestMonto = value;
    }

}
