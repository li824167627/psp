package com.northend.api.go;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.northend.api.go.bean.NotEmpty;
import com.northend.api.go.bean.Pattern;
import com.northend.api.go.bean.Protocol;
import com.northend.api.go.bean.ProtocolFile;
import com.northend.api.go.bean.RequestParam;
import com.northend.api.go.bean.ResType;
import com.northend.api.go.bean.Size;
import com.northend.api.go.type.Types;
import com.northend.api.go.util.StringUtil;

public class Controller2SpringMVCCreatetor extends ControllerCreatetor {

	public Controller2SpringMVCCreatetor(XmlParse parse) {
		super(parse);
	}

	/**
	 * 创建文件
	 */
	public void createFiles() {
		try {
			File packDir = new File(
					config.getDistPath() + "/" + config.getPackageName().replace(".", File.separator) + "/springmvc");
			if (!packDir.exists()) {
				packDir.mkdirs();
			}
			for (ProtocolFile pfile : protocolFiles) {
				if (pfile.isToCreate()) {
					File file = new File(packDir, "S" + pfile.getClassName() + ".java");
					if (!file.exists()) {
						file.createNewFile();
					}

					FileWriter fw = new FileWriter(file);
					fw.write(createJavaClass(pfile));
					fw.close();
				}
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
	private String createJavaClass(ProtocolFile pFile) {
		StringBuffer sb = new StringBuffer();
		// package
		sb.append("package " + config.getPackageName() + ".springmvc;\n\n");

		// import
		sb.append("import javax.servlet.http.HttpServletRequest;\n");
		sb.append("import javax.servlet.http.HttpServletResponse;\n");

		sb.append("import org.springframework.validation.BindingResult;\n");
		sb.append("import org.springframework.validation.annotation.Validated;\n");
		sb.append("import org.springframework.beans.factory.annotation.Autowired;\n");
		sb.append("import org.springframework.stereotype.Controller;\n");
		sb.append("import org.springframework.web.bind.annotation.RequestMapping;\n");
		sb.append("import org.springframework.web.bind.annotation.ResponseBody;\n");

		sb.append("import java.util.*;\n");

		sb.append(String.format("import %s.res.*;\n", config.getPackageName()));
		sb.append(String.format("import %s.%s.*;\n", config.getPackageName(),
				config.getResPackageName().replaceAll("/", ".")));
		sb.append(String.format("import %s.springmvc.%s.*;\n", config.getPackageName(), config.getReqPackageName()));

		sb.append("\n/**" + "\n");
		sb.append(" * " + pFile.getNotes() + "\n");
		sb.append(" **/" + "\n");
		sb.append("@Controller" + "\n");
		sb.append("@RequestMapping(value = \"" + pFile.getRequestMapping() + "\", produces = \"application/json\")" + "\n");
		sb.append("public class " + "S" + pFile.getClassName() + " {" + "\n");

		sb.append("\t@Autowired\n\t" + config.getPackageName() + "." + pFile.getClassName() + " "
				+ StringUtil.toFirstLowerCase(pFile.getClassName()) + ";");
		// methods
		sb.append(createMethods(pFile, pFile.getProtocols()));

		sb.append("\n}\n");

		return sb.toString();
	}

	/**
	 * 生产方法
	 * 
	 * @param attrs
	 * @return
	 */
	private String createMethods(ProtocolFile pFile, List<Protocol> pros) {
		StringBuffer sb = new StringBuffer();
		for (Protocol pro : pros) {
			// 生成 请求
			createReqFile(pro);

			// 生成方法
			sb.append(String.format("\n\n\t/**\n\t * %s\n\t **/", pro.getNotes()));

			// requestMapping
			sb.append(String.format("\n\t@RequestMapping(\"%s\")", pro.getRequestMapping()));
			// 是否输出json
			if (getResType(pro.getResType()) == ResType.JSON) {
				sb.append("\n\t@ResponseBody");
			}
			// 是否含有参数
			if (StringUtil.isEmpty(pro.getRequestName())) {
				String format = "\n\tpublic %s %s(HttpServletRequest request, HttpServletResponse response) {\n";
				sb.append(String.format(format, getResultType(pro.getResDataType(), getType(pro.getResponseName())),
						pro.getName(), pro.getRequestName()));

				if (pro.getState() != 1) {
					// error
					sb.append(String.format("\t\t%s res = new %s();\n",
							getResultType(pro.getResDataType(), getType(pro.getResponseName())),
							getResultType(pro.getResDataType(), getType(pro.getResponseName()))));
				}
			} else {
				String format = "\n\tpublic %s %s(@Validated %s param, BindingResult error, HttpServletRequest request, HttpServletResponse response) {\n";
				sb.append(String.format(format, getResultType(pro.getResDataType(), getType(pro.getResponseName())),
						pro.getName(), pro.getRequestName()));

				// error
				sb.append(String.format("\t\t%s res = new %s();\n",
						getResultType(pro.getResDataType(), getType(pro.getResponseName())),
						getResultType(pro.getResDataType(), getType(pro.getResponseName()))));

				if (!StringUtil.isEmpty(pro.getRequestName())) {
					sb.append("\t\tif (error.hasErrors()) {\n");
					sb.append("\t\t\tres.setRescode(BaseResult.param.getCode());\n");
					sb.append("\t\t\tres.setMsg(error.getFieldError().getDefaultMessage());\n");
					sb.append("\t\t\treturn res;\n");
					sb.append("	\t}\n");
				}
			}

			// 判断接口是否完成
			if (pro.getState() != 1) {
				if ("base".equals(pro.getResDataType())) {
					sb.append(String.format("\t\tres.setMsg(%s);", pro.getResponseDemoValue()));
				} else {
					if (Types.isObject(pro.getResponseName())) {
						if ("list".equals(pro.getResDataType())) {
							sb.append(String.format("\n\t\tList<%s> data = new ArrayList<>();", pro.getResponseName()));
							sb.append(String.format("\n\t\tdata.add(new %s().getDemoValue());", pro.getResponseName()));
							sb.append("\n\t\tres.setData(data);");
						} else {
							sb.append(
									String.format("\t\tres.setData(new %s().getDemoValue());", pro.getResponseName()));
						}
					} else {
						sb.append(String.format("\t\tres.setData(%s);",
								Types.getValue(pro.getResponseName(), pro.getResponseDemoValue())));
					}
				}
				sb.append("\n\t\treturn res;");
			} else {
				if (StringUtil.isEmpty(pro.getRequestName())) {
					sb.append(String.format("\n\t\treturn %s.%s(request, response);",
							StringUtil.toFirstLowerCase(pFile.getClassName()), pro.getName()));
				} else {
					sb.append(String.format("\n\t\treturn %s.%s(param, request, response);",
							StringUtil.toFirstLowerCase(pFile.getClassName()), pro.getName()));

				}
			}

			sb.append("\n\t}");
		}
		return sb.toString();

	}

	/**
	 * 生成请求类
	 * 
	 * @return
	 */
	private void createReqFile(Protocol pro) {
		try {
			if (!StringUtil.isEmpty(pro.getRequestName())) {
				File packDir = new File(
						config.getDistPath() + "/" + config.getPackageName().replace(".", File.separator)
								+ "/springmvc/" + config.getReqPackageName());
				if (!packDir.exists()) {
					packDir.mkdirs();
				}
				File file = new File(packDir, pro.getRequestName() + ".java");
				if (!file.exists()) {
					file.createNewFile();
				}

				FileWriter fw = new FileWriter(file);
				fw.write(createReqJavaClass(pro));
				fw.close();
			}
		} catch (Exception e) {
		}
	}

	/**
	 * 生成请求参数类
	 * 
	 * @param pro
	 * @return
	 */
	private String createReqJavaClass(Protocol pro) {
		StringBuffer sb = new StringBuffer();
		// package
		sb.append("package " + config.getPackageName() + ".springmvc." + config.getReqPackageName() + ";\n\n");
		sb.append(createReqImports(pro.getRequest()));
		sb.append("\n\n/**" + "\n");
		sb.append(" * " + pro.getNotes() + "\n");
		sb.append(" **/" + "\n");

		sb.append("public class " + pro.getRequestName() + " {" + "\n");

		// attrs
		sb.append(createAttrs(pro.getRequest()));

		sb.append("\n}\n");

		return sb.toString();
	}

	/**
	 * 生产属性
	 * 
	 * @param attrs
	 * @return
	 */
	private String createAttrs(List<RequestParam> attrs) {
		StringBuffer sb = new StringBuffer();
		for (RequestParam attr : attrs) {
			// 提示验证
			sb.append(createReqValiedAttr(attr));

			// 属性
			sb.append("\tprivate " + getType(attr.getType()) + " " + attr.getName());
			if (attr.getDefValue() != null) {
				sb.append(" = " + getValue(attr.getType(), attr.getDefValue()));
			}
			sb.append("; // " + attr.getNotes() + "\n");
		}
		// 生产get set
		for (RequestParam attr : attrs) {
			sb.append(createSetMethod(attr));
			sb.append(createGetMethod(attr));
		}
		return sb.toString();
	}

	/**
	 * 创建请求import
	 * 
	 * @param attr
	 * @return
	 */
	private String createReqImports(List<RequestParam> attrs) {
		StringBuffer sb = new StringBuffer();

		NotEmpty notEmpty = null;
		Size size = null;
		Pattern pattern = null;

		boolean hasNE = false, hasS = false, hasP = false;
		for (RequestParam attr : attrs) {
			notEmpty = attr.getNotEmpty();
			size = attr.getSize();
			pattern = attr.getPattern();

			if (notEmpty != null && !hasNE) {
				sb.append("\nimport org.hibernate.validator.constraints.NotEmpty;");
				hasNE = true;
			}

			if (pattern != null && !hasP) {
				sb.append("\nimport javax.validation.constraints.Pattern;");
				hasP = true;
			}

			if (size != null && !hasS) {
				sb.append("\nimport javax.validation.constraints.Size;");
				hasS = true;
			}
		}

		return sb.toString();
	}

	/**
	 * 生成验证属性
	 * 
	 * @param attr
	 * @return
	 */
	private String createReqValiedAttr(RequestParam attr) {
		StringBuffer sb = new StringBuffer();
		NotEmpty notEmpty = attr.getNotEmpty();
		Size size = attr.getSize();
		Pattern pattern = attr.getPattern();

		if (notEmpty != null) {
			sb.append(String.format("\t@NotEmpty(message = \"%s\")\n", notEmpty.getMessage()));
		}

		if (size != null) {
			sb.append("\t@Size(");
			if (size.getMax() != Integer.MAX_VALUE)
				sb.append(String.format("min = %s, ", size.getMin()));
			if (size.getMax() != Integer.MAX_VALUE)
				sb.append(String.format("max = %s, ", size.getMax()));
			sb.append(String.format("message = \"%s\")\n", size.getMessage()));
		}

		if (pattern != null) {
			sb.append(String.format("\t@Pattern(regexp = \"%s\", message = \"%s\")\n", pattern.getRegexp(),
					pattern.getMessage()));
		}

		return sb.toString();
	}

	/**
	 * 生成set方法
	 * 
	 * @return
	 */
	private String createSetMethod(RequestParam attr) {
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
	private String createGetMethod(RequestParam attr) {
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

	/**
	 * 获取返回类型
	 * 
	 * @param type
	 * @param value
	 * @return
	 */
	private String getResultType(String type, String value) {
		if ("object".equalsIgnoreCase(type)) {
			return "ObjectResult<" + value + ">";
		} else if ("list".equalsIgnoreCase(type)) {
			return "ListResult<" + value + ">";
		} else {
			return "BaseResult";
		}
	}

}
