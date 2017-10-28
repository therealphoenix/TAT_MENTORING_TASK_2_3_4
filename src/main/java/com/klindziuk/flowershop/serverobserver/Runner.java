package com.klindziuk.flowershop.serverobserver;

import org.apache.log4j.Logger;

import com.klindziuk.flowershop.serverobserver.file.XmlFileReader;

public class Runner {
	private static final Logger logger = Logger.getLogger(XmlFileReader.class);
	private static final String XMLFILEPATH_GET = "requests/get";
	private static final String XMLFILEPATH_ADD = "requests/add";
	private static final String XMLFILEPATH_DELETE = "requests/delete";

	public static void main(String[] args) {
		try {
		new Thread(new XmlFileReader(XMLFILEPATH_GET)).start();
		new Thread(new XmlFileReader(XMLFILEPATH_ADD)).start();
		new Thread(new XmlFileReader(XMLFILEPATH_DELETE)).start();

	} catch (IllegalArgumentException iaex){
		logger.error(iaex.getMessage());
}
	}
}
