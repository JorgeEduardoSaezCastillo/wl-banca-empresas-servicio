
package cl.bice.nominas.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Consulta_Firma_Op complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Consulta_Firma_Op">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="consultaFirmaApo" type="{http://ws.nominas.bice.cl/}consultaApoOpIn"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Consulta_Firma_Op", propOrder = {
    "consultaFirmaApo"
})
public class ConsultaFirmaOp {

    @XmlElement(required = true)
    protected ConsultaApoOpIn consultaFirmaApo;

    /**
     * Obtiene el valor de la propiedad consultaFirmaApo.
     * 
     * @return
     *     possible object is
     *     {@link ConsultaApoOpIn }
     *     
     */
    public ConsultaApoOpIn getConsultaFirmaApo() {
        return consultaFirmaApo;
    }

    /**
     * Define el valor de la propiedad consultaFirmaApo.
     * 
     * @param value
     *     allowed object is
     *     {@link ConsultaApoOpIn }
     *     
     */
    public void setConsultaFirmaApo(ConsultaApoOpIn value) {
        this.consultaFirmaApo = value;
    }

}
