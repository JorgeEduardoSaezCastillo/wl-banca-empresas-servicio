
package cl.bice.nominasenlinea.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the cl.bice.nominasenlinea.ws package. 
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

    private final static QName _ActEstadoNomLinResponse_QNAME = new QName("http://portal.ws.bice.cl/", "Act_Estado_NomLinResponse");
    private final static QName _RegistrarNomLinResponse_QNAME = new QName("http://portal.ws.bice.cl/", "Registrar_Nom_LinResponse");
    private final static QName _ListarRegNomLinResponse_QNAME = new QName("http://portal.ws.bice.cl/", "Listar_Reg_NomLinResponse");
    private final static QName _InsSegFdaResponse_QNAME = new QName("http://portal.ws.bice.cl/", "Ins_Seg_FdaResponse");
    private final static QName _ActEstadoNomLin_QNAME = new QName("http://portal.ws.bice.cl/", "Act_Estado_NomLin");
    private final static QName _ActEnvioMail_QNAME = new QName("http://portal.ws.bice.cl/", "Act_Envio_Mail");
    private final static QName _ObtenerMtoMaxNomLin_QNAME = new QName("http://portal.ws.bice.cl/", "Obtener_MtoMax_NomLin");
    private final static QName _RegRestriccionMontoResponse_QNAME = new QName("http://portal.ws.bice.cl/", "Reg_Restriccion_MontoResponse");
    private final static QName _ConsEnvioMail_QNAME = new QName("http://portal.ws.bice.cl/", "Cons_Envio_Mail");
    private final static QName _ActEnvioMailResponse_QNAME = new QName("http://portal.ws.bice.cl/", "Act_Envio_MailResponse");
    private final static QName _ObtenerParametros_QNAME = new QName("http://portal.ws.bice.cl/", "Obtener_Parametros");
    private final static QName _ConsRestMonto_QNAME = new QName("http://portal.ws.bice.cl/", "Cons_Rest_Monto");
    private final static QName _ActCerrarNomLinResponse_QNAME = new QName("http://portal.ws.bice.cl/", "Act_Cerrar_NomLinResponse");
    private final static QName _ConsNomClienteResponse_QNAME = new QName("http://portal.ws.bice.cl/", "Cons_Nom_ClienteResponse");
    private final static QName _AprobarNomLinReversa_QNAME = new QName("http://portal.ws.bice.cl/", "Aprobar_NomLin_Reversa");
    private final static QName _ObtenerMtoMaxNomLinResponse_QNAME = new QName("http://portal.ws.bice.cl/", "Obtener_MtoMax_NomLinResponse");
    private final static QName _RegEnvioMail_QNAME = new QName("http://portal.ws.bice.cl/", "Reg_Envio_Mail");
    private final static QName _ActCerrarNomLin_QNAME = new QName("http://portal.ws.bice.cl/", "Act_Cerrar_NomLin");
    private final static QName _ObtenerParametrosResponse_QNAME = new QName("http://portal.ws.bice.cl/", "Obtener_ParametrosResponse");
    private final static QName _ConsNomCliente_QNAME = new QName("http://portal.ws.bice.cl/", "Cons_Nom_Cliente");
    private final static QName _ConsRestMontoResponse_QNAME = new QName("http://portal.ws.bice.cl/", "Cons_Rest_MontoResponse");
    private final static QName _ActRestriccionMonto_QNAME = new QName("http://portal.ws.bice.cl/", "Act_Restriccion_Monto");
    private final static QName _ConsCuentasNomLinResponse_QNAME = new QName("http://portal.ws.bice.cl/", "Cons_Cuentas_NomLinResponse");
    private final static QName _RegRestriccionMonto_QNAME = new QName("http://portal.ws.bice.cl/", "Reg_Restriccion_Monto");
    private final static QName _ObtenerNomParametros_QNAME = new QName("http://portal.ws.bice.cl/", "Obtener_Nom_Parametros");
    private final static QName _ListarRegNomLin_QNAME = new QName("http://portal.ws.bice.cl/", "Listar_Reg_NomLin");
    private final static QName _ConsEnvioMailResponse_QNAME = new QName("http://portal.ws.bice.cl/", "Cons_Envio_MailResponse");
    private final static QName _RegistrarNomLin_QNAME = new QName("http://portal.ws.bice.cl/", "Registrar_Nom_Lin");
    private final static QName _RegEnvioMailResponse_QNAME = new QName("http://portal.ws.bice.cl/", "Reg_Envio_MailResponse");
    private final static QName _ObtenerNomParametrosResponse_QNAME = new QName("http://portal.ws.bice.cl/", "Obtener_Nom_ParametrosResponse");
    private final static QName _ConsCuentasNomLin_QNAME = new QName("http://portal.ws.bice.cl/", "Cons_Cuentas_NomLin");
    private final static QName _InsSegFda_QNAME = new QName("http://portal.ws.bice.cl/", "Ins_Seg_Fda");
    private final static QName _ActRestriccionMontoResponse_QNAME = new QName("http://portal.ws.bice.cl/", "Act_Restriccion_MontoResponse");
    private final static QName _AprobarNomLinReversaResponse_QNAME = new QName("http://portal.ws.bice.cl/", "Aprobar_NomLin_ReversaResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cl.bice.nominasenlinea.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ObtenerMtoMaxNomLinResponse }
     * 
     */
    public ObtenerMtoMaxNomLinResponse createObtenerMtoMaxNomLinResponse() {
        return new ObtenerMtoMaxNomLinResponse();
    }

    /**
     * Create an instance of {@link RegEnvioMail }
     * 
     */
    public RegEnvioMail createRegEnvioMail() {
        return new RegEnvioMail();
    }

    /**
     * Create an instance of {@link ConsNomClienteResponse }
     * 
     */
    public ConsNomClienteResponse createConsNomClienteResponse() {
        return new ConsNomClienteResponse();
    }

    /**
     * Create an instance of {@link AprobarNomLinReversa }
     * 
     */
    public AprobarNomLinReversa createAprobarNomLinReversa() {
        return new AprobarNomLinReversa();
    }

    /**
     * Create an instance of {@link ObtenerMtoMaxNomLin }
     * 
     */
    public ObtenerMtoMaxNomLin createObtenerMtoMaxNomLin() {
        return new ObtenerMtoMaxNomLin();
    }

    /**
     * Create an instance of {@link RegRestriccionMontoResponse }
     * 
     */
    public RegRestriccionMontoResponse createRegRestriccionMontoResponse() {
        return new RegRestriccionMontoResponse();
    }

    /**
     * Create an instance of {@link ConsEnvioMail }
     * 
     */
    public ConsEnvioMail createConsEnvioMail() {
        return new ConsEnvioMail();
    }

    /**
     * Create an instance of {@link ActEnvioMailResponse }
     * 
     */
    public ActEnvioMailResponse createActEnvioMailResponse() {
        return new ActEnvioMailResponse();
    }

    /**
     * Create an instance of {@link ObtenerParametros }
     * 
     */
    public ObtenerParametros createObtenerParametros() {
        return new ObtenerParametros();
    }

    /**
     * Create an instance of {@link ConsRestMonto }
     * 
     */
    public ConsRestMonto createConsRestMonto() {
        return new ConsRestMonto();
    }

    /**
     * Create an instance of {@link ActCerrarNomLinResponse }
     * 
     */
    public ActCerrarNomLinResponse createActCerrarNomLinResponse() {
        return new ActCerrarNomLinResponse();
    }

    /**
     * Create an instance of {@link ActEstadoNomLinResponse }
     * 
     */
    public ActEstadoNomLinResponse createActEstadoNomLinResponse() {
        return new ActEstadoNomLinResponse();
    }

    /**
     * Create an instance of {@link RegistrarNomLinResponse }
     * 
     */
    public RegistrarNomLinResponse createRegistrarNomLinResponse() {
        return new RegistrarNomLinResponse();
    }

    /**
     * Create an instance of {@link ListarRegNomLinResponse }
     * 
     */
    public ListarRegNomLinResponse createListarRegNomLinResponse() {
        return new ListarRegNomLinResponse();
    }

    /**
     * Create an instance of {@link InsSegFdaResponse }
     * 
     */
    public InsSegFdaResponse createInsSegFdaResponse() {
        return new InsSegFdaResponse();
    }

    /**
     * Create an instance of {@link ActEstadoNomLin }
     * 
     */
    public ActEstadoNomLin createActEstadoNomLin() {
        return new ActEstadoNomLin();
    }

    /**
     * Create an instance of {@link ActEnvioMail }
     * 
     */
    public ActEnvioMail createActEnvioMail() {
        return new ActEnvioMail();
    }

    /**
     * Create an instance of {@link ConsCuentasNomLin }
     * 
     */
    public ConsCuentasNomLin createConsCuentasNomLin() {
        return new ConsCuentasNomLin();
    }

    /**
     * Create an instance of {@link InsSegFda }
     * 
     */
    public InsSegFda createInsSegFda() {
        return new InsSegFda();
    }

    /**
     * Create an instance of {@link ActRestriccionMontoResponse }
     * 
     */
    public ActRestriccionMontoResponse createActRestriccionMontoResponse() {
        return new ActRestriccionMontoResponse();
    }

    /**
     * Create an instance of {@link AprobarNomLinReversaResponse }
     * 
     */
    public AprobarNomLinReversaResponse createAprobarNomLinReversaResponse() {
        return new AprobarNomLinReversaResponse();
    }

    /**
     * Create an instance of {@link ListarRegNomLin }
     * 
     */
    public ListarRegNomLin createListarRegNomLin() {
        return new ListarRegNomLin();
    }

    /**
     * Create an instance of {@link ConsEnvioMailResponse }
     * 
     */
    public ConsEnvioMailResponse createConsEnvioMailResponse() {
        return new ConsEnvioMailResponse();
    }

    /**
     * Create an instance of {@link RegistrarNomLin }
     * 
     */
    public RegistrarNomLin createRegistrarNomLin() {
        return new RegistrarNomLin();
    }

    /**
     * Create an instance of {@link RegEnvioMailResponse }
     * 
     */
    public RegEnvioMailResponse createRegEnvioMailResponse() {
        return new RegEnvioMailResponse();
    }

    /**
     * Create an instance of {@link ObtenerNomParametrosResponse }
     * 
     */
    public ObtenerNomParametrosResponse createObtenerNomParametrosResponse() {
        return new ObtenerNomParametrosResponse();
    }

    /**
     * Create an instance of {@link RegRestriccionMonto }
     * 
     */
    public RegRestriccionMonto createRegRestriccionMonto() {
        return new RegRestriccionMonto();
    }

    /**
     * Create an instance of {@link ObtenerNomParametros }
     * 
     */
    public ObtenerNomParametros createObtenerNomParametros() {
        return new ObtenerNomParametros();
    }

    /**
     * Create an instance of {@link ActCerrarNomLin }
     * 
     */
    public ActCerrarNomLin createActCerrarNomLin() {
        return new ActCerrarNomLin();
    }

    /**
     * Create an instance of {@link ObtenerParametrosResponse }
     * 
     */
    public ObtenerParametrosResponse createObtenerParametrosResponse() {
        return new ObtenerParametrosResponse();
    }

    /**
     * Create an instance of {@link ConsNomCliente }
     * 
     */
    public ConsNomCliente createConsNomCliente() {
        return new ConsNomCliente();
    }

    /**
     * Create an instance of {@link ConsRestMontoResponse }
     * 
     */
    public ConsRestMontoResponse createConsRestMontoResponse() {
        return new ConsRestMontoResponse();
    }

    /**
     * Create an instance of {@link ActRestriccionMonto }
     * 
     */
    public ActRestriccionMonto createActRestriccionMonto() {
        return new ActRestriccionMonto();
    }

    /**
     * Create an instance of {@link ConsCuentasNomLinResponse }
     * 
     */
    public ConsCuentasNomLinResponse createConsCuentasNomLinResponse() {
        return new ConsCuentasNomLinResponse();
    }

    /**
     * Create an instance of {@link RegNomIn }
     * 
     */
    public RegNomIn createRegNomIn() {
        return new RegNomIn();
    }

    /**
     * Create an instance of {@link RestriccionesDto }
     * 
     */
    public RestriccionesDto createRestriccionesDto() {
        return new RestriccionesDto();
    }

    /**
     * Create an instance of {@link ConsEnvioMailIn }
     * 
     */
    public ConsEnvioMailIn createConsEnvioMailIn() {
        return new ConsEnvioMailIn();
    }

    /**
     * Create an instance of {@link ActCerrarNomIn }
     * 
     */
    public ActCerrarNomIn createActCerrarNomIn() {
        return new ActCerrarNomIn();
    }

    /**
     * Create an instance of {@link RegistrosNomDto }
     * 
     */
    public RegistrosNomDto createRegistrosNomDto() {
        return new RegistrosNomDto();
    }

    /**
     * Create an instance of {@link ConsParametrosOut }
     * 
     */
    public ConsParametrosOut createConsParametrosOut() {
        return new ConsParametrosOut();
    }

    /**
     * Create an instance of {@link ConsRestriccionMontoIn }
     * 
     */
    public ConsRestriccionMontoIn createConsRestriccionMontoIn() {
        return new ConsRestriccionMontoIn();
    }

    /**
     * Create an instance of {@link ActRestriccionMontoIn }
     * 
     */
    public ActRestriccionMontoIn createActRestriccionMontoIn() {
        return new ActRestriccionMontoIn();
    }

    /**
     * Create an instance of {@link RegEnvioMailOut }
     * 
     */
    public RegEnvioMailOut createRegEnvioMailOut() {
        return new RegEnvioMailOut();
    }

    /**
     * Create an instance of {@link ActCerrarNomOut }
     * 
     */
    public ActCerrarNomOut createActCerrarNomOut() {
        return new ActCerrarNomOut();
    }

    /**
     * Create an instance of {@link MontoMaxDto }
     * 
     */
    public MontoMaxDto createMontoMaxDto() {
        return new MontoMaxDto();
    }

    /**
     * Create an instance of {@link RegistrosNomLinOut }
     * 
     */
    public RegistrosNomLinOut createRegistrosNomLinOut() {
        return new RegistrosNomLinOut();
    }

    /**
     * Create an instance of {@link ConsMtoMaxIn }
     * 
     */
    public ConsMtoMaxIn createConsMtoMaxIn() {
        return new ConsMtoMaxIn();
    }

    /**
     * Create an instance of {@link ConsNombreClienteIn }
     * 
     */
    public ConsNombreClienteIn createConsNombreClienteIn() {
        return new ConsNombreClienteIn();
    }

    /**
     * Create an instance of {@link ConsMtoMaxOut }
     * 
     */
    public ConsMtoMaxOut createConsMtoMaxOut() {
        return new ConsMtoMaxOut();
    }

    /**
     * Create an instance of {@link ConsEnvioMailOut }
     * 
     */
    public ConsEnvioMailOut createConsEnvioMailOut() {
        return new ConsEnvioMailOut();
    }

    /**
     * Create an instance of {@link InsertSegFdaOut }
     * 
     */
    public InsertSegFdaOut createInsertSegFdaOut() {
        return new InsertSegFdaOut();
    }

    /**
     * Create an instance of {@link ConsCuentasNomLinOut }
     * 
     */
    public ConsCuentasNomLinOut createConsCuentasNomLinOut() {
        return new ConsCuentasNomLinOut();
    }

    /**
     * Create an instance of {@link ConsNomParamIn }
     * 
     */
    public ConsNomParamIn createConsNomParamIn() {
        return new ConsNomParamIn();
    }

    /**
     * Create an instance of {@link InsertSegFdaIn }
     * 
     */
    public InsertSegFdaIn createInsertSegFdaIn() {
        return new InsertSegFdaIn();
    }

    /**
     * Create an instance of {@link RegNomOut }
     * 
     */
    public RegNomOut createRegNomOut() {
        return new RegNomOut();
    }

    /**
     * Create an instance of {@link ActEnvioMailOut }
     * 
     */
    public ActEnvioMailOut createActEnvioMailOut() {
        return new ActEnvioMailOut();
    }

    /**
     * Create an instance of {@link RegRestriccionMontoOut }
     * 
     */
    public RegRestriccionMontoOut createRegRestriccionMontoOut() {
        return new RegRestriccionMontoOut();
    }

    /**
     * Create an instance of {@link ActRestriccionMontoOut }
     * 
     */
    public ActRestriccionMontoOut createActRestriccionMontoOut() {
        return new ActRestriccionMontoOut();
    }

    /**
     * Create an instance of {@link ParametrosDto }
     * 
     */
    public ParametrosDto createParametrosDto() {
        return new ParametrosDto();
    }

    /**
     * Create an instance of {@link EnvioMailNomLinDto }
     * 
     */
    public EnvioMailNomLinDto createEnvioMailNomLinDto() {
        return new EnvioMailNomLinDto();
    }

    /**
     * Create an instance of {@link ConsParametrosIn }
     * 
     */
    public ConsParametrosIn createConsParametrosIn() {
        return new ConsParametrosIn();
    }

    /**
     * Create an instance of {@link RegRestriccionMontoIn }
     * 
     */
    public RegRestriccionMontoIn createRegRestriccionMontoIn() {
        return new RegRestriccionMontoIn();
    }

    /**
     * Create an instance of {@link ActEstadoNomOut }
     * 
     */
    public ActEstadoNomOut createActEstadoNomOut() {
        return new ActEstadoNomOut();
    }

    /**
     * Create an instance of {@link ActEstadoNomIn }
     * 
     */
    public ActEstadoNomIn createActEstadoNomIn() {
        return new ActEstadoNomIn();
    }

    /**
     * Create an instance of {@link CuentasNomLinDto }
     * 
     */
    public CuentasNomLinDto createCuentasNomLinDto() {
        return new CuentasNomLinDto();
    }

    /**
     * Create an instance of {@link OpNomLinDto }
     * 
     */
    public OpNomLinDto createOpNomLinDto() {
        return new OpNomLinDto();
    }

    /**
     * Create an instance of {@link ConsRestriccionMontoOut }
     * 
     */
    public ConsRestriccionMontoOut createConsRestriccionMontoOut() {
        return new ConsRestriccionMontoOut();
    }

    /**
     * Create an instance of {@link NombreClienteDto }
     * 
     */
    public NombreClienteDto createNombreClienteDto() {
        return new NombreClienteDto();
    }

    /**
     * Create an instance of {@link RegistrosNomLinIn }
     * 
     */
    public RegistrosNomLinIn createRegistrosNomLinIn() {
        return new RegistrosNomLinIn();
    }

    /**
     * Create an instance of {@link RevAprobarNomLinOut }
     * 
     */
    public RevAprobarNomLinOut createRevAprobarNomLinOut() {
        return new RevAprobarNomLinOut();
    }

    /**
     * Create an instance of {@link ConsNombreClienteOut }
     * 
     */
    public ConsNombreClienteOut createConsNombreClienteOut() {
        return new ConsNombreClienteOut();
    }

    /**
     * Create an instance of {@link RevAprobarNomLinIn }
     * 
     */
    public RevAprobarNomLinIn createRevAprobarNomLinIn() {
        return new RevAprobarNomLinIn();
    }

    /**
     * Create an instance of {@link RegEnvioMailIn }
     * 
     */
    public RegEnvioMailIn createRegEnvioMailIn() {
        return new RegEnvioMailIn();
    }

    /**
     * Create an instance of {@link ActEnvioMailIn }
     * 
     */
    public ActEnvioMailIn createActEnvioMailIn() {
        return new ActEnvioMailIn();
    }

    /**
     * Create an instance of {@link ConsCuentasNomLinIn }
     * 
     */
    public ConsCuentasNomLinIn createConsCuentasNomLinIn() {
        return new ConsCuentasNomLinIn();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ActEstadoNomLinResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.ws.bice.cl/", name = "Act_Estado_NomLinResponse")
    public JAXBElement<ActEstadoNomLinResponse> createActEstadoNomLinResponse(ActEstadoNomLinResponse value) {
        return new JAXBElement<ActEstadoNomLinResponse>(_ActEstadoNomLinResponse_QNAME, ActEstadoNomLinResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegistrarNomLinResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.ws.bice.cl/", name = "Registrar_Nom_LinResponse")
    public JAXBElement<RegistrarNomLinResponse> createRegistrarNomLinResponse(RegistrarNomLinResponse value) {
        return new JAXBElement<RegistrarNomLinResponse>(_RegistrarNomLinResponse_QNAME, RegistrarNomLinResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarRegNomLinResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.ws.bice.cl/", name = "Listar_Reg_NomLinResponse")
    public JAXBElement<ListarRegNomLinResponse> createListarRegNomLinResponse(ListarRegNomLinResponse value) {
        return new JAXBElement<ListarRegNomLinResponse>(_ListarRegNomLinResponse_QNAME, ListarRegNomLinResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InsSegFdaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.ws.bice.cl/", name = "Ins_Seg_FdaResponse")
    public JAXBElement<InsSegFdaResponse> createInsSegFdaResponse(InsSegFdaResponse value) {
        return new JAXBElement<InsSegFdaResponse>(_InsSegFdaResponse_QNAME, InsSegFdaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ActEstadoNomLin }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.ws.bice.cl/", name = "Act_Estado_NomLin")
    public JAXBElement<ActEstadoNomLin> createActEstadoNomLin(ActEstadoNomLin value) {
        return new JAXBElement<ActEstadoNomLin>(_ActEstadoNomLin_QNAME, ActEstadoNomLin.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ActEnvioMail }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.ws.bice.cl/", name = "Act_Envio_Mail")
    public JAXBElement<ActEnvioMail> createActEnvioMail(ActEnvioMail value) {
        return new JAXBElement<ActEnvioMail>(_ActEnvioMail_QNAME, ActEnvioMail.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerMtoMaxNomLin }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.ws.bice.cl/", name = "Obtener_MtoMax_NomLin")
    public JAXBElement<ObtenerMtoMaxNomLin> createObtenerMtoMaxNomLin(ObtenerMtoMaxNomLin value) {
        return new JAXBElement<ObtenerMtoMaxNomLin>(_ObtenerMtoMaxNomLin_QNAME, ObtenerMtoMaxNomLin.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegRestriccionMontoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.ws.bice.cl/", name = "Reg_Restriccion_MontoResponse")
    public JAXBElement<RegRestriccionMontoResponse> createRegRestriccionMontoResponse(RegRestriccionMontoResponse value) {
        return new JAXBElement<RegRestriccionMontoResponse>(_RegRestriccionMontoResponse_QNAME, RegRestriccionMontoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsEnvioMail }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.ws.bice.cl/", name = "Cons_Envio_Mail")
    public JAXBElement<ConsEnvioMail> createConsEnvioMail(ConsEnvioMail value) {
        return new JAXBElement<ConsEnvioMail>(_ConsEnvioMail_QNAME, ConsEnvioMail.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ActEnvioMailResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.ws.bice.cl/", name = "Act_Envio_MailResponse")
    public JAXBElement<ActEnvioMailResponse> createActEnvioMailResponse(ActEnvioMailResponse value) {
        return new JAXBElement<ActEnvioMailResponse>(_ActEnvioMailResponse_QNAME, ActEnvioMailResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerParametros }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.ws.bice.cl/", name = "Obtener_Parametros")
    public JAXBElement<ObtenerParametros> createObtenerParametros(ObtenerParametros value) {
        return new JAXBElement<ObtenerParametros>(_ObtenerParametros_QNAME, ObtenerParametros.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsRestMonto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.ws.bice.cl/", name = "Cons_Rest_Monto")
    public JAXBElement<ConsRestMonto> createConsRestMonto(ConsRestMonto value) {
        return new JAXBElement<ConsRestMonto>(_ConsRestMonto_QNAME, ConsRestMonto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ActCerrarNomLinResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.ws.bice.cl/", name = "Act_Cerrar_NomLinResponse")
    public JAXBElement<ActCerrarNomLinResponse> createActCerrarNomLinResponse(ActCerrarNomLinResponse value) {
        return new JAXBElement<ActCerrarNomLinResponse>(_ActCerrarNomLinResponse_QNAME, ActCerrarNomLinResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsNomClienteResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.ws.bice.cl/", name = "Cons_Nom_ClienteResponse")
    public JAXBElement<ConsNomClienteResponse> createConsNomClienteResponse(ConsNomClienteResponse value) {
        return new JAXBElement<ConsNomClienteResponse>(_ConsNomClienteResponse_QNAME, ConsNomClienteResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AprobarNomLinReversa }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.ws.bice.cl/", name = "Aprobar_NomLin_Reversa")
    public JAXBElement<AprobarNomLinReversa> createAprobarNomLinReversa(AprobarNomLinReversa value) {
        return new JAXBElement<AprobarNomLinReversa>(_AprobarNomLinReversa_QNAME, AprobarNomLinReversa.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerMtoMaxNomLinResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.ws.bice.cl/", name = "Obtener_MtoMax_NomLinResponse")
    public JAXBElement<ObtenerMtoMaxNomLinResponse> createObtenerMtoMaxNomLinResponse(ObtenerMtoMaxNomLinResponse value) {
        return new JAXBElement<ObtenerMtoMaxNomLinResponse>(_ObtenerMtoMaxNomLinResponse_QNAME, ObtenerMtoMaxNomLinResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegEnvioMail }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.ws.bice.cl/", name = "Reg_Envio_Mail")
    public JAXBElement<RegEnvioMail> createRegEnvioMail(RegEnvioMail value) {
        return new JAXBElement<RegEnvioMail>(_RegEnvioMail_QNAME, RegEnvioMail.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ActCerrarNomLin }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.ws.bice.cl/", name = "Act_Cerrar_NomLin")
    public JAXBElement<ActCerrarNomLin> createActCerrarNomLin(ActCerrarNomLin value) {
        return new JAXBElement<ActCerrarNomLin>(_ActCerrarNomLin_QNAME, ActCerrarNomLin.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerParametrosResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.ws.bice.cl/", name = "Obtener_ParametrosResponse")
    public JAXBElement<ObtenerParametrosResponse> createObtenerParametrosResponse(ObtenerParametrosResponse value) {
        return new JAXBElement<ObtenerParametrosResponse>(_ObtenerParametrosResponse_QNAME, ObtenerParametrosResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsNomCliente }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.ws.bice.cl/", name = "Cons_Nom_Cliente")
    public JAXBElement<ConsNomCliente> createConsNomCliente(ConsNomCliente value) {
        return new JAXBElement<ConsNomCliente>(_ConsNomCliente_QNAME, ConsNomCliente.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsRestMontoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.ws.bice.cl/", name = "Cons_Rest_MontoResponse")
    public JAXBElement<ConsRestMontoResponse> createConsRestMontoResponse(ConsRestMontoResponse value) {
        return new JAXBElement<ConsRestMontoResponse>(_ConsRestMontoResponse_QNAME, ConsRestMontoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ActRestriccionMonto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.ws.bice.cl/", name = "Act_Restriccion_Monto")
    public JAXBElement<ActRestriccionMonto> createActRestriccionMonto(ActRestriccionMonto value) {
        return new JAXBElement<ActRestriccionMonto>(_ActRestriccionMonto_QNAME, ActRestriccionMonto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsCuentasNomLinResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.ws.bice.cl/", name = "Cons_Cuentas_NomLinResponse")
    public JAXBElement<ConsCuentasNomLinResponse> createConsCuentasNomLinResponse(ConsCuentasNomLinResponse value) {
        return new JAXBElement<ConsCuentasNomLinResponse>(_ConsCuentasNomLinResponse_QNAME, ConsCuentasNomLinResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegRestriccionMonto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.ws.bice.cl/", name = "Reg_Restriccion_Monto")
    public JAXBElement<RegRestriccionMonto> createRegRestriccionMonto(RegRestriccionMonto value) {
        return new JAXBElement<RegRestriccionMonto>(_RegRestriccionMonto_QNAME, RegRestriccionMonto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerNomParametros }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.ws.bice.cl/", name = "Obtener_Nom_Parametros")
    public JAXBElement<ObtenerNomParametros> createObtenerNomParametros(ObtenerNomParametros value) {
        return new JAXBElement<ObtenerNomParametros>(_ObtenerNomParametros_QNAME, ObtenerNomParametros.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarRegNomLin }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.ws.bice.cl/", name = "Listar_Reg_NomLin")
    public JAXBElement<ListarRegNomLin> createListarRegNomLin(ListarRegNomLin value) {
        return new JAXBElement<ListarRegNomLin>(_ListarRegNomLin_QNAME, ListarRegNomLin.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsEnvioMailResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.ws.bice.cl/", name = "Cons_Envio_MailResponse")
    public JAXBElement<ConsEnvioMailResponse> createConsEnvioMailResponse(ConsEnvioMailResponse value) {
        return new JAXBElement<ConsEnvioMailResponse>(_ConsEnvioMailResponse_QNAME, ConsEnvioMailResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegistrarNomLin }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.ws.bice.cl/", name = "Registrar_Nom_Lin")
    public JAXBElement<RegistrarNomLin> createRegistrarNomLin(RegistrarNomLin value) {
        return new JAXBElement<RegistrarNomLin>(_RegistrarNomLin_QNAME, RegistrarNomLin.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegEnvioMailResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.ws.bice.cl/", name = "Reg_Envio_MailResponse")
    public JAXBElement<RegEnvioMailResponse> createRegEnvioMailResponse(RegEnvioMailResponse value) {
        return new JAXBElement<RegEnvioMailResponse>(_RegEnvioMailResponse_QNAME, RegEnvioMailResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerNomParametrosResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.ws.bice.cl/", name = "Obtener_Nom_ParametrosResponse")
    public JAXBElement<ObtenerNomParametrosResponse> createObtenerNomParametrosResponse(ObtenerNomParametrosResponse value) {
        return new JAXBElement<ObtenerNomParametrosResponse>(_ObtenerNomParametrosResponse_QNAME, ObtenerNomParametrosResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsCuentasNomLin }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.ws.bice.cl/", name = "Cons_Cuentas_NomLin")
    public JAXBElement<ConsCuentasNomLin> createConsCuentasNomLin(ConsCuentasNomLin value) {
        return new JAXBElement<ConsCuentasNomLin>(_ConsCuentasNomLin_QNAME, ConsCuentasNomLin.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InsSegFda }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.ws.bice.cl/", name = "Ins_Seg_Fda")
    public JAXBElement<InsSegFda> createInsSegFda(InsSegFda value) {
        return new JAXBElement<InsSegFda>(_InsSegFda_QNAME, InsSegFda.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ActRestriccionMontoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.ws.bice.cl/", name = "Act_Restriccion_MontoResponse")
    public JAXBElement<ActRestriccionMontoResponse> createActRestriccionMontoResponse(ActRestriccionMontoResponse value) {
        return new JAXBElement<ActRestriccionMontoResponse>(_ActRestriccionMontoResponse_QNAME, ActRestriccionMontoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AprobarNomLinReversaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.ws.bice.cl/", name = "Aprobar_NomLin_ReversaResponse")
    public JAXBElement<AprobarNomLinReversaResponse> createAprobarNomLinReversaResponse(AprobarNomLinReversaResponse value) {
        return new JAXBElement<AprobarNomLinReversaResponse>(_AprobarNomLinReversaResponse_QNAME, AprobarNomLinReversaResponse.class, null, value);
    }

}
