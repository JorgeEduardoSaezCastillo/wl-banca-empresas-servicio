
package cl.bice.nominasenlinea.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para regEnvioMailIn complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="regEnvioMailIn">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IN_RUT_EMPRESA" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IN_RUT_ADM_BICE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "regEnvioMailIn", propOrder = {
    "inrutempresa",
    "inrutadmbice"
})
public class RegEnvioMailIn {

    @XmlElement(name = "IN_RUT_EMPRESA", required = true)
    protected String inrutempresa;
    @XmlElement(name = "IN_RUT_ADM_BICE", required = true)
    protected String inrutadmbice;

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
     * Obtiene el valor de la propiedad inrutadmbice.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINRUTADMBICE() {
        return inrutadmbice;
    }

    /**
     * Define el valor de la propiedad inrutadmbice.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINRUTADMBICE(String value) {
        this.inrutadmbice = value;
    }

}
