
package cl.bice.nominasenlinea.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Cons_Cuentas_NomLin complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Cons_Cuentas_NomLin">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ConsCuentasNomLin" type="{http://portal.ws.bice.cl/}consCuentasNomLinIn"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Cons_Cuentas_NomLin", propOrder = {
    "consCuentasNomLin"
})
public class ConsCuentasNomLin {

    @XmlElement(name = "ConsCuentasNomLin", required = true)
    protected ConsCuentasNomLinIn consCuentasNomLin;

    /**
     * Obtiene el valor de la propiedad consCuentasNomLin.
     * 
     * @return
     *     possible object is
     *     {@link ConsCuentasNomLinIn }
     *     
     */
    public ConsCuentasNomLinIn getConsCuentasNomLin() {
        return consCuentasNomLin;
    }

    /**
     * Define el valor de la propiedad consCuentasNomLin.
     * 
     * @param value
     *     allowed object is
     *     {@link ConsCuentasNomLinIn }
     *     
     */
    public void setConsCuentasNomLin(ConsCuentasNomLinIn value) {
        this.consCuentasNomLin = value;
    }

}
