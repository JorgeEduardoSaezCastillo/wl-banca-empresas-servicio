
package cl.bice.nominas.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para firmaOpVo complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="firmaOpVo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dato_firma" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fec_firma" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nom_apoderado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rut_apoderado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "firmaOpVo", propOrder = {
    "datoFirma",
    "fecFirma",
    "nomApoderado",
    "rutApoderado"
})
public class FirmaOpVo {

    @XmlElement(name = "dato_firma")
    protected String datoFirma;
    @XmlElement(name = "fec_firma")
    protected String fecFirma;
    @XmlElement(name = "nom_apoderado")
    protected String nomApoderado;
    @XmlElement(name = "rut_apoderado")
    protected String rutApoderado;

    /**
     * Obtiene el valor de la propiedad datoFirma.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDatoFirma() {
        return datoFirma;
    }

    /**
     * Define el valor de la propiedad datoFirma.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatoFirma(String value) {
        this.datoFirma = value;
    }

    /**
     * Obtiene el valor de la propiedad fecFirma.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFecFirma() {
        return fecFirma;
    }

    /**
     * Define el valor de la propiedad fecFirma.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFecFirma(String value) {
        this.fecFirma = value;
    }

    /**
     * Obtiene el valor de la propiedad nomApoderado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomApoderado() {
        return nomApoderado;
    }

    /**
     * Define el valor de la propiedad nomApoderado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomApoderado(String value) {
        this.nomApoderado = value;
    }

    /**
     * Obtiene el valor de la propiedad rutApoderado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRutApoderado() {
        return rutApoderado;
    }

    /**
     * Define el valor de la propiedad rutApoderado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRutApoderado(String value) {
        this.rutApoderado = value;
    }

}
