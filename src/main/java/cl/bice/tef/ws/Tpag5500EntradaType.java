
package cl.bice.tef.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para Tpag5500EntradaType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Tpag5500EntradaType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CodigoBancoOrigen" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FechaContableCCA" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="NumeroOperacion" type="{http://www.w3.org/2001/XMLSchema}long"/>
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
@XmlType(name = "Tpag5500EntradaType", propOrder = {
    "codigoBancoOrigen",
    "fechaContableCCA",
    "numeroOperacion",
    "canal"
})
public class Tpag5500EntradaType {

    @XmlElement(name = "CodigoBancoOrigen", required = true)
    protected String codigoBancoOrigen;
    @XmlElement(name = "FechaContableCCA", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaContableCCA;
    @XmlElement(name = "NumeroOperacion")
    protected long numeroOperacion;
    @XmlElement(name = "Canal", required = true)
    protected String canal;

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
