
package cl.bice.tef.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Tpag5110RequestType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Tpag5110RequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Tpag5110Entrada" type="{http://www.bice.cl/tef/schemas}Tpag5110EntradaType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Tpag5110RequestType", propOrder = {
    "tpag5110Entrada"
})
public class Tpag5110RequestType {

    @XmlElement(name = "Tpag5110Entrada", required = true)
    protected Tpag5110EntradaType tpag5110Entrada;

    /**
     * Obtiene el valor de la propiedad tpag5110Entrada.
     * 
     * @return
     *     possible object is
     *     {@link Tpag5110EntradaType }
     *     
     */
    public Tpag5110EntradaType getTpag5110Entrada() {
        return tpag5110Entrada;
    }

    /**
     * Define el valor de la propiedad tpag5110Entrada.
     * 
     * @param value
     *     allowed object is
     *     {@link Tpag5110EntradaType }
     *     
     */
    public void setTpag5110Entrada(Tpag5110EntradaType value) {
        this.tpag5110Entrada = value;
    }

}
