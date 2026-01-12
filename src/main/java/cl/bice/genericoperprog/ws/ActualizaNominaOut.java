
package cl.bice.genericoperprog.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para actualizaNominaOut complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="actualizaNominaOut">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cod_estado" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="msg_status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "actualizaNominaOut", propOrder = {
    "codEstado",
    "msgStatus"
})
public class ActualizaNominaOut {

    @XmlElement(name = "cod_estado")
    protected int codEstado;
    @XmlElement(name = "msg_status")
    protected String msgStatus;

    /**
     * Obtiene el valor de la propiedad codEstado.
     * 
     */
    public int getCodEstado() {
        return codEstado;
    }

    /**
     * Define el valor de la propiedad codEstado.
     * 
     */
    public void setCodEstado(int value) {
        this.codEstado = value;
    }

    /**
     * Obtiene el valor de la propiedad msgStatus.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMsgStatus() {
        return msgStatus;
    }

    /**
     * Define el valor de la propiedad msgStatus.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMsgStatus(String value) {
        this.msgStatus = value;
    }

}
