
package cl.bice.nominasenlinea.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para consNomParamIn complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="consNomParamIn">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IN_NOM_PARAMETRO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consNomParamIn", propOrder = {
    "innomparametro"
})
public class ConsNomParamIn {

    @XmlElement(name = "IN_NOM_PARAMETRO", required = true)
    protected String innomparametro;

    /**
     * Obtiene el valor de la propiedad innomparametro.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINNOMPARAMETRO() {
        return innomparametro;
    }

    /**
     * Define el valor de la propiedad innomparametro.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINNOMPARAMETRO(String value) {
        this.innomparametro = value;
    }

}
