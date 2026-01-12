
package cl.bice.nominas.ws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para consultaParametrosOut complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="consultaParametrosOut">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cod_estado" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="msg_result" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="parametrosVo" type="{http://ws.nominas.bice.cl/}parametrosVo" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultaParametrosOut", propOrder = {
    "codEstado",
    "msgResult",
    "parametrosVo"
})
@XmlRootElement(name = "ConsultaParametrosOut")
public class ConsultaParametrosOut {

    @XmlElement(name = "cod_estado")
    protected int codEstado;
    @XmlElement(name = "msg_result")
    protected String msgResult;
    @XmlElement(nillable = true)
    protected List<ParametrosVo> parametrosVo;

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
     * Gets the value of the parametrosVo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the parametrosVo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getParametrosVo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ParametrosVo }
     * 
     * 
     */
    public List<ParametrosVo> getParametrosVo() {
        if (parametrosVo == null) {
            parametrosVo = new ArrayList<ParametrosVo>();
        }
        return this.parametrosVo;
    }

}
