
package cl.bice.tef.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para Tpag5110EntradaType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Tpag5110EntradaType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="NumeroCuenta" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="TipoCuenta" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="RutCuenta" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Monto" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="Moneda" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="TraceNumber" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="CodigoBancoOrigen" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TipoCtaOrigen" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="CuentaOrigen" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RutCuentaOrigen" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FechaContableBCO" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="CobraImpuesto" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "Tpag5110EntradaType", propOrder = {
    "numeroCuenta",
    "tipoCuenta",
    "rutCuenta",
    "monto",
    "moneda",
    "traceNumber",
    "codigoBancoOrigen",
    "tipoCtaOrigen",
    "cuentaOrigen",
    "rutCuentaOrigen",
    "fechaContableBCO",
    "cobraImpuesto",
    "canal"
})
public class Tpag5110EntradaType {

    @XmlElement(name = "NumeroCuenta")
    protected long numeroCuenta;
    @XmlElement(name = "TipoCuenta")
    protected int tipoCuenta;
    @XmlElement(name = "RutCuenta", required = true)
    protected String rutCuenta;
    @XmlElement(name = "Monto")
    protected long monto;
    @XmlElement(name = "Moneda")
    protected int moneda;
    @XmlElement(name = "TraceNumber")
    protected long traceNumber;
    @XmlElement(name = "CodigoBancoOrigen", required = true)
    protected String codigoBancoOrigen;
    @XmlElement(name = "TipoCtaOrigen")
    protected int tipoCtaOrigen;
    @XmlElement(name = "CuentaOrigen", required = true)
    protected String cuentaOrigen;
    @XmlElement(name = "RutCuentaOrigen", required = true)
    protected String rutCuentaOrigen;
    @XmlElement(name = "FechaContableBCO", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaContableBCO;
    @XmlElement(name = "CobraImpuesto", required = true)
    protected String cobraImpuesto;
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
     * Obtiene el valor de la propiedad tipoCuenta.
     * 
     */
    public int getTipoCuenta() {
        return tipoCuenta;
    }

    /**
     * Define el valor de la propiedad tipoCuenta.
     * 
     */
    public void setTipoCuenta(int value) {
        this.tipoCuenta = value;
    }

    /**
     * Obtiene el valor de la propiedad rutCuenta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRutCuenta() {
        return rutCuenta;
    }

    /**
     * Define el valor de la propiedad rutCuenta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRutCuenta(String value) {
        this.rutCuenta = value;
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
     * Obtiene el valor de la propiedad traceNumber.
     * 
     */
    public long getTraceNumber() {
        return traceNumber;
    }

    /**
     * Define el valor de la propiedad traceNumber.
     * 
     */
    public void setTraceNumber(long value) {
        this.traceNumber = value;
    }

    /**
     * Obtiene el valor de la propiedad codigoBancoOrigen.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoBancoOrigen() {
        return codigoBancoOrigen;
    }

    /**
     * Define el valor de la propiedad codigoBancoOrigen.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoBancoOrigen(String value) {
        this.codigoBancoOrigen = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoCtaOrigen.
     * 
     */
    public int getTipoCtaOrigen() {
        return tipoCtaOrigen;
    }

    /**
     * Define el valor de la propiedad tipoCtaOrigen.
     * 
     */
    public void setTipoCtaOrigen(int value) {
        this.tipoCtaOrigen = value;
    }

    /**
     * Obtiene el valor de la propiedad cuentaOrigen.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCuentaOrigen() {
        return cuentaOrigen;
    }

    /**
     * Define el valor de la propiedad cuentaOrigen.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCuentaOrigen(String value) {
        this.cuentaOrigen = value;
    }

    /**
     * Obtiene el valor de la propiedad rutCuentaOrigen.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRutCuentaOrigen() {
        return rutCuentaOrigen;
    }

    /**
     * Define el valor de la propiedad rutCuentaOrigen.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRutCuentaOrigen(String value) {
        this.rutCuentaOrigen = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaContableBCO.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaContableBCO() {
        return fechaContableBCO;
    }

    /**
     * Define el valor de la propiedad fechaContableBCO.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaContableBCO(XMLGregorianCalendar value) {
        this.fechaContableBCO = value;
    }

    /**
     * Obtiene el valor de la propiedad cobraImpuesto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCobraImpuesto() {
        return cobraImpuesto;
    }

    /**
     * Define el valor de la propiedad cobraImpuesto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCobraImpuesto(String value) {
        this.cobraImpuesto = value;
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
