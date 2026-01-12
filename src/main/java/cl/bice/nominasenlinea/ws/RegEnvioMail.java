
package cl.bice.nominasenlinea.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Reg_Envio_Mail complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Reg_Envio_Mail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RegEnvioMail" type="{http://portal.ws.bice.cl/}regEnvioMailIn"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Reg_Envio_Mail", propOrder = {
    "regEnvioMail"
})
public class RegEnvioMail {

    @XmlElement(name = "RegEnvioMail", required = true)
    protected RegEnvioMailIn regEnvioMail;

    /**
     * Obtiene el valor de la propiedad regEnvioMail.
     * 
     * @return
     *     possible object is
     *     {@link RegEnvioMailIn }
     *     
     */
    public RegEnvioMailIn getRegEnvioMail() {
        return regEnvioMail;
    }

    /**
     * Define el valor de la propiedad regEnvioMail.
     * 
     * @param value
     *     allowed object is
     *     {@link RegEnvioMailIn }
     *     
     */
    public void setRegEnvioMail(RegEnvioMailIn value) {
        this.regEnvioMail = value;
    }

}
