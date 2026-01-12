
package cl.bice.nominasenlinea.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Cons_Envio_Mail complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Cons_Envio_Mail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ConsEnvioMail" type="{http://portal.ws.bice.cl/}consEnvioMailIn"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Cons_Envio_Mail", propOrder = {
    "consEnvioMail"
})
public class ConsEnvioMail {

    @XmlElement(name = "ConsEnvioMail", required = true)
    protected ConsEnvioMailIn consEnvioMail;

    /**
     * Obtiene el valor de la propiedad consEnvioMail.
     * 
     * @return
     *     possible object is
     *     {@link ConsEnvioMailIn }
     *     
     */
    public ConsEnvioMailIn getConsEnvioMail() {
        return consEnvioMail;
    }

    /**
     * Define el valor de la propiedad consEnvioMail.
     * 
     * @param value
     *     allowed object is
     *     {@link ConsEnvioMailIn }
     *     
     */
    public void setConsEnvioMail(ConsEnvioMailIn value) {
        this.consEnvioMail = value;
    }

}
