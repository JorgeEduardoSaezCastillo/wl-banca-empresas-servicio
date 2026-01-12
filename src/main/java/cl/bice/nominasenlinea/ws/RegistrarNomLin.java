
package cl.bice.nominasenlinea.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Registrar_Nom_Lin complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Registrar_Nom_Lin">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RegistrarNomLin" type="{http://portal.ws.bice.cl/}regNomIn"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Registrar_Nom_Lin", propOrder = {
    "registrarNomLin"
})
public class RegistrarNomLin {

    @XmlElement(name = "RegistrarNomLin", required = true)
    protected RegNomIn registrarNomLin;

    /**
     * Obtiene el valor de la propiedad registrarNomLin.
     * 
     * @return
     *     possible object is
     *     {@link RegNomIn }
     *     
     */
    public RegNomIn getRegistrarNomLin() {
        return registrarNomLin;
    }

    /**
     * Define el valor de la propiedad registrarNomLin.
     * 
     * @param value
     *     allowed object is
     *     {@link RegNomIn }
     *     
     */
    public void setRegistrarNomLin(RegNomIn value) {
        this.registrarNomLin = value;
    }

}
