
package cl.bice.nominasenlinea.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Cons_Nom_Cliente complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Cons_Nom_Cliente">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ConsNomCliente" type="{http://portal.ws.bice.cl/}consNombreClienteIn"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Cons_Nom_Cliente", propOrder = {
    "consNomCliente"
})
public class ConsNomCliente {

    @XmlElement(name = "ConsNomCliente", required = true)
    protected ConsNombreClienteIn consNomCliente;

    /**
     * Obtiene el valor de la propiedad consNomCliente.
     * 
     * @return
     *     possible object is
     *     {@link ConsNombreClienteIn }
     *     
     */
    public ConsNombreClienteIn getConsNomCliente() {
        return consNomCliente;
    }

    /**
     * Define el valor de la propiedad consNomCliente.
     * 
     * @param value
     *     allowed object is
     *     {@link ConsNombreClienteIn }
     *     
     */
    public void setConsNomCliente(ConsNombreClienteIn value) {
        this.consNomCliente = value;
    }

}
