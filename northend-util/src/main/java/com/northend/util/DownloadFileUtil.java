package com.northend.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;

public class DownloadFileUtil {

	/**
	 * 
	 * @param files
	 *            网络图片url
	 * @param os
	 *            响应输出(图片jpg，打包zip)
	 * @throws IOException
	 */
	public static void downloadZipImage(String[] files, OutputStream os) throws IOException {
		ZipOutputStream zos = new ZipOutputStream(os);
		try {
			for (int i = 0; i < files.length; i++) {
				URL url = new URL(files[i]);
				zos.putNextEntry(new ZipEntry(i + ".jpg"));
				InputStream fis = url.openConnection().getInputStream();
				byte[] buffer = new byte[1024];
				int r = 0;
				while ((r = fis.read(buffer)) != -1) {
					zos.write(buffer, 0, r);
				}
				fis.close();
			}

		} catch (Exception e1) {
			System.out.println("连接打不开!");
			throw e1;
		} finally {
			zos.flush();
			zos.close();
		}

	}

	/**
	 * 浏览器直接下载文件<br>
	 *
	 * @param request
	 * @param response
	 * @param file
	 *            文件
	 * @throws IOException
	 */
	public static void downloadFile(HttpServletRequest request, HttpServletResponse response, File file) {
		if (file == null || !file.exists()) {
			return;
		}
		String fileName = file.getName();
		ServletOutputStream sos = null;
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		try {
			sos = response.getOutputStream();
			buffer.write(FileUtils.readFileToByteArray(file));

			// 解决前台显示下文件名乱码问题
			if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > -1) {
				// 火狐浏览器
				fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
			} else if (request.getHeader("User-Agent").toLowerCase().indexOf("safari") > -1) {
				// Safari 浏览器
				fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
			} else {
				// IE
				fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
			}
			response.setContentType("applcation/vnd.ms-excel");
			response.setContentLengthLong(buffer.size());
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
			response.setHeader("Pragma", "public");
			response.setHeader("Cache-Control", "max-age=0");
			sos.write(buffer.toByteArray());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (buffer != null) {
					buffer.flush();
					buffer.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (sos != null) {
					sos.flush();
					sos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获取项目所在服务器的绝对路径
	 * 如：/usr/local/tomcat/tomcat8088/apache-tomcat-8.5.15/webapps/security-plot
	 * 
	 * @param request
	 * @return
	 */
	public static String getServerPath(HttpServletRequest request) {
		String realPath = request.getSession().getServletContext().getRealPath(File.separator);
		String path = realPath.substring(0, realPath.lastIndexOf(File.separator));
		return path;
	}

}
