
package cl.bice.nominas.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Opera_Online complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Opera_Online">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="operaOnline" type="{http://ws.nominas.bice.cl/}operaCargoOnlineIn"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Opera_Online", propOrder = {
    "operaOnline"
})
public class OperaOnline {

    @XmlElement(required = true)
    protected OperaCargoOnlineIn operaOnline;

    /**
     * Obtiene el valor de la propiedad operaOnline.
     * 
     * @return
     *     possible object is
     *     {@link OperaCargoOnlineIn }
     *     
     */
    public OperaCargoOnlineIn getOperaOnline() {
        return operaOnline;
    }

    /**
     * Define el valor de la propiedad operaOnline.
     * 
     * @param value
     *     allowed object is
     *     {@link OperaCargoOnlineIn }
     *     
     */
    public void setOperaOnline(OperaCargoOnlineIn value) {
        this.operaOnline = value;
    }

}
