
package cl.bice.tef.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Tpag2000ResponseType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Tpag2000ResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Codigo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SubCodigo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Glosa" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Tpag2000ResponseType", propOrder = {
    "codigo",
    "subCodigo",
    "glosa"
})
@XmlRootElement(name = "Tpag2000ResponseType")
public class Tpag2000ResponseType {

    @XmlElement(name = "Codigo", required = true)
    protected String codigo;
    @XmlElement(name = "SubCodigo", required = true)
    protected String subCodigo;
    @XmlElement(name = "Glosa", required = true)
    protected String glosa;

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
     * Obtiene el valor de la propiedad subCodigo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubCodigo() {
        return subCodigo;
    }

    /**
     * Define el valor de la propiedad subCodigo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubCodigo(String value) {
        this.subCodigo = value;
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

}
