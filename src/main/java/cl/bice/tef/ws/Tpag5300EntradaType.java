
package cl.bice.tef.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para Tpag5300EntradaType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Tpag5300EntradaType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CuentaDestinoBICE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CodigoTipoCtaDestinoBICE" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="RutClienteBICE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Monto" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="Moneda" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="NumeroRastro" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="CodigoBancoOrigen" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CodigoTipoCtaOrigen" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="CuentaOrigen" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RutOriginador" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FechaContableCCA" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="Canal" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CanalResolutor" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Tpag5300EntradaType", propOrder = {
    "cuentaDestinoBICE",
    "codigoTipoCtaDestinoBICE",
    "rutClienteBICE",
    "monto",
    "moneda",
    "numeroRastro",
    "codigoBancoOrigen",
    "codigoTipoCtaOrigen",
    "cuentaOrigen",
    "rutOriginador",
    "fechaContableCCA",
    "canal",
    "canalResolutor"
})
public class Tpag5300EntradaType {

    @XmlElement(name = "CuentaDestinoBICE", required = true)
    protected String cuentaDestinoBICE;
    @XmlElement(name = "CodigoTipoCtaDestinoBICE")
    protected int codigoTipoCtaDestinoBICE;
    @XmlElement(name = "RutClienteBICE", required = true)
    protected String rutClienteBICE;
    @XmlElement(name = "Monto")
    protected long monto;
    @XmlElement(name = "Moneda")
    protected int moneda;
    @XmlElement(name = "NumeroRastro")
    protected long numeroRastro;
    @XmlElement(name = "CodigoBancoOrigen", required = true)
    protected String codigoBancoOrigen;
    @XmlElement(name = "CodigoTipoCtaOrigen")
    protected int codigoTipoCtaOrigen;
    @XmlElement(name = "CuentaOrigen", required = true)
    protected String cuentaOrigen;
    @XmlElement(name = "RutOriginador", required = true)
    protected String rutOriginador;
    @XmlElement(name = "FechaContableCCA", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaContableCCA;
    @XmlElement(name = "Canal", required = true)
    protected String canal;
    @XmlElement(name = "CanalResolutor", required = true)
    protected String canalResolutor;

    /**
     * Obtiene el valor de la propiedad cuentaDestinoBICE.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCuentaDestinoBICE() {
        return cuentaDestinoBICE;
    }

    /**
     * Define el valor de la propiedad cuentaDestinoBICE.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCuentaDestinoBICE(String value) {
        this.cuentaDestinoBICE = value;
    }

    /**
     * Obtiene el valor de la propiedad codigoTipoCtaDestinoBICE.
     * 
     */
    public int getCodigoTipoCtaDestinoBICE() {
        return codigoTipoCtaDestinoBICE;
    }

    /**
     * Define el valor de la propiedad codigoTipoCtaDestinoBICE.
     * 
     */
    public void setCodigoTipoCtaDestinoBICE(int value) {
        this.codigoTipoCtaDestinoBICE = value;
    }

    /**
     * Obtiene el valor de la propiedad rutClienteBICE.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRutClienteBICE() {
        return rutClienteBICE;
    }

    /**
     * Define el valor de la propiedad rutClienteBICE.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRutClienteBICE(String value) {
        this.rutClienteBICE = value;
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
     * Obtiene el valor de la propiedad numeroRastro.
     * 
     */
    public long getNumeroRastro() {
        return numeroRastro;
    }

    /**
     * Define el valor de la propiedad numeroRastro.
     * 
     */
    public void setNumeroRastro(long value) {
        this.numeroRastro = value;
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
     * Obtiene el valor de la propiedad codigoTipoCtaOrigen.
     * 
     */
    public int getCodigoTipoCtaOrigen() {
        return codigoTipoCtaOrigen;
    }

    /**
     * Define el valor de la propiedad codigoTipoCtaOrigen.
     * 
     */
    public void setCodigoTipoCtaOrigen(int value) {
        this.codigoTipoCtaOrigen = value;
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

    /**
     * Obtiene el valor de la propiedad canalResolutor.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCanalResolutor() {
        return canalResolutor;
    }

    /**
     * Define el valor de la propiedad canalResolutor.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCanalResolutor(String value) {
        this.canalResolutor = value;
    }

}
