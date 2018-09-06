package com.northend.api.go;

import java.util.List;

import com.northend.api.go.bean.ProtocolConfig;
import com.northend.api.go.bean.ProtocolFile;
import com.northend.api.go.bean.ResDataType;
import com.northend.api.go.bean.ResType;

public abstract class ControllerCreatetor {
	protected ProtocolConfig config;
	protected List<ProtocolFile> protocolFiles;

	public ControllerCreatetor(XmlParse parse) {
		config = parse.getConfig();
		protocolFiles = parse.getProtocolFiles();
	}

	/**
	 * 创建文件
	 */
	public abstract void createFiles();

	/**
	 * 返回输出类型
	 * 
	 * @param type
	 * @return
	 */
	protected ResType getResType(String type) {
		if (type == null) {
			return ResType.JSON;
		} else if (type.equalsIgnoreCase("json")) {
			return ResType.JSON;
		} else if (type.equalsIgnoreCase("xml")) {
			return ResType.XML;
		} else if (type.equalsIgnoreCase("view")) {
			return ResType.View;
		} else if (type.equalsIgnoreCase("string")) {
			return ResType.String;
		} else {
			return ResType.JSON;
		}
	}

	/**
	 * 返回输出是base list object
	 * 
	 * @param type
	 * @return
	 */
	protected ResDataType getResDataType(String type) {
		if (type == null) {
			return ResDataType.Base;
		} else if (type.equalsIgnoreCase("object")) {
			return ResDataType.Object;
		} else if (type.equalsIgnoreCase("list")) {
			return ResDataType.List;
		} else {
			return ResDataType.Base;
		}
	}
}
