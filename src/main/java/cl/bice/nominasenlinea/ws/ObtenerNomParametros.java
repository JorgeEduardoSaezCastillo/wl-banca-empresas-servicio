
package cl.bice.nominasenlinea.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Obtener_Nom_Parametros complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Obtener_Nom_Parametros">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ObtNomParametros" type="{http://portal.ws.bice.cl/}consNomParamIn"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Obtener_Nom_Parametros", propOrder = {
    "obtNomParametros"
})
public class ObtenerNomParametros {

    @XmlElement(name = "ObtNomParametros", required = true)
    protected ConsNomParamIn obtNomParametros;

    /**
     * Obtiene el valor de la propiedad obtNomParametros.
     * 
     * @return
     *     possible object is
     *     {@link ConsNomParamIn }
     *     
     */
    public ConsNomParamIn getObtNomParametros() {
        return obtNomParametros;
    }

    /**
     * Define el valor de la propiedad obtNomParametros.
     * 
     * @param value
     *     allowed object is
     *     {@link ConsNomParamIn }
     *     
     */
    public void setObtNomParametros(ConsNomParamIn value) {
        this.obtNomParametros = value;
    }

}
