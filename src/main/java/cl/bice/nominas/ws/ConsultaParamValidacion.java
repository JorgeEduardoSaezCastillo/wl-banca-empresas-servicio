
package cl.bice.nominas.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Consulta_Param_Validacion complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Consulta_Param_Validacion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="consultaParamVal" type="{http://ws.nominas.bice.cl/}consultaParametrosIn"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Consulta_Param_Validacion", propOrder = {
    "consultaParamVal"
})
public class ConsultaParamValidacion {

    @XmlElement(required = true)
    protected ConsultaParametrosIn consultaParamVal;

    /**
     * Obtiene el valor de la propiedad consultaParamVal.
     * 
     * @return
     *     possible object is
     *     {@link ConsultaParametrosIn }
     *     
     */
    public ConsultaParametrosIn getConsultaParamVal() {
        return consultaParamVal;
    }

    /**
     * Define el valor de la propiedad consultaParamVal.
     * 
     * @param value
     *     allowed object is
     *     {@link ConsultaParametrosIn }
     *     
     */
    public void setConsultaParamVal(ConsultaParametrosIn value) {
        this.consultaParamVal = value;
    }

}
