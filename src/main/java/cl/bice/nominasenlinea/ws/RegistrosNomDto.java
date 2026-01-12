
package cl.bice.nominasenlinea.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para registrosNomDto complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="registrosNomDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="banco" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codBanco" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="codMoneda" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="codRegistro" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="cuentaAbono" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="emailBeneficiario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="estado" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="factura" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechaPago" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="fechaPagoFormateada" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="glsEstado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="glsTipoCuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="i_EnvioBeneficiario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="monto" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="nombreTitular" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numeroRegistro" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="oficDestino" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="oficOrigen" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="rutTitular" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoCuenta" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "registrosNomDto", propOrder = {
    "banco",
    "codBanco",
    "codMoneda",
    "codRegistro",
    "cuentaAbono",
    "emailBeneficiario",
    "estado",
    "factura",
    "fechaPago",
    "fechaPagoFormateada",
    "glsEstado",
    "glsTipoCuenta",
    "iEnvioBeneficiario",
    "monto",
    "nombreTitular",
    "numeroRegistro",
    "oficDestino",
    "oficOrigen",
    "rutTitular",
    "tipoCuenta"
})
public class RegistrosNomDto {

    protected String banco;
    protected int codBanco;
    protected int codMoneda;
    protected int codRegistro;
    protected String cuentaAbono;
    protected String emailBeneficiario;
    protected int estado;
    protected String factura;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaPago;
    protected String fechaPagoFormateada;
    protected String glsEstado;
    protected String glsTipoCuenta;
    @XmlElement(name = "i_EnvioBeneficiario")
    protected String iEnvioBeneficiario;
    protected long monto;
    protected String nombreTitular;
    protected int numeroRegistro;
    protected int oficDestino;
    protected int oficOrigen;
    protected String rutTitular;
    protected int tipoCuenta;

    /**
     * Obtiene el valor de la propiedad banco.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBanco() {
        return banco;
    }

    /**
     * Define el valor de la propiedad banco.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBanco(String value) {
        this.banco = value;
    }

    /**
     * Obtiene el valor de la propiedad codBanco.
     * 
     */
    public int getCodBanco() {
        return codBanco;
    }

    /**
     * Define el valor de la propiedad codBanco.
     * 
     */
    public void setCodBanco(int value) {
        this.codBanco = value;
    }

    /**
     * Obtiene el valor de la propiedad codMoneda.
     * 
     */
    public int getCodMoneda() {
        return codMoneda;
    }

    /**
     * Define el valor de la propiedad codMoneda.
     * 
     */
    public void setCodMoneda(int value) {
        this.codMoneda = value;
    }

    /**
     * Obtiene el valor de la propiedad codRegistro.
     * 
     */
    public int getCodRegistro() {
        return codRegistro;
    }

    /**
     * Define el valor de la propiedad codRegistro.
     * 
     */
    public void setCodRegistro(int value) {
        this.codRegistro = value;
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
     * Obtiene el valor de la propiedad emailBeneficiario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailBeneficiario() {
        return emailBeneficiario;
    }

    /**
     * Define el valor de la propiedad emailBeneficiario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailBeneficiario(String value) {
        this.emailBeneficiario = value;
    }

    /**
     * Obtiene el valor de la propiedad estado.
     * 
     */
    public int getEstado() {
        return estado;
    }

    /**
     * Define el valor de la propiedad estado.
     * 
     */
    public void setEstado(int value) {
        this.estado = value;
    }

    /**
     * Obtiene el valor de la propiedad factura.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFactura() {
        return factura;
    }

    /**
     * Define el valor de la propiedad factura.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFactura(String value) {
        this.factura = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaPago.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaPago() {
        return fechaPago;
    }

    /**
     * Define el valor de la propiedad fechaPago.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaPago(XMLGregorianCalendar value) {
        this.fechaPago = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaPagoFormateada.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaPagoFormateada() {
        return fechaPagoFormateada;
    }

    /**
     * Define el valor de la propiedad fechaPagoFormateada.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaPagoFormateada(String value) {
        this.fechaPagoFormateada = value;
    }

    /**
     * Obtiene el valor de la propiedad glsEstado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGlsEstado() {
        return glsEstado;
    }

    /**
     * Define el valor de la propiedad glsEstado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGlsEstado(String value) {
        this.glsEstado = value;
    }

    /**
     * Obtiene el valor de la propiedad glsTipoCuenta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGlsTipoCuenta() {
        return glsTipoCuenta;
    }

    /**
     * Define el valor de la propiedad glsTipoCuenta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGlsTipoCuenta(String value) {
        this.glsTipoCuenta = value;
    }

    /**
     * Obtiene el valor de la propiedad iEnvioBeneficiario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIEnvioBeneficiario() {
        return iEnvioBeneficiario;
    }

    /**
     * Define el valor de la propiedad iEnvioBeneficiario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIEnvioBeneficiario(String value) {
        this.iEnvioBeneficiario = value;
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
     * Obtiene el valor de la propiedad nombreTitular.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreTitular() {
        return nombreTitular;
    }

    /**
     * Define el valor de la propiedad nombreTitular.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreTitular(String value) {
        this.nombreTitular = value;
    }

    /**
     * Obtiene el valor de la propiedad numeroRegistro.
     * 
     */
    public int getNumeroRegistro() {
        return numeroRegistro;
    }

    /**
     * Define el valor de la propiedad numeroRegistro.
     * 
     */
    public void setNumeroRegistro(int value) {
        this.numeroRegistro = value;
    }

    /**
     * Obtiene el valor de la propiedad oficDestino.
     * 
     */
    public int getOficDestino() {
        return oficDestino;
    }

    /**
     * Define el valor de la propiedad oficDestino.
     * 
     */
    public void setOficDestino(int value) {
        this.oficDestino = value;
    }

    /**
     * Obtiene el valor de la propiedad oficOrigen.
     * 
     */
    public int getOficOrigen() {
        return oficOrigen;
    }

    /**
     * Define el valor de la propiedad oficOrigen.
     * 
     */
    public void setOficOrigen(int value) {
        this.oficOrigen = value;
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

}
