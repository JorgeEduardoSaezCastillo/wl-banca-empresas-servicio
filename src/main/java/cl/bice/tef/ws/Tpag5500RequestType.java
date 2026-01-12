
package cl.bice.tef.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Tpag5500RequestType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Tpag5500RequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Tpag5500Entrada" type="{http://www.bice.cl/tef/schemas}Tpag5500EntradaType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Tpag5500RequestType", propOrder = {
    "tpag5500Entrada"
})
public class Tpag5500RequestType {

    @XmlElement(name = "Tpag5500Entrada", required = true)
    protected Tpag5500EntradaType tpag5500Entrada;

    /**
     * Obtiene el valor de la propiedad tpag5500Entrada.
     * 
     * @return
     *     possible object is
     *     {@link Tpag5500EntradaType }
     *     
     */
    public Tpag5500EntradaType getTpag5500Entrada() {
        return tpag5500Entrada;
    }

    /**
     * Define el valor de la propiedad tpag5500Entrada.
     * 
     * @param value
     *     allowed object is
     *     {@link Tpag5500EntradaType }
     *     
     */
    public void setTpag5500Entrada(Tpag5500EntradaType value) {
        this.tpag5500Entrada = value;
    }

}
