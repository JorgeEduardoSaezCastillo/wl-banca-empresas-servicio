
package cl.bice.genericoperprog.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Actualiza_Oper_Prog complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Actualiza_Oper_Prog">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="actualizaOp" type="{http://ws.operprog.bice.cl/}actualizaOpIn"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Actualiza_Oper_Prog", propOrder = {
    "actualizaOp"
})
public class ActualizaOperProg {

    @XmlElement(required = true)
    protected ActualizaOpIn actualizaOp;

    /**
     * Obtiene el valor de la propiedad actualizaOp.
     * 
     * @return
     *     possible object is
     *     {@link ActualizaOpIn }
     *     
     */
    public ActualizaOpIn getActualizaOp() {
        return actualizaOp;
    }

    /**
     * Define el valor de la propiedad actualizaOp.
     * 
     * @param value
     *     allowed object is
     *     {@link ActualizaOpIn }
     *     
     */
    public void setActualizaOp(ActualizaOpIn value) {
        this.actualizaOp = value;
    }

}
