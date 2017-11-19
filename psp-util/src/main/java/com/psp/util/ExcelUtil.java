package com.psp.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * excel 工具类
 * 
 * @author 车库互联网+ 2016年9月19日
 */
public class ExcelUtil {
	
	
	

	/**
	 * 通过流的方式生成 Excel 文件
	 *
	 * 使用调用 String filename = "二次邮寄清单_"+""+".xls"; HSSFWorkbook wb =
	 * ExcelUtil.exportExcelUtil("测试报表", c, list, "", 1);
	 * ExcelUtil.runExcelFileExport(request,response, wb, filename);
	 * 
	 * @author 车库互联网+ 2016年9月19日
	 * @param request
	 * @param response
	 * @param wb
	 * @param fileName
	 * @throws IOException
	 */
	public static void runExcelFileExport(HttpServletRequest request, HttpServletResponse response, HSSFWorkbook wb,
			String fileName) {

		ServletOutputStream sos = null;
		ByteArrayOutputStream buffer = null;
		try {
			sos = response.getOutputStream();
			buffer = new ByteArrayOutputStream();
			wb.write(buffer);

			// 解决前台显示下文件名乱码问题
			if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > -1) {
				// 火狐浏览器
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
	 * 
	 * 生成Excel公共方法 附带公式
	 * 
	 * @param title
	 *            文件的标题名称
	 * @param colName
	 *            列名 String[]
	 * @param colTitle
	 *            查询出数据对应的列标名 String[]
	 * @param list
	 *            数据列表List<Map<String,Object>>
	 * @param display
	 * @param auto
	 *            自动对齐 1中 2右 否则左
	 * @return
	 */
	public static HSSFWorkbook exportExcelUtil(String title, String[] colName, String[] colTitle,
			List<Map<String, Object>> list, String display, int auto) {
		HSSFWorkbook workbook = null;
		try {
			workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("sheet1");
			// 设置行的大标题
			setRowBigTitle(title, colName, workbook, sheet);
			// 设置行标题
			setRowTitle(colName, auto, workbook, sheet);
			// 设置行数据
			setRowData(colTitle, list, auto, workbook, sheet);

//			workbook = setRegionStyle(workbook);// 设置和并单元格样式

		} catch (Exception e) {
			e.printStackTrace();
		}
		return workbook;
	}

	

	private static void setRowBigTitle(String title, String[] colName, HSSFWorkbook workbook, HSSFSheet sheet) {
		// 设置表头
		// 创建合并单元格的第一个单元格数据第一行 大标题
		HSSFRow rowBigTitle = sheet.createRow(0);
		rowBigTitle.setHeight((short) (15.625 * 40));
		HSSFCell rowBigTitleCell_0 = rowBigTitle.createCell(0);
		HSSFRichTextString rowBigTitleTextString = new HSSFRichTextString(title);

		HSSFFont rowBigTitleFont = workbook.createFont();
		rowBigTitleFont.setFontHeightInPoints((short) 16);
		rowBigTitleFont.setBold(true); // 字体加粗
		rowBigTitleFont.setFontName("微软雅黑");// 字体

		HSSFCellStyle rowBigTitleCellStyle = workbook.createCellStyle();
		rowBigTitleCellStyle.setFont(rowBigTitleFont);
		rowBigTitleCellStyle.setAlignment(HorizontalAlignment.CENTER);
		rowBigTitleCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		rowBigTitleTextString.applyFont(0, rowBigTitleTextString.length(), rowBigTitleFont);
		rowBigTitleCell_0.setCellValue(rowBigTitleTextString);
		rowBigTitleCell_0.setCellStyle(rowBigTitleCellStyle);
		CellRangeAddress cRangeAddress = null;
		if (colName != null && colName.length > 0) {
			cRangeAddress = new CellRangeAddress(0, 0, 0, colName.length - 1);
		}
		sheet.addMergedRegion(cRangeAddress);
	}

	private static void setRowTitle(String[] colName, int auto, HSSFWorkbook workbook, HSSFSheet sheet) {
		// 列标题显示设置
		HSSFFont rowTitleFont = workbook.createFont();
		rowTitleFont.setBold(true);
		rowTitleFont.setFontHeightInPoints((short) 12); // 字体大小
		rowTitleFont.setFontName("宋体");
		HSSFCellStyle rowTitleCellStyle = workbook.createCellStyle();
		rowTitleCellStyle.setFont(rowTitleFont);
		rowTitleCellStyle.setAlignment(HorizontalAlignment.CENTER); // 让合并后的单元格内容居中
		rowTitleCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		// 设置上下左右四个边框宽度
		rowTitleCellStyle.setBorderTop(BorderStyle.THIN);
		rowTitleCellStyle.setBorderBottom(BorderStyle.THIN);
		rowTitleCellStyle.setBorderLeft(BorderStyle.THIN);
		rowTitleCellStyle.setBorderRight(BorderStyle.THIN);
		// 设置上下左右四个边框颜色
		rowTitleCellStyle.setTopBorderColor(HSSFColorPredefined.BLACK.getIndex());
		rowTitleCellStyle.setBottomBorderColor(HSSFColorPredefined.BLACK.getIndex());
		rowTitleCellStyle.setLeftBorderColor(HSSFColorPredefined.BLACK.getIndex());
		rowTitleCellStyle.setRightBorderColor(HSSFColorPredefined.BLACK.getIndex());

		HSSFRow rowTitle = sheet.createRow(1);
		rowTitle.setHeight((short) (15.625 * 20));
		HSSFCell rowTitleCell = rowTitle.createCell(0);
		rowTitleCell.setCellStyle(rowTitleCellStyle);
		rowTitleCell.setCellType(HSSFCell.CELL_TYPE_STRING);

		// 根据条件显示对其方式(自动选择)
		HSSFCellStyle cellStyleAuto = workbook.createCellStyle();
		if (1 == auto) {
			cellStyleAuto.setAlignment(HorizontalAlignment.CENTER);
		} else if (2 == auto) {
			cellStyleAuto.setAlignment(HorizontalAlignment.RIGHT);
		} else {
			cellStyleAuto.setAlignment(HorizontalAlignment.LEFT);
		}
		cellStyleAuto.setBorderBottom(BorderStyle.MEDIUM);

		// 标题设置
		HSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeightInPoints((short) 18); // 字体大小
		font.setFontName("宋体");
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setFont(font);
		cellStyle.setAlignment(HorizontalAlignment.CENTER); // 让合并后的单元格内容居中
		cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

		// 列标题名称及样式设置
		HSSFRow row = sheet.createRow(1);
		for (int i = 0; i < colName.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellStyle(rowTitleCellStyle);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(colName[i]);

			// 调整列宽度
			sheet.autoSizeColumn((short) i);
		}
	}
	
	private static void setRowData(String[] colTitle, List<Map<String, Object>> list, int auto, HSSFWorkbook workbook,
			HSSFSheet sheet) {
		// 根据条件显示对其方式(自动选择)
		HSSFCellStyle cellStyleAuto = workbook.createCellStyle();
		if (1 == auto) {
			cellStyleAuto.setAlignment(HorizontalAlignment.CENTER);
		} else if (2 == auto) {
			cellStyleAuto.setAlignment(HorizontalAlignment.RIGHT);
		} else {
			cellStyleAuto.setAlignment(HorizontalAlignment.LEFT);
		}
		cellStyleAuto.setBorderBottom(BorderStyle.MEDIUM);

		// 数据行样式
		HSSFFont dataRowCellFont = workbook.createFont();
		dataRowCellFont.setBold(true);
		dataRowCellFont.setFontHeightInPoints((short) 12); // 字体大小
		dataRowCellFont.setFontName("宋体");
		HSSFCellStyle dataRowCellStyle = workbook.createCellStyle();
		dataRowCellStyle.setFont(dataRowCellFont);
		dataRowCellStyle.setAlignment(HorizontalAlignment.CENTER); // 内容居中

		// 每列的值设值
		if (list != null && list.size() > 0) {
			Iterator<Map<String, Object>> iterator = list.iterator();
			int dataRowIndex = 2;
			while (iterator.hasNext()) {
				Map<String, Object> map = iterator.next();
				HSSFRow dataRow = sheet.createRow(dataRowIndex);
				dataRow.setHeight((short) (15.625 * 20));
				for (int j = 0; j < map.size(); j++) {
					sheet.setColumnWidth(j, (250 * 10));
					HSSFCell cell = dataRow.createCell(j);
					cell.setCellStyle(dataRowCellStyle);
					cell.setCellValue(objToStr(map.get(colTitle[j])));
				}

				dataRowIndex++;
			}
		}
	}

	/**
	 * 设置合并单元格 样式
	 * 
	 * @param workbook
	 * @style 单元格样式
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static HSSFWorkbook setRegionStyle(HSSFWorkbook workbook) throws IOException, ClassNotFoundException {
		// 获取第一张sheet
		HSSFSheet sheet = workbook.getSheet("Sheet1");
		// 准备样式
		HSSFCellStyle style = createCellStyle(workbook);
		// 循环所有的合并单元格
		for (int numMR = 0; numMR < sheet.getNumMergedRegions(); numMR++) {
			// 获取合并单元格
			CellRangeAddress region = (CellRangeAddress) sheet.getMergedRegion(numMR);
			// 获取合并单元格每一个单元格
			for (int rownum = region.getFirstRow(); rownum <= region.getLastRow(); rownum++) {
				HSSFRow row = sheet.getRow(rownum);
				for (int cellnum = region.getFirstColumn(); cellnum <= region.getLastColumn(); cellnum++) {
					if (row != null) {
						HSSFCell cell = row.getCell(cellnum);
						if (cell == null) {
							cell = row.createCell(cellnum);
						}
						cell.setCellStyle(style);
					}

				}
			}
		}
		return workbook;
	}

	/**
	 * * 设置单元格的边框（细）且为黑色 *
	 * 
	 * @param workbook
	 *            *
	 * @param cellnum
	 *            *
	 * @return
	 */
	public static HSSFCellStyle createCellStyle(HSSFWorkbook workbook) {
		HSSFCellStyle style = workbook.createCellStyle();
		// 设置上下左右四个边框宽度
		style.setBorderTop(BorderStyle.THIN);
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		// 设置上下左右四个边框颜色
		style.setTopBorderColor(HSSFColor.BLACK.index);
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		style.setRightBorderColor(HSSFColor.BLACK.index);
		// 设置字体格式
		HSSFFont font = workbook.createFont();
		font.setFontName("微软雅黑");
		font.setFontHeightInPoints((short) 11);
		font.setColor(HSSFColor.BLACK.index);
		// 将字体格式设置到HSSFCellStyle上
		style.setFont(font);
		// 设置单元格居中方式
		// style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//水平居中且垂直居中
		style.setAlignment(HorizontalAlignment.LEFT);
		style.setWrapText(true);
		return style;
	}
	
	
	/**
	 * 导出Excel 居中大标题加黑加粗，居中列标题加黑加粗。内容数据左对齐，加边框黑色。 START
	 * 
	 * @param title
	 * @param cloTitle
	 *            列名 String[]
	 * @param list
	 *            数据列表List<Object>
	 * @param display
	 * @param auto
	 *            自动对齐 1中 2右 否则左
	 * @return 20151020
	 */
	public static HSSFWorkbook exportExcelIndex(String title, String[] cloTitle, List list, String display, int auto) {
		HSSFWorkbook workbook = null;
		try {
			workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("sheet1");

			// 设置表头
			// 创建合并单元格的第一个单元格数据 第一行
			HSSFRow row0 = sheet.createRow(0);
			row0.setHeight((short) (15.625 * 40)); // 设置行高
			HSSFCell c0 = row0.createCell(0);
			HSSFRichTextString ts = new HSSFRichTextString(title);
			HSSFFont font0_1 = workbook.createFont();
			font0_1.setFontHeightInPoints((short) 16);
			font0_1.setBold(true);
			font0_1.setFontName("微软雅黑");// 字体
			HSSFCellStyle cellStyleTiele = workbook.createCellStyle();
			cellStyleTiele.setFont(font0_1);
			cellStyleTiele.setVerticalAlignment(VerticalAlignment.CENTER);
			cellStyleTiele.setAlignment(HorizontalAlignment.CENTER); // 让合并后的单元格内容居中
			cellStyleTiele.setVerticalAlignment(VerticalAlignment.JUSTIFY);
			ts.applyFont(0, ts.length(), font0_1);
			c0.setCellValue(ts);
			c0.setCellStyle(cellStyleTiele);
			CellRangeAddress cRangeAddress = null;
			if (cloTitle != null && cloTitle.length > 0) {
				cRangeAddress = new CellRangeAddress(0, 0, 0, cloTitle.length - 1);
			}
			sheet.addMergedRegion(cRangeAddress);

			// 列标题样式设置
			HSSFFont fontT = workbook.createFont();
			fontT.setBold(true);
			fontT.setFontHeightInPoints((short) 12); // 字体大小
			fontT.setFontName("宋体");
			HSSFCellStyle cellStyleT = workbook.createCellStyle();
			cellStyleT.setFont(fontT);
			cellStyleT.setAlignment(HorizontalAlignment.CENTER); // 让合并后的单元格内容居中
			cellStyleT.setVerticalAlignment(VerticalAlignment.CENTER);
			// 设置上下左右四个边框宽度
			cellStyleT.setBorderTop(BorderStyle.THIN);
			cellStyleT.setBorderBottom(BorderStyle.THIN);
			cellStyleT.setBorderLeft(BorderStyle.THIN);
			cellStyleT.setBorderRight(BorderStyle.THIN);
			// 设置上下左右四个边框颜色
			cellStyleT.setTopBorderColor(HSSFColor.BLACK.index);
			cellStyleT.setBottomBorderColor(HSSFColor.BLACK.index);
			cellStyleT.setLeftBorderColor(HSSFColor.BLACK.index);
			cellStyleT.setRightBorderColor(HSSFColor.BLACK.index);
			HSSFRow row0T = sheet.createRow(1);
			row0T.setHeight((short) (15.625 * 20));
			HSSFCell cellTitleT = row0T.createCell((short) 0);
			cellTitleT.setCellStyle(cellStyleT);
			cellTitleT.setCellType(HSSFCell.CELL_TYPE_STRING);
			// cellTitleT.setEncoding(HSSFCell.ENCODING_UTF_16);//显示中文

			// 设置序号列的样式
			HSSFFont font1 = workbook.createFont();
			font1.setBold(true);
			font1.setFontHeightInPoints((short) 12); // 字体大小
			font1.setFontName("宋体");
			HSSFCellStyle cellStyle1 = workbook.createCellStyle();
			cellStyle1.setFont(font1);
			cellStyle1.setAlignment(HorizontalAlignment.CENTER); // 内容居中

			// 右对齐
			HSSFCellStyle cellStyleRight = workbook.createCellStyle();
			cellStyleRight.setAlignment(HorizontalAlignment.RIGHT);
			cellStyleRight.setBorderBottom(BorderStyle.MEDIUM);

			// 根据条件显示对其方式(自动选择)
			HSSFCellStyle cellStyleAuto = workbook.createCellStyle();
			if (1 == auto) {
				cellStyleAuto.setAlignment(HorizontalAlignment.CENTER);
			} else if (2 == auto) {
				cellStyleAuto.setAlignment(HorizontalAlignment.RIGHT);
			} else {
				cellStyleAuto.setAlignment(HorizontalAlignment.LEFT);
			}
			cellStyleAuto.setBorderBottom(BorderStyle.MEDIUM);

			// 大标题设置
			HSSFFont font = workbook.createFont();
			font.setBold(true);
			font.setFontHeightInPoints((short) 18); // 字体大小
			font.setFontName("宋体");
			HSSFCellStyle cellStyle = workbook.createCellStyle();
			cellStyle.setFont(font);
			cellStyle.setAlignment(HorizontalAlignment.CENTER); // 让合并后的单元格内容居中
			cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
			
			// 列标题名称及样式设置
			HSSFRow row = sheet.createRow(1);
			for (int i = 0; i < cloTitle.length; i++) {
				HSSFCell cell = row.createCell((short) i);
				cell.setCellStyle(cellStyleT);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				// cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellValue(cloTitle[i]);
			}

			HSSFCellStyle style = createCellStyle(workbook);
			style.setAlignment(HorizontalAlignment.LEFT);
			// 每列的值设值
			for (int i = 0; i < list.size(); i++) {
				Object[] obj = (Object[]) list.get(i);
				HSSFRow row2 = sheet.createRow(i + 2); // 创建行
				row2.setHeight((short) (15.625 * 20));

				for (int j = 0; j < obj.length; j++) {
					sheet.setColumnWidth((short) j, (short) ((short) 250 * 10));
					HSSFCell cell = row2.createCell((short) j);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					// cell.setEncoding(HSSFCell.ENCODING_UTF_16);
					cell.setCellStyle(style);
					cell.setCellValue(objToStr(obj[j]));
				}
			}

			// 调整列宽度
			for (int i = 0; i < cloTitle.length; i++) {
				sheet.autoSizeColumn((short) i);
			}

//			workbook = setRegionStyle(workbook);// 设置和并单元格样式

			c0.setCellStyle(cellStyleTiele); // 设置单元格样式

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return workbook;
	}
	
	public static String objToStr(Object obj) {
		if (obj != null) {
			return obj.toString();
		} else {
			return "";
		}
	}
	
}
