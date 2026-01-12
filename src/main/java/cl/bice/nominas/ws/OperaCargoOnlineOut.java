
package cl.bice.nominas.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para operaCargoOnlineOut complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="operaCargoOnlineOut">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codResult" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="msgResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "operaCargoOnlineOut", propOrder = {
    "codResult",
    "msgResult"
})
@XmlRootElement(name = "OperaCargoOnlineOut")
public class OperaCargoOnlineOut {

    protected int codResult;
    protected String msgResult;

    /**
     * Obtiene el valor de la propiedad codResult.
     * 
     */
    public int getCodResult() {
        return codResult;
    }

    /**
     * Define el valor de la propiedad codResult.
     * 
     */
    public void setCodResult(int value) {
        this.codResult = value;
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
