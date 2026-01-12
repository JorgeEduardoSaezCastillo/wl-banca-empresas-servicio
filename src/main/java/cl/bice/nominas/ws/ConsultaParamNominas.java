
package cl.bice.nominas.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Consulta_Param_Nominas complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Consulta_Param_Nominas">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="consultaParam" type="{http://ws.nominas.bice.cl/}consultaParametrosIn"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Consulta_Param_Nominas", propOrder = {
    "consultaParam"
})
public class ConsultaParamNominas {

    @XmlElement(required = true)
    protected ConsultaParametrosIn consultaParam;

    /**
     * Obtiene el valor de la propiedad consultaParam.
     * 
     * @return
     *     possible object is
     *     {@link ConsultaParametrosIn }
     *     
     */
    public ConsultaParametrosIn getConsultaParam() {
        return consultaParam;
    }

    /**
     * Define el valor de la propiedad consultaParam.
     * 
     * @param value
     *     allowed object is
     *     {@link ConsultaParametrosIn }
     *     
     */
    public void setConsultaParam(ConsultaParametrosIn value) {
        this.consultaParam = value;
    }

}
