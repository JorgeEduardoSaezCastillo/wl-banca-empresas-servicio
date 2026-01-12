
package cl.bice.tef.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Tpag5300ResponseType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Tpag5300ResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Codigo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Glosa" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Cajero" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Agencia" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="NumeroSerieTrx" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Tpag5300ResponseType", propOrder = {
    "codigo",
    "glosa",
    "cajero",
    "agencia",
    "numeroSerieTrx"
})
public class Tpag5300ResponseType {

    @XmlElement(name = "Codigo", required = true)
    protected String codigo;
    @XmlElement(name = "Glosa", required = true)
    protected String glosa;
    @XmlElement(name = "Cajero")
    protected int cajero;
    @XmlElement(name = "Agencia")
    protected int agencia;
    @XmlElement(name = "NumeroSerieTrx")
    protected int numeroSerieTrx;

    /**
     * Obtiene el valor de la propiedad codigo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Define el valor de la propiedad codigo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigo(String value) {
        this.codigo = value;
    }

    /**
     * Obtiene el valor de la propiedad glosa.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGlosa() {
        return glosa;
    }

    /**
     * Define el valor de la propiedad glosa.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGlosa(String value) {
        this.glosa = value;
    }

    /**
     * Obtiene el valor de la propiedad cajero.
     * 
     */
    public int getCajero() {
        return cajero;
    }

    /**
     * Define el valor de la propiedad cajero.
     * 
     */
    public void setCajero(int value) {
        this.cajero = value;
    }

    /**
     * Obtiene el valor de la propiedad agencia.
     * 
     */
    public int getAgencia() {
        return agencia;
    }

    /**
     * Define el valor de la propiedad agencia.
     * 
     */
    public void setAgencia(int value) {
        this.agencia = value;
    }

    /**
     * Obtiene el valor de la propiedad numeroSerieTrx.
     * 
     */
    public int getNumeroSerieTrx() {
        return numeroSerieTrx;
    }

    /**
     * Define el valor de la propiedad numeroSerieTrx.
     * 
     */
    public void setNumeroSerieTrx(int value) {
        this.numeroSerieTrx = value;
    }

}
