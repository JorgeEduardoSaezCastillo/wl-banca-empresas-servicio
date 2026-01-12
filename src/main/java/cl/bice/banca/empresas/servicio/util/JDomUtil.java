package cl.bice.banca.empresas.servicio.util;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.xpath.XPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;

public class JDomUtil {

	/**
	 * Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(JDomUtil.class);

	public Document string2JDomDocument(String xmlin) {
		Document doc = null;
		SAXBuilder saxBuilder = new SAXBuilder(false);
		try {
			doc = saxBuilder.build(new InputSource(new StringReader(xmlin)));
		} catch (JDOMException | IOException e) {
			LOGGER.warn("{}", e);
		}
		return doc;
	}

	public String getPathSingleNode(String xpathPath, Document doc) {
		XPath x = null;
		String resultado = null;
		try {
			x = XPath.newInstance(xpathPath);
			resultado = (String) x.selectSingleNode(doc);

		} catch (JDOMException e) {
			LOGGER.warn("{}", e);
		}
		return resultado;
	}

	public List getPathMultipleNode(String xpathPath, Document doc) {
		XPath x = null;
		List<?> resultado = null;
		try {
			x = XPath.newInstance(xpathPath);
			resultado = (List<?>) x.selectNodes(doc);
		} catch (JDOMException e) {
			LOGGER.warn("{}", e);
		}
		return resultado;
	}
}
