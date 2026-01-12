
package cl.bice.genericoperprog.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Registra_Op complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Registra_Op">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="registraOperProg" type="{http://ws.operprog.bice.cl/}insertaOpIn"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Registra_Op", propOrder = {
    "registraOperProg"
})
public class RegistraOp {

    @XmlElement(required = true)
    protected InsertaOpIn registraOperProg;

    /**
     * Obtiene el valor de la propiedad registraOperProg.
     * 
     * @return
     *     possible object is
     *     {@link InsertaOpIn }
     *     
     */
    public InsertaOpIn getRegistraOperProg() {
        return registraOperProg;
    }

    /**
     * Define el valor de la propiedad registraOperProg.
     * 
     * @param value
     *     allowed object is
     *     {@link InsertaOpIn }
     *     
     */
    public void setRegistraOperProg(InsertaOpIn value) {
        this.registraOperProg = value;
    }

}
