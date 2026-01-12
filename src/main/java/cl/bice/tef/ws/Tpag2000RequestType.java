
package cl.bice.tef.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Tpag2000RequestType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Tpag2000RequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Tpag2000Entrada" type="{http://www.bice.cl/tef/schemas}Tpag2000EntradaType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Tpag2000RequestType", propOrder = {
    "tpag2000Entrada"
})
@XmlRootElement(name = "Tpag2000RequestType")
public class Tpag2000RequestType {

    @XmlElement(name = "Tpag2000Entrada", required = true)
    protected Tpag2000EntradaType tpag2000Entrada;

    /**
     * Obtiene el valor de la propiedad tpag2000Entrada.
     * 
     * @return
     *     possible object is
     *     {@link Tpag2000EntradaType }
     *     
     */
    public Tpag2000EntradaType getTpag2000Entrada() {
        return tpag2000Entrada;
    }

    /**
     * Define el valor de la propiedad tpag2000Entrada.
     * 
     * @param value
     *     allowed object is
     *     {@link Tpag2000EntradaType }
     *     
     */
    public void setTpag2000Entrada(Tpag2000EntradaType value) {
        this.tpag2000Entrada = value;
    }

}
