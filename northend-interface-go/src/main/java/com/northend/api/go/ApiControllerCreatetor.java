package com.northend.api.go;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.northend.api.go.bean.Bean;
import com.northend.api.go.bean.ProtocolConfig;
import com.northend.api.go.bean.ProtocolFile;
import com.northend.api.go.bean.Rescode;

public class ApiControllerCreatetor {

	private ProtocolConfig config;
	private List<Bean> beans;
	private List<ProtocolFile> protocolFiles;
	private List<Rescode> rescodes;

	private Api api;

	public ApiControllerCreatetor(XmlParse parse) {
		config = parse.getConfig();

		protocolFiles = parse.getProtocolFiles();
		beans = parse.getBeans();
		rescodes = parse.getRescodes();

		api = new Api();
		api.setBeans(beans);
		api.setProtocolFiles(protocolFiles);
		api.setRescodes(rescodes);
	}

	/**
	 * 创建文件
	 */
	public void createFiles() {
		try {
			File packDir = new File(
					config.getDistPath() + "/" + config.getPackageName().replace(".", File.separator) + "/api");
			if (!packDir.exists()) {
				packDir.mkdirs();
			}
			File file = new File(packDir, "api.json");
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file);
			fw.write(JSON.toJSONString(api));
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	class Api {
		List<Bean> beans;
		List<ProtocolFile> protocolFiles;
		List<Rescode> rescodes;

		public List<Bean> getBeans() {
			return beans;
		}

		public void setBeans(List<Bean> beans) {
			this.beans = beans;
		}

		public List<ProtocolFile> getProtocolFiles() {
			return protocolFiles;
		}

		public void setProtocolFiles(List<ProtocolFile> protocolFiles) {
			this.protocolFiles = protocolFiles;
		}

		public List<Rescode> getRescodes() {
			return rescodes;
		}

		public void setRescodes(List<Rescode> rescodes) {
			this.rescodes = rescodes;
		}

	}
}
