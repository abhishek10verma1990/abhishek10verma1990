package com.npst.upi.portal.merchant.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PropertyUtil {
	private static final String properties_path = "upiportalprop";
	private static final Logger log = LoggerFactory.getLogger(PropertyUtil.class);
	private static Properties p = new Properties();
	private static List<String> fileList;
	private static boolean propertiesLoaded;
	private static boolean empty;

	/*public static String getPeroprtyValue(PropertyKey propNameOrKey) {
		log.info("inside");
		if (!propertiesLoaded)
			loadProperty();
		return p.getProperty(propNameOrKey.toString());
	}*/
	public static String getPeroprtyValue(String propName) {
		log.info("inside");
		if (!propertiesLoaded)
			loadProperty();
		return p.getProperty(propName);
	}

	/*public static Integer getPeroprtyValueAsInt(PropertyKey propNameOrKey) {
		log.info("inside");
		Integer i = null;
		if (!propertiesLoaded)
			loadProperty();
		String s = null;
		try {
			s = p.getProperty(propNameOrKey.toString());
			i = Integer.parseInt(s);
			return i;
		} catch (Exception e) {
			log.error("error :" + e);
			log.error("Property (" + propNameOrKey.toString() + ") value ie (" + s + ") can not be cast to Integer");
		}
		return i;
	}*/

	public static boolean isEmpty() {
		return empty;
	}

	public static boolean isPropertiesLoaded() {
		return propertiesLoaded;
	}

	public static boolean loadProperty() {
		try {
			loadInitiallyProperties(properties_path);
		} catch (Exception e) {
			log.info("error :" + e);
		}
		return propertiesLoaded;
	}

	private static synchronized void loadInitiallyProperties(String file) throws Exception {
		if (propertiesLoaded)
			return;
		log.info("inside");
		fileList = getAllPropFiles(file);
		if (fileList != null && fileList.size() > 0) {
			for (String f : fileList) {
				try {
					if (f != null && f.endsWith(".properties")) {
						p.load(new FileReader(f));
						log.info("loaded properties file ie : " + f);
						propertiesLoaded = true;
					} else if (f != null && f.endsWith(".xml")) {
						p.loadFromXML(new FileInputStream(f));
						log.info("loaded property xml file ie : " + f);
						propertiesLoaded = true;
					} else {
						log.info("invalid file ie :" + f);
					}

				} catch (Exception e) {
					log.error("property file not loaded ie :" + f + " ,error=" + e);
				}
			}
			if (propertiesLoaded) {
				empty = p.isEmpty();
				//test();
				log.info(p.toString());
			} else {
				throw new Exception("properties files not loaded");
			}
		} else {
			log.info("properties file not found");
			throw new Exception("properties file not found");
		}
		log.info("out");
	}

	/*private static void test() throws Exception {
		log.info("inside");
		PropertyKey[] list = PropertyKey.values();
		String t = null;
		StringBuilder sb = new StringBuilder();
		for (PropertyKey key : list) {
			t = p.getProperty(key.toString());
			if (t == null) {
				sb.append(", " + key.toString());

			}
		}
		if (sb != null && sb.length() > 0) {
			throw new Exception("ERROR :properties key not found ie " + sb.toString());
		}
		log.info("out");
	}*/

	private static List<String> getAllPropFiles(String file) throws Exception {
		log.info("inside");
		List<String> list = new ArrayList<String>(10);
		if (file != null) {
			File[] flist = null;
			File f = new File(file);
			if (f.isDirectory()) {
				flist = f.listFiles();
				if (flist != null) {
					log.info("flist size=" + flist.length);
					for (File fl : flist) {
						if (fl != null && (fl.getName().endsWith(".properties") || fl.getName().endsWith(".xml")))
							list.add(fl.getAbsolutePath());
					}

				} else {
					log.info("flist==null");
				}
			} else if (f.isFile() && (file.endsWith(".properties") || file.endsWith(".xml"))) {

				list.add(file);
			} else {
				log.info("neither directory nor file");
			}
		} else {
			log.info("file==null");
		}
		log.info("out");
		return list;

	}
}
