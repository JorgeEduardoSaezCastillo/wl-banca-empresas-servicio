
package cl.bice.tef.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Tpag5510RequestType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Tpag5510RequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Tpag5510Entrada" type="{http://www.bice.cl/tef/schemas}Tpag5510EntradaType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Tpag5510RequestType", propOrder = {
    "tpag5510Entrada"
})
public class Tpag5510RequestType {

    @XmlElement(name = "Tpag5510Entrada", required = true)
    protected Tpag5510EntradaType tpag5510Entrada;

    /**
     * Obtiene el valor de la propiedad tpag5510Entrada.
     * 
     * @return
     *     possible object is
     *     {@link Tpag5510EntradaType }
     *     
     */
    public Tpag5510EntradaType getTpag5510Entrada() {
        return tpag5510Entrada;
    }

    /**
     * Define el valor de la propiedad tpag5510Entrada.
     * 
     * @param value
     *     allowed object is
     *     {@link Tpag5510EntradaType }
     *     
     */
    public void setTpag5510Entrada(Tpag5510EntradaType value) {
        this.tpag5510Entrada = value;
    }

}
