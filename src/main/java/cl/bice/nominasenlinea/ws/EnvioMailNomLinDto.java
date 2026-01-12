
package cl.bice.nominasenlinea.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para envioMailNomLinDto complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="envioMailNomLinDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fec_modifica" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fec_registro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="flg_activo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id_registro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rut_empresa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rut_modifica" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "envioMailNomLinDto", propOrder = {
    "fecModifica",
    "fecRegistro",
    "flgActivo",
    "idRegistro",
    "rutEmpresa",
    "rutModifica"
})
public class EnvioMailNomLinDto {

    @XmlElement(name = "fec_modifica")
    protected String fecModifica;
    @XmlElement(name = "fec_registro")
    protected String fecRegistro;
    @XmlElement(name = "flg_activo")
    protected String flgActivo;
    @XmlElement(name = "id_registro")
    protected String idRegistro;
    @XmlElement(name = "rut_empresa")
    protected String rutEmpresa;
    @XmlElement(name = "rut_modifica")
    protected String rutModifica;

    /**
     * Obtiene el valor de la propiedad fecModifica.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFecModifica() {
        return fecModifica;
    }

    /**
     * Define el valor de la propiedad fecModifica.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFecModifica(String value) {
        this.fecModifica = value;
    }

    /**
     * Obtiene el valor de la propiedad fecRegistro.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFecRegistro() {
        return fecRegistro;
    }

    /**
     * Define el valor de la propiedad fecRegistro.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFecRegistro(String value) {
        this.fecRegistro = value;
    }

    /**
     * Obtiene el valor de la propiedad flgActivo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFlgActivo() {
        return flgActivo;
    }

    /**
     * Define el valor de la propiedad flgActivo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlgActivo(String value) {
        this.flgActivo = value;
    }

    /**
     * Obtiene el valor de la propiedad idRegistro.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdRegistro() {
        return idRegistro;
    }

    /**
     * Define el valor de la propiedad idRegistro.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdRegistro(String value) {
        this.idRegistro = value;
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

    /**
     * Obtiene el valor de la propiedad rutModifica.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRutModifica() {
        return rutModifica;
    }

    /**
     * Define el valor de la propiedad rutModifica.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRutModifica(String value) {
        this.rutModifica = value;
    }

}
