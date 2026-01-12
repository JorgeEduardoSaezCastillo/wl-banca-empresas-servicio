
package cl.bice.nominasenlinea.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Listar_Reg_NomLin complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Listar_Reg_NomLin">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ListarRegNomLin" type="{http://portal.ws.bice.cl/}registrosNomLinIn"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Listar_Reg_NomLin", propOrder = {
    "listarRegNomLin"
})
public class ListarRegNomLin {

    @XmlElement(name = "ListarRegNomLin", required = true)
    protected RegistrosNomLinIn listarRegNomLin;

    /**
     * Obtiene el valor de la propiedad listarRegNomLin.
     * 
     * @return
     *     possible object is
     *     {@link RegistrosNomLinIn }
     *     
     */
    public RegistrosNomLinIn getListarRegNomLin() {
        return listarRegNomLin;
    }

    /**
     * Define el valor de la propiedad listarRegNomLin.
     * 
     * @param value
     *     allowed object is
     *     {@link RegistrosNomLinIn }
     *     
     */
    public void setListarRegNomLin(RegistrosNomLinIn value) {
        this.listarRegNomLin = value;
    }

}
