
package cl.bice.nominasenlinea.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para consEnvioMailIn complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="consEnvioMailIn">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IN_RUT_EMPRESA" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IN_COD_ESTADO" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consEnvioMailIn", propOrder = {
    "inrutempresa",
    "incodestado"
})
public class ConsEnvioMailIn {

    @XmlElement(name = "IN_RUT_EMPRESA", required = true)
    protected String inrutempresa;
    @XmlElement(name = "IN_COD_ESTADO")
    protected int incodestado;

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
     * Obtiene el valor de la propiedad incodestado.
     * 
     */
    public int getINCODESTADO() {
        return incodestado;
    }

    /**
     * Define el valor de la propiedad incodestado.
     * 
     */
    public void setINCODESTADO(int value) {
        this.incodestado = value;
    }

}
