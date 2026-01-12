
package cl.bice.nominasenlinea.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para consMtoMaxIn complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="consMtoMaxIn">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IN_RUT_EMPRESA" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IN_CUENTA_CARGO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consMtoMaxIn", propOrder = {
    "inrutempresa",
    "incuentacargo"
})
public class ConsMtoMaxIn {

    @XmlElement(name = "IN_RUT_EMPRESA", required = true)
    protected String inrutempresa;
    @XmlElement(name = "IN_CUENTA_CARGO", required = true)
    protected String incuentacargo;

    /**
     * Obtiene el valor de la propiedad inrutempresa.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINRUTEMPRESA() {
        return inrutempresa;
    }

    /**
     * Define el valor de la propiedad inrutempresa.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINRUTEMPRESA(String value) {
        this.inrutempresa = value;
    }

    /**
     * Obtiene el valor de la propiedad incuentacargo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINCUENTACARGO() {
        return incuentacargo;
    }

    /**
     * Define el valor de la propiedad incuentacargo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINCUENTACARGO(String value) {
        this.incuentacargo = value;
    }

}
