package cl.bice.banca.empresas.servicio.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class ProductosService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BiceComexService.class);
	/**
	 * Propiedades Externas.
	 */
	@Autowired
	Properties propiedadesExterna;
	
	
	
	public HashMap<String, Object> obtenerProductosCCWS(String rut, String dispositivo, String canal, String token) {

		String tipos [] = {"CC"};
		
		HashMap<String, Object> reqs = new HashMap<String, Object>();
		reqs.put("data", "ME");
		reqs.put("dispositivo", dispositivo);
		reqs.put("origen", canal);
		reqs.put("rut", rut);
		reqs.put("tipos",tipos);
		reqs.put("token", token);

		LOGGER.info("Se envia request [{}]", reqs);

		String urlProductos = this.propiedadesExterna.getProperty("url.rest.productos");

		HashMap<String, Object> responseProductos = new RestTemplate().postForObject(urlProductos, reqs, HashMap.class);

		HashMap<String, Object> productos = (HashMap<String, Object>) responseProductos.get("productos");
		/*ArrayList<HashMap<String, Object>> tc = (ArrayList<HashMap<String, Object>>) productos.get("TC");
		ArrayList<HashMap<String, Object>> cc = (ArrayList<HashMap<String, Object>>) productos.get("CC");
		ArrayList<HashMap<String, Object>> cctc = (ArrayList<HashMap<String, Object>>) productos.get("CCTC");


		if (tc != null) {
		    tc.forEach(data -> {
			String numeroOperacionEncriptado = AES.encrypt(request.getRut() + "|" + data.get("numeroOperacion").toString());
			String numeroPanTarjetaEncrypt = AES.encrypt(request.getRut() + "|" + data.get("numeroPanTarjeta").toString());
			data.replace("numeroOperacion", numeroOperacionEncriptado);
			data.replace("numeroPanTarjeta", numeroPanTarjetaEncrypt);
		    });
		}

		if (cc != null) {
		    cc.forEach(data -> {
			String numeroOperacionEncriptado = AES.encrypt(request.getRut() + "|" + data.get("numeroOperacion").toString());
			data.replace("numeroOperacion", numeroOperacionEncriptado);
		    });
		}

		if (cctc != null) {
		    cctc.forEach(data -> {
			if (data.get("codigoProducto").toString().equals("210")) {
			    String numeroOperacionEncriptado = AES.encrypt(request.getRut() + "|" + data.get("numeroOperacion").toString());
			    String numeroPanTarjetaEncrypt = AES.encrypt(request.getRut() + "|" + data.get("numeroPanTarjeta").toString());
			    data.replace("numeroOperacion", numeroOperacionEncriptado);
			    data.replace("numeroPanTarjeta", numeroPanTarjetaEncrypt);
			} else if (data.get("codigoProducto").toString().equals("100") || data.get("codigoProducto").toString().equals("110")) {
			    String numeroOperacionEncriptado = AES.encrypt(request.getRut() + "|" + data.get("numeroOperacion").toString());
			    data.replace("numeroOperacion", numeroOperacionEncriptado);
			}

		    });
		}*/

		LOGGER.info("Respuesta del rest [{}]", productos);

		return productos;
    }
	
	public HashMap<String, Object> obtenerProductoCC(HashMap<String, Object> productos, String numCuenta) {
		LOGGER.info("buscando producto numCuenta:"+ numCuenta);
		ArrayList<HashMap<String, Object>> cc = (ArrayList<HashMap<String, Object>>) productos.get("CC");
		
		LOGGER.info("largo productos:"+ productos.size());
		if (cc != null) {
			for (int i=0; i<cc.size();i++) {
				HashMap<String, Object> data = cc.get(i);
				//LOGGER.info("comparando numCuenta:"+numCuenta+" | numOperacion:"+data.get("numeroOperacion").toString());
				if(data.containsKey("numeroOperacion") && numCuenta.equals(data.get("numeroOperacion").toString())) {
					return data;
				}
			}
		}
		
		return null;
	}

}
