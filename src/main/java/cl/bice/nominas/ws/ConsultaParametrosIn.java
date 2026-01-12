
package cl.bice.nominas.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para consultaParametrosIn complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="consultaParametrosIn">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IN_NOM_PARAM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IN_NOM_TIPO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultaParametrosIn", propOrder = {
    "innomparam",
    "innomtipo"
})
@XmlRootElement(name = "ConsultaParametrosIn")
public class ConsultaParametrosIn {

    @XmlElement(name = "IN_NOM_PARAM")
    protected String innomparam;
    @XmlElement(name = "IN_NOM_TIPO")
    protected String innomtipo;

    /**
     * Obtiene el valor de la propiedad innomparam.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINNOMPARAM() {
        return innomparam;
    }

    /**
     * Define el valor de la propiedad innomparam.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINNOMPARAM(String value) {
        this.innomparam = value;
    }

    /**
     * Obtiene el valor de la propiedad innomtipo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINNOMTIPO() {
        return innomtipo;
    }

    /**
     * Define el valor de la propiedad innomtipo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINNOMTIPO(String value) {
        this.innomtipo = value;
    }

}
