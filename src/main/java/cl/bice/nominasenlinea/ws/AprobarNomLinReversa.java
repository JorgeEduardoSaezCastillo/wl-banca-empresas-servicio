
package cl.bice.nominasenlinea.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Aprobar_NomLin_Reversa complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Aprobar_NomLin_Reversa">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AprobarNomLinReversa" type="{http://portal.ws.bice.cl/}revAprobarNomLinIn"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Aprobar_NomLin_Reversa", propOrder = {
    "aprobarNomLinReversa"
})
public class AprobarNomLinReversa {

    @XmlElement(name = "AprobarNomLinReversa", required = true)
    protected RevAprobarNomLinIn aprobarNomLinReversa;

    /**
     * Obtiene el valor de la propiedad aprobarNomLinReversa.
     * 
     * @return
     *     possible object is
     *     {@link RevAprobarNomLinIn }
     *     
     */
    public RevAprobarNomLinIn getAprobarNomLinReversa() {
        return aprobarNomLinReversa;
    }

    /**
     * Define el valor de la propiedad aprobarNomLinReversa.
     * 
     * @param value
     *     allowed object is
     *     {@link RevAprobarNomLinIn }
     *     
     */
    public void setAprobarNomLinReversa(RevAprobarNomLinIn value) {
        this.aprobarNomLinReversa = value;
    }

}
