package com.psp.util.excel;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.psp.util.NumUtil;

/**
 * 导入excel工具类，根据读取excel内容
 * 	需要引入： name: 'org.apache.poi', version: '3.16'
 * 			 name: 'org.apache.poi-ooxml', version: '3.16'
 * @author chengl
 *
 * @param <T>
 */
public class ImportExcel<T> {

	Logger logger = Logger.getLogger(this.getClass());

	Class<T> clazz;

	public ImportExcel(Class<T> clazz) {
		this.clazz = clazz;
	}

	public Collection<T> importExcel(File file, String... pattern) {
		Collection<T> dist = new ArrayList();
		try {
			/**
			 * 类反射得到调用方法
			 */
			// 得到目标目标类的所有的字段列表
			Field filed[] = clazz.getDeclaredFields();
			// 将所有标有Annotation的字段，也就是允许导入数据的字段,放入到一个map中
			Map fieldmap = new HashMap();
			// 循环读取所有字段
			for (int i = 0; i < filed.length; i++) {
				Field f = filed[i];
				// 得到单个字段上的Annotation
				ExcelAnnotation exa = f.getAnnotation(ExcelAnnotation.class);
				// 如果标识了Annotationd的话
				if (exa != null) {
					// 构造设置了Annotation的字段的Setter方法
					String fieldname = f.getName();
					String setMethodName = "set" + fieldname.substring(0, 1).toUpperCase() + fieldname.substring(1);
					// 构造调用的method，
					Method setMethod = clazz.getMethod(setMethodName, new Class[] { f.getType() });
					// 将这个method以Annotaion的名字为key来存入。
					fieldmap.put(exa.exportName(), setMethod);
				}
			}
			/**
			 * excel的解析开始
			 */
			// // 得到工作表
			Workbook book = WorkbookFactory.create(file);
			// // 得到第一页
			Sheet sheet = book.getSheetAt(0);
			// // 得到第一面的所有行
			Iterator<Row> row = sheet.rowIterator();

			/**
			 * 标题解析
			 */
			// 得到第一行，也就是标题行
			Row title = row.next();
			// 得到第一行的所有列
			Iterator<Cell> cellTitle = title.cellIterator();
			// 将标题的文字内容放入到一个map中。
			Map titlemap = new HashMap();
			// 从标题第一列开始
			int i = 0;
			// 循环标题所有的列
			while (cellTitle.hasNext()) {
				Cell cell = cellTitle.next();
				String value = cell.getStringCellValue();
				titlemap.put(i, value);
				i = i + 1;
			}
			/**
			 * 解析内容行
			 */
			// 用来格式化日期的DateFormat
			SimpleDateFormat sf;
			if (pattern.length < 1) {
				sf = new SimpleDateFormat("yyyy-MM-dd");
			} else
				sf = new SimpleDateFormat(pattern[0]);
			while (row.hasNext()) {
				// 标题下的第一行
				Row rown = row.next();

				// 行的所有列
				Iterator<Cell> cellbody = rown.cellIterator();
				// 得到传入类的实例
				T tObject = clazz.newInstance();
				int k = 0;
				// 遍历一行的列
				while (cellbody.hasNext()) {
					Cell cell = cellbody.next();
					// 这里得到此列的对应的标题
					String titleString = (String) titlemap.get(k);
					// 如果这一列的标题和类中的某一列的Annotation相同，那么则调用此类的的set方法，进行设值

					if (fieldmap.containsKey(titleString)) {

						Method setMethod = (Method) fieldmap.get(titleString);
						// 得到setter方法的参数
						Type[] ts = setMethod.getGenericParameterTypes();
						// 只要一个参数
						String xclass = ts[0].toString();
						// 判断参数类型
						if (xclass.equals("class java.lang.String")) {
							setMethod.invoke(tObject, cell.getStringCellValue());
						} else if (xclass.equals("class java.util.Date")) {
							setMethod.invoke(tObject, sf.parse(cell.getStringCellValue()));
						} else if (xclass.equals("class java.lang.Boolean")) {
							Boolean boolname = true;
							if (cell.getStringCellValue().equals("否")) {
								boolname = false;
							}
							setMethod.invoke(tObject, boolname);
						} else if (xclass.equals("class java.lang.Integer")) {
							if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
								setMethod.invoke(tObject, cell);
								
							} else if(cell.getCellType() == Cell.CELL_TYPE_STRING){
								setMethod.invoke(tObject, cell.getStringCellValue());
							}
							//setMethod.invoke(tObject, NumUtil.toInt(cell.getNumericCellValue(), 0));
						} else if (xclass.equals("class java.lang.Double")) {
							if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
								setMethod.invoke(tObject, NumUtil.toDouble(cell, 0));
								
							} 
						}
						else if (xclass.equals("class java.lang.Long")) {
							setMethod.invoke(tObject, new Long(cell.getStringCellValue()));
						}
					}
					// 下一列
					k = k + 1;
				}
				dist.add(tObject);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return dist;
	}

}
