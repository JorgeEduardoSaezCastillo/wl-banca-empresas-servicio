
package cl.bice.gfs.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ESTADO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DESCRIPCION" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IDOPERACIONFCC" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NROREFERENCIA" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FECHACONTABLE_OUT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FECHA" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "estado",
    "descripcion",
    "idoperacionfcc",
    "nroreferencia",
    "fechacontableout",
    "fecha"
})
@XmlRootElement(name = "GENERICFINANCIALSERVICE_OUT")
public class GENERICFINANCIALSERVICEOUT {

    @XmlElement(name = "ESTADO", required = true)
    protected String estado;
    @XmlElement(name = "DESCRIPCION", required = true)
    protected String descripcion;
    @XmlElement(name = "IDOPERACIONFCC", required = true)
    protected String idoperacionfcc;
    @XmlElement(name = "NROREFERENCIA", required = true)
    protected String nroreferencia;
    @XmlElement(name = "FECHACONTABLE_OUT", required = true)
    protected String fechacontableout;
    @XmlElement(name = "FECHA", required = true)
    protected String fecha;

    /**
     * Obtiene el valor de la propiedad estado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getESTADO() {
        return estado;
    }

    /**
     * Define el valor de la propiedad estado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setESTADO(String value) {
        this.estado = value;
    }

    /**
     * Obtiene el valor de la propiedad descripcion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDESCRIPCION() {
        return descripcion;
    }

    /**
     * Define el valor de la propiedad descripcion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDESCRIPCION(String value) {
        this.descripcion = value;
    }

    /**
     * Obtiene el valor de la propiedad idoperacionfcc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIDOPERACIONFCC() {
        return idoperacionfcc;
    }

    /**
     * Define el valor de la propiedad idoperacionfcc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIDOPERACIONFCC(String value) {
        this.idoperacionfcc = value;
    }

    /**
     * Obtiene el valor de la propiedad nroreferencia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNROREFERENCIA() {
        return nroreferencia;
    }

    /**
     * Define el valor de la propiedad nroreferencia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNROREFERENCIA(String value) {
        this.nroreferencia = value;
    }

    /**
     * Obtiene el valor de la propiedad fechacontableout.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFECHACONTABLEOUT() {
        return fechacontableout;
    }

    /**
     * Define el valor de la propiedad fechacontableout.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFECHACONTABLEOUT(String value) {
        this.fechacontableout = value;
    }

    /**
     * Obtiene el valor de la propiedad fecha.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFECHA() {
        return fecha;
    }

    /**
     * Define el valor de la propiedad fecha.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFECHA(String value) {
        this.fecha = value;
    }

}
