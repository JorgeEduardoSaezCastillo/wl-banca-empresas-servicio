
package cl.bice.tef.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ReversarRequestType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ReversarRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Agencia" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Cajero" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
@XmlType(name = "ReversarRequestType", propOrder = {
    "agencia",
    "cajero",
    "numeroSerieTrx"
})
public class ReversarRequestType {

    @XmlElement(name = "Agencia")
    protected int agencia;
    @XmlElement(name = "Cajero")
    protected int cajero;
    @XmlElement(name = "NumeroSerieTrx")
    protected int numeroSerieTrx;

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
