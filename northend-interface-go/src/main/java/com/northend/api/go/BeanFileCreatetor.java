package com.northend.api.go;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.northend.api.go.bean.Bean;
import com.northend.api.go.bean.BeanAttribute;
import com.northend.api.go.bean.ProtocolConfig;
import com.northend.api.go.type.Types;
import com.northend.api.go.util.StringUtil;

public class BeanFileCreatetor {
	private ProtocolConfig config;
	private List<Bean> beans;

	public BeanFileCreatetor(XmlParse parse) {
		config = parse.getConfig();
		beans = parse.getBeans();
	}

	/**
	 * 创建文件
	 */
	public void createFiles() {
		try {
			File packDir = new File(config.getDistPath() + "/" + config.getPackageName().replace(".", File.separator)
					+ File.separator + config.getResPackageName());
			if (!packDir.exists()) {
				packDir.mkdirs();
			}

			for (Bean bean : beans) {
				File file = new File(packDir, bean.getClassName() + ".java");
				if (!file.exists()) {
					file.createNewFile();
				}

				FileWriter fw = new FileWriter(file);
				fw.write(createJavaClass(bean));
				fw.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 生产Java class
	 * 
	 * @param bean
	 * @return
	 */
	private String createJavaClass(Bean bean) {
		StringBuffer sb = new StringBuffer();
		// package
		sb.append(
				"package " + config.getPackageName() + "." + config.getResPackageName().replaceAll("/", ".") + ";\n\n");
		String listImport = createListImport(bean);
		String mapImport = createMapImport(bean);
		String jsonArrayImport = createJsonArrayImport(bean);

		if (listImport != null) {
			sb.append(listImport);
		}

		if (mapImport != null) {
			sb.append(mapImport);
		}

		if (jsonArrayImport != null) {
			sb.append(jsonArrayImport);
		}

		// sb.append("import com.fasterxml.jackson.annotation.JsonIgnore;\n");
		sb.append("\n/**" + "\n");
		sb.append(" * " + bean.getNotes() + "\n");
		sb.append(" **/" + "\n");
		sb.append("public class " + bean.getClassName() + " {" + "\n");
		// attrs

		sb.append(createAttrs(bean.getAttrs()));
		// sb.append(createDemoValue(bean));

		sb.append("\n}\n");

		return sb.toString();
	}

	/**
	 * 生产属性
	 * 
	 * @param attrs
	 * @return
	 */
	private String createAttrs(List<BeanAttribute> attrs) {
		StringBuffer sb = new StringBuffer();
		for (BeanAttribute attr : attrs) {

			if (Types.isList(attr.getType())) {// list
				sb.append(String.format("\tprivate List<%s> %s", attr.getObjName(), attr.getName()));
			} else if (Types.isMap(attr.getType())) {
				sb.append(String.format("\tprivate Map<%s> %s", attr.getObjName(), attr.getName()));
			} else if (Types.isJsonArray(attr.getType())) {
				sb.append("\tprivate JSONArray " + attr.getName());
			} else {
				sb.append("\tprivate " + getType(attr.getType()) + " " + attr.getName());
				if (attr.getDefValue() != null) {
					sb.append(" = " + getValue(attr.getType(), attr.getDefValue()));
				}
			}
			sb.append("; // " + attr.getNotes() + "\n");
		}
		// 生产get set
		for (BeanAttribute attr : attrs) {
			sb.append(createSetMethod(attr));
			sb.append(createGetMethod(attr));
		}
		return sb.toString();
	}

	/**
	 * 生成假数据
	 * 
	 * @return
	 */
	private String createDemoValue(Bean bean) {
		StringBuffer sb = new StringBuffer();
		sb.append("\n\t@JsonIgnore");
		sb.append("\n\tpublic " + bean.getClassName() + " getDemoValue() {");

		String onObjFormat = "\n\t\tthis.%s = %s;";

		String objFormat = "\n\t\tthis.%s = new %s().getDemoValue();";

		String listFormat = "\n\t\tthis.%s = new ArrayList<>();\n\t\tthis.%s.add(new %s().getDemoValue());";

		String listFormat2 = "\n\t\tthis.%s = new ArrayList<>();";

		String jsonArrayFormat = "\n\t\tthis.%s = JSON.parseArray(\"%s\")";

		for (BeanAttribute attr : bean.getAttrs()) {
			if (Types.isObject(attr.getType())) {
				if (Types.isList(attr.getType())) {
					if (attr.getObjName().equals(bean.getClassName())) {
						sb.append(String.format(listFormat2, attr.getName()));
					} else {
						sb.append(String.format(listFormat, attr.getName(), attr.getName(), attr.getObjName()));
					}
				} else {
					sb.append(String.format(objFormat, attr.getName(), attr.getType()));
				}
			} else if (Types.isJsonArray(attr.getType())) {
				sb.append(String.format(jsonArrayFormat, attr.getName(), attr.getDemoValue()));
			} else {
				sb.append(String.format(onObjFormat, attr.getName(), getValue(attr.getType(), attr.getDemoValue())));
			}
		}

		sb.append("\n\t\treturn this;");
		sb.append("\n\t}");

		return sb.toString();
	}

	/**
	 * 生成set方法
	 * 
	 * @return
	 */
	private String createSetMethod(BeanAttribute attr) {
		StringBuffer sb = new StringBuffer();
		if (Types.isList(attr.getType())) {
			String setFormat = "void set%s(List<%s> %s) {\n \t\tthis.%s = %s;" + "\n\t}";// set
			String name = attr.getName();
			String setStr = String.format(setFormat, StringUtil.toFirstUpperCase(name), attr.getObjName(), name, name,
					name);

			sb.append("\n\tpublic " + setStr);
			sb.append("\n");
		} else if (Types.isMap(attr.getType())) {
			String setFormat = "void set%s(Map<%s> %s) {\n \t\tthis.%s = %s;" + "\n\t}";// set
			String name = attr.getName();
			String setStr = String.format(setFormat, StringUtil.toFirstUpperCase(name), attr.getObjName(), name, name,
					name);

			sb.append("\n\tpublic " + setStr);
			sb.append("\n");
		} else if (Types.isJsonArray(attr.getType())) {
			String setFormat = "void set%s(JSONArray %s) {\n \t\tthis.%s = %s;" + "\n\t}";// set
			String name = attr.getName();
			String setStr = String.format(setFormat, StringUtil.toFirstUpperCase(name), name, name, name, name);

			sb.append("\n\tpublic " + setStr);
			sb.append("\n");
		} else {
			String setFormat = "void set%s(%s %s) {\n \t\tthis.%s = %s;" + "\n\t}";// set
			String name = attr.getName();
			String type = getType(attr.getType());
			String setStr = String.format(setFormat, StringUtil.toFirstUpperCase(name), type, name, name, name);

			sb.append("\n\tpublic " + setStr);
			sb.append("\n");
		}

		return sb.toString();
	}

	/**
	 * 生成get方法
	 * 
	 * @param attr
	 * @return
	 */
	private String createGetMethod(BeanAttribute attr) {
		StringBuffer sb = new StringBuffer();
		if (Types.isList(attr.getType())) {
			String getFormat = "List<%s> get%s() {\n \t\treturn %s;" + "\n\t}";// get
			String name = attr.getName();
			String getStr = String.format(getFormat, attr.getObjName(), StringUtil.toFirstUpperCase(name), name);

			sb.append("\n\tpublic " + getStr);
			sb.append("\n");
		} else if (Types.isMap(attr.getType())) {
			String getFormat = "Map<%s> get%s() {\n \t\treturn %s;" + "\n\t}";// get
			String name = attr.getName();
			String getStr = String.format(getFormat, attr.getObjName(), StringUtil.toFirstUpperCase(name), name);

			sb.append("\n\tpublic " + getStr);
			sb.append("\n");
		} else if (Types.isJsonArray(attr.getType())) {
			String getFormat = "JSONArray get%s() {\n \t\treturn %s;" + "\n\t}";// get
			String name = attr.getName();
			String getStr = String.format(getFormat, StringUtil.toFirstUpperCase(name), name);

			sb.append("\n\tpublic " + getStr);
			sb.append("\n");
		} else {
			String getFormat = "%s get%s() {\n \t\treturn %s;" + "\n\t}";// get
			String name = attr.getName();
			String type = getType(attr.getType());
			String getStr = String.format(getFormat, type, StringUtil.toFirstUpperCase(name), name);

			sb.append("\n\tpublic " + getStr);
			sb.append("\n");
		}
		return sb.toString();
	}

	/**
	 * 获取类型
	 * 
	 * @param type
	 * @return
	 */
	private String getType(String type) {
		if (!Types.isExist(type)) {
			return type;
		} else {
			return Types.getType(type)[1];
		}
	}

	/**
	 * 获取值
	 * 
	 * @param type
	 * @param value
	 * @return
	 */
	private String getValue(String type, String value) {
		if (!Types.isExist(type)) {
			return value;
		} else {
			return Types.getValue(type, value);
		}
	}

	private String createListImport(Bean bean) {
		for (BeanAttribute attr : bean.getAttrs()) {
			if (Types.isList(attr.getType())) {
				return "import java.util.List;\nimport java.util.ArrayList;\n";
			}
		}
		return null;
	}

	private String createMapImport(Bean bean) {
		for (BeanAttribute attr : bean.getAttrs()) {
			if (Types.isMap(attr.getType())) {
				return "import java.util.Map;\n";
			}
		}
		return null;
	}

	private String createJsonArrayImport(Bean bean) {
		for (BeanAttribute attr : bean.getAttrs()) {
			if (Types.isJsonArray(attr.getType())) {
				return "\nimport com.alibaba.fastjson.JSONArray;\n";
			}
		}
		return null;
	}

}
