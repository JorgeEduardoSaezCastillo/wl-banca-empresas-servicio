
package cl.bice.tef.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Tpag2000EntradaType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Tpag2000EntradaType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="NumeroOperacion" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="CodigoServicio" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="RutCliente" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CuentaCargo" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="Moneda" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Monto" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="CodigoTipoCtaCargo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="RutApoderado1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RutApoderado2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RutApoderado3" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RutApoderado4" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TipoTransferencia" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
@XmlType(name = "Tpag2000EntradaType", propOrder = {
    "numeroOperacion",
    "codigoServicio",
    "rutCliente",
    "cuentaCargo",
    "moneda",
    "monto",
    "codigoTipoCtaCargo",
    "rutApoderado1",
    "rutApoderado2",
    "rutApoderado3",
    "rutApoderado4",
    "tipoTransferencia",
    "canal"
})
public class Tpag2000EntradaType {

    @XmlElement(name = "NumeroOperacion")
    protected long numeroOperacion;
    @XmlElement(name = "CodigoServicio")
    protected int codigoServicio;
    @XmlElement(name = "RutCliente", required = true)
    protected String rutCliente;
    @XmlElement(name = "CuentaCargo")
    protected long cuentaCargo;
    @XmlElement(name = "Moneda", required = true)
    protected String moneda;
    @XmlElement(name = "Monto")
    protected long monto;
    @XmlElement(name = "CodigoTipoCtaCargo")
    protected int codigoTipoCtaCargo;
    @XmlElement(name = "RutApoderado1", required = true)
    protected String rutApoderado1;
    @XmlElement(name = "RutApoderado2", required = true)
    protected String rutApoderado2;
    @XmlElement(name = "RutApoderado3", required = true)
    protected String rutApoderado3;
    @XmlElement(name = "RutApoderado4", required = true)
    protected String rutApoderado4;
    @XmlElement(name = "TipoTransferencia")
    protected int tipoTransferencia;
    @XmlElement(name = "Canal", required = true)
    protected String canal;

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
     * Obtiene el valor de la propiedad codigoServicio.
     * 
     */
    public int getCodigoServicio() {
        return codigoServicio;
    }

    /**
     * Define el valor de la propiedad codigoServicio.
     * 
     */
    public void setCodigoServicio(int value) {
        this.codigoServicio = value;
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
     * Obtiene el valor de la propiedad moneda.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMoneda() {
        return moneda;
    }

    /**
     * Define el valor de la propiedad moneda.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMoneda(String value) {
        this.moneda = value;
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
     * Obtiene el valor de la propiedad rutApoderado1.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRutApoderado1() {
        return rutApoderado1;
    }

    /**
     * Define el valor de la propiedad rutApoderado1.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRutApoderado1(String value) {
        this.rutApoderado1 = value;
    }

    /**
     * Obtiene el valor de la propiedad rutApoderado2.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRutApoderado2() {
        return rutApoderado2;
    }

    /**
     * Define el valor de la propiedad rutApoderado2.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRutApoderado2(String value) {
        this.rutApoderado2 = value;
    }

    /**
     * Obtiene el valor de la propiedad rutApoderado3.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRutApoderado3() {
        return rutApoderado3;
    }

    /**
     * Define el valor de la propiedad rutApoderado3.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRutApoderado3(String value) {
        this.rutApoderado3 = value;
    }

    /**
     * Obtiene el valor de la propiedad rutApoderado4.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRutApoderado4() {
        return rutApoderado4;
    }

    /**
     * Define el valor de la propiedad rutApoderado4.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRutApoderado4(String value) {
        this.rutApoderado4 = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoTransferencia.
     * 
     */
    public int getTipoTransferencia() {
        return tipoTransferencia;
    }

    /**
     * Define el valor de la propiedad tipoTransferencia.
     * 
     */
    public void setTipoTransferencia(int value) {
        this.tipoTransferencia = value;
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
