
package cl.bice.tel23300.ws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para tel23300Out complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="tel23300Out">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fechaUltimaFacturacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechaVencimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MENSAJE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="montoAprobadoLineaSobregiro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="montoDesconocido" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="montoDisponibleLineaSobregiro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="montoFacturado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="montoLBTR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="montoUtilizadoLineaSobregiro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="otroMonto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RETCODE" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="retencionesMismaPlaza" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="retencionesOtrasPlazas" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="saldoContable" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="saldoDisponible" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="ultimosAbonos" type="{http://tel23300.ws.bice.cl/}movimiento" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ultimosCheques" type="{http://tel23300.ws.bice.cl/}cheque" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tel23300Out", propOrder = {
    "fechaUltimaFacturacion",
    "fechaVencimiento",
    "mensaje",
    "montoAprobadoLineaSobregiro",
    "montoDesconocido",
    "montoDisponibleLineaSobregiro",
    "montoFacturado",
    "montoLBTR",
    "montoUtilizadoLineaSobregiro",
    "otroMonto",
    "retcode",
    "retencionesMismaPlaza",
    "retencionesOtrasPlazas",
    "saldoContable",
    "saldoDisponible",
    "status",
    "ultimosAbonos",
    "ultimosCheques"
})
@XmlRootElement(name = "Tel23300Out")
public class Tel23300Out {

    protected String fechaUltimaFacturacion;
    protected String fechaVencimiento;
    @XmlElement(name = "MENSAJE")
    protected String mensaje;
    protected String montoAprobadoLineaSobregiro;
    protected String montoDesconocido;
    protected String montoDisponibleLineaSobregiro;
    protected String montoFacturado;
    protected String montoLBTR;
    protected String montoUtilizadoLineaSobregiro;
    protected String otroMonto;
    @XmlElement(name = "RETCODE")
    protected Integer retcode;
    protected String retencionesMismaPlaza;
    protected String retencionesOtrasPlazas;
    protected String saldoContable;
    protected String saldoDisponible;
    protected Integer status;
    @XmlElement(nillable = true)
    protected List<Movimiento> ultimosAbonos;
    @XmlElement(nillable = true)
    protected List<Cheque> ultimosCheques;

    /**
     * Obtiene el valor de la propiedad fechaUltimaFacturacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaUltimaFacturacion() {
        return fechaUltimaFacturacion;
    }

    /**
     * Define el valor de la propiedad fechaUltimaFacturacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaUltimaFacturacion(String value) {
        this.fechaUltimaFacturacion = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaVencimiento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    /**
     * Define el valor de la propiedad fechaVencimiento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaVencimiento(String value) {
        this.fechaVencimiento = value;
    }

    /**
     * Obtiene el valor de la propiedad mensaje.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMENSAJE() {
        return mensaje;
    }

    /**
     * Define el valor de la propiedad mensaje.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMENSAJE(String value) {
        this.mensaje = value;
    }

    /**
     * Obtiene el valor de la propiedad montoAprobadoLineaSobregiro.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMontoAprobadoLineaSobregiro() {
        return montoAprobadoLineaSobregiro;
    }

    /**
     * Define el valor de la propiedad montoAprobadoLineaSobregiro.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMontoAprobadoLineaSobregiro(String value) {
        this.montoAprobadoLineaSobregiro = value;
    }

    /**
     * Obtiene el valor de la propiedad montoDesconocido.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMontoDesconocido() {
        return montoDesconocido;
    }

    /**
     * Define el valor de la propiedad montoDesconocido.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMontoDesconocido(String value) {
        this.montoDesconocido = value;
    }

    /**
     * Obtiene el valor de la propiedad montoDisponibleLineaSobregiro.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMontoDisponibleLineaSobregiro() {
        return montoDisponibleLineaSobregiro;
    }

    /**
     * Define el valor de la propiedad montoDisponibleLineaSobregiro.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMontoDisponibleLineaSobregiro(String value) {
        this.montoDisponibleLineaSobregiro = value;
    }

    /**
     * Obtiene el valor de la propiedad montoFacturado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMontoFacturado() {
        return montoFacturado;
    }

    /**
     * Define el valor de la propiedad montoFacturado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMontoFacturado(String value) {
        this.montoFacturado = value;
    }

    /**
     * Obtiene el valor de la propiedad montoLBTR.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMontoLBTR() {
        return montoLBTR;
    }

    /**
     * Define el valor de la propiedad montoLBTR.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMontoLBTR(String value) {
        this.montoLBTR = value;
    }

    /**
     * Obtiene el valor de la propiedad montoUtilizadoLineaSobregiro.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMontoUtilizadoLineaSobregiro() {
        return montoUtilizadoLineaSobregiro;
    }

    /**
     * Define el valor de la propiedad montoUtilizadoLineaSobregiro.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMontoUtilizadoLineaSobregiro(String value) {
        this.montoUtilizadoLineaSobregiro = value;
    }

    /**
     * Obtiene el valor de la propiedad otroMonto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtroMonto() {
        return otroMonto;
    }

    /**
     * Define el valor de la propiedad otroMonto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtroMonto(String value) {
        this.otroMonto = value;
    }

    /**
     * Obtiene el valor de la propiedad retcode.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRETCODE() {
        return retcode;
    }

    /**
     * Define el valor de la propiedad retcode.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRETCODE(Integer value) {
        this.retcode = value;
    }

    /**
     * Obtiene el valor de la propiedad retencionesMismaPlaza.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRetencionesMismaPlaza() {
        return retencionesMismaPlaza;
    }

    /**
     * Define el valor de la propiedad retencionesMismaPlaza.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRetencionesMismaPlaza(String value) {
        this.retencionesMismaPlaza = value;
    }

    /**
     * Obtiene el valor de la propiedad retencionesOtrasPlazas.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRetencionesOtrasPlazas() {
        return retencionesOtrasPlazas;
    }

    /**
     * Define el valor de la propiedad retencionesOtrasPlazas.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRetencionesOtrasPlazas(String value) {
        this.retencionesOtrasPlazas = value;
    }

    /**
     * Obtiene el valor de la propiedad saldoContable.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSaldoContable() {
        return saldoContable;
    }

    /**
     * Define el valor de la propiedad saldoContable.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSaldoContable(String value) {
        this.saldoContable = value;
    }

    /**
     * Obtiene el valor de la propiedad saldoDisponible.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSaldoDisponible() {
        return saldoDisponible;
    }

    /**
     * Define el valor de la propiedad saldoDisponible.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSaldoDisponible(String value) {
        this.saldoDisponible = value;
    }

    /**
     * Obtiene el valor de la propiedad status.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * Define el valor de la propiedad status.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setStatus(Integer value) {
        this.status = value;
    }

    /**
     * Gets the value of the ultimosAbonos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ultimosAbonos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUltimosAbonos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Movimiento }
     * 
     * 
     */
    public List<Movimiento> getUltimosAbonos() {
        if (ultimosAbonos == null) {
            ultimosAbonos = new ArrayList<Movimiento>();
        }
        return this.ultimosAbonos;
    }

    /**
     * Gets the value of the ultimosCheques property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ultimosCheques property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUltimosCheques().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Cheque }
     * 
     * 
     */
    public List<Cheque> getUltimosCheques() {
        if (ultimosCheques == null) {
            ultimosCheques = new ArrayList<Cheque>();
        }
        return this.ultimosCheques;
    }

}
