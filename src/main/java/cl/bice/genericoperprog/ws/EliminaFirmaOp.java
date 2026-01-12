
package cl.bice.genericoperprog.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Elimina_Firma_Op complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Elimina_Firma_Op">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="eliminafirmaApo" type="{http://ws.operprog.bice.cl/}eliminaApoOpIn"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Elimina_Firma_Op", propOrder = {
    "eliminafirmaApo"
})
public class EliminaFirmaOp {

    @XmlElement(required = true)
    protected EliminaApoOpIn eliminafirmaApo;

    /**
     * Obtiene el valor de la propiedad eliminafirmaApo.
     * 
     * @return
     *     possible object is
     *     {@link EliminaApoOpIn }
     *     
     */
    public EliminaApoOpIn getEliminafirmaApo() {
        return eliminafirmaApo;
    }

    /**
     * Define el valor de la propiedad eliminafirmaApo.
     * 
     * @param value
     *     allowed object is
     *     {@link EliminaApoOpIn }
     *     
     */
    public void setEliminafirmaApo(EliminaApoOpIn value) {
        this.eliminafirmaApo = value;
    }

}
