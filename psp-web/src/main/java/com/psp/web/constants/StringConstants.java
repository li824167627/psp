package com.psp.web.constants;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 * 字符、字符数组处理
 */
public class StringConstants {

	private static StringConstants instance;
	private Map<String, String> strMaps;
	private Map<String, String[]> strArrayMaps;

	private StringConstants() {
		if (strMaps == null)
			strMaps = new HashMap<String, String>();

		if (strArrayMaps == null)
			strArrayMaps = new HashMap<String, String[]>();

		parserXml();
	}

	public static StringConstants getInstance() {
		synchronized (StringConstants.class) {
			if (instance == null)
				instance = new StringConstants();
			return instance;
		}
	}

	/**
	 * 解析
	 */
	private void parserXml() {
		SAXBuilder builder = new SAXBuilder();
		try {
			Document doc = builder.build(this.getClass().getResourceAsStream("/assets/strings.xml"));
			Element rootEl = doc.getRootElement();
			// 获得所有string元素
			List<Element> results = rootEl.getChildren("string");
			for (Element result : results) {
				// 获取name、value
				String name = result.getAttributeValue("name");
				String value = result.getText();

				strMaps.put(name, value);
			}
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取string value
	 * 
	 * @param name
	 * @return
	 */
	public String getString(String name) {
		return strMaps.get(name);
	}

	/**
	 * 带格式化，获取字符串
	 * 
	 * @param name
	 * @return
	 */
	public String getString(String name, Object... args) {
		String format = getString(name);
		if (format == null)
			return null;
		return String.format(format, args);
	}

	/**
	 * 获取字符数组
	 * 
	 * @param name
	 * @return
	 */
	public String[] getArray(String name) {
		return strArrayMaps.get(name);
	}

}
