
package cl.bice.nominas.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para operaCargoOnlineIn complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="operaCargoOnlineIn">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="rutCliente" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numCtaCargo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "operaCargoOnlineIn", propOrder = {
    "rutCliente",
    "numCtaCargo"
})
@XmlRootElement(name = "OperaCargoOnlineIn")
public class OperaCargoOnlineIn {

    @XmlElement(required = true)
    protected String rutCliente;
    @XmlElement(required = true)
    protected String numCtaCargo;

    /**
     * Obtiene el valor de la propiedad rutCliente.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRutCliente() {
        return rutCliente;
    }

    /**
     * Define el valor de la propiedad rutCliente.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRutCliente(String value) {
        this.rutCliente = value;
    }

    /**
     * Obtiene el valor de la propiedad numCtaCargo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumCtaCargo() {
        return numCtaCargo;
    }

    /**
     * Define el valor de la propiedad numCtaCargo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumCtaCargo(String value) {
        this.numCtaCargo = value;
    }

}
