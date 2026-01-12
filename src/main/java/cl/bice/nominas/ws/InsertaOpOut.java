
package cl.bice.nominas.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para insertaOpOut complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="insertaOpOut">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cod_estado" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="msg_result" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="num_oper_prog" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "insertaOpOut", propOrder = {
    "codEstado",
    "msgResult",
    "numOperProg"
})
public class InsertaOpOut {

    @XmlElement(name = "cod_estado")
    protected int codEstado;
    @XmlElement(name = "msg_result")
    protected String msgResult;
    @XmlElement(name = "num_oper_prog")
    protected String numOperProg;

    /**
     * Obtiene el valor de la propiedad codEstado.
     * 
     */
    public int getCodEstado() {
        return codEstado;
    }

    /**
     * Define el valor de la propiedad codEstado.
     * 
     */
    public void setCodEstado(int value) {
        this.codEstado = value;
    }

    /**
     * Obtiene el valor de la propiedad msgResult.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMsgResult() {
        return msgResult;
    }

    /**
     * Define el valor de la propiedad msgResult.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMsgResult(String value) {
        this.msgResult = value;
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

}
