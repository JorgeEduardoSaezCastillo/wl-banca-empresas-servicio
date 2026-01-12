
package cl.bice.tef.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para Tpag5310EntradaType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Tpag5310EntradaType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="NumeroCuenta" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="CodigoTipoCuenta" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="RutCliente" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Monto" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="Moneda" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="NumeroOperacion" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="CodigoBancoDestino" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CuentaDestino" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CodigoTipoCtaDestino" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="RutOriginador" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FechaContableCCA" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="DevuelveImpuesto" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Canal" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Tpag5310EntradaType", propOrder = {
    "numeroCuenta",
    "codigoTipoCuenta",
    "rutCliente",
    "monto",
    "moneda",
    "numeroOperacion",
    "codigoBancoDestino",
    "cuentaDestino",
    "codigoTipoCtaDestino",
    "rutOriginador",
    "fechaContableCCA",
    "devuelveImpuesto",
    "canal"
})
public class Tpag5310EntradaType {

    @XmlElement(name = "NumeroCuenta")
    protected long numeroCuenta;
    @XmlElement(name = "CodigoTipoCuenta")
    protected int codigoTipoCuenta;
    @XmlElement(name = "RutCliente", required = true)
    protected String rutCliente;
    @XmlElement(name = "Monto")
    protected long monto;
    @XmlElement(name = "Moneda")
    protected int moneda;
    @XmlElement(name = "NumeroOperacion")
    protected long numeroOperacion;
    @XmlElement(name = "CodigoBancoDestino", required = true)
    protected String codigoBancoDestino;
    @XmlElement(name = "CuentaDestino", required = true)
    protected String cuentaDestino;
    @XmlElement(name = "CodigoTipoCtaDestino")
    protected int codigoTipoCtaDestino;
    @XmlElement(name = "RutOriginador", required = true)
    protected String rutOriginador;
    @XmlElement(name = "FechaContableCCA", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaContableCCA;
    @XmlElement(name = "DevuelveImpuesto", required = true)
    protected String devuelveImpuesto;
    @XmlElement(name = "Canal", required = true)
    protected String canal;

    /**
     * Obtiene el valor de la propiedad numeroCuenta.
     * 
     */
    public long getNumeroCuenta() {
        return numeroCuenta;
    }

    /**
     * Define el valor de la propiedad numeroCuenta.
     * 
     */
    public void setNumeroCuenta(long value) {
        this.numeroCuenta = value;
    }

    /**
     * Obtiene el valor de la propiedad codigoTipoCuenta.
     * 
     */
    public int getCodigoTipoCuenta() {
        return codigoTipoCuenta;
    }

    /**
     * Define el valor de la propiedad codigoTipoCuenta.
     * 
     */
    public void setCodigoTipoCuenta(int value) {
        this.codigoTipoCuenta = value;
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

    /**
     * Obtiene el valor de la propiedad monto.
     * 
     */
    public long getMonto() {
        return monto;
    }

    /**
     * Define el valor de la propiedad monto.
     * 
     */
    public void setMonto(long value) {
        this.monto = value;
    }

    /**
     * Obtiene el valor de la propiedad moneda.
     * 
     */
    public int getMoneda() {
        return moneda;
    }

    /**
     * Define el valor de la propiedad moneda.
     * 
     */
    public void setMoneda(int value) {
        this.moneda = value;
    }

    /**
     * Obtiene el valor de la propiedad numeroOperacion.
     * 
     */
    public long getNumeroOperacion() {
        return numeroOperacion;
    }

    /**
     * Define el valor de la propiedad numeroOperacion.
     * 
     */
    public void setNumeroOperacion(long value) {
        this.numeroOperacion = value;
    }

    /**
     * Obtiene el valor de la propiedad codigoBancoDestino.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoBancoDestino() {
        return codigoBancoDestino;
    }

    /**
     * Define el valor de la propiedad codigoBancoDestino.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoBancoDestino(String value) {
        this.codigoBancoDestino = value;
    }

    /**
     * Obtiene el valor de la propiedad cuentaDestino.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCuentaDestino() {
        return cuentaDestino;
    }

    /**
     * Define el valor de la propiedad cuentaDestino.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCuentaDestino(String value) {
        this.cuentaDestino = value;
    }

    /**
     * Obtiene el valor de la propiedad codigoTipoCtaDestino.
     * 
     */
    public int getCodigoTipoCtaDestino() {
        return codigoTipoCtaDestino;
    }

    /**
     * Define el valor de la propiedad codigoTipoCtaDestino.
     * 
     */
    public void setCodigoTipoCtaDestino(int value) {
        this.codigoTipoCtaDestino = value;
    }

    /**
     * Obtiene el valor de la propiedad rutOriginador.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRutOriginador() {
        return rutOriginador;
    }

    /**
     * Define el valor de la propiedad rutOriginador.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRutOriginador(String value) {
        this.rutOriginador = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaContableCCA.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaContableCCA() {
        return fechaContableCCA;
    }

    /**
     * Define el valor de la propiedad fechaContableCCA.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaContableCCA(XMLGregorianCalendar value) {
        this.fechaContableCCA = value;
    }

    /**
     * Obtiene el valor de la propiedad devuelveImpuesto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDevuelveImpuesto() {
        return devuelveImpuesto;
    }

    /**
     * Define el valor de la propiedad devuelveImpuesto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDevuelveImpuesto(String value) {
        this.devuelveImpuesto = value;
    }

    /**
     * Obtiene el valor de la propiedad canal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCanal() {
        return canal;
    }

    /**
     * Define el valor de la propiedad canal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCanal(String value) {
        this.canal = value;
    }

}
