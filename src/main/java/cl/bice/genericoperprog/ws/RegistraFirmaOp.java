
package cl.bice.genericoperprog.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Registra_Firma_Op complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Registra_Firma_Op">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="firmaApoderado" type="{http://ws.operprog.bice.cl/}registraApoOpIn"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Registra_Firma_Op", propOrder = {
    "firmaApoderado"
})
public class RegistraFirmaOp {

    @XmlElement(required = true)
    protected RegistraApoOpIn firmaApoderado;

    /**
     * Obtiene el valor de la propiedad firmaApoderado.
     * 
     * @return
     *     possible object is
     *     {@link RegistraApoOpIn }
     *     
     */
    public RegistraApoOpIn getFirmaApoderado() {
        return firmaApoderado;
    }

    /**
     * Define el valor de la propiedad firmaApoderado.
     * 
     * @param value
     *     allowed object is
     *     {@link RegistraApoOpIn }
     *     
     */
    public void setFirmaApoderado(RegistraApoOpIn value) {
        this.firmaApoderado = value;
    }

}
