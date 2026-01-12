
package cl.bice.nominasenlinea.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para parametrosDto complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="parametrosDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nom_parametro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nom_tipo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="val_parametro1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "parametrosDto", propOrder = {
    "nomParametro",
    "nomTipo",
    "valParametro1",
    "valParametro2",
    "valParametro3",
    "valParametro4"
})
public class ParametrosDto {

    @XmlElement(name = "nom_parametro")
    protected String nomParametro;
    @XmlElement(name = "nom_tipo")
    protected String nomTipo;
    @XmlElement(name = "val_parametro1")
    protected String valParametro1;
    @XmlElement(name = "val_parametro2")
    protected String valParametro2;
    @XmlElement(name = "val_parametro3")
    protected String valParametro3;
    @XmlElement(name = "val_parametro4")
    protected String valParametro4;

    /**
     * Obtiene el valor de la propiedad nomParametro.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomParametro() {
        return nomParametro;
    }

    /**
     * Define el valor de la propiedad nomParametro.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomParametro(String value) {
        this.nomParametro = value;
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
     * Obtiene el valor de la propiedad valParametro1.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValParametro1() {
        return valParametro1;
    }

    /**
     * Define el valor de la propiedad valParametro1.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValParametro1(String value) {
        this.valParametro1 = value;
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
