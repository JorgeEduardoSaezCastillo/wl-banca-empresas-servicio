
package cl.bice.nominasenlinea.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para revAprobarNomLinIn complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="revAprobarNomLinIn">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IN_COD_NOMINA" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="IN_RUT_USUARIO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IN_ESTADO" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "revAprobarNomLinIn", propOrder = {
    "incodnomina",
    "inrutusuario",
    "inestado"
})
@XmlRootElement(name = "RevAprobarNomLinIn")
public class RevAprobarNomLinIn {

    @XmlElement(name = "IN_COD_NOMINA")
    protected int incodnomina;
    @XmlElement(name = "IN_RUT_USUARIO", required = true)
    protected String inrutusuario;
    @XmlElement(name = "IN_ESTADO")
    protected int inestado;

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

}
