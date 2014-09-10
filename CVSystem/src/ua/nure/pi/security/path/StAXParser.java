package ua.nure.pi.security.path;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stax.StAXSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import ua.nure.pi.entity.Right;

/**
 * Controller for StAX parser.
 * 
 * @author Volodymyr_Semerkov
 */
public class StAXParser extends DefaultHandler {
	private static final String SECURITY = "security";
	private static final String CONSTRAINT = "constraint";
	private static final String URL_PATTERN = "url-pattern";
	private static final String RIGHT = "right";
	private static final String XSD_FILE_PATH = "/security.xsd";

	/**
	 * Validate XML document.
	 * 
	 * @param xmlFileName
	 *            XML document name.
	 * @return
	 * @throws XMLStreamException
	 */
	public static boolean validate(String xmlFileName)
			throws XMLStreamException {
		XMLInputFactory inputFactory = XMLInputFactory.newInstance();
		XMLStreamReader reader = null;
		try {
			reader = inputFactory.createXMLStreamReader(StAXParser.class
					.getResourceAsStream(xmlFileName));
			SchemaFactory schemaFactory = SchemaFactory
					.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = schemaFactory.newSchema(StAXParser.class
					.getResource(XSD_FILE_PATH));
			Validator validator = schema.newValidator();
			validator.validate(new StAXSource(reader));
		} catch (XMLStreamException | SAXException | IOException e) {
			return false;
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
		return true;
	}

	/**
	 * Parse XML document with StAX.
	 * 
	 * @param xmlFileName
	 *            XML document name.
	 * @return Collection of constraints.
	 * @throws XMLStreamException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static List<Constraint> parse(String xmlFileName)
			throws XMLStreamException {
		List<Constraint> constraints = null;
		Constraint currentConstraint = null;
		String currentElement = null;
		XMLInputFactory inputFactory = XMLInputFactory.newInstance();
		XMLStreamReader reader = inputFactory
				.createXMLStreamReader(StAXParser.class
						.getResourceAsStream(xmlFileName));
		while (reader.hasNext()) {
			int event = reader.next();
			switch (event) {
			case XMLStreamConstants.START_ELEMENT:
				currentElement = reader.getLocalName();
				if (SECURITY.equals(currentElement)) {
					constraints = new ArrayList<Constraint>();
				}
				if (CONSTRAINT.equals(currentElement)) {
					currentConstraint = new Constraint();
				}
				break;
			case XMLStreamConstants.CHARACTERS:
				String text = reader.getText().trim();
				if (text.isEmpty())
					break;
				if (URL_PATTERN.equals(currentElement)) {
					currentConstraint.setURLPattern(text);
				}
				if (RIGHT.equals(currentElement)) {
					Right right = Right.valueOf(text.toUpperCase());
					currentConstraint.getRights().add(right);
				}
				break;
			case XMLStreamConstants.END_ELEMENT:
				currentElement = reader.getLocalName();
				if (CONSTRAINT.equals(currentElement)) {
					constraints.add(currentConstraint);
				}
				currentElement = null;
				break;
			}
		}
		reader.close();
		return constraints;
	}
}
