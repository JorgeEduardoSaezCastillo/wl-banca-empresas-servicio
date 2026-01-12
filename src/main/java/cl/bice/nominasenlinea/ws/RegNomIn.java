
package cl.bice.nominasenlinea.ws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para regNomIn complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="regNomIn">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IN_COD_TIPO_NOM" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="IN_RUT_EMPRESA" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IN_NOMBRE_EMPRESA" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IN_NOM_ARCHIVO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IN_REFERENCIA" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IN_CUENTA_CARGO" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="IN_MONTO" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="IN_CANTIDAD_REGISTROS" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="IN_RUT_USUARIO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IN_NOM_USUARIO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IN_REGISTROS_DTO" type="{http://portal.ws.bice.cl/}registrosNomDto" maxOccurs="unbounded"/>
 *         &lt;element name="IN_EMAIL_ORDENANTE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IN_ENVIOMAIL_ORDENANTE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "regNomIn", propOrder = {
    "incodtiponom",
    "inrutempresa",
    "innombreempresa",
    "innomarchivo",
    "inreferencia",
    "incuentacargo",
    "inmonto",
    "incantidadregistros",
    "inrutusuario",
    "innomusuario",
    "inregistrosdto",
    "inemailordenante",
    "inenviomailordenante"
})
public class RegNomIn {

    @XmlElement(name = "IN_COD_TIPO_NOM")
    protected int incodtiponom;
    @XmlElement(name = "IN_RUT_EMPRESA", required = true)
    protected String inrutempresa;
    @XmlElement(name = "IN_NOMBRE_EMPRESA", required = true)
    protected String innombreempresa;
    @XmlElement(name = "IN_NOM_ARCHIVO", required = true)
    protected String innomarchivo;
    @XmlElement(name = "IN_REFERENCIA", required = true)
    protected String inreferencia;
    @XmlElement(name = "IN_CUENTA_CARGO")
    protected long incuentacargo;
    @XmlElement(name = "IN_MONTO")
    protected long inmonto;
    @XmlElement(name = "IN_CANTIDAD_REGISTROS")
    protected int incantidadregistros;
    @XmlElement(name = "IN_RUT_USUARIO", required = true)
    protected String inrutusuario;
    @XmlElement(name = "IN_NOM_USUARIO", required = true)
    protected String innomusuario;
    @XmlElement(name = "IN_REGISTROS_DTO", required = true)
    protected List<RegistrosNomDto> inregistrosdto;
    @XmlElement(name = "IN_EMAIL_ORDENANTE", required = true)
    protected String inemailordenante;
    @XmlElement(name = "IN_ENVIOMAIL_ORDENANTE", required = true)
    protected String inenviomailordenante;

    /**
     * Obtiene el valor de la propiedad incodtiponom.
     * 
     */
    public int getINCODTIPONOM() {
        return incodtiponom;
    }

    /**
     * Define el valor de la propiedad incodtiponom.
     * 
     */
    public void setINCODTIPONOM(int value) {
        this.incodtiponom = value;
    }

    /**
     * Obtiene el valor de la propiedad inrutempresa.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINRUTEMPRESA() {
        return inrutempresa;
    }

    /**
     * Define el valor de la propiedad inrutempresa.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINRUTEMPRESA(String value) {
        this.inrutempresa = value;
    }

    /**
     * Obtiene el valor de la propiedad innombreempresa.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINNOMBREEMPRESA() {
        return innombreempresa;
    }

    /**
     * Define el valor de la propiedad innombreempresa.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINNOMBREEMPRESA(String value) {
        this.innombreempresa = value;
    }

    /**
     * Obtiene el valor de la propiedad innomarchivo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINNOMARCHIVO() {
        return innomarchivo;
    }

    /**
     * Define el valor de la propiedad innomarchivo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINNOMARCHIVO(String value) {
        this.innomarchivo = value;
    }

    /**
     * Obtiene el valor de la propiedad inreferencia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINREFERENCIA() {
        return inreferencia;
    }

    /**
     * Define el valor de la propiedad inreferencia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINREFERENCIA(String value) {
        this.inreferencia = value;
    }

    /**
     * Obtiene el valor de la propiedad incuentacargo.
     * 
     */
    public long getINCUENTACARGO() {
        return incuentacargo;
    }

    /**
     * Define el valor de la propiedad incuentacargo.
     * 
     */
    public void setINCUENTACARGO(long value) {
        this.incuentacargo = value;
    }

    /**
     * Obtiene el valor de la propiedad inmonto.
     * 
     */
    public long getINMONTO() {
        return inmonto;
    }

    /**
     * Define el valor de la propiedad inmonto.
     * 
     */
    public void setINMONTO(long value) {
        this.inmonto = value;
    }

    /**
     * Obtiene el valor de la propiedad incantidadregistros.
     * 
     */
    public int getINCANTIDADREGISTROS() {
        return incantidadregistros;
    }

    /**
     * Define el valor de la propiedad incantidadregistros.
     * 
     */
    public void setINCANTIDADREGISTROS(int value) {
        this.incantidadregistros = value;
    }

    /**
     * Obtiene el valor de la propiedad inrutusuario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINRUTUSUARIO() {
        return inrutusuario;
    }

    /**
     * Define el valor de la propiedad inrutusuario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINRUTUSUARIO(String value) {
        this.inrutusuario = value;
    }

    /**
     * Obtiene el valor de la propiedad innomusuario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINNOMUSUARIO() {
        return innomusuario;
    }

    /**
     * Define el valor de la propiedad innomusuario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINNOMUSUARIO(String value) {
        this.innomusuario = value;
    }

    /**
     * Gets the value of the inregistrosdto property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the inregistrosdto property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getINREGISTROSDTO().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RegistrosNomDto }
     * 
     * 
     */
    public List<RegistrosNomDto> getINREGISTROSDTO() {
        if (inregistrosdto == null) {
            inregistrosdto = new ArrayList<RegistrosNomDto>();
        }
        return this.inregistrosdto;
    }

    /**
     * Obtiene el valor de la propiedad inemailordenante.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINEMAILORDENANTE() {
        return inemailordenante;
    }

    /**
     * Define el valor de la propiedad inemailordenante.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINEMAILORDENANTE(String value) {
        this.inemailordenante = value;
    }

    /**
     * Obtiene el valor de la propiedad inenviomailordenante.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINENVIOMAILORDENANTE() {
        return inenviomailordenante;
    }

    /**
     * Define el valor de la propiedad inenviomailordenante.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINENVIOMAILORDENANTE(String value) {
        this.inenviomailordenante = value;
    }

}
