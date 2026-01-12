
package cl.bice.nominas.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para regNomiCargoLineaVo complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="regNomiCargoLineaVo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fecCurse" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="glsGlosa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="monto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numCtaCargo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numOperProg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rutEmpresa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "regNomiCargoLineaVo", propOrder = {
    "fecCurse",
    "glsGlosa",
    "monto",
    "numCtaCargo",
    "numOperProg",
    "rutEmpresa"
})
public class RegNomiCargoLineaVo {

    protected String fecCurse;
    protected String glsGlosa;
    protected String monto;
    protected String numCtaCargo;
    protected String numOperProg;
    protected String rutEmpresa;

    /**
     * Obtiene el valor de la propiedad fecCurse.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFecCurse() {
        return fecCurse;
    }

    /**
     * Define el valor de la propiedad fecCurse.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFecCurse(String value) {
        this.fecCurse = value;
    }

    /**
     * Obtiene el valor de la propiedad glsGlosa.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGlsGlosa() {
        return glsGlosa;
    }

    /**
     * Define el valor de la propiedad glsGlosa.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGlsGlosa(String value) {
        this.glsGlosa = value;
    }

    /**
     * Obtiene el valor de la propiedad monto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMonto() {
        return monto;
    }

    /**
     * Define el valor de la propiedad monto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMonto(String value) {
        this.monto = value;
    }

    /**
     * Obtiene el valor de la propiedad numCtaCargo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumCtaCargo() {
        return numCtaCargo;
    }

    /**
     * Define el valor de la propiedad numCtaCargo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumCtaCargo(String value) {
        this.numCtaCargo = value;
    }

    /**
     * Obtiene el valor de la propiedad numOperProg.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumOperProg() {
        return numOperProg;
    }

    /**
     * Define el valor de la propiedad numOperProg.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumOperProg(String value) {
        this.numOperProg = value;
    }

    /**
     * Obtiene el valor de la propiedad rutEmpresa.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRutEmpresa() {
        return rutEmpresa;
    }

    /**
     * Define el valor de la propiedad rutEmpresa.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRutEmpresa(String value) {
        this.rutEmpresa = value;
    }

}
