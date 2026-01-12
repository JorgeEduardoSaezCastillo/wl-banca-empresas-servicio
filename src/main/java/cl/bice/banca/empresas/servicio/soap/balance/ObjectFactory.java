//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.11 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2017.09.07 a las 01:21:23 PM CLST 
//


package cl.bice.banca.empresas.servicio.soap.balance;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the cl.bice.inversiones.balance package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _BalanceRequest_QNAME = new QName("http://www.bice.cl/inversiones/balance", "BalanceRequest");
    private final static QName _BalanceResponse_QNAME = new QName("http://www.bice.cl/inversiones/balance", "BalanceResponse");
    private final static QName _BalanceEnLineaRequest_QNAME = new QName("http://www.bice.cl/inversiones/balance", "BalanceEnLineaRequest");
    private final static QName _BalanceEnLineaResponse_QNAME = new QName("http://www.bice.cl/inversiones/balance", "BalanceEnLineaResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cl.bice.inversiones.balance
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link BalanceProcessRequestType }
     * 
     */
    public BalanceProcessRequestType createBalanceProcessRequestType() {
        return new BalanceProcessRequestType();
    }

    /**
     * Create an instance of {@link BalanceProcessResponseType }
     * 
     */
    public BalanceProcessResponseType createBalanceProcessResponseType() {
        return new BalanceProcessResponseType();
    }

    /**
     * Create an instance of {@link EstadoType }
     * 
     */
    public EstadoType createEstadoType() {
        return new EstadoType();
    }

    /**
     * Create an instance of {@link CuentasType }
     * 
     */
    public CuentasType createCuentasType() {
        return new CuentasType();
    }

    /**
     * Create an instance of {@link CuentaType }
     * 
     */
    public CuentaType createCuentaType() {
        return new CuentaType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BalanceProcessRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bice.cl/inversiones/balance", name = "BalanceRequest")
    public JAXBElement<BalanceProcessRequestType> createBalanceRequest(BalanceProcessRequestType value) {
        return new JAXBElement<BalanceProcessRequestType>(_BalanceRequest_QNAME, BalanceProcessRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BalanceProcessResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bice.cl/inversiones/balance", name = "BalanceResponse")
    public JAXBElement<BalanceProcessResponseType> createBalanceResponse(BalanceProcessResponseType value) {
        return new JAXBElement<BalanceProcessResponseType>(_BalanceResponse_QNAME, BalanceProcessResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BalanceProcessRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bice.cl/inversiones/balance", name = "BalanceEnLineaRequest")
    public JAXBElement<BalanceProcessRequestType> createBalanceEnLineaRequest(BalanceProcessRequestType value) {
        return new JAXBElement<BalanceProcessRequestType>(_BalanceEnLineaRequest_QNAME, BalanceProcessRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BalanceProcessResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bice.cl/inversiones/balance", name = "BalanceEnLineaResponse")
    public JAXBElement<BalanceProcessResponseType> createBalanceEnLineaResponse(BalanceProcessResponseType value) {
        return new JAXBElement<BalanceProcessResponseType>(_BalanceEnLineaResponse_QNAME, BalanceProcessResponseType.class, null, value);
    }

}
