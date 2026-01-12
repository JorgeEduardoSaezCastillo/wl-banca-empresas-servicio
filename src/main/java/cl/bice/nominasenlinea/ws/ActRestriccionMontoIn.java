
package cl.bice.nominasenlinea.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para actRestriccionMontoIn complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="actRestriccionMontoIn">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IN_RUT_EMPRESA" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IN_RUT_ADM_BICE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IN_NUM_CUENTA" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="IN_MONTO" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="IN_ESTADO" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "actRestriccionMontoIn", propOrder = {
    "inrutempresa",
    "inrutadmbice",
    "innumcuenta",
    "inmonto",
    "inestado"
})
public class ActRestriccionMontoIn {

    @XmlElement(name = "IN_RUT_EMPRESA", required = true)
    protected String inrutempresa;
    @XmlElement(name = "IN_RUT_ADM_BICE", required = true)
    protected String inrutadmbice;
    @XmlElement(name = "IN_NUM_CUENTA")
    protected long innumcuenta;
    @XmlElement(name = "IN_MONTO")
    protected long inmonto;
    @XmlElement(name = "IN_ESTADO")
    protected int inestado;

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

    /**
     * Obtiene el valor de la propiedad innumcuenta.
     * 
     */
    public long getINNUMCUENTA() {
        return innumcuenta;
    }

    /**
     * Define el valor de la propiedad innumcuenta.
     * 
     */
    public void setINNUMCUENTA(long value) {
        this.innumcuenta = value;
    }

    /**
     * Obtiene el valor de la propiedad inmonto.
     * 
     */
    public long getINMONTO() {
        return inmonto;
    }

    /**
     * Define el valor de la propiedad inmonto.
     * 
     */
    public void setINMONTO(long value) {
        this.inmonto = value;
    }

    /**
     * Obtiene el valor de la propiedad inestado.
     * 
     */
    public int getINESTADO() {
        return inestado;
    }

    /**
     * Define el valor de la propiedad inestado.
     * 
     */
    public void setINESTADO(int value) {
        this.inestado = value;
    }

}
