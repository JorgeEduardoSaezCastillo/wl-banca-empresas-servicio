
package cl.bice.tef.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Tpag5100RequestType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Tpag5100RequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Tpag5100Entrada" type="{http://www.bice.cl/tef/schemas}Tpag5100EntradaType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Tpag5100RequestType", propOrder = {
    "tpag5100Entrada"
})
public class Tpag5100RequestType {

    @XmlElement(name = "Tpag5100Entrada", required = true)
    protected Tpag5100EntradaType tpag5100Entrada;

    /**
     * Obtiene el valor de la propiedad tpag5100Entrada.
     * 
     * @return
     *     possible object is
     *     {@link Tpag5100EntradaType }
     *     
     */
    public Tpag5100EntradaType getTpag5100Entrada() {
        return tpag5100Entrada;
    }

    /**
     * Define el valor de la propiedad tpag5100Entrada.
     * 
     * @param value
     *     allowed object is
     *     {@link Tpag5100EntradaType }
     *     
     */
    public void setTpag5100Entrada(Tpag5100EntradaType value) {
        this.tpag5100Entrada = value;
    }

}
