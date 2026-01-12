
package cl.bice.nominasenlinea.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para actCerrarNomIn complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="actCerrarNomIn">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IN_COD_NOMINA" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="IN_CTA_CARGO" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="IN_FEC_PAGO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IN_SRV_CIERRE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IN_IP_CIERRE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IN_RUT_USUARIO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IN_NOM_USUARIO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "actCerrarNomIn", propOrder = {
    "incodnomina",
    "inctacargo",
    "infecpago",
    "insrvcierre",
    "inipcierre",
    "inrutusuario",
    "innomusuario"
})
public class ActCerrarNomIn {

    @XmlElement(name = "IN_COD_NOMINA")
    protected int incodnomina;
    @XmlElement(name = "IN_CTA_CARGO")
    protected long inctacargo;
    @XmlElement(name = "IN_FEC_PAGO", required = true)
    protected String infecpago;
    @XmlElement(name = "IN_SRV_CIERRE", required = true)
    protected String insrvcierre;
    @XmlElement(name = "IN_IP_CIERRE", required = true)
    protected String inipcierre;
    @XmlElement(name = "IN_RUT_USUARIO", required = true)
    protected String inrutusuario;
    @XmlElement(name = "IN_NOM_USUARIO", required = true)
    protected String innomusuario;

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
     * Obtiene el valor de la propiedad inctacargo.
     * 
     */
    public long getINCTACARGO() {
        return inctacargo;
    }

    /**
     * Define el valor de la propiedad inctacargo.
     * 
     */
    public void setINCTACARGO(long value) {
        this.inctacargo = value;
    }

    /**
     * Obtiene el valor de la propiedad infecpago.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINFECPAGO() {
        return infecpago;
    }

    /**
     * Define el valor de la propiedad infecpago.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINFECPAGO(String value) {
        this.infecpago = value;
    }

    /**
     * Obtiene el valor de la propiedad insrvcierre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINSRVCIERRE() {
        return insrvcierre;
    }

    /**
     * Define el valor de la propiedad insrvcierre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINSRVCIERRE(String value) {
        this.insrvcierre = value;
    }

    /**
     * Obtiene el valor de la propiedad inipcierre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINIPCIERRE() {
        return inipcierre;
    }

    /**
     * Define el valor de la propiedad inipcierre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINIPCIERRE(String value) {
        this.inipcierre = value;
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

}
