
package cl.bice.nominasenlinea.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para opNomLinDto complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="opNomLinDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codBanco" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codEstado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codTipoCuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codTrx" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fecPago" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="glsTitular" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mtoOperacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nomBanco" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numCuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numFactura" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rutTitular" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "opNomLinDto", propOrder = {
    "codBanco",
    "codEstado",
    "codTipoCuenta",
    "codTrx",
    "fecPago",
    "glsTitular",
    "mtoOperacion",
    "nomBanco",
    "numCuenta",
    "numFactura",
    "rutTitular"
})
public class OpNomLinDto {

    protected String codBanco;
    protected String codEstado;
    protected String codTipoCuenta;
    protected String codTrx;
    protected String fecPago;
    protected String glsTitular;
    protected String mtoOperacion;
    protected String nomBanco;
    protected String numCuenta;
    protected String numFactura;
    protected String rutTitular;

    /**
     * Obtiene el valor de la propiedad codBanco.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodBanco() {
        return codBanco;
    }

    /**
     * Define el valor de la propiedad codBanco.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodBanco(String value) {
        this.codBanco = value;
    }

    /**
     * Obtiene el valor de la propiedad codEstado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodEstado() {
        return codEstado;
    }

    /**
     * Define el valor de la propiedad codEstado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodEstado(String value) {
        this.codEstado = value;
    }

    /**
     * Obtiene el valor de la propiedad codTipoCuenta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodTipoCuenta() {
        return codTipoCuenta;
    }

    /**
     * Define el valor de la propiedad codTipoCuenta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodTipoCuenta(String value) {
        this.codTipoCuenta = value;
    }

    /**
     * Obtiene el valor de la propiedad codTrx.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodTrx() {
        return codTrx;
    }

    /**
     * Define el valor de la propiedad codTrx.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodTrx(String value) {
        this.codTrx = value;
    }

    /**
     * Obtiene el valor de la propiedad fecPago.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFecPago() {
        return fecPago;
    }

    /**
     * Define el valor de la propiedad fecPago.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFecPago(String value) {
        this.fecPago = value;
    }

    /**
     * Obtiene el valor de la propiedad glsTitular.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGlsTitular() {
        return glsTitular;
    }

    /**
     * Define el valor de la propiedad glsTitular.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGlsTitular(String value) {
        this.glsTitular = value;
    }

    /**
     * Obtiene el valor de la propiedad mtoOperacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMtoOperacion() {
        return mtoOperacion;
    }

    /**
     * Define el valor de la propiedad mtoOperacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMtoOperacion(String value) {
        this.mtoOperacion = value;
    }

    /**
     * Obtiene el valor de la propiedad nomBanco.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomBanco() {
        return nomBanco;
    }

    /**
     * Define el valor de la propiedad nomBanco.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomBanco(String value) {
        this.nomBanco = value;
    }

    /**
     * Obtiene el valor de la propiedad numCuenta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumCuenta() {
        return numCuenta;
    }

    /**
     * Define el valor de la propiedad numCuenta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumCuenta(String value) {
        this.numCuenta = value;
    }

    /**
     * Obtiene el valor de la propiedad numFactura.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumFactura() {
        return numFactura;
    }

    /**
     * Define el valor de la propiedad numFactura.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumFactura(String value) {
        this.numFactura = value;
    }

    /**
     * Obtiene el valor de la propiedad rutTitular.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRutTitular() {
        return rutTitular;
    }

    /**
     * Define el valor de la propiedad rutTitular.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRutTitular(String value) {
        this.rutTitular = value;
    }

}
