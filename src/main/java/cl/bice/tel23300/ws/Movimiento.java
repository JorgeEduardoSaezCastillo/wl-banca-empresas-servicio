
package cl.bice.tel23300.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para movimiento complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="movimiento">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="abono" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cargo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="documento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fecha" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechaMovimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lineaSobregiro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="otroP1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="otroP2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="otroP3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="saldo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "movimiento", propOrder = {
    "abono",
    "cargo",
    "descripcion",
    "documento",
    "fecha",
    "fechaMovimiento",
    "lineaSobregiro",
    "otroP1",
    "otroP2",
    "otroP3",
    "saldo"
})
public class Movimiento {

    protected String abono;
    protected String cargo;
    protected String descripcion;
    protected String documento;
    protected String fecha;
    protected String fechaMovimiento;
    protected String lineaSobregiro;
    protected String otroP1;
    protected String otroP2;
    protected String otroP3;
    protected String saldo;

    /**
     * Obtiene el valor de la propiedad abono.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAbono() {
        return abono;
    }

    /**
     * Define el valor de la propiedad abono.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAbono(String value) {
        this.abono = value;
    }

    /**
     * Obtiene el valor de la propiedad cargo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCargo() {
        return cargo;
    }

    /**
     * Define el valor de la propiedad cargo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCargo(String value) {
        this.cargo = value;
    }

    /**
     * Obtiene el valor de la propiedad descripcion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Define el valor de la propiedad descripcion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcion(String value) {
        this.descripcion = value;
    }

    /**
     * Obtiene el valor de la propiedad documento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocumento() {
        return documento;
    }

    /**
     * Define el valor de la propiedad documento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocumento(String value) {
        this.documento = value;
    }

    /**
     * Obtiene el valor de la propiedad fecha.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * Define el valor de la propiedad fecha.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFecha(String value) {
        this.fecha = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaMovimiento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaMovimiento() {
        return fechaMovimiento;
    }

    /**
     * Define el valor de la propiedad fechaMovimiento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaMovimiento(String value) {
        this.fechaMovimiento = value;
    }

    /**
     * Obtiene el valor de la propiedad lineaSobregiro.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLineaSobregiro() {
        return lineaSobregiro;
    }

    /**
     * Define el valor de la propiedad lineaSobregiro.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLineaSobregiro(String value) {
        this.lineaSobregiro = value;
    }

    /**
     * Obtiene el valor de la propiedad otroP1.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtroP1() {
        return otroP1;
    }

    /**
     * Define el valor de la propiedad otroP1.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtroP1(String value) {
        this.otroP1 = value;
    }

    /**
     * Obtiene el valor de la propiedad otroP2.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtroP2() {
        return otroP2;
    }

    /**
     * Define el valor de la propiedad otroP2.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtroP2(String value) {
        this.otroP2 = value;
    }

    /**
     * Obtiene el valor de la propiedad otroP3.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtroP3() {
        return otroP3;
    }

    /**
     * Define el valor de la propiedad otroP3.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtroP3(String value) {
        this.otroP3 = value;
    }

    /**
     * Obtiene el valor de la propiedad saldo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSaldo() {
        return saldo;
    }

    /**
     * Define el valor de la propiedad saldo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSaldo(String value) {
        this.saldo = value;
    }

}
