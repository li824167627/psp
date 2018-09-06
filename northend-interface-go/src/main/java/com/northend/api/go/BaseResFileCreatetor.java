package com.northend.api.go;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.northend.api.go.bean.BeanAttribute;
import com.northend.api.go.bean.ProtocolConfig;
import com.northend.api.go.bean.ResBean;
import com.northend.api.go.type.Types;
import com.northend.api.go.util.StringUtil;

public class BaseResFileCreatetor {
	private ProtocolConfig config;

	public BaseResFileCreatetor(XmlParse parse) {
		config = parse.getConfig();
	}

	/**
	 * 创建文件
	 */
	public void createFiles() {
		if (!config.getResBean().isToCreate())
			return;
		try {
			File packDir = new File(config.getDistPath() + "/" + config.getPackageName().replace(".", File.separator)
					+ File.separator + "res");
			if (!packDir.exists()) {
				packDir.mkdirs();
			}

			String[] names = new String[] { "BaseResult", "ObjectResult", "ListResult" };
			for (String name : names) {
				File file = new File(packDir, name + ".java");
				if (!file.exists()) {
					file.createNewFile();
				}

				FileWriter fw = new FileWriter(file);
				if (name.equals("BaseResult")) {
					fw.write(createBaseResJavaClass(config.getResBean()));
				} else if (name.equals("ObjectResult")) {
					fw.write(createObjectResJavaClass(config.getResBean()));
				} else if (name.equals("ListResult")) {
					fw.write(createListResJavaClass(config.getResBean()));
				}
				fw.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 生产base res java class
	 * 
	 * @param bean
	 * @return
	 */
	private String createBaseResJavaClass(ResBean bean) {
		StringBuffer sb = new StringBuffer();
		// package
		sb.append("package " + config.getPackageName() + "." + config.getResPackageName() + ";\n\n");
		sb.append("/**" + "\n");
		sb.append(" * 基本信息返回\n");
		sb.append(" **/" + "\n");
		sb.append("public class BaseResult {" + "\n");

		sb.append("\n\tpublic final static Rescode success;");
		sb.append("\n\tpublic final static Rescode fail;");
		sb.append("\n\tpublic final static Rescode session;");
		sb.append("\n\tpublic final static Rescode param;\n");

		// attrs
		List<BeanAttribute> attrs = new ArrayList<>();
		BeanAttribute attr = new BeanAttribute();
		attr.setName(bean.getResCode());
		attr.setNotes("返回code");
		attr.setType("int");
		attrs.add(attr);

		attr = new BeanAttribute();
		attr.setName(bean.getResMsg());
		attr.setType("string");
		attr.setNotes("返回msg");
		attrs.add(attr);

		sb.append(createAttrs(attrs));

		sb.append("\n}\n");

		return sb.toString();
	}

	/**
	 * 生产object res java class
	 * 
	 * @param bean
	 * @return
	 */
	private String createObjectResJavaClass(ResBean bean) {
		StringBuffer sb = new StringBuffer();
		// package
		sb.append("package " + config.getPackageName() + "." + config.getResPackageName() + ";\n\n");
		sb.append("/**" + "\n");
		sb.append(" * 对象信息返回\n");
		sb.append(" **/" + "\n");
		sb.append("public class ObjectResult<T> extends BaseResult {" + "\n");
		// attrs

		List<BeanAttribute> attrs = new ArrayList<>();
		BeanAttribute attr = new BeanAttribute();

		attr = new BeanAttribute();
		attr.setName(bean.getResData());
		attr.setType("T");
		attr.setNotes("返回对象数据，泛型");
		attrs.add(attr);

		sb.append(createAttrs(attrs));

		sb.append("\n}\n");

		return sb.toString();
	}

	/**
	 * 生产object res java class
	 * 
	 * @param bean
	 * @return
	 */
	private String createListResJavaClass(ResBean bean) {
		StringBuffer sb = new StringBuffer();
		// package
		sb.append("package " + config.getPackageName() + "." + config.getResPackageName() + ";\n\n");
		sb.append("import java.util.List;");

		sb.append("\n\n/**" + "\n");
		sb.append(" * 数组信息返回\n");
		sb.append(" **/" + "\n");
		sb.append("public class ListResult<T> extends BaseResult {" + "\n");
		// attrs

		List<BeanAttribute> attrs = new ArrayList<>();
		BeanAttribute attr = new BeanAttribute();

		attr = new BeanAttribute();
		attr.setName(bean.getResData());
		attr.setType("List<T>");
		attr.setNotes("返回List数据，泛型");
		attrs.add(attr);

		sb.append(createAttrs(attrs));

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
			sb.append("\tprivate " + getType(attr.getType()) + " " + attr.getName());
			if (attr.getDefValue() != null) {
				sb.append(" = " + getValue(attr.getType(), attr.getDefValue()));
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
	 * 生成set方法
	 * 
	 * @return
	 */
	private String createSetMethod(BeanAttribute attr) {
		StringBuffer sb = new StringBuffer();

		String setFormat = "void set%s(%s %s) {\n \t\tthis.%s = %s;" + "\n\t}";// set
		String name = attr.getName();
		String type = getType(attr.getType());
		String setStr = String.format(setFormat, StringUtil.toFirstUpperCase(name), type, name, name, name);

		sb.append("\n\tpublic " + setStr);
		sb.append("\n");

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

		String getFormat = "%s get%s() {\n \t\treturn %s;" + "\n\t}";// get
		String name = attr.getName();
		String type = getType(attr.getType());
		String getStr = String.format(getFormat, type, StringUtil.toFirstUpperCase(name), name);

		sb.append("\n\tpublic " + getStr);
		sb.append("\n");

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

}
