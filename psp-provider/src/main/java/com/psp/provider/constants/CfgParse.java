package com.psp.provider.constants;

import java.io.File;
import java.io.IOException;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class CfgParse {
	private static CfgParse instance;

	private CfgParse() {
		parserXml();
	}

	public static CfgParse getInstance() {
		synchronized (CfgParse.class) {
			if (instance == null)
				instance = new CfgParse();
			return instance;
		}
	}

	private CfgBean config;

	public void parserXml() {
		String configPath = this.getClass().getResource("/assets/cfg.xml").getFile();
		XmlMapper xml = new XmlMapper();
		try {
			// config
			config = xml.readValue(new File(configPath), CfgBean.class);
			// 输出config
			System.out.println(JSON.toJSON(config));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取配置
	 * 
	 * @return
	 */
	public CfgBean getConfig() {
		return config;
	}
}
