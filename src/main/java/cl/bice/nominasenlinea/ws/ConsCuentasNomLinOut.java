
package cl.bice.nominasenlinea.ws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para consCuentasNomLinOut complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="consCuentasNomLinOut">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cod_estado" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="cuentasNomLinDto" type="{http://portal.ws.bice.cl/}cuentasNomLinDto" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="msg_result" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consCuentasNomLinOut", propOrder = {
    "codEstado",
    "cuentasNomLinDto",
    "msgResult"
})
public class ConsCuentasNomLinOut {

    @XmlElement(name = "cod_estado")
    protected int codEstado;
    @XmlElement(nillable = true)
    protected List<CuentasNomLinDto> cuentasNomLinDto;
    @XmlElement(name = "msg_result")
    protected String msgResult;

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
     * Gets the value of the cuentasNomLinDto property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cuentasNomLinDto property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCuentasNomLinDto().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CuentasNomLinDto }
     * 
     * 
     */
    public List<CuentasNomLinDto> getCuentasNomLinDto() {
        if (cuentasNomLinDto == null) {
            cuentasNomLinDto = new ArrayList<CuentasNomLinDto>();
        }
        return this.cuentasNomLinDto;
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

}
