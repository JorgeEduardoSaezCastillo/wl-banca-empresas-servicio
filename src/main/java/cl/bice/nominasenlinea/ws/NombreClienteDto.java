
package cl.bice.nominasenlinea.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para nombreClienteDto complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="nombreClienteDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ap_materno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ap_paterno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombre_completo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombres_cliente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rut_cliente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "nombreClienteDto", propOrder = {
    "apMaterno",
    "apPaterno",
    "nombreCompleto",
    "nombresCliente",
    "rutCliente"
})
public class NombreClienteDto {

    @XmlElement(name = "ap_materno")
    protected String apMaterno;
    @XmlElement(name = "ap_paterno")
    protected String apPaterno;
    @XmlElement(name = "nombre_completo")
    protected String nombreCompleto;
    @XmlElement(name = "nombres_cliente")
    protected String nombresCliente;
    @XmlElement(name = "rut_cliente")
    protected String rutCliente;

    /**
     * Obtiene el valor de la propiedad apMaterno.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApMaterno() {
        return apMaterno;
    }

    /**
     * Define el valor de la propiedad apMaterno.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApMaterno(String value) {
        this.apMaterno = value;
    }

    /**
     * Obtiene el valor de la propiedad apPaterno.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApPaterno() {
        return apPaterno;
    }

    /**
     * Define el valor de la propiedad apPaterno.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApPaterno(String value) {
        this.apPaterno = value;
    }

    /**
     * Obtiene el valor de la propiedad nombreCompleto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    /**
     * Define el valor de la propiedad nombreCompleto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreCompleto(String value) {
        this.nombreCompleto = value;
    }

    /**
     * Obtiene el valor de la propiedad nombresCliente.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombresCliente() {
        return nombresCliente;
    }

    /**
     * Define el valor de la propiedad nombresCliente.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombresCliente(String value) {
        this.nombresCliente = value;
    }

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

}
