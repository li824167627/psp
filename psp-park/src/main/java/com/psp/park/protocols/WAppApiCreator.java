package com.psp.park.protocols;

import com.northend.api.go.ApiControllerCreatetor;
import com.northend.api.go.BaseResFileCreatetor;
import com.northend.api.go.BeanFileCreatetor;
import com.northend.api.go.Controller2SpringMVCCreatetor;
import com.northend.api.go.XmlParse;

public class WAppApiCreator {
	public static void main(String[] args) {
		XmlParse parse = new XmlParse();
		parse.parse("src/main/java/com/psp/park/protocols/protocols-config.xml");

		BaseResFileCreatetor bcreator = new BaseResFileCreatetor(parse);
		bcreator.createFiles();

		BeanFileCreatetor creator = new BeanFileCreatetor(parse);
		creator.createFiles();

		Controller2SpringMVCCreatetor controllerCreator = new Controller2SpringMVCCreatetor(parse);
		controllerCreator.createFiles();

		ApiControllerCreatetor apiCreatetor = new ApiControllerCreatetor(parse);
		apiCreatetor.createFiles();
	}
}
