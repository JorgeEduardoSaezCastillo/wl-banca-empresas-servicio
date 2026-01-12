
package cl.bice.genericoperprog.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para actualizaOpOut complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="actualizaOpOut">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cod_estado" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="msg_result" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "actualizaOpOut", propOrder = {
    "codEstado",
    "msgResult"
})
@XmlRootElement(name = "ActualizaOpOut")
public class ActualizaOpOut {

    @XmlElement(name = "cod_estado")
    protected int codEstado;
    @XmlElement(name = "msg_result")
    protected String msgResult;

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
     * Obtiene el valor de la propiedad msgResult.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMsgResult() {
        return msgResult;
    }

    /**
     * Define el valor de la propiedad msgResult.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMsgResult(String value) {
        this.msgResult = value;
    }

}
