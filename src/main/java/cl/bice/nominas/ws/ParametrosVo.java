
package cl.bice.nominas.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para parametrosVo complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="parametrosVo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nom_param" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nom_tipo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="val_parametro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="val_parametro2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="val_parametro3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="val_parametro4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "parametrosVo", propOrder = {
    "nomParam",
    "nomTipo",
    "valParametro",
    "valParametro2",
    "valParametro3",
    "valParametro4"
})
public class ParametrosVo {

    @XmlElement(name = "nom_param")
    protected String nomParam;
    @XmlElement(name = "nom_tipo")
    protected String nomTipo;
    @XmlElement(name = "val_parametro")
    protected String valParametro;
    @XmlElement(name = "val_parametro2")
    protected String valParametro2;
    @XmlElement(name = "val_parametro3")
    protected String valParametro3;
    @XmlElement(name = "val_parametro4")
    protected String valParametro4;

    /**
     * Obtiene el valor de la propiedad nomParam.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomParam() {
        return nomParam;
    }

    /**
     * Define el valor de la propiedad nomParam.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomParam(String value) {
        this.nomParam = value;
    }

    /**
     * Obtiene el valor de la propiedad nomTipo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomTipo() {
        return nomTipo;
    }

    /**
     * Define el valor de la propiedad nomTipo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomTipo(String value) {
        this.nomTipo = value;
    }

    /**
     * Obtiene el valor de la propiedad valParametro.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValParametro() {
        return valParametro;
    }

    /**
     * Define el valor de la propiedad valParametro.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValParametro(String value) {
        this.valParametro = value;
    }

    /**
     * Obtiene el valor de la propiedad valParametro2.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValParametro2() {
        return valParametro2;
    }

    /**
     * Define el valor de la propiedad valParametro2.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValParametro2(String value) {
        this.valParametro2 = value;
    }

    /**
     * Obtiene el valor de la propiedad valParametro3.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValParametro3() {
        return valParametro3;
    }

    /**
     * Define el valor de la propiedad valParametro3.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValParametro3(String value) {
        this.valParametro3 = value;
    }

    /**
     * Obtiene el valor de la propiedad valParametro4.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValParametro4() {
        return valParametro4;
    }

    /**
     * Define el valor de la propiedad valParametro4.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValParametro4(String value) {
        this.valParametro4 = value;
    }

}
