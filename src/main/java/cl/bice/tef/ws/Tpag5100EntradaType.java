
package cl.bice.tef.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para Tpag5100EntradaType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Tpag5100EntradaType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CuentaCargo" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="CodigoTipoCtaCargo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="RutCliente" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Monto" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="Moneda" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="NumeroOperacion" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="CodigoBancoAbono" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="CuentaAbono" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CodigoTipoCtaAbono" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="RutCtaAbono" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FechaContable" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="Canal" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CanalResolutor" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CodigoServicio" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Tpag5100EntradaType", propOrder = {
    "cuentaCargo",
    "codigoTipoCtaCargo",
    "rutCliente",
    "monto",
    "moneda",
    "numeroOperacion",
    "codigoBancoAbono",
    "cuentaAbono",
    "codigoTipoCtaAbono",
    "rutCtaAbono",
    "fechaContable",
    "canal",
    "canalResolutor",
    "codigoServicio"
})
public class Tpag5100EntradaType {

    @XmlElement(name = "CuentaCargo")
    protected long cuentaCargo;
    @XmlElement(name = "CodigoTipoCtaCargo")
    protected int codigoTipoCtaCargo;
    @XmlElement(name = "RutCliente", required = true)
    protected String rutCliente;
    @XmlElement(name = "Monto")
    protected long monto;
    @XmlElement(name = "Moneda")
    protected int moneda;
    @XmlElement(name = "NumeroOperacion")
    protected long numeroOperacion;
    @XmlElement(name = "CodigoBancoAbono")
    protected int codigoBancoAbono;
    @XmlElement(name = "CuentaAbono", required = true)
    protected String cuentaAbono;
    @XmlElement(name = "CodigoTipoCtaAbono")
    protected int codigoTipoCtaAbono;
    @XmlElement(name = "RutCtaAbono", required = true)
    protected String rutCtaAbono;
    @XmlElement(name = "FechaContable", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaContable;
    @XmlElement(name = "Canal", required = true)
    protected String canal;
    @XmlElement(name = "CanalResolutor", required = true)
    protected String canalResolutor;
    @XmlElement(name = "CodigoServicio", required = true)
    protected String codigoServicio;

    /**
     * Obtiene el valor de la propiedad cuentaCargo.
     * 
     */
    public long getCuentaCargo() {
        return cuentaCargo;
    }

    /**
     * Define el valor de la propiedad cuentaCargo.
     * 
     */
    public void setCuentaCargo(long value) {
        this.cuentaCargo = value;
    }

    /**
     * Obtiene el valor de la propiedad codigoTipoCtaCargo.
     * 
     */
    public int getCodigoTipoCtaCargo() {
        return codigoTipoCtaCargo;
    }

    /**
     * Define el valor de la propiedad codigoTipoCtaCargo.
     * 
     */
    public void setCodigoTipoCtaCargo(int value) {
        this.codigoTipoCtaCargo = value;
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
     * Obtiene el valor de la propiedad codigoBancoAbono.
     * 
     */
    public int getCodigoBancoAbono() {
        return codigoBancoAbono;
    }

    /**
     * Define el valor de la propiedad codigoBancoAbono.
     * 
     */
    public void setCodigoBancoAbono(int value) {
        this.codigoBancoAbono = value;
    }

    /**
     * Obtiene el valor de la propiedad cuentaAbono.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCuentaAbono() {
        return cuentaAbono;
    }

    /**
     * Define el valor de la propiedad cuentaAbono.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCuentaAbono(String value) {
        this.cuentaAbono = value;
    }

    /**
     * Obtiene el valor de la propiedad codigoTipoCtaAbono.
     * 
     */
    public int getCodigoTipoCtaAbono() {
        return codigoTipoCtaAbono;
    }

    /**
     * Define el valor de la propiedad codigoTipoCtaAbono.
     * 
     */
    public void setCodigoTipoCtaAbono(int value) {
        this.codigoTipoCtaAbono = value;
    }

    /**
     * Obtiene el valor de la propiedad rutCtaAbono.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRutCtaAbono() {
        return rutCtaAbono;
    }

    /**
     * Define el valor de la propiedad rutCtaAbono.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRutCtaAbono(String value) {
        this.rutCtaAbono = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaContable.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaContable() {
        return fechaContable;
    }

    /**
     * Define el valor de la propiedad fechaContable.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaContable(XMLGregorianCalendar value) {
        this.fechaContable = value;
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

    /**
     * Obtiene el valor de la propiedad codigoServicio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoServicio() {
        return codigoServicio;
    }

    /**
     * Define el valor de la propiedad codigoServicio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoServicio(String value) {
        this.codigoServicio = value;
    }

}
