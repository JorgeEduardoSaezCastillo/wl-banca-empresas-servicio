//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.11 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2017.09.27 a las 09:26:39 AM CLST 
//


package cl.bice.banca.empresas.servicio.soap.cartola;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://www.bice.cl/wsdl}NUMCHQ"/&gt;
 *         &lt;element ref="{http://www.bice.cl/wsdl}MONTOCHQ"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "numchq",
    "montochq"
})
@XmlRootElement(name = "CHEQUES")
public class CHEQUES {

    @XmlElement(name = "NUMCHQ", required = true)
    protected String numchq;
    @XmlElement(name = "MONTOCHQ", required = true)
    protected String montochq;

    /**
     * Obtiene el valor de la propiedad numchq.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNUMCHQ() {
        return numchq;
    }

    /**
     * Define el valor de la propiedad numchq.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNUMCHQ(String value) {
        this.numchq = value;
    }

    /**
     * Obtiene el valor de la propiedad montochq.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMONTOCHQ() {
        return montochq;
    }

    /**
     * Define el valor de la propiedad montochq.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMONTOCHQ(String value) {
        this.montochq = value;
    }

}
