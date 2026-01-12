//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación
// de la referencia de enlace (JAXB) XML v2.2.11
// Visite <a
// href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve
// a compilar el esquema de origen.
// Generado el: 2017.09.07 a las 01:21:23 PM CLST
//

package cl.bice.banca.empresas.servicio.soap.balance;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Clase Java para BalanceProcessResponseType complex type.
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que
 * haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="BalanceProcessResponseType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Estado" type="{http://www.bice.cl/inversiones/balance}EstadoType"/&gt;
 *         &lt;element name="Cuentas" type="{http://www.bice.cl/inversiones/balance}CuentasType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BalanceProcessResponseType", propOrder = {
    "estado",
    "cuentas"
})
@XmlRootElement(name = "BalanceEnLineaResponse")
public class BalanceProcessResponseType {

    @XmlElement(name = "Estado", required = true)
    protected EstadoType estado;
    @XmlElement(name = "Cuentas")
    protected CuentasType cuentas;

    /**
     * Obtiene el valor de la propiedad estado.
     * 
     * @return possible object is {@link EstadoType }
     */
    public EstadoType getEstado() {
        return estado;
    }

    /**
     * Define el valor de la propiedad estado.
     * 
     * @param value allowed object is {@link EstadoType }
     */
    public void setEstado(EstadoType value) {
        this.estado = value;
    }

    /**
     * Obtiene el valor de la propiedad cuentas.
     * 
     * @return possible object is {@link CuentasType }
     */
    public CuentasType getCuentas() {
        return cuentas;
    }

    /**
     * Define el valor de la propiedad cuentas.
     * 
     * @param value allowed object is {@link CuentasType }
     */
    public void setCuentas(CuentasType value) {
        this.cuentas = value;
    }

	@Override
	public String toString() {
		return "BalanceProcessResponseType [estado=" + estado + ", cuentas=" + cuentas + "]";
	}
    

}
