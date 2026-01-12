
package cl.bice.nominasenlinea.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para actEstadoNomIn complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="actEstadoNomIn">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IN_COD_NOMINA" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="IN_RUT_USUARIO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IN_ESTADO" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="IN_NOM_USUARIO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IN_SRV_BICE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IN_IP_CLIENTE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IN_DATO_FIRMA" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "actEstadoNomIn", propOrder = {
    "incodnomina",
    "inrutusuario",
    "inestado",
    "innomusuario",
    "insrvbice",
    "inipcliente",
    "indatofirma"
})
@XmlRootElement(name = "ActEstadoNomIn")
public class ActEstadoNomIn {

    @XmlElement(name = "IN_COD_NOMINA")
    protected int incodnomina;
    @XmlElement(name = "IN_RUT_USUARIO", required = true)
    protected String inrutusuario;
    @XmlElement(name = "IN_ESTADO")
    protected int inestado;
    @XmlElement(name = "IN_NOM_USUARIO", required = true)
    protected String innomusuario;
    @XmlElement(name = "IN_SRV_BICE", required = true)
    protected String insrvbice;
    @XmlElement(name = "IN_IP_CLIENTE", required = true)
    protected String inipcliente;
    @XmlElement(name = "IN_DATO_FIRMA", required = true)
    protected String indatofirma;

    /**
     * Obtiene el valor de la propiedad incodnomina.
     * 
     */
    public int getINCODNOMINA() {
        return incodnomina;
    }

    /**
     * Define el valor de la propiedad incodnomina.
     * 
     */
    public void setINCODNOMINA(int value) {
        this.incodnomina = value;
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
     * Obtiene el valor de la propiedad inestado.
     * 
     */
    public int getINESTADO() {
        return inestado;
    }

    /**
     * Define el valor de la propiedad inestado.
     * 
     */
    public void setINESTADO(int value) {
        this.inestado = value;
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
     * Obtiene el valor de la propiedad insrvbice.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINSRVBICE() {
        return insrvbice;
    }

    /**
     * Define el valor de la propiedad insrvbice.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINSRVBICE(String value) {
        this.insrvbice = value;
    }

    /**
     * Obtiene el valor de la propiedad inipcliente.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINIPCLIENTE() {
        return inipcliente;
    }

    /**
     * Define el valor de la propiedad inipcliente.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINIPCLIENTE(String value) {
        this.inipcliente = value;
    }

    /**
     * Obtiene el valor de la propiedad indatofirma.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINDATOFIRMA() {
        return indatofirma;
    }

    /**
     * Define el valor de la propiedad indatofirma.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINDATOFIRMA(String value) {
        this.indatofirma = value;
    }

}
