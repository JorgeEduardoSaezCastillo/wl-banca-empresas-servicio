
package cl.bice.tef.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Tpag5300RequestType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Tpag5300RequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Tpag5300Entrada" type="{http://www.bice.cl/tef/schemas}Tpag5300EntradaType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Tpag5300RequestType", propOrder = {
    "tpag5300Entrada"
})
public class Tpag5300RequestType {

    @XmlElement(name = "Tpag5300Entrada", required = true)
    protected Tpag5300EntradaType tpag5300Entrada;

    /**
     * Obtiene el valor de la propiedad tpag5300Entrada.
     * 
     * @return
     *     possible object is
     *     {@link Tpag5300EntradaType }
     *     
     */
    public Tpag5300EntradaType getTpag5300Entrada() {
        return tpag5300Entrada;
    }

    /**
     * Define el valor de la propiedad tpag5300Entrada.
     * 
     * @param value
     *     allowed object is
     *     {@link Tpag5300EntradaType }
     *     
     */
    public void setTpag5300Entrada(Tpag5300EntradaType value) {
        this.tpag5300Entrada = value;
    }

}
