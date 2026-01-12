
package cl.bice.nominasenlinea.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Obtener_Parametros complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Obtener_Parametros">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ObtenerParametros" type="{http://portal.ws.bice.cl/}consParametrosIn"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Obtener_Parametros", propOrder = {
    "obtenerParametros"
})
public class ObtenerParametros {

    @XmlElement(name = "ObtenerParametros", required = true)
    protected ConsParametrosIn obtenerParametros;

    /**
     * Obtiene el valor de la propiedad obtenerParametros.
     * 
     * @return
     *     possible object is
     *     {@link ConsParametrosIn }
     *     
     */
    public ConsParametrosIn getObtenerParametros() {
        return obtenerParametros;
    }

    /**
     * Define el valor de la propiedad obtenerParametros.
     * 
     * @param value
     *     allowed object is
     *     {@link ConsParametrosIn }
     *     
     */
    public void setObtenerParametros(ConsParametrosIn value) {
        this.obtenerParametros = value;
    }

}
