
package cl.bice.nominasenlinea.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Act_Cerrar_NomLin complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Act_Cerrar_NomLin">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ActCerrarNomLin" type="{http://portal.ws.bice.cl/}actCerrarNomIn"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Act_Cerrar_NomLin", propOrder = {
    "actCerrarNomLin"
})
public class ActCerrarNomLin {

    @XmlElement(name = "ActCerrarNomLin", required = true)
    protected ActCerrarNomIn actCerrarNomLin;

    /**
     * Obtiene el valor de la propiedad actCerrarNomLin.
     * 
     * @return
     *     possible object is
     *     {@link ActCerrarNomIn }
     *     
     */
    public ActCerrarNomIn getActCerrarNomLin() {
        return actCerrarNomLin;
    }

    /**
     * Define el valor de la propiedad actCerrarNomLin.
     * 
     * @param value
     *     allowed object is
     *     {@link ActCerrarNomIn }
     *     
     */
    public void setActCerrarNomLin(ActCerrarNomIn value) {
        this.actCerrarNomLin = value;
    }

}
