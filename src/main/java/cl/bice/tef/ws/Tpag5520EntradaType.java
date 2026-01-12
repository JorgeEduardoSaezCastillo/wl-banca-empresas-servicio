
package cl.bice.tef.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Tpag5520EntradaType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Tpag5520EntradaType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CodigoBancoOrigen" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="TraceNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "Tpag5520EntradaType", propOrder = {
    "codigoBancoOrigen",
    "traceNumber",
    "canal",
    "canalResolutor"
})
public class Tpag5520EntradaType {

    @XmlElement(name = "CodigoBancoOrigen")
    protected int codigoBancoOrigen;
    @XmlElement(name = "TraceNumber", required = true)
    protected String traceNumber;
    @XmlElement(name = "Canal", required = true)
    protected String canal;
    @XmlElement(name = "CanalResolutor", required = true)
    protected String canalResolutor;

    /**
     * Obtiene el valor de la propiedad codigoBancoOrigen.
     * 
     */
    public int getCodigoBancoOrigen() {
        return codigoBancoOrigen;
    }

    /**
     * Define el valor de la propiedad codigoBancoOrigen.
     * 
     */
    public void setCodigoBancoOrigen(int value) {
        this.codigoBancoOrigen = value;
    }

    /**
     * Obtiene el valor de la propiedad traceNumber.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTraceNumber() {
        return traceNumber;
    }

    /**
     * Define el valor de la propiedad traceNumber.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTraceNumber(String value) {
        this.traceNumber = value;
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
