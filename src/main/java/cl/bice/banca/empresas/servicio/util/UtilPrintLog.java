package cl.bice.banca.empresas.servicio.util;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cl.bice.banca.empresas.servicio.model.common.BaseRequest;


/**
 * Pretty-prints xml, supplied as a string.
 * <p/>
 * eg. <code>
 * String formattedXml = new UtilXML().format("<tag><nested>hello</nested></tag>");
 * </code>
 */
public class UtilPrintLog {
	private UtilPrintLog() {

	}

	private static final Logger LOGGER = LoggerFactory.getLogger(UtilPrintLog.class);

	public static String fromXMLtoString(Object input, Class<?> originalClass) {
		String resp = "";
		try {
			JAXBContext context = JAXBContext.newInstance(originalClass);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			StringWriter sw = new StringWriter();
			m.marshal(input, sw);
			resp = sw.toString();
		} catch (JAXBException e) {
			LOGGER.error("{}", e);
		}
		return resp;
	}

	public static String format(String unformattedXml) {
		try {
			TransformerFactory factory = TransformerFactory.newInstance();
			factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

			Transformer transformer = factory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			// initialize StreamResult with File object to save to file
			StreamResult result = new StreamResult(new StringWriter());
			DOMSource source = new DOMSource(parseXmlFile(unformattedXml));
			transformer.transform(source, result);
			unformattedXml = result.getWriter().toString();

		} catch (TransformerFactoryConfigurationError | TransformerException e) {
			LOGGER.error("{}", e);
		}
		return unformattedXml;
	}

	private static Document parseXmlFile(String in) {

		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db;
			db = dbf.newDocumentBuilder();
			InputSource is = new InputSource(new StringReader(in));
			return db.parse(is);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			LOGGER.error("{}", e);
		}
		return null;
	}

	public static void printXML(Object input, Class<?> originalClass, String msj) {
		try {
			String xml = UtilPrintLog.format(UtilPrintLog.fromXMLtoString(input, originalClass));
			LOGGER.info("{} --> \n{} ", msj, xml);
		} catch (RuntimeException e) {
			LOGGER.info("Error parse {} a XML: {}", msj, e);
		}

	}

	public static void printJSON(Object input, String msj) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String json = mapper.writeValueAsString(input);
			LOGGER.info("{} --> {} ", msj, json);

		} catch (JsonProcessingException e) {
			LOGGER.info("Error parse {} a json: {}", msj, e);
		}

	}

	public static void printXML(Object input, Class<?> originalClass, String msj, BaseRequest baseRequest) {
		try {
			String xml = UtilPrintLog.format(UtilPrintLog.fromXMLtoString(input, originalClass));
			LOGGER.info("[{}] [{}] [{}] {} --> \n {} ", baseRequest.getDispositivo(), baseRequest.getToken(),
					baseRequest.getRut(), msj, xml);
		} catch (RuntimeException e) {
			LOGGER.info("[{}] [{}] [{}] Error parse {} a XML", baseRequest.getDispositivo(), baseRequest.getToken(),
					baseRequest.getRut(), msj, e);
		}

	}
}
