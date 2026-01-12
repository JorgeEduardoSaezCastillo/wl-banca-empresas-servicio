
package cl.bice.nominasenlinea.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Act_Envio_Mail complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Act_Envio_Mail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ActEnvioMail" type="{http://portal.ws.bice.cl/}actEnvioMailIn"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Act_Envio_Mail", propOrder = {
    "actEnvioMail"
})
public class ActEnvioMail {

    @XmlElement(name = "ActEnvioMail", required = true)
    protected ActEnvioMailIn actEnvioMail;

    /**
     * Obtiene el valor de la propiedad actEnvioMail.
     * 
     * @return
     *     possible object is
     *     {@link ActEnvioMailIn }
     *     
     */
    public ActEnvioMailIn getActEnvioMail() {
        return actEnvioMail;
    }

    /**
     * Define el valor de la propiedad actEnvioMail.
     * 
     * @param value
     *     allowed object is
     *     {@link ActEnvioMailIn }
     *     
     */
    public void setActEnvioMail(ActEnvioMailIn value) {
        this.actEnvioMail = value;
    }

}
