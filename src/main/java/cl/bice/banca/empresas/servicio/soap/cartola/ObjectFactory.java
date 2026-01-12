//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.11 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2017.09.27 a las 09:26:39 AM CLST 
//


package cl.bice.banca.empresas.servicio.soap.cartola;

import java.math.BigInteger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the cl.bice.wsdl package. 
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

    private final static QName _STATUS_QNAME = new QName("http://www.bice.cl/wsdl", "STATUS");
    private final static QName _STATUSDES_QNAME = new QName("http://www.bice.cl/wsdl", "STATUS_DES");
    private final static QName _MASPAGINAS_QNAME = new QName("http://www.bice.cl/wsdl", "MASPAGINAS");
    private final static QName _TOTALREGISTROS_QNAME = new QName("http://www.bice.cl/wsdl", "TOTALREGISTROS");
    private final static QName _REGISTROSPAGINA_QNAME = new QName("http://www.bice.cl/wsdl", "REGISTROSPAGINA");
    private final static QName _ULTIMOREGISTROPAG_QNAME = new QName("http://www.bice.cl/wsdl", "ULTIMOREGISTROPAG");
    private final static QName _SALDOCONTABLE_QNAME = new QName("http://www.bice.cl/wsdl", "SALDOCONTABLE");
    private final static QName _SALDODISPONIBLE_QNAME = new QName("http://www.bice.cl/wsdl", "SALDODISPONIBLE");
    private final static QName _SALDOINICIAL_QNAME = new QName("http://www.bice.cl/wsdl", "SALDOINICIAL");
    private final static QName _TIPOCUENTA_QNAME = new QName("http://www.bice.cl/wsdl", "TIPOCUENTA");
    private final static QName _LIMITEAUTORIZADO_QNAME = new QName("http://www.bice.cl/wsdl", "LIMITEAUTORIZADO");
    private final static QName _LIMITEUTILIZADO_QNAME = new QName("http://www.bice.cl/wsdl", "LIMITEUTILIZADO");
    private final static QName _LIMITEDISPONIBLE_QNAME = new QName("http://www.bice.cl/wsdl", "LIMITEDISPONIBLE");
    private final static QName _RUT_QNAME = new QName("http://www.bice.cl/wsdl", "RUT");
    private final static QName _NOMCLIENTE_QNAME = new QName("http://www.bice.cl/wsdl", "NOMCLIENTE");
    private final static QName _SUCURSAL_QNAME = new QName("http://www.bice.cl/wsdl", "SUCURSAL");
    private final static QName _TOTALDEBITOS_QNAME = new QName("http://www.bice.cl/wsdl", "TOTALDEBITOS");
    private final static QName _TOTALCREDITOS_QNAME = new QName("http://www.bice.cl/wsdl", "TOTALCREDITOS");
    private final static QName _RETENCIONDIA_QNAME = new QName("http://www.bice.cl/wsdl", "RETENCIONDIA");
    private final static QName _RETENCIONMASDIAS_QNAME = new QName("http://www.bice.cl/wsdl", "RETENCIONMASDIAS");
    private final static QName _MONEDA_QNAME = new QName("http://www.bice.cl/wsdl", "MONEDA");
    private final static QName _CUENTA_QNAME = new QName("http://www.bice.cl/wsdl", "CUENTA");
    private final static QName _FECULCARTOLA_QNAME = new QName("http://www.bice.cl/wsdl", "FECULCARTOLA");
    private final static QName _SALDOULFACTURACION_QNAME = new QName("http://www.bice.cl/wsdl", "SALDOULFACTURACION");
    private final static QName _FECHA_QNAME = new QName("http://www.bice.cl/wsdl", "FECHA");
    private final static QName _FECHACONTABLE_QNAME = new QName("http://www.bice.cl/wsdl", "FECHACONTABLE");
    private final static QName _CODTRN_QNAME = new QName("http://www.bice.cl/wsdl", "CODTRN");
    private final static QName _DESCRIPCION_QNAME = new QName("http://www.bice.cl/wsdl", "DESCRIPCION");
    private final static QName _NARRATIVA_QNAME = new QName("http://www.bice.cl/wsdl", "NARRATIVA");
    private final static QName _REMARKS_QNAME = new QName("http://www.bice.cl/wsdl", "REMARKS");
    private final static QName _SUCMOV_QNAME = new QName("http://www.bice.cl/wsdl", "SUCMOV");
    private final static QName _NROINST_QNAME = new QName("http://www.bice.cl/wsdl", "NROINST");
    private final static QName _TIPOMOV_QNAME = new QName("http://www.bice.cl/wsdl", "TIPOMOV");
    private final static QName _MONTO_QNAME = new QName("http://www.bice.cl/wsdl", "MONTO");
    private final static QName _NUMCHQ_QNAME = new QName("http://www.bice.cl/wsdl", "NUMCHQ");
    private final static QName _MONTOCHQ_QNAME = new QName("http://www.bice.cl/wsdl", "MONTOCHQ");
    private final static QName _CANAL_QNAME = new QName("http://www.bice.cl/wsdl", "CANAL");
    private final static QName _CUENTAIN_QNAME = new QName("http://www.bice.cl/wsdl", "CUENTA_IN");
    private final static QName _TIPOMOVIN_QNAME = new QName("http://www.bice.cl/wsdl", "TIPOMOV_IN");
    private final static QName _INFOCHEQUESIN_QNAME = new QName("http://www.bice.cl/wsdl", "INFOCHEQUES_IN");
    private final static QName _REGISTRO_QNAME = new QName("http://www.bice.cl/wsdl", "REGISTRO");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cl.bice.wsdl
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TCMW0025OUT }
     * 
     */
    public TCMW0025OUT createTCMW0025OUT() {
        return new TCMW0025OUT();
    }

    /**
     * Create an instance of {@link INFOCUENTA }
     * 
     */
    public INFOCUENTA createINFOCUENTA() {
        return new INFOCUENTA();
    }

    /**
     * Create an instance of {@link MOVIMIENTOS }
     * 
     */
    public MOVIMIENTOS createMOVIMIENTOS() {
        return new MOVIMIENTOS();
    }

    /**
     * Create an instance of {@link MOVIMIENTO }
     * 
     */
    public MOVIMIENTO createMOVIMIENTO() {
        return new MOVIMIENTO();
    }

    /**
     * Create an instance of {@link INFOCHEQUES }
     * 
     */
    public INFOCHEQUES createINFOCHEQUES() {
        return new INFOCHEQUES();
    }

    /**
     * Create an instance of {@link CHEQUES }
     * 
     */
    public CHEQUES createCHEQUES() {
        return new CHEQUES();
    }

    /**
     * Create an instance of {@link TCMW0025IN }
     * 
     */
    public TCMW0025IN createTCMW0025IN() {
        return new TCMW0025IN();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bice.cl/wsdl", name = "STATUS")
    public JAXBElement<String> createSTATUS(String value) {
        return new JAXBElement<String>(_STATUS_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bice.cl/wsdl", name = "STATUS_DES")
    public JAXBElement<String> createSTATUSDES(String value) {
        return new JAXBElement<String>(_STATUSDES_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bice.cl/wsdl", name = "MASPAGINAS")
    public JAXBElement<String> createMASPAGINAS(String value) {
        return new JAXBElement<String>(_MASPAGINAS_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bice.cl/wsdl", name = "TOTALREGISTROS")
    public JAXBElement<String> createTOTALREGISTROS(String value) {
        return new JAXBElement<String>(_TOTALREGISTROS_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bice.cl/wsdl", name = "REGISTROSPAGINA")
    public JAXBElement<String> createREGISTROSPAGINA(String value) {
        return new JAXBElement<String>(_REGISTROSPAGINA_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bice.cl/wsdl", name = "ULTIMOREGISTROPAG")
    public JAXBElement<String> createULTIMOREGISTROPAG(String value) {
        return new JAXBElement<String>(_ULTIMOREGISTROPAG_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bice.cl/wsdl", name = "SALDOCONTABLE")
    public JAXBElement<String> createSALDOCONTABLE(String value) {
        return new JAXBElement<String>(_SALDOCONTABLE_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bice.cl/wsdl", name = "SALDODISPONIBLE")
    public JAXBElement<String> createSALDODISPONIBLE(String value) {
        return new JAXBElement<String>(_SALDODISPONIBLE_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bice.cl/wsdl", name = "SALDOINICIAL")
    public JAXBElement<String> createSALDOINICIAL(String value) {
        return new JAXBElement<String>(_SALDOINICIAL_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bice.cl/wsdl", name = "TIPOCUENTA")
    public JAXBElement<String> createTIPOCUENTA(String value) {
        return new JAXBElement<String>(_TIPOCUENTA_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bice.cl/wsdl", name = "LIMITEAUTORIZADO")
    public JAXBElement<String> createLIMITEAUTORIZADO(String value) {
        return new JAXBElement<String>(_LIMITEAUTORIZADO_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bice.cl/wsdl", name = "LIMITEUTILIZADO")
    public JAXBElement<String> createLIMITEUTILIZADO(String value) {
        return new JAXBElement<String>(_LIMITEUTILIZADO_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bice.cl/wsdl", name = "LIMITEDISPONIBLE")
    public JAXBElement<String> createLIMITEDISPONIBLE(String value) {
        return new JAXBElement<String>(_LIMITEDISPONIBLE_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bice.cl/wsdl", name = "RUT")
    public JAXBElement<String> createRUT(String value) {
        return new JAXBElement<String>(_RUT_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bice.cl/wsdl", name = "NOMCLIENTE")
    public JAXBElement<String> createNOMCLIENTE(String value) {
        return new JAXBElement<String>(_NOMCLIENTE_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bice.cl/wsdl", name = "SUCURSAL")
    public JAXBElement<String> createSUCURSAL(String value) {
        return new JAXBElement<String>(_SUCURSAL_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bice.cl/wsdl", name = "TOTALDEBITOS")
    public JAXBElement<String> createTOTALDEBITOS(String value) {
        return new JAXBElement<String>(_TOTALDEBITOS_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bice.cl/wsdl", name = "TOTALCREDITOS")
    public JAXBElement<String> createTOTALCREDITOS(String value) {
        return new JAXBElement<String>(_TOTALCREDITOS_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bice.cl/wsdl", name = "RETENCIONDIA")
    public JAXBElement<String> createRETENCIONDIA(String value) {
        return new JAXBElement<String>(_RETENCIONDIA_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bice.cl/wsdl", name = "RETENCIONMASDIAS")
    public JAXBElement<String> createRETENCIONMASDIAS(String value) {
        return new JAXBElement<String>(_RETENCIONMASDIAS_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bice.cl/wsdl", name = "MONEDA")
    public JAXBElement<String> createMONEDA(String value) {
        return new JAXBElement<String>(_MONEDA_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bice.cl/wsdl", name = "CUENTA")
    public JAXBElement<String> createCUENTA(String value) {
        return new JAXBElement<String>(_CUENTA_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bice.cl/wsdl", name = "FECULCARTOLA")
    public JAXBElement<String> createFECULCARTOLA(String value) {
        return new JAXBElement<String>(_FECULCARTOLA_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bice.cl/wsdl", name = "SALDOULFACTURACION")
    public JAXBElement<String> createSALDOULFACTURACION(String value) {
        return new JAXBElement<String>(_SALDOULFACTURACION_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bice.cl/wsdl", name = "FECHA")
    public JAXBElement<String> createFECHA(String value) {
        return new JAXBElement<String>(_FECHA_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bice.cl/wsdl", name = "FECHACONTABLE")
    public JAXBElement<String> createFECHACONTABLE(String value) {
        return new JAXBElement<String>(_FECHACONTABLE_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bice.cl/wsdl", name = "CODTRN")
    public JAXBElement<String> createCODTRN(String value) {
        return new JAXBElement<String>(_CODTRN_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bice.cl/wsdl", name = "DESCRIPCION")
    public JAXBElement<String> createDESCRIPCION(String value) {
        return new JAXBElement<String>(_DESCRIPCION_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bice.cl/wsdl", name = "NARRATIVA")
    public JAXBElement<String> createNARRATIVA(String value) {
        return new JAXBElement<String>(_NARRATIVA_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bice.cl/wsdl", name = "REMARKS")
    public JAXBElement<String> createREMARKS(String value) {
        return new JAXBElement<String>(_REMARKS_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bice.cl/wsdl", name = "SUCMOV")
    public JAXBElement<String> createSUCMOV(String value) {
        return new JAXBElement<String>(_SUCMOV_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bice.cl/wsdl", name = "NROINST")
    public JAXBElement<String> createNROINST(String value) {
        return new JAXBElement<String>(_NROINST_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bice.cl/wsdl", name = "TIPOMOV")
    public JAXBElement<String> createTIPOMOV(String value) {
        return new JAXBElement<String>(_TIPOMOV_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bice.cl/wsdl", name = "MONTO")
    public JAXBElement<String> createMONTO(String value) {
        return new JAXBElement<String>(_MONTO_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bice.cl/wsdl", name = "NUMCHQ")
    public JAXBElement<String> createNUMCHQ(String value) {
        return new JAXBElement<String>(_NUMCHQ_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bice.cl/wsdl", name = "MONTOCHQ")
    public JAXBElement<String> createMONTOCHQ(String value) {
        return new JAXBElement<String>(_MONTOCHQ_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bice.cl/wsdl", name = "CANAL")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createCANAL(String value) {
        return new JAXBElement<String>(_CANAL_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bice.cl/wsdl", name = "CUENTA_IN")
    public JAXBElement<BigInteger> createCUENTAIN(BigInteger value) {
        return new JAXBElement<BigInteger>(_CUENTAIN_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bice.cl/wsdl", name = "TIPOMOV_IN")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createTIPOMOVIN(String value) {
        return new JAXBElement<String>(_TIPOMOVIN_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bice.cl/wsdl", name = "INFOCHEQUES_IN")
    public JAXBElement<BigInteger> createINFOCHEQUESIN(BigInteger value) {
        return new JAXBElement<BigInteger>(_INFOCHEQUESIN_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bice.cl/wsdl", name = "REGISTRO")
    public JAXBElement<BigInteger> createREGISTRO(BigInteger value) {
        return new JAXBElement<BigInteger>(_REGISTRO_QNAME, BigInteger.class, null, value);
    }

}
