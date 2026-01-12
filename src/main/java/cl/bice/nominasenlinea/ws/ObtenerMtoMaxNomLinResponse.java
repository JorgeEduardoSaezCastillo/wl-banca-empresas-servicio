
package cl.bice.nominasenlinea.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Obtener_MtoMax_NomLinResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Obtener_MtoMax_NomLinResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://portal.ws.bice.cl/}consMtoMaxOut" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Obtener_MtoMax_NomLinResponse", propOrder = {
    "_return"
})
public class ObtenerMtoMaxNomLinResponse {

    @XmlElement(name = "return")
    protected ConsMtoMaxOut _return;

    /**
     * Obtiene el valor de la propiedad return.
     * 
     * @return
     *     possible object is
     *     {@link ConsMtoMaxOut }
     *     
     */
    public ConsMtoMaxOut getReturn() {
        return _return;
    }

    /**
     * Define el valor de la propiedad return.
     * 
     * @param value
     *     allowed object is
     *     {@link ConsMtoMaxOut }
     *     
     */
    public void setReturn(ConsMtoMaxOut value) {
        this._return = value;
    }

}
