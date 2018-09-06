package com.northend.api.go;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.northend.api.go.bean.Bean;
import com.northend.api.go.bean.BeanFile;
import com.northend.api.go.bean.Protocol;
import com.northend.api.go.bean.ProtocolConfig;
import com.northend.api.go.bean.ProtocolFile;
import com.northend.api.go.bean.Rescode;
import com.northend.api.go.bean.RescodeFile;

public class XmlParse {

	private ProtocolConfig config;
	private List<Bean> beans;
	private List<ProtocolFile> protocolFiles;
	private List<Rescode> rescodes;

	public void parse(String configPath) {
		XmlMapper xml = new XmlMapper();
		try {
			// config
			config = xml.readValue(new File(configPath), ProtocolConfig.class);
			// 输出config
			System.out.println(JSON.toJSON(config));

			// beans
			List<BeanFile> beanFiles = config.getBeanFiles();
			beans = new ArrayList<>();
			for (BeanFile file : beanFiles) {
				List<Bean> temp = xml.readValue(new File(config.getXmlPath(), file.getFileName()),
						new TypeReference<List<Bean>>() {
						});

				beans.addAll(temp);
			}
			// 输出beans
			System.out.println(JSON.toJSON(beans));

			// protocols
			protocolFiles = config.getProtocolFiles();
			for (ProtocolFile file : protocolFiles) {
				List<Protocol> temp = xml.readValue(new File(config.getXmlPath(), file.getFileName()),
						new TypeReference<List<Protocol>>() {
						});

				file.setProtocols(temp);
			}
			// 输出beans
			System.out.println(JSON.toJSON(protocolFiles));

			// rescodes
			rescodes = new ArrayList<>();
			RescodeFile rescodeFile = config.getRescodeFile();
			if (rescodeFile != null) {
				List<Rescode> temp = xml.readValue(new File(config.getXmlPath(), rescodeFile.getFileName()),
						new TypeReference<List<Rescode>>() {
						});
				rescodes.addAll(temp);
			}
			System.out.println(JSON.toJSON(rescodes));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ProtocolConfig getConfig() {
		return config;
	}

	public void setConfig(ProtocolConfig config) {
		this.config = config;
	}

	public List<ProtocolFile> getProtocolFiles() {
		return protocolFiles;
	}

	public List<Bean> getBeans() {
		return beans;
	}

	public void setBeans(List<Bean> beans) {
		this.beans = beans;
	}

	public List<Rescode> getRescodes() {
		return rescodes;
	}

	public void setRescodes(List<Rescode> rescodes) {
		this.rescodes = rescodes;
	}

}
