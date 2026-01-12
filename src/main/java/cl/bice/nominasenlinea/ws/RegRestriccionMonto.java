
package cl.bice.nominasenlinea.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Reg_Restriccion_Monto complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Reg_Restriccion_Monto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RegRestriccionMonto" type="{http://portal.ws.bice.cl/}regRestriccionMontoIn"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Reg_Restriccion_Monto", propOrder = {
    "regRestriccionMonto"
})
public class RegRestriccionMonto {

    @XmlElement(name = "RegRestriccionMonto", required = true)
    protected RegRestriccionMontoIn regRestriccionMonto;

    /**
     * Obtiene el valor de la propiedad regRestriccionMonto.
     * 
     * @return
     *     possible object is
     *     {@link RegRestriccionMontoIn }
     *     
     */
    public RegRestriccionMontoIn getRegRestriccionMonto() {
        return regRestriccionMonto;
    }

    /**
     * Define el valor de la propiedad regRestriccionMonto.
     * 
     * @param value
     *     allowed object is
     *     {@link RegRestriccionMontoIn }
     *     
     */
    public void setRegRestriccionMonto(RegRestriccionMontoIn value) {
        this.regRestriccionMonto = value;
    }

}
