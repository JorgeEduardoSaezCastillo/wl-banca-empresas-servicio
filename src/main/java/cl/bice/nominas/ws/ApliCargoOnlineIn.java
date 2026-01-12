
package cl.bice.nominas.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para apliCargoOnlineIn complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="apliCargoOnlineIn">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="rutCliente" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numCtaCargo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numNomina" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="saldoDisp" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="actRegisros" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "apliCargoOnlineIn", propOrder = {
    "rutCliente",
    "numCtaCargo",
    "numNomina",
    "saldoDisp",
    "actRegisros"
})
@XmlRootElement(name = "ApliCargoOnlineIn")
public class ApliCargoOnlineIn {

    @XmlElement(required = true)
    protected String rutCliente;
    @XmlElement(required = true)
    protected String numCtaCargo;
    @XmlElement(required = true)
    protected String numNomina;
    @XmlElement(required = true)
    protected String saldoDisp;
    protected String actRegisros;

    /**
     * Obtiene el valor de la propiedad rutCliente.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRutCliente() {
        return rutCliente;
    }

    /**
     * Define el valor de la propiedad rutCliente.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRutCliente(String value) {
        this.rutCliente = value;
    }

    /**
     * Obtiene el valor de la propiedad numCtaCargo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumCtaCargo() {
        return numCtaCargo;
    }

    /**
     * Define el valor de la propiedad numCtaCargo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumCtaCargo(String value) {
        this.numCtaCargo = value;
    }

    /**
     * Obtiene el valor de la propiedad numNomina.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumNomina() {
        return numNomina;
    }

    /**
     * Define el valor de la propiedad numNomina.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumNomina(String value) {
        this.numNomina = value;
    }

    /**
     * Obtiene el valor de la propiedad saldoDisp.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSaldoDisp() {
        return saldoDisp;
    }

    /**
     * Define el valor de la propiedad saldoDisp.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSaldoDisp(String value) {
        this.saldoDisp = value;
    }

    /**
     * Obtiene el valor de la propiedad actRegisros.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActRegisros() {
        return actRegisros;
    }

    /**
     * Define el valor de la propiedad actRegisros.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActRegisros(String value) {
        this.actRegisros = value;
    }

}
