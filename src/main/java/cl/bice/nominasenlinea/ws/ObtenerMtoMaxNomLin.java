
package cl.bice.nominasenlinea.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Obtener_MtoMax_NomLin complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Obtener_MtoMax_NomLin">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ObetenerMtoMaxNomLin" type="{http://portal.ws.bice.cl/}consMtoMaxIn"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Obtener_MtoMax_NomLin", propOrder = {
    "obetenerMtoMaxNomLin"
})
public class ObtenerMtoMaxNomLin {

    @XmlElement(name = "ObetenerMtoMaxNomLin", required = true)
    protected ConsMtoMaxIn obetenerMtoMaxNomLin;

    /**
     * Obtiene el valor de la propiedad obetenerMtoMaxNomLin.
     * 
     * @return
     *     possible object is
     *     {@link ConsMtoMaxIn }
     *     
     */
    public ConsMtoMaxIn getObetenerMtoMaxNomLin() {
        return obetenerMtoMaxNomLin;
    }

    /**
     * Define el valor de la propiedad obetenerMtoMaxNomLin.
     * 
     * @param value
     *     allowed object is
     *     {@link ConsMtoMaxIn }
     *     
     */
    public void setObetenerMtoMaxNomLin(ConsMtoMaxIn value) {
        this.obetenerMtoMaxNomLin = value;
    }

}
