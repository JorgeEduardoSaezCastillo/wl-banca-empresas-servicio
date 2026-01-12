package cl.bice.banca.empresas.servicio.controller.fixtures;

import cl.bice.banca.empresas.servicio.model.request.desafios.ListarCrearDesafiosRequest;
import cl.bice.banca.empresas.servicio.model.request.desafios.ValidarEstadoConfirmacionRequest;
import cl.bice.banca.empresas.servicio.model.request.operaciones.AprobarOperacionesRequest;
import cl.bice.banca.empresas.servicio.model.request.operaciones.LiberarOperacionesRequest;
import cl.bice.banca.empresas.servicio.model.request.operaciones.ValidaPoderRequest;
import cl.bice.banca.empresas.servicio.service.EstadoService;
import cl.bice.banca.empresas.servicio.util.EstadoEnum;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class ControllersFixtures {

    public static LiberarOperacionesRequest LIBERAR_OPERACIONES_REQUEST_FIXTURE(String codigoServicio){
        List<String> operaciones = new ArrayList<>();
        operaciones.add("123456");
        LiberarOperacionesRequest a = new LiberarOperacionesRequest();
        a.setCodigoServicio(codigoServicio);
        a.setListaOperaciones(operaciones);
        a.setNombreApoderado("test-apo");
        a.setGlsTipoDisp("SMS");
        a.setGlsDesafio("123456");
        a.setGlsTipocliente("BANCA_EMPRESAS");
        a.setTipo("test-tipo");
        a.setIp("1.1.1.1");
        a.setOrigenLlamada("IE");
        a.setSessionID("aaa");
        a.setServerName("srv1");
        a.setCanal("IE");
        a.setDispositivo("aaaaa");
        a.setDispositivoFirma("SMS");
        a.setRut("0060623538");
        a.setRutEmpresa("0965965408");
        a.setToken("aaabbb");
        a.setMapaDesafio(null);
         
        return a;
    }

    public static AprobarOperacionesRequest APROBAR_OPERACIONES_REQUEST_FIXTURE(String codigoServicio){
        List<String> operaciones = new ArrayList<>();
        operaciones.add("123456");
        AprobarOperacionesRequest a = new AprobarOperacionesRequest();
        a.setCodigoServicio(codigoServicio);
        a.setListaOperaciones(operaciones);
        a.setNombreApoderado("test-apo");
        a.setGlsTipoDisp("SMS");
        a.setGlsDesafio("123456");
        a.setGlsTipocliente("BANCA_EMPRESAS");
        a.setIp("1.1.1.1");
        a.setOrigenLlamada("IE");
        a.setSessionID("aaa");
        a.setServerName("srv1");

        return a;
    }

    public static ValidaPoderRequest VALIDA_PODER_REQUEST_FIXTURE(String codigoServicio){
        ValidaPoderRequest a = new ValidaPoderRequest();
        a.setCodigoServicio(codigoServicio);

        return a;
    }

    public static ListarCrearDesafiosRequest LISTAR_CREAR_DESAFIOS_REQUEST_FIXTURE () {
        ListarCrearDesafiosRequest r = new ListarCrearDesafiosRequest();
        r.setCodigoServicio("935");
        r.setCanal("IE");
        return r;
    }

    public static ValidarEstadoConfirmacionRequest VALIDAR_ESTADO_CONFIRMACION_REQUEST_FIXTURE() {
        ValidarEstadoConfirmacionRequest r = new ValidarEstadoConfirmacionRequest();
        r.setListaOperaciones(new ArrayList<>());
        r.setCodigoServicio("935");
        r.setIdTransaccion("12344");
        r.setCanal("IE");
        r.setFechaDesde("20220124");
        r.setFechaHasta("20220124");
        r.setRutEmpresa("123445");
        r.setRut("2313112");
        return r;
    }
}
