
package cl.bice.genericoperprog.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Inserta_IOdata complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Inserta_IOdata">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="insertaIOdata" type="{http://ws.operprog.bice.cl/}insertaIOdataIn"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Inserta_IOdata", propOrder = {
    "insertaIOdata"
})
public class InsertaIOdata {

    @XmlElement(required = true)
    protected InsertaIOdataIn insertaIOdata;

    /**
     * Obtiene el valor de la propiedad insertaIOdata.
     * 
     * @return
     *     possible object is
     *     {@link InsertaIOdataIn }
     *     
     */
    public InsertaIOdataIn getInsertaIOdata() {
        return insertaIOdata;
    }

    /**
     * Define el valor de la propiedad insertaIOdata.
     * 
     * @param value
     *     allowed object is
     *     {@link InsertaIOdataIn }
     *     
     */
    public void setInsertaIOdata(InsertaIOdataIn value) {
        this.insertaIOdata = value;
    }

}
