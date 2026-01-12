
package cl.bice.tef.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Tpag5520RequestType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Tpag5520RequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Tpag5520Entrada" type="{http://www.bice.cl/tef/schemas}Tpag5520EntradaType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Tpag5520RequestType", propOrder = {
    "tpag5520Entrada"
})
public class Tpag5520RequestType {

    @XmlElement(name = "Tpag5520Entrada", required = true)
    protected Tpag5520EntradaType tpag5520Entrada;

    /**
     * Obtiene el valor de la propiedad tpag5520Entrada.
     * 
     * @return
     *     possible object is
     *     {@link Tpag5520EntradaType }
     *     
     */
    public Tpag5520EntradaType getTpag5520Entrada() {
        return tpag5520Entrada;
    }

    /**
     * Define el valor de la propiedad tpag5520Entrada.
     * 
     * @param value
     *     allowed object is
     *     {@link Tpag5520EntradaType }
     *     
     */
    public void setTpag5520Entrada(Tpag5520EntradaType value) {
        this.tpag5520Entrada = value;
    }

}
