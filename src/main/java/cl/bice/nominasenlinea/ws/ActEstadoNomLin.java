
package cl.bice.nominasenlinea.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Act_Estado_NomLin complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Act_Estado_NomLin">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ActEstadoNomLin" type="{http://portal.ws.bice.cl/}actEstadoNomIn"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Act_Estado_NomLin", propOrder = {
    "actEstadoNomLin"
})
public class ActEstadoNomLin {

    @XmlElement(name = "ActEstadoNomLin", required = true)
    protected ActEstadoNomIn actEstadoNomLin;

    /**
     * Obtiene el valor de la propiedad actEstadoNomLin.
     * 
     * @return
     *     possible object is
     *     {@link ActEstadoNomIn }
     *     
     */
    public ActEstadoNomIn getActEstadoNomLin() {
        return actEstadoNomLin;
    }

    /**
     * Define el valor de la propiedad actEstadoNomLin.
     * 
     * @param value
     *     allowed object is
     *     {@link ActEstadoNomIn }
     *     
     */
    public void setActEstadoNomLin(ActEstadoNomIn value) {
        this.actEstadoNomLin = value;
    }

}
