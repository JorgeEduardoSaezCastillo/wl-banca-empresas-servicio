
package cl.bice.nominas.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the cl.bice.nominas.ws package. 
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

    private final static QName _ActualizaDetCampResponse_QNAME = new QName("http://ws.nominas.bice.cl/", "Actualiza_Det_CampResponse");
    private final static QName _ConsultaFirmaOp_QNAME = new QName("http://ws.nominas.bice.cl/", "Consulta_Firma_Op");
    private final static QName _ConsultaFirmaOpResponse_QNAME = new QName("http://ws.nominas.bice.cl/", "Consulta_Firma_OpResponse");
    private final static QName _ReversaNomina_QNAME = new QName("http://ws.nominas.bice.cl/", "Reversa_Nomina");
    private final static QName _ConsultaParamNominas_QNAME = new QName("http://ws.nominas.bice.cl/", "Consulta_Param_Nominas");
    private final static QName _RegistraFirmaOp_QNAME = new QName("http://ws.nominas.bice.cl/", "Registra_Firma_Op");
    private final static QName _InsertaIOdata_QNAME = new QName("http://ws.nominas.bice.cl/", "Inserta_IOdata");
    private final static QName _ActualizaNominaResponse_QNAME = new QName("http://ws.nominas.bice.cl/", "Actualiza_NominaResponse");
    private final static QName _ReversaNominaResponse_QNAME = new QName("http://ws.nominas.bice.cl/", "Reversa_NominaResponse");
    private final static QName _AplicaCargoOnline_QNAME = new QName("http://ws.nominas.bice.cl/", "Aplica_Cargo_Online");
    private final static QName _ConsultaParamValidacion_QNAME = new QName("http://ws.nominas.bice.cl/", "Consulta_Param_Validacion");
    private final static QName _ConsultaParamValidacionResponse_QNAME = new QName("http://ws.nominas.bice.cl/", "Consulta_Param_ValidacionResponse");
    private final static QName _RegistraFirmaOpResponse_QNAME = new QName("http://ws.nominas.bice.cl/", "Registra_Firma_OpResponse");
    private final static QName _ActualizaOperProg_QNAME = new QName("http://ws.nominas.bice.cl/", "Actualiza_Oper_Prog");
    private final static QName _InsertaIOdataResponse_QNAME = new QName("http://ws.nominas.bice.cl/", "Inserta_IOdataResponse");
    private final static QName _AplicaCargoOnlineResponse_QNAME = new QName("http://ws.nominas.bice.cl/", "Aplica_Cargo_OnlineResponse");
    private final static QName _RegistraOpResponse_QNAME = new QName("http://ws.nominas.bice.cl/", "Registra_OpResponse");
    private final static QName _EliminaFirmaOpResponse_QNAME = new QName("http://ws.nominas.bice.cl/", "Elimina_Firma_OpResponse");
    private final static QName _RegistraOp_QNAME = new QName("http://ws.nominas.bice.cl/", "Registra_Op");
    private final static QName _ActualizaOperProgResponse_QNAME = new QName("http://ws.nominas.bice.cl/", "Actualiza_Oper_ProgResponse");
    private final static QName _OperaOnlineResponse_QNAME = new QName("http://ws.nominas.bice.cl/", "Opera_OnlineResponse");
    private final static QName _EliminaFirmaOp_QNAME = new QName("http://ws.nominas.bice.cl/", "Elimina_Firma_Op");
    private final static QName _ConsultaParamNominasResponse_QNAME = new QName("http://ws.nominas.bice.cl/", "Consulta_Param_NominasResponse");
    private final static QName _ActualizaDetCamp_QNAME = new QName("http://ws.nominas.bice.cl/", "Actualiza_Det_Camp");
    private final static QName _ActualizaNomina_QNAME = new QName("http://ws.nominas.bice.cl/", "Actualiza_Nomina");
    private final static QName _OperaOnline_QNAME = new QName("http://ws.nominas.bice.cl/", "Opera_Online");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cl.bice.nominas.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link InsertaIOdataResponse }
     * 
     */
    public InsertaIOdataResponse createInsertaIOdataResponse() {
        return new InsertaIOdataResponse();
    }

    /**
     * Create an instance of {@link AplicaCargoOnlineResponse }
     * 
     */
    public AplicaCargoOnlineResponse createAplicaCargoOnlineResponse() {
        return new AplicaCargoOnlineResponse();
    }

    /**
     * Create an instance of {@link RegistraOp }
     * 
     */
    public RegistraOp createRegistraOp() {
        return new RegistraOp();
    }

    /**
     * Create an instance of {@link RegistraOpResponse }
     * 
     */
    public RegistraOpResponse createRegistraOpResponse() {
        return new RegistraOpResponse();
    }

    /**
     * Create an instance of {@link EliminaFirmaOpResponse }
     * 
     */
    public EliminaFirmaOpResponse createEliminaFirmaOpResponse() {
        return new EliminaFirmaOpResponse();
    }

    /**
     * Create an instance of {@link ActualizaOperProgResponse }
     * 
     */
    public ActualizaOperProgResponse createActualizaOperProgResponse() {
        return new ActualizaOperProgResponse();
    }

    /**
     * Create an instance of {@link ConsultaParamNominasResponse }
     * 
     */
    public ConsultaParamNominasResponse createConsultaParamNominasResponse() {
        return new ConsultaParamNominasResponse();
    }

    /**
     * Create an instance of {@link ActualizaDetCamp }
     * 
     */
    public ActualizaDetCamp createActualizaDetCamp() {
        return new ActualizaDetCamp();
    }

    /**
     * Create an instance of {@link ActualizaNomina }
     * 
     */
    public ActualizaNomina createActualizaNomina() {
        return new ActualizaNomina();
    }

    /**
     * Create an instance of {@link OperaOnline }
     * 
     */
    public OperaOnline createOperaOnline() {
        return new OperaOnline();
    }

    /**
     * Create an instance of {@link OperaOnlineResponse }
     * 
     */
    public OperaOnlineResponse createOperaOnlineResponse() {
        return new OperaOnlineResponse();
    }

    /**
     * Create an instance of {@link EliminaFirmaOp }
     * 
     */
    public EliminaFirmaOp createEliminaFirmaOp() {
        return new EliminaFirmaOp();
    }

    /**
     * Create an instance of {@link ConsultaParamNominas }
     * 
     */
    public ConsultaParamNominas createConsultaParamNominas() {
        return new ConsultaParamNominas();
    }

    /**
     * Create an instance of {@link RegistraFirmaOp }
     * 
     */
    public RegistraFirmaOp createRegistraFirmaOp() {
        return new RegistraFirmaOp();
    }

    /**
     * Create an instance of {@link InsertaIOdata }
     * 
     */
    public InsertaIOdata createInsertaIOdata() {
        return new InsertaIOdata();
    }

    /**
     * Create an instance of {@link ActualizaDetCampResponse }
     * 
     */
    public ActualizaDetCampResponse createActualizaDetCampResponse() {
        return new ActualizaDetCampResponse();
    }

    /**
     * Create an instance of {@link ConsultaFirmaOp }
     * 
     */
    public ConsultaFirmaOp createConsultaFirmaOp() {
        return new ConsultaFirmaOp();
    }

    /**
     * Create an instance of {@link ConsultaFirmaOpResponse }
     * 
     */
    public ConsultaFirmaOpResponse createConsultaFirmaOpResponse() {
        return new ConsultaFirmaOpResponse();
    }

    /**
     * Create an instance of {@link ReversaNomina }
     * 
     */
    public ReversaNomina createReversaNomina() {
        return new ReversaNomina();
    }

    /**
     * Create an instance of {@link ActualizaNominaResponse }
     * 
     */
    public ActualizaNominaResponse createActualizaNominaResponse() {
        return new ActualizaNominaResponse();
    }

    /**
     * Create an instance of {@link ReversaNominaResponse }
     * 
     */
    public ReversaNominaResponse createReversaNominaResponse() {
        return new ReversaNominaResponse();
    }

    /**
     * Create an instance of {@link AplicaCargoOnline }
     * 
     */
    public AplicaCargoOnline createAplicaCargoOnline() {
        return new AplicaCargoOnline();
    }

    /**
     * Create an instance of {@link ActualizaOperProg }
     * 
     */
    public ActualizaOperProg createActualizaOperProg() {
        return new ActualizaOperProg();
    }

    /**
     * Create an instance of {@link ConsultaParamValidacion }
     * 
     */
    public ConsultaParamValidacion createConsultaParamValidacion() {
        return new ConsultaParamValidacion();
    }

    /**
     * Create an instance of {@link ConsultaParamValidacionResponse }
     * 
     */
    public ConsultaParamValidacionResponse createConsultaParamValidacionResponse() {
        return new ConsultaParamValidacionResponse();
    }

    /**
     * Create an instance of {@link RegistraFirmaOpResponse }
     * 
     */
    public RegistraFirmaOpResponse createRegistraFirmaOpResponse() {
        return new RegistraFirmaOpResponse();
    }

    /**
     * Create an instance of {@link ReversaNominaIn }
     * 
     */
    public ReversaNominaIn createReversaNominaIn() {
        return new ReversaNominaIn();
    }

    /**
     * Create an instance of {@link InsertaOpOut }
     * 
     */
    public InsertaOpOut createInsertaOpOut() {
        return new InsertaOpOut();
    }

    /**
     * Create an instance of {@link InsertaIOdataOut }
     * 
     */
    public InsertaIOdataOut createInsertaIOdataOut() {
        return new InsertaIOdataOut();
    }

    /**
     * Create an instance of {@link ParametrosVo }
     * 
     */
    public ParametrosVo createParametrosVo() {
        return new ParametrosVo();
    }

    /**
     * Create an instance of {@link ActualizaOpIn }
     * 
     */
    public ActualizaOpIn createActualizaOpIn() {
        return new ActualizaOpIn();
    }

    /**
     * Create an instance of {@link OperaCargoOnlineIn }
     * 
     */
    public OperaCargoOnlineIn createOperaCargoOnlineIn() {
        return new OperaCargoOnlineIn();
    }

    /**
     * Create an instance of {@link ActualizaDetCampIn }
     * 
     */
    public ActualizaDetCampIn createActualizaDetCampIn() {
        return new ActualizaDetCampIn();
    }

    /**
     * Create an instance of {@link InsertaOpIn }
     * 
     */
    public InsertaOpIn createInsertaOpIn() {
        return new InsertaOpIn();
    }

    /**
     * Create an instance of {@link RegistraApoOpIn }
     * 
     */
    public RegistraApoOpIn createRegistraApoOpIn() {
        return new RegistraApoOpIn();
    }

    /**
     * Create an instance of {@link OperaCargoOnlineOut }
     * 
     */
    public OperaCargoOnlineOut createOperaCargoOnlineOut() {
        return new OperaCargoOnlineOut();
    }

    /**
     * Create an instance of {@link ConsultaApoOpIn }
     * 
     */
    public ConsultaApoOpIn createConsultaApoOpIn() {
        return new ConsultaApoOpIn();
    }

    /**
     * Create an instance of {@link ApliCargoOnlineIn }
     * 
     */
    public ApliCargoOnlineIn createApliCargoOnlineIn() {
        return new ApliCargoOnlineIn();
    }

    /**
     * Create an instance of {@link ActualizaNominaIn }
     * 
     */
    public ActualizaNominaIn createActualizaNominaIn() {
        return new ActualizaNominaIn();
    }

    /**
     * Create an instance of {@link EliminaApoOpIn }
     * 
     */
    public EliminaApoOpIn createEliminaApoOpIn() {
        return new EliminaApoOpIn();
    }

    /**
     * Create an instance of {@link RegNomiCargoLineaVo }
     * 
     */
    public RegNomiCargoLineaVo createRegNomiCargoLineaVo() {
        return new RegNomiCargoLineaVo();
    }

    /**
     * Create an instance of {@link CargoOnlineOut }
     * 
     */
    public CargoOnlineOut createCargoOnlineOut() {
        return new CargoOnlineOut();
    }

    /**
     * Create an instance of {@link EliminaApoOpOut }
     * 
     */
    public EliminaApoOpOut createEliminaApoOpOut() {
        return new EliminaApoOpOut();
    }

    /**
     * Create an instance of {@link ActualizaOpOut }
     * 
     */
    public ActualizaOpOut createActualizaOpOut() {
        return new ActualizaOpOut();
    }

    /**
     * Create an instance of {@link ConsultaParametrosOut }
     * 
     */
    public ConsultaParametrosOut createConsultaParametrosOut() {
        return new ConsultaParametrosOut();
    }

    /**
     * Create an instance of {@link RegistraApoOpOut }
     * 
     */
    public RegistraApoOpOut createRegistraApoOpOut() {
        return new RegistraApoOpOut();
    }

    /**
     * Create an instance of {@link FirmaOpVo }
     * 
     */
    public FirmaOpVo createFirmaOpVo() {
        return new FirmaOpVo();
    }

    /**
     * Create an instance of {@link InsertaIOdataIn }
     * 
     */
    public InsertaIOdataIn createInsertaIOdataIn() {
        return new InsertaIOdataIn();
    }

    /**
     * Create an instance of {@link ReversaNominaOut }
     * 
     */
    public ReversaNominaOut createReversaNominaOut() {
        return new ReversaNominaOut();
    }

    /**
     * Create an instance of {@link ConsultaParametrosIn }
     * 
     */
    public ConsultaParametrosIn createConsultaParametrosIn() {
        return new ConsultaParametrosIn();
    }

    /**
     * Create an instance of {@link ConsultaApoOpOut }
     * 
     */
    public ConsultaApoOpOut createConsultaApoOpOut() {
        return new ConsultaApoOpOut();
    }

    /**
     * Create an instance of {@link ActualizaDetCampOut }
     * 
     */
    public ActualizaDetCampOut createActualizaDetCampOut() {
        return new ActualizaDetCampOut();
    }

    /**
     * Create an instance of {@link ActualizaNominaOut }
     * 
     */
    public ActualizaNominaOut createActualizaNominaOut() {
        return new ActualizaNominaOut();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ActualizaDetCampResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.nominas.bice.cl/", name = "Actualiza_Det_CampResponse")
    public JAXBElement<ActualizaDetCampResponse> createActualizaDetCampResponse(ActualizaDetCampResponse value) {
        return new JAXBElement<ActualizaDetCampResponse>(_ActualizaDetCampResponse_QNAME, ActualizaDetCampResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaFirmaOp }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.nominas.bice.cl/", name = "Consulta_Firma_Op")
    public JAXBElement<ConsultaFirmaOp> createConsultaFirmaOp(ConsultaFirmaOp value) {
        return new JAXBElement<ConsultaFirmaOp>(_ConsultaFirmaOp_QNAME, ConsultaFirmaOp.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaFirmaOpResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.nominas.bice.cl/", name = "Consulta_Firma_OpResponse")
    public JAXBElement<ConsultaFirmaOpResponse> createConsultaFirmaOpResponse(ConsultaFirmaOpResponse value) {
        return new JAXBElement<ConsultaFirmaOpResponse>(_ConsultaFirmaOpResponse_QNAME, ConsultaFirmaOpResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReversaNomina }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.nominas.bice.cl/", name = "Reversa_Nomina")
    public JAXBElement<ReversaNomina> createReversaNomina(ReversaNomina value) {
        return new JAXBElement<ReversaNomina>(_ReversaNomina_QNAME, ReversaNomina.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaParamNominas }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.nominas.bice.cl/", name = "Consulta_Param_Nominas")
    public JAXBElement<ConsultaParamNominas> createConsultaParamNominas(ConsultaParamNominas value) {
        return new JAXBElement<ConsultaParamNominas>(_ConsultaParamNominas_QNAME, ConsultaParamNominas.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegistraFirmaOp }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.nominas.bice.cl/", name = "Registra_Firma_Op")
    public JAXBElement<RegistraFirmaOp> createRegistraFirmaOp(RegistraFirmaOp value) {
        return new JAXBElement<RegistraFirmaOp>(_RegistraFirmaOp_QNAME, RegistraFirmaOp.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InsertaIOdata }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.nominas.bice.cl/", name = "Inserta_IOdata")
    public JAXBElement<InsertaIOdata> createInsertaIOdata(InsertaIOdata value) {
        return new JAXBElement<InsertaIOdata>(_InsertaIOdata_QNAME, InsertaIOdata.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ActualizaNominaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.nominas.bice.cl/", name = "Actualiza_NominaResponse")
    public JAXBElement<ActualizaNominaResponse> createActualizaNominaResponse(ActualizaNominaResponse value) {
        return new JAXBElement<ActualizaNominaResponse>(_ActualizaNominaResponse_QNAME, ActualizaNominaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReversaNominaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.nominas.bice.cl/", name = "Reversa_NominaResponse")
    public JAXBElement<ReversaNominaResponse> createReversaNominaResponse(ReversaNominaResponse value) {
        return new JAXBElement<ReversaNominaResponse>(_ReversaNominaResponse_QNAME, ReversaNominaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AplicaCargoOnline }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.nominas.bice.cl/", name = "Aplica_Cargo_Online")
    public JAXBElement<AplicaCargoOnline> createAplicaCargoOnline(AplicaCargoOnline value) {
        return new JAXBElement<AplicaCargoOnline>(_AplicaCargoOnline_QNAME, AplicaCargoOnline.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaParamValidacion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.nominas.bice.cl/", name = "Consulta_Param_Validacion")
    public JAXBElement<ConsultaParamValidacion> createConsultaParamValidacion(ConsultaParamValidacion value) {
        return new JAXBElement<ConsultaParamValidacion>(_ConsultaParamValidacion_QNAME, ConsultaParamValidacion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaParamValidacionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.nominas.bice.cl/", name = "Consulta_Param_ValidacionResponse")
    public JAXBElement<ConsultaParamValidacionResponse> createConsultaParamValidacionResponse(ConsultaParamValidacionResponse value) {
        return new JAXBElement<ConsultaParamValidacionResponse>(_ConsultaParamValidacionResponse_QNAME, ConsultaParamValidacionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegistraFirmaOpResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.nominas.bice.cl/", name = "Registra_Firma_OpResponse")
    public JAXBElement<RegistraFirmaOpResponse> createRegistraFirmaOpResponse(RegistraFirmaOpResponse value) {
        return new JAXBElement<RegistraFirmaOpResponse>(_RegistraFirmaOpResponse_QNAME, RegistraFirmaOpResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ActualizaOperProg }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.nominas.bice.cl/", name = "Actualiza_Oper_Prog")
    public JAXBElement<ActualizaOperProg> createActualizaOperProg(ActualizaOperProg value) {
        return new JAXBElement<ActualizaOperProg>(_ActualizaOperProg_QNAME, ActualizaOperProg.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InsertaIOdataResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.nominas.bice.cl/", name = "Inserta_IOdataResponse")
    public JAXBElement<InsertaIOdataResponse> createInsertaIOdataResponse(InsertaIOdataResponse value) {
        return new JAXBElement<InsertaIOdataResponse>(_InsertaIOdataResponse_QNAME, InsertaIOdataResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AplicaCargoOnlineResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.nominas.bice.cl/", name = "Aplica_Cargo_OnlineResponse")
    public JAXBElement<AplicaCargoOnlineResponse> createAplicaCargoOnlineResponse(AplicaCargoOnlineResponse value) {
        return new JAXBElement<AplicaCargoOnlineResponse>(_AplicaCargoOnlineResponse_QNAME, AplicaCargoOnlineResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegistraOpResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.nominas.bice.cl/", name = "Registra_OpResponse")
    public JAXBElement<RegistraOpResponse> createRegistraOpResponse(RegistraOpResponse value) {
        return new JAXBElement<RegistraOpResponse>(_RegistraOpResponse_QNAME, RegistraOpResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EliminaFirmaOpResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.nominas.bice.cl/", name = "Elimina_Firma_OpResponse")
    public JAXBElement<EliminaFirmaOpResponse> createEliminaFirmaOpResponse(EliminaFirmaOpResponse value) {
        return new JAXBElement<EliminaFirmaOpResponse>(_EliminaFirmaOpResponse_QNAME, EliminaFirmaOpResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegistraOp }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.nominas.bice.cl/", name = "Registra_Op")
    public JAXBElement<RegistraOp> createRegistraOp(RegistraOp value) {
        return new JAXBElement<RegistraOp>(_RegistraOp_QNAME, RegistraOp.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ActualizaOperProgResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.nominas.bice.cl/", name = "Actualiza_Oper_ProgResponse")
    public JAXBElement<ActualizaOperProgResponse> createActualizaOperProgResponse(ActualizaOperProgResponse value) {
        return new JAXBElement<ActualizaOperProgResponse>(_ActualizaOperProgResponse_QNAME, ActualizaOperProgResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OperaOnlineResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.nominas.bice.cl/", name = "Opera_OnlineResponse")
    public JAXBElement<OperaOnlineResponse> createOperaOnlineResponse(OperaOnlineResponse value) {
        return new JAXBElement<OperaOnlineResponse>(_OperaOnlineResponse_QNAME, OperaOnlineResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EliminaFirmaOp }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.nominas.bice.cl/", name = "Elimina_Firma_Op")
    public JAXBElement<EliminaFirmaOp> createEliminaFirmaOp(EliminaFirmaOp value) {
        return new JAXBElement<EliminaFirmaOp>(_EliminaFirmaOp_QNAME, EliminaFirmaOp.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaParamNominasResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.nominas.bice.cl/", name = "Consulta_Param_NominasResponse")
    public JAXBElement<ConsultaParamNominasResponse> createConsultaParamNominasResponse(ConsultaParamNominasResponse value) {
        return new JAXBElement<ConsultaParamNominasResponse>(_ConsultaParamNominasResponse_QNAME, ConsultaParamNominasResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ActualizaDetCamp }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.nominas.bice.cl/", name = "Actualiza_Det_Camp")
    public JAXBElement<ActualizaDetCamp> createActualizaDetCamp(ActualizaDetCamp value) {
        return new JAXBElement<ActualizaDetCamp>(_ActualizaDetCamp_QNAME, ActualizaDetCamp.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ActualizaNomina }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.nominas.bice.cl/", name = "Actualiza_Nomina")
    public JAXBElement<ActualizaNomina> createActualizaNomina(ActualizaNomina value) {
        return new JAXBElement<ActualizaNomina>(_ActualizaNomina_QNAME, ActualizaNomina.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OperaOnline }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.nominas.bice.cl/", name = "Opera_Online")
    public JAXBElement<OperaOnline> createOperaOnline(OperaOnline value) {
        return new JAXBElement<OperaOnline>(_OperaOnline_QNAME, OperaOnline.class, null, value);
    }

}
