
package cl.bice.genericoperprog.ws;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para consultaApoOpOut complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="consultaApoOpOut">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cod_estado" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="firmaOpDto" type="{http://ws.operprog.bice.cl/}firmaOpDto" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "consultaApoOpOut", propOrder = {
    "codEstado",
    "firmaOpDto",
    "msgResult"
})
@XmlRootElement(name = "ConsultaApoOpOut")
public class ConsultaApoOpOut {

    @XmlElement(name = "cod_estado")
    protected int codEstado;
    @XmlElement(nillable = true)
    protected List<FirmaOpDto> firmaOpDto;
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
     * Gets the value of the firmaOpDto property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the firmaOpDto property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFirmaOpDto().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FirmaOpDto }
     * 
     * 
     */
    public List<FirmaOpDto> getFirmaOpDto() {
        if (firmaOpDto == null) {
            firmaOpDto = new ArrayList<FirmaOpDto>();
        }
        return this.firmaOpDto;
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
