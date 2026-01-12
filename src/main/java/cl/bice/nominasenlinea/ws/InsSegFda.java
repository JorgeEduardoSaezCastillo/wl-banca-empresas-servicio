
package cl.bice.nominasenlinea.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Ins_Seg_Fda complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Ins_Seg_Fda">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="InsSegFda" type="{http://portal.ws.bice.cl/}insertSegFdaIn"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Ins_Seg_Fda", propOrder = {
    "insSegFda"
})
public class InsSegFda {

    @XmlElement(name = "InsSegFda", required = true)
    protected InsertSegFdaIn insSegFda;

    /**
     * Obtiene el valor de la propiedad insSegFda.
     * 
     * @return
     *     possible object is
     *     {@link InsertSegFdaIn }
     *     
     */
    public InsertSegFdaIn getInsSegFda() {
        return insSegFda;
    }

    /**
     * Define el valor de la propiedad insSegFda.
     * 
     * @param value
     *     allowed object is
     *     {@link InsertSegFdaIn }
     *     
     */
    public void setInsSegFda(InsertSegFdaIn value) {
        this.insSegFda = value;
    }

}
