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
 *         &lt;element ref="{http://www.bice.cl/wsdl}STATUS"/&gt;
 *         &lt;element ref="{http://www.bice.cl/wsdl}STATUS_DES"/&gt;
 *         &lt;element ref="{http://www.bice.cl/wsdl}MASPAGINAS"/&gt;
 *         &lt;element ref="{http://www.bice.cl/wsdl}TOTALREGISTROS"/&gt;
 *         &lt;element ref="{http://www.bice.cl/wsdl}REGISTROSPAGINA"/&gt;
 *         &lt;element ref="{http://www.bice.cl/wsdl}ULTIMOREGISTROPAG"/&gt;
 *         &lt;element ref="{http://www.bice.cl/wsdl}INFOCUENTA"/&gt;
 *         &lt;element ref="{http://www.bice.cl/wsdl}MOVIMIENTOS"/&gt;
 *         &lt;element ref="{http://www.bice.cl/wsdl}INFOCHEQUES"/&gt;
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
    "status",
    "statusdes",
    "maspaginas",
    "totalregistros",
    "registrospagina",
    "ultimoregistropag",
    "infocuenta",
    "movimientos",
    "infocheques"
})
@XmlRootElement(name = "TCMW0025_OUT")
public class TCMW0025OUT {

    @XmlElement(name = "STATUS", required = true)
    protected String status;
    @XmlElement(name = "STATUS_DES", required = true)
    protected String statusdes;
    @XmlElement(name = "MASPAGINAS", required = true)
    protected String maspaginas;
    @XmlElement(name = "TOTALREGISTROS", required = true)
    protected String totalregistros;
    @XmlElement(name = "REGISTROSPAGINA", required = true)
    protected String registrospagina;
    @XmlElement(name = "ULTIMOREGISTROPAG", required = true)
    protected String ultimoregistropag;
    @XmlElement(name = "INFOCUENTA", required = true)
    protected INFOCUENTA infocuenta;
    @XmlElement(name = "MOVIMIENTOS", required = true)
    protected MOVIMIENTOS movimientos;
    @XmlElement(name = "INFOCHEQUES", required = true)
    protected INFOCHEQUES infocheques;

    /**
     * Obtiene el valor de la propiedad status.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSTATUS() {
        return status;
    }

    /**
     * Define el valor de la propiedad status.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSTATUS(String value) {
        this.status = value;
    }

    /**
     * Obtiene el valor de la propiedad statusdes.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSTATUSDES() {
        return statusdes;
    }

    /**
     * Define el valor de la propiedad statusdes.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSTATUSDES(String value) {
        this.statusdes = value;
    }

    /**
     * Obtiene el valor de la propiedad maspaginas.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMASPAGINAS() {
        return maspaginas;
    }

    /**
     * Define el valor de la propiedad maspaginas.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMASPAGINAS(String value) {
        this.maspaginas = value;
    }

    /**
     * Obtiene el valor de la propiedad totalregistros.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTOTALREGISTROS() {
        return totalregistros;
    }

    /**
     * Define el valor de la propiedad totalregistros.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTOTALREGISTROS(String value) {
        this.totalregistros = value;
    }

    /**
     * Obtiene el valor de la propiedad registrospagina.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getREGISTROSPAGINA() {
        return registrospagina;
    }

    /**
     * Define el valor de la propiedad registrospagina.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setREGISTROSPAGINA(String value) {
        this.registrospagina = value;
    }

    /**
     * Obtiene el valor de la propiedad ultimoregistropag.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getULTIMOREGISTROPAG() {
        return ultimoregistropag;
    }

    /**
     * Define el valor de la propiedad ultimoregistropag.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setULTIMOREGISTROPAG(String value) {
        this.ultimoregistropag = value;
    }

    /**
     * Obtiene el valor de la propiedad infocuenta.
     * 
     * @return
     *     possible object is
     *     {@link INFOCUENTA }
     *     
     */
    public INFOCUENTA getINFOCUENTA() {
        return infocuenta;
    }

    /**
     * Define el valor de la propiedad infocuenta.
     * 
     * @param value
     *     allowed object is
     *     {@link INFOCUENTA }
     *     
     */
    public void setINFOCUENTA(INFOCUENTA value) {
        this.infocuenta = value;
    }

    /**
     * Obtiene el valor de la propiedad movimientos.
     * 
     * @return
     *     possible object is
     *     {@link MOVIMIENTOS }
     *     
     */
    public MOVIMIENTOS getMOVIMIENTOS() {
        return movimientos;
    }

    /**
     * Define el valor de la propiedad movimientos.
     * 
     * @param value
     *     allowed object is
     *     {@link MOVIMIENTOS }
     *     
     */
    public void setMOVIMIENTOS(MOVIMIENTOS value) {
        this.movimientos = value;
    }

    /**
     * Obtiene el valor de la propiedad infocheques.
     * 
     * @return
     *     possible object is
     *     {@link INFOCHEQUES }
     *     
     */
    public INFOCHEQUES getINFOCHEQUES() {
        return infocheques;
    }

    /**
     * Define el valor de la propiedad infocheques.
     * 
     * @param value
     *     allowed object is
     *     {@link INFOCHEQUES }
     *     
     */
    public void setINFOCHEQUES(INFOCHEQUES value) {
        this.infocheques = value;
    }

}
