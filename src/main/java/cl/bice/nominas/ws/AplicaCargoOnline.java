
package cl.bice.nominas.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Aplica_Cargo_Online complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Aplica_Cargo_Online">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="aplicaCargo" type="{http://ws.nominas.bice.cl/}apliCargoOnlineIn"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Aplica_Cargo_Online", propOrder = {
    "aplicaCargo"
})
public class AplicaCargoOnline {

    @XmlElement(required = true)
    protected ApliCargoOnlineIn aplicaCargo;

    /**
     * Obtiene el valor de la propiedad aplicaCargo.
     * 
     * @return
     *     possible object is
     *     {@link ApliCargoOnlineIn }
     *     
     */
    public ApliCargoOnlineIn getAplicaCargo() {
        return aplicaCargo;
    }

    /**
     * Define el valor de la propiedad aplicaCargo.
     * 
     * @param value
     *     allowed object is
     *     {@link ApliCargoOnlineIn }
     *     
     */
    public void setAplicaCargo(ApliCargoOnlineIn value) {
        this.aplicaCargo = value;
    }

}
