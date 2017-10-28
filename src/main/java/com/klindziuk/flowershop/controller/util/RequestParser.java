package com.klindziuk.flowershop.controller.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import com.klindziuk.flowershop.model.Bouquet;
import org.apache.log4j.Logger;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.klindziuk.flowershop.model.Flower;

public final class RequestParser {
	private static final Logger logger = Logger.getLogger(RequestParser.class);
	private final static int ITEM_POSITION = 0;
	private final static String XML_ID_TAG = "id";
	private final static String XML_COMMAND_TAG = "command";
	private final static String XML_FLOWER_TAG = "flower";
	private final static String XML_NAME_TAG = "name";
	private final static String XML_COUNTRY_TAG = "country";
	private final static String XML_QUANTITY_TAG = "quantity";
	private final static String XML_PRICE_TAG = "price";
	private final static String XML_AVAILABLE_TAG = "available";
	private final static String PARSE_EXCEPTION_MESSAGE = "Exception during parsing request file.";
	private final static String NULL_REQUEST_EXCEPTION_MESSAGE = "Request cannot be null or empty.";
	private final static String NULL_FILENAME_EXCEPTION_MESSAGE = "Filename cannot be null or empty.";
	private final static String NEWLINE_APPENDER = "\n";

	private RequestParser() {
	}

	public static String readFile(String fileName) throws IOException {
		if (null == fileName || fileName.isEmpty()) {
			throw new IllegalArgumentException(NULL_FILENAME_EXCEPTION_MESSAGE);
		}
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		try {
			StringBuilder builder = new StringBuilder();
			String line = reader.readLine();

			while (line != null) {
				builder.append(line);
				builder.append(NEWLINE_APPENDER);
				line = reader.readLine();
			}
			return builder.toString();
		} finally {
			reader.close();
		}
	}

