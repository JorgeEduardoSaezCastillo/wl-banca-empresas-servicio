
package cl.bice.tef.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Tpag5310RequestType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Tpag5310RequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Tpag5310Entrada" type="{http://www.bice.cl/tef/schemas}Tpag5310EntradaType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Tpag5310RequestType", propOrder = {
    "tpag5310Entrada"
})
public class Tpag5310RequestType {

    @XmlElement(name = "Tpag5310Entrada", required = true)
    protected Tpag5310EntradaType tpag5310Entrada;

    /**
     * Obtiene el valor de la propiedad tpag5310Entrada.
     * 
     * @return
     *     possible object is
     *     {@link Tpag5310EntradaType }
     *     
     */
    public Tpag5310EntradaType getTpag5310Entrada() {
        return tpag5310Entrada;
    }

    /**
     * Define el valor de la propiedad tpag5310Entrada.
     * 
     * @param value
     *     allowed object is
     *     {@link Tpag5310EntradaType }
     *     
     */
    public void setTpag5310Entrada(Tpag5310EntradaType value) {
        this.tpag5310Entrada = value;
    }

}
