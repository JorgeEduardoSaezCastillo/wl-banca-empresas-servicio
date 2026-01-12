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
 *         &lt;element ref="{http://www.bice.cl/wsdl}SALDOCONTABLE"/&gt;
 *         &lt;element ref="{http://www.bice.cl/wsdl}SALDODISPONIBLE"/&gt;
 *         &lt;element ref="{http://www.bice.cl/wsdl}SALDOINICIAL"/&gt;
 *         &lt;element ref="{http://www.bice.cl/wsdl}TIPOCUENTA"/&gt;
 *         &lt;element ref="{http://www.bice.cl/wsdl}LIMITEAUTORIZADO"/&gt;
 *         &lt;element ref="{http://www.bice.cl/wsdl}LIMITEUTILIZADO"/&gt;
 *         &lt;element ref="{http://www.bice.cl/wsdl}LIMITEDISPONIBLE"/&gt;
 *         &lt;element ref="{http://www.bice.cl/wsdl}RUT"/&gt;
 *         &lt;element ref="{http://www.bice.cl/wsdl}NOMCLIENTE"/&gt;
 *         &lt;element ref="{http://www.bice.cl/wsdl}SUCURSAL"/&gt;
 *         &lt;element ref="{http://www.bice.cl/wsdl}TOTALDEBITOS"/&gt;
 *         &lt;element ref="{http://www.bice.cl/wsdl}TOTALCREDITOS"/&gt;
 *         &lt;element ref="{http://www.bice.cl/wsdl}RETENCIONDIA"/&gt;
 *         &lt;element ref="{http://www.bice.cl/wsdl}RETENCIONMASDIAS"/&gt;
 *         &lt;element ref="{http://www.bice.cl/wsdl}MONEDA"/&gt;
 *         &lt;element ref="{http://www.bice.cl/wsdl}CUENTA"/&gt;
 *         &lt;element ref="{http://www.bice.cl/wsdl}FECULCARTOLA"/&gt;
 *         &lt;element ref="{http://www.bice.cl/wsdl}SALDOULFACTURACION"/&gt;
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
    "saldocontable",
    "saldodisponible",
    "saldoinicial",
    "tipocuenta",
    "limiteautorizado",
    "limiteutilizado",
    "limitedisponible",
    "rut",
    "nomcliente",
    "sucursal",
    "totaldebitos",
    "totalcreditos",
    "retenciondia",
    "retencionmasdias",
    "moneda",
    "cuenta",
    "feculcartola",
    "saldoulfacturacion"
})
@XmlRootElement(name = "INFOCUENTA")
public class INFOCUENTA {

    @XmlElement(name = "SALDOCONTABLE", required = true)
    protected String saldocontable;
    @XmlElement(name = "SALDODISPONIBLE", required = true)
    protected String saldodisponible;
    @XmlElement(name = "SALDOINICIAL", required = true)
    protected String saldoinicial;
    @XmlElement(name = "TIPOCUENTA", required = true)
    protected String tipocuenta;
    @XmlElement(name = "LIMITEAUTORIZADO", required = true)
    protected String limiteautorizado;
    @XmlElement(name = "LIMITEUTILIZADO", required = true)
    protected String limiteutilizado;
    @XmlElement(name = "LIMITEDISPONIBLE", required = true)
    protected String limitedisponible;
    @XmlElement(name = "RUT", required = true)
    protected String rut;
    @XmlElement(name = "NOMCLIENTE", required = true)
    protected String nomcliente;
    @XmlElement(name = "SUCURSAL", required = true)
    protected String sucursal;
    @XmlElement(name = "TOTALDEBITOS", required = true)
    protected String totaldebitos;
    @XmlElement(name = "TOTALCREDITOS", required = true)
    protected String totalcreditos;
    @XmlElement(name = "RETENCIONDIA", required = true)
    protected String retenciondia;
    @XmlElement(name = "RETENCIONMASDIAS", required = true)
    protected String retencionmasdias;
    @XmlElement(name = "MONEDA", required = true)
    protected String moneda;
    @XmlElement(name = "CUENTA", required = true)
    protected String cuenta;
    @XmlElement(name = "FECULCARTOLA", required = true)
    protected String feculcartola;
    @XmlElement(name = "SALDOULFACTURACION", required = true)
    protected String saldoulfacturacion;

