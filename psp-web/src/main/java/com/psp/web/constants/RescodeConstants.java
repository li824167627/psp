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
 * 返回结果处理
 */
public class RescodeConstants {

	private static RescodeConstants instance;
	private Map<String, Rescode> maps;

	private RescodeConstants() {
		if (maps == null)
			maps = new HashMap<String, Rescode>();
		parserXml();
	}

	public static RescodeConstants getInstance() {
		synchronized (RescodeConstants.class) {
			if (instance == null)
				instance = new RescodeConstants();
			return instance;
		}
	}

	/**
	 * 解析
	 */
	private void parserXml() {
		SAXBuilder builder = new SAXBuilder();
		try {
			Document doc = builder.build(this.getClass().getResourceAsStream("/assets/rescode.xml"));
			Element rootEl = doc.getRootElement();
			// 获得所有子元素
			List<Element> results = rootEl.getChildren("result");
			for (Element result : results) {
				Rescode rescode = new Rescode();
				// 获取name
				String name = result.getAttributeValue("name");
				// 获取code, msg
				int code = parseInt(result.getChildText("code"), -1);

				String msg = result.getChildText("msg");

				rescode.setCode(code);
				rescode.setMsg(msg);
				rescode.setName(name);

				maps.put(name, rescode);
			}
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取返回值常量
	 * 
	 * @param name
	 * @return
	 */
	public Rescode get(String name) {
		return maps.get(name);
	}

	/**
	 * 格式化int
	 * 
	 * @param value
	 * @param def
	 * @return
	 */
	private int parseInt(String value, int def) {
		if (value == null)
			return def;
		try {
			return Integer.valueOf(value);
		} catch (Exception e) {
			return def;
		}
	}

}
