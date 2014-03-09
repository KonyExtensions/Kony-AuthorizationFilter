package com.kony.prateek.filters;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.konylabs.middleware.common.MiddlewareValidationListener;

/**
 * Utility to read xml content from config file and populate a hashmap in memory for quick access
 * @author Prateek
 */
public class UtilXMLReader
{
	private final static String SERVICE_ELT = "service";
	private final static String ROLES = "role";
	private final static String TYPE = "type";
	private final static Logger log = Logger.getLogger(UtilXMLReader.class);
	private static HashMap<String,ArrayList<String>> serviceMap = null;
	private static final String propertyFilePath = MiddlewareValidationListener.getMIDDLEWARE_HOME() + "/middleware/middleware-bootconfig/AuthFilter.xml";
	static{
		try{
			serviceMap = UtilXMLReader.populateServiceMap(propertyFilePath);
		}catch(Exception e){
			log.error("Exception while reading configuration file:"+propertyFilePath+e.getMessage());
		}
	}
	
	public static HashMap<String,ArrayList<String>> getServiceMap(){
		return serviceMap;
	}
	
	/**
	 * @param path to read the config file
	 * @return Hashmap containing services mapped to each role
	 * @throws Exception
	 */
	public static HashMap<String,ArrayList<String>> populateServiceMap(String path) throws Exception
	{
		HashMap<String,ArrayList<String>> hm = null;

		hm = new HashMap<String,ArrayList<String>>();
		File xmlFile = new File(path);  
		DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();  
		DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();  
		Document doc = documentBuilder.parse(xmlFile);
		doc.getDocumentElement().normalize();

		NodeList roles = doc.getElementsByTagName(ROLES);
		for(int i=0;i<roles.getLength();i++)
		{
			ArrayList<String> ar = new ArrayList<String>();
			Element roleElt = (Element) roles.item(i);
			String id = getElementValue(roleElt.getElementsByTagName(TYPE).item(0));
			 
			NodeList services = roleElt.getElementsByTagName(SERVICE_ELT);
			for(int x=0;x<services.getLength();x++)
			{
				ar.add(getElementValue(services.item(x)));				
			}
			 
			hm.put(id, ar);
		 }		
		
		return hm;
	}
	
	public static String getElementValue(Node node) throws Exception
	{
		return node.getTextContent().toString().trim();
	}
	
}
