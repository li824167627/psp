package com.psp.provider.constants;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class ConfigConstants {
	private static ConfigConstants instance;
	private Map<String, Author> strMaps;
	private Map<String, String> webMap;

	private ConfigConstants() {
		if (this.strMaps == null) {
			this.strMaps = new HashMap<String, Author>();
		}
		if (this.webMap == null) {
			this.webMap = new HashMap<String, String>();
		}
		parserXml();
	}

	public static ConfigConstants getInstance() {
		synchronized (ConfigConstants.class) {
			if (instance == null) {
				instance = new ConfigConstants();
			}
			return instance;
		}
	}

	private void parserXml() {
		SAXBuilder builder = new SAXBuilder();
		try {
			Document doc = builder.build(getClass().getResourceAsStream("/assets/cfg.xml"));
			Element rootEl = doc.getRootElement();

			List<Element> results = rootEl.getChildren("author");
			String name;
			for (Element result : results) {
				name = result.getAttributeValue("name");
				Author auth = new Author();
				String key = result.getChildText("key");
				String code = result.getChildText("author_code");
				String ident = result.getChildText("ident");
				auth.setKey(key);
				auth.setCode(code);
				auth.setIdent(ident);
				this.strMaps.put(name, auth);
			}
			List<Element> webs = rootEl.getChildren("webConfig");
			String webname;
			for (Element w : webs) {
				webname = w.getAttributeValue("name");
				String value = w.getChildText("value");
				this.webMap.put(webname, value);
			}
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Author getString(String name) {
		return (Author) this.strMaps.get(name);
	}

	public String getPath(String name) {
		return (String) this.webMap.get(name);
	}
}
