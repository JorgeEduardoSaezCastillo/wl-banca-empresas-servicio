//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.11 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2017.09.27 a las 09:26:39 AM CLST 
//


package cl.bice.banca.empresas.servicio.soap.cartola;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


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
 *         &lt;element ref="{http://www.bice.cl/wsdl}CANAL"/&gt;
 *         &lt;element ref="{http://www.bice.cl/wsdl}CUENTA_IN"/&gt;
 *         &lt;element ref="{http://www.bice.cl/wsdl}TIPOMOV_IN"/&gt;
 *         &lt;element ref="{http://www.bice.cl/wsdl}INFOCHEQUES_IN"/&gt;
 *         &lt;element ref="{http://www.bice.cl/wsdl}REGISTRO"/&gt;
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
    "canal",
    "cuentain",
    "tipomovin",
    "infochequesin",
    "registro"
})
@XmlRootElement(name = "TCMW0025_IN")
public class TCMW0025IN {

    @XmlElement(name = "CANAL", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String canal;
    @XmlElement(name = "CUENTA_IN", required = true)
    protected BigInteger cuentain;
    @XmlElement(name = "TIPOMOV_IN", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String tipomovin;
    @XmlElement(name = "INFOCHEQUES_IN", required = true)
    protected BigInteger infochequesin;
    @XmlElement(name = "REGISTRO", required = true)
    protected BigInteger registro;

    /**
     * Obtiene el valor de la propiedad canal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCANAL() {
        return canal;
    }

    /**
     * Define el valor de la propiedad canal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCANAL(String value) {
        this.canal = value;
    }

    /**
     * Obtiene el valor de la propiedad cuentain.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCUENTAIN() {
        return cuentain;
    }

    /**
     * Define el valor de la propiedad cuentain.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCUENTAIN(BigInteger value) {
        this.cuentain = value;
    }

    /**
     * Obtiene el valor de la propiedad tipomovin.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTIPOMOVIN() {
        return tipomovin;
    }

    /**
     * Define el valor de la propiedad tipomovin.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTIPOMOVIN(String value) {
        this.tipomovin = value;
    }

    /**
     * Obtiene el valor de la propiedad infochequesin.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getINFOCHEQUESIN() {
        return infochequesin;
    }

    /**
     * Define el valor de la propiedad infochequesin.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setINFOCHEQUESIN(BigInteger value) {
        this.infochequesin = value;
    }

    /**
     * Obtiene el valor de la propiedad registro.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getREGISTRO() {
        return registro;
    }

    /**
     * Define el valor de la propiedad registro.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setREGISTRO(BigInteger value) {
        this.registro = value;
    }

}
