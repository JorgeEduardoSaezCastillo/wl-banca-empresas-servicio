
package cl.bice.nominasenlinea.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para montoMaxDto complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="montoMaxDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="monto_maximo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numero_producto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rut_empresa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "montoMaxDto", propOrder = {
    "montoMaximo",
    "numeroProducto",
    "rutEmpresa"
})
public class MontoMaxDto {

    @XmlElement(name = "monto_maximo")
    protected String montoMaximo;
    @XmlElement(name = "numero_producto")
    protected String numeroProducto;
    @XmlElement(name = "rut_empresa")
    protected String rutEmpresa;

    /**
     * Obtiene el valor de la propiedad montoMaximo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMontoMaximo() {
        return montoMaximo;
    }

    /**
     * Define el valor de la propiedad montoMaximo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMontoMaximo(String value) {
        this.montoMaximo = value;
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
     * Obtiene el valor de la propiedad rutEmpresa.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRutEmpresa() {
        return rutEmpresa;
    }

    /**
     * Define el valor de la propiedad rutEmpresa.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRutEmpresa(String value) {
        this.rutEmpresa = value;
    }

}
