
package cl.bice.nominasenlinea.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para cuentasNomLinDto complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="cuentasNomLinDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cod_producto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cod_tipo_produ" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numero_producto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "cuentasNomLinDto", propOrder = {
    "codProducto",
    "codTipoProdu",
    "numeroProducto",
    "rutCliente"
})
public class CuentasNomLinDto {

    @XmlElement(name = "cod_producto")
    protected String codProducto;
    @XmlElement(name = "cod_tipo_produ")
    protected String codTipoProdu;
    @XmlElement(name = "numero_producto")
    protected String numeroProducto;
    @XmlElement(name = "rut_cliente")
    protected String rutCliente;

    /**
     * Obtiene el valor de la propiedad codProducto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodProducto() {
        return codProducto;
    }

    /**
     * Define el valor de la propiedad codProducto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodProducto(String value) {
        this.codProducto = value;
    }

    /**
     * Obtiene el valor de la propiedad codTipoProdu.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodTipoProdu() {
        return codTipoProdu;
    }

    /**
     * Define el valor de la propiedad codTipoProdu.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodTipoProdu(String value) {
        this.codTipoProdu = value;
    }

    /**
     * Obtiene el valor de la propiedad numeroProducto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroProducto() {
        return numeroProducto;
    }

    /**
     * Define el valor de la propiedad numeroProducto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroProducto(String value) {
        this.numeroProducto = value;
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