	public static int getId(String request) throws IllegalArgumentException {
		if (null == request || request.isEmpty()) {
			throw new IllegalArgumentException(NULL_REQUEST_EXCEPTION_MESSAGE);
		}
		int result = 0;
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			InputSource src = new InputSource();
			src.setCharacterStream(new StringReader(request));
			Document doc = builder.parse(src);
			int id = RequestValidator.validateInt(doc.getElementsByTagName(XML_ID_TAG).item(ITEM_POSITION).getTextContent());
			return id;
		} catch (DOMException | ParserConfigurationException | SAXException | IOException e) {
			logger.error(e.getMessage(), e);
		}
		return result;
	}

	public static String getCommand(String request) throws IllegalArgumentException {
		if (null == request || request.isEmpty()) {
			throw new IllegalArgumentException(NULL_REQUEST_EXCEPTION_MESSAGE);
		}
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			InputSource src = new InputSource();
			src.setCharacterStream(new StringReader(request));
			Document doc = builder.parse(src);
			String command = RequestValidator
					.validateString(doc.getElementsByTagName(XML_COMMAND_TAG).item(ITEM_POSITION).getTextContent());
			return command;
		} catch (DOMException | ParserConfigurationException | SAXException | IOException e) {
			logger.error(e.getMessage(), e);
		}
		return PARSE_EXCEPTION_MESSAGE;
	}

	public static float getPrice(String request) throws IllegalArgumentException {
		if (null == request || request.isEmpty()) {
			throw new IllegalArgumentException(NULL_REQUEST_EXCEPTION_MESSAGE);
		}
		float price = 0;
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			InputSource src = new InputSource();
			src.setCharacterStream(new StringReader(request));
			Document doc = builder.parse(src);
			price = RequestValidator
					.validateFloat(doc.getElementsByTagName(XML_PRICE_TAG).item(ITEM_POSITION).getTextContent());
		} catch (DOMException | ParserConfigurationException | SAXException | IOException e) {
			logger.error(e.getMessage(), e);
		}
		return price;
	}

	public static String getName(String request) throws IllegalArgumentException {
		if (null == request || request.isEmpty()) {
			throw new IllegalArgumentException(NULL_REQUEST_EXCEPTION_MESSAGE);
		}
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			InputSource src = new InputSource();
			src.setCharacterStream(new StringReader(request));
			Document doc = builder.parse(src);
			String name = RequestValidator
					.validateString(doc.getElementsByTagName(XML_NAME_TAG).item(ITEM_POSITION).getTextContent());
			return name;
		} catch (DOMException | ParserConfigurationException | SAXException | IOException e) {
			logger.error(e.getMessage(), e);
		}
		return PARSE_EXCEPTION_MESSAGE;
	}

	public static Flower createFlower(String request) throws IllegalArgumentException {
		if (null == request || request.isEmpty()) {
			throw new IllegalArgumentException(NULL_REQUEST_EXCEPTION_MESSAGE);
		}
		Flower flower = null;
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			InputSource src = new InputSource();
			src.setCharacterStream(new StringReader(request));
			Document doc = builder.parse(src);
			String name = RequestValidator
					.validateString(doc.getElementsByTagName(XML_NAME_TAG).item(ITEM_POSITION).getTextContent());
			float price = RequestValidator
					.validateFloat(doc.getElementsByTagName(XML_PRICE_TAG).item(ITEM_POSITION).getTextContent());
			String country = RequestValidator.validateString(doc.getElementsByTagName(XML_COUNTRY_TAG).item(ITEM_POSITION).getTextContent());
			flower = new Flower(name, price, country);
		} catch (DOMException | ParserConfigurationException | SAXException | IOException e) {
			logger.error(e.getMessage(), e);
		}
		return flower;
	}

	public static Flower getFlower(String request) throws IllegalArgumentException {
		if (null == request || request.isEmpty()) {
			throw new IllegalArgumentException(NULL_REQUEST_EXCEPTION_MESSAGE);
		}
		Flower flower = null;
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			InputSource src = new InputSource();
			src.setCharacterStream(new StringReader(request));
			Document doc = builder.parse(src);
			System.out.println();
			int id = RequestValidator.validateInt(doc.getElementsByTagName(XML_ID_TAG).item(ITEM_POSITION).getTextContent());
			String name = RequestValidator
					.validateString(doc.getElementsByTagName(XML_NAME_TAG).item(ITEM_POSITION).getTextContent());
			float price = RequestValidator
					.validateFloat(doc.getElementsByTagName(XML_PRICE_TAG).item(ITEM_POSITION).getTextContent());
			String country = RequestValidator.validateString(doc.getElementsByTagName(XML_COUNTRY_TAG).item(0).getTextContent());
			boolean isAvailable = RequestValidator
					.validateBoolean(doc.getElementsByTagName(XML_AVAILABLE_TAG).item(ITEM_POSITION).getTextContent());
			flower = new Flower(id, name, price, country, isAvailable);
		} catch (DOMException | ParserConfigurationException | SAXException | IOException e) {
			logger.error(e.getMessage(), e);
		}
		return flower;
	}

	//TODO : get bouqet from XML
	public static Map<Integer,Integer> getBouquetMap(String request) throws IllegalArgumentException {
		if (null == request || request.isEmpty()) {
			throw new IllegalArgumentException(NULL_REQUEST_EXCEPTION_MESSAGE);
		}
		Map<Integer, Integer> bouquetMap = new HashMap<>();

		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			InputSource src = new InputSource();
			src.setCharacterStream(new StringReader(request));
			Document doc = builder.parse(src);
			NodeList flowers = doc.getElementsByTagName(XML_FLOWER_TAG);
			for(int i = 0; i < flowers.getLength(); i++){
				Node node =  flowers.item(i);
				Element element = (Element) node;
				int id = RequestValidator.validateInt(element.getElementsByTagName(XML_ID_TAG).item(ITEM_POSITION).getTextContent());
				int quantity = RequestValidator.validateInt(element.getElementsByTagName(XML_QUANTITY_TAG).item(ITEM_POSITION).getTextContent());
				bouquetMap.put(id, quantity);
			}

		} catch (DOMException | ParserConfigurationException | SAXException | IOException e) {
			logger.error(e.getMessage(), e);
		}
		return bouquetMap;
	}
}
