package com.psp.web.controller.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.psp.web.constants.StringConstants;

@Controller
@RequestMapping("/api")
public class ApiController {
	private Logger logger = Logger.getLogger(this.getClass());

	@RequestMapping("/getData")
	public void readApi(HttpServletResponse response) {
		logger.info("api:" + getClass().getResource("/"));
		String apipath = StringConstants.getInstance().getString("api_path");
		logger.info("api path:" + apipath);
		String path = getClass().getResource(apipath + "controller/api/api.json").getFile();
		File file = new File(path);
		if (file.exists()) {
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(file);
				byte[] buf = new byte[1024];
				int len = -1;
				while ((len = fis.read(buf)) != -1) {
					response.getOutputStream().write(buf, 0, len);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (fis != null) {
					try {
						fis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
