//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.11 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2017.09.07 a las 01:21:23 PM CLST 
//


package cl.bice.banca.empresas.servicio.soap.balance;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para CuentaType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CuentaType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Tipo" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Pesos" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="Dolar" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="Euro" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="FechaReferencia" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CuentaType", propOrder = {
    "tipo",
    "pesos",
    "dolar",
    "euro",
    "fechaReferencia"
})
public class CuentaType {

    @XmlElement(name = "Tipo", required = true)
    protected String tipo;
    @XmlElement(name = "Pesos")
    protected double pesos;
    @XmlElement(name = "Dolar")
    protected double dolar;
    @XmlElement(name = "Euro")
    protected double euro;
    @XmlElement(name = "FechaReferencia", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaReferencia;

    /**
     * Obtiene el valor de la propiedad tipo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Define el valor de la propiedad tipo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipo(String value) {
        this.tipo = value;
    }

    /**
     * Obtiene el valor de la propiedad pesos.
     * 
     */
    public double getPesos() {
        return pesos;
    }

    /**
     * Define el valor de la propiedad pesos.
     * 
     */
    public void setPesos(double value) {
        this.pesos = value;
    }

    /**
     * Obtiene el valor de la propiedad dolar.
     * 
     */
    public double getDolar() {
        return dolar;
    }

    /**
     * Define el valor de la propiedad dolar.
     * 
     */
    public void setDolar(double value) {
        this.dolar = value;
    }

    /**
     * Obtiene el valor de la propiedad euro.
     * 
     */
    public double getEuro() {
        return euro;
    }

    /**
     * Define el valor de la propiedad euro.
     * 
     */
    public void setEuro(double value) {
        this.euro = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaReferencia.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaReferencia() {
        return fechaReferencia;
    }

    /**
     * Define el valor de la propiedad fechaReferencia.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaReferencia(XMLGregorianCalendar value) {
        this.fechaReferencia = value;
    }

	@Override
	public String toString() {
		return "CuentaType [tipo=" + tipo + ", pesos=" + pesos + ", dolar=" + dolar + ", euro=" + euro
				+ ", fechaReferencia=" + fechaReferencia + "]";
	}

}