    /**
     * Obtiene el valor de la propiedad saldocontable.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSALDOCONTABLE() {
        return saldocontable;
    }

    /**
     * Define el valor de la propiedad saldocontable.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSALDOCONTABLE(String value) {
        this.saldocontable = value;
    }

    /**
     * Obtiene el valor de la propiedad saldodisponible.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSALDODISPONIBLE() {
        return saldodisponible;
    }

    /**
     * Define el valor de la propiedad saldodisponible.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSALDODISPONIBLE(String value) {
        this.saldodisponible = value;
    }

    /**
     * Obtiene el valor de la propiedad saldoinicial.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSALDOINICIAL() {
        return saldoinicial;
    }

    /**
     * Define el valor de la propiedad saldoinicial.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSALDOINICIAL(String value) {
        this.saldoinicial = value;
    }

    /**
     * Obtiene el valor de la propiedad tipocuenta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTIPOCUENTA() {
        return tipocuenta;
    }

    /**
     * Define el valor de la propiedad tipocuenta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTIPOCUENTA(String value) {
        this.tipocuenta = value;
    }

    /**
     * Obtiene el valor de la propiedad limiteautorizado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLIMITEAUTORIZADO() {
        return limiteautorizado;
    }

    /**
     * Define el valor de la propiedad limiteautorizado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLIMITEAUTORIZADO(String value) {
        this.limiteautorizado = value;
    }

    /**
     * Obtiene el valor de la propiedad limiteutilizado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLIMITEUTILIZADO() {
        return limiteutilizado;
    }

    /**
     * Define el valor de la propiedad limiteutilizado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLIMITEUTILIZADO(String value) {
        this.limiteutilizado = value;
    }

    /**
     * Obtiene el valor de la propiedad limitedisponible.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLIMITEDISPONIBLE() {
        return limitedisponible;
    }

    /**
     * Define el valor de la propiedad limitedisponible.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLIMITEDISPONIBLE(String value) {
        this.limitedisponible = value;
    }

    /**
     * Obtiene el valor de la propiedad rut.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRUT() {
        return rut;
    }

    /**
     * Define el valor de la propiedad rut.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRUT(String value) {
        this.rut = value;
    }

    /**
     * Obtiene el valor de la propiedad nomcliente.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNOMCLIENTE() {
        return nomcliente;
    }

    /**
     * Define el valor de la propiedad nomcliente.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNOMCLIENTE(String value) {
        this.nomcliente = value;
    }

    /**
     * Obtiene el valor de la propiedad sucursal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSUCURSAL() {
        return sucursal;
    }

    /**
     * Define el valor de la propiedad sucursal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSUCURSAL(String value) {
        this.sucursal = value;
    }

    /**
     * Obtiene el valor de la propiedad totaldebitos.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTOTALDEBITOS() {
        return totaldebitos;
    }

    /**
     * Define el valor de la propiedad totaldebitos.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTOTALDEBITOS(String value) {
        this.totaldebitos = value;
    }

    /**
     * Obtiene el valor de la propiedad totalcreditos.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTOTALCREDITOS() {
        return totalcreditos;
    }

    /**
     * Define el valor de la propiedad totalcreditos.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTOTALCREDITOS(String value) {
        this.totalcreditos = value;
    }

    /**
     * Obtiene el valor de la propiedad retenciondia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRETENCIONDIA() {
        return retenciondia;
    }

    /**
     * Define el valor de la propiedad retenciondia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRETENCIONDIA(String value) {
        this.retenciondia = value;
    }

    /**
     * Obtiene el valor de la propiedad retencionmasdias.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRETENCIONMASDIAS() {
        return retencionmasdias;
    }

    /**
     * Define el valor de la propiedad retencionmasdias.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRETENCIONMASDIAS(String value) {
        this.retencionmasdias = value;
    }

    /**
     * Obtiene el valor de la propiedad moneda.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMONEDA() {
        return moneda;
    }

    /**
     * Define el valor de la propiedad moneda.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMONEDA(String value) {
        this.moneda = value;
    }

    /**
     * Obtiene el valor de la propiedad cuenta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCUENTA() {
        return cuenta;
    }

    /**
     * Define el valor de la propiedad cuenta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCUENTA(String value) {
        this.cuenta = value;
    }

    /**
     * Obtiene el valor de la propiedad feculcartola.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFECULCARTOLA() {
        return feculcartola;
    }

    /**
     * Define el valor de la propiedad feculcartola.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFECULCARTOLA(String value) {
        this.feculcartola = value;
    }

    /**
     * Obtiene el valor de la propiedad saldoulfacturacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSALDOULFACTURACION() {
        return saldoulfacturacion;
    }

    /**
     * Define el valor de la propiedad saldoulfacturacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSALDOULFACTURACION(String value) {
        this.saldoulfacturacion = value;
    }

}
