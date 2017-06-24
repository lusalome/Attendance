package tools;

import java.util.List;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * 考勤管理应用 - 写Excel
 *
 * @author wanglu lusalome@163.com
 * @version 2012-09-08 21:18
 */
public class WriteExcel {
	public static HSSFWorkbook exportExcel(List<Result> dataList) {
		// 创建excel文件对象
		HSSFWorkbook wb = new HSSFWorkbook();
		// 创建一个张表
		Sheet sheet = wb.createSheet();
		// 创建第一行
		Row row = sheet.createRow(0);
		// 创建第二行
		Row row1 = sheet.createRow(1);
		// 文件头字体
		Font font0 = createFonts(wb, Font.BOLDWEIGHT_BOLD, "微软雅黑", false,
				(short) 400);
		Font font1 = createFonts(wb, Font.BOLDWEIGHT_NORMAL, "微软雅黑", false,
				(short) 200);
		// 红色字体
		//生成一个字体
		HSSFFont redFont = wb.createFont();
		redFont.setColor(HSSFColor.RED.index);//HSSFColor.VIOLET.index //字体颜色
		redFont.setFontName("微软雅黑");
		redFont.setFontHeight((short) 200);

		// 合并第一行的单元格： 0-6列
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 6));
		// 设置第一列的文字
		createCell(wb, row, 0, "考勤结果表", font0);

		// 给第二行添加文本
		createCell(wb, row1, 0, "编号", font1);
		createCell(wb, row1, 1, "姓名", font1);
		createCell(wb, row1, 2, "应到(小时)", font1);
		createCell(wb, row1, 3, "实到(小时)", font1);
		createCell(wb, row1, 4, "差值(实到-应到)", font1);
		createCell(wb, row1, 5, "出勤率", font1);
		createCell(wb, row1, 6, "迟到次数", font1);
		// 第三行表示
		int l = 2;
		// 这里将数据存入到表格中
		for (int i = 0; i < dataList.size(); i++) {
			// 创建一行
			Row rowData = sheet.createRow(l++);

			Result result = dataList.get(i);
			createCell(wb, rowData, 0, result.getStaffNo(), font1);
			createCell(wb, rowData, 1, result.getStaffName(), font1);
			createCell(wb, rowData, 2, Double.toString(result.getShouldHours()), font1);
			createCell(wb, rowData, 3, Double.toString(result.getActualHours()), font1);

			// 如果差值为负数，设置字体为红色
			if(result.getdValue() < 0) {
				createCell(wb, rowData, 4, Double.toString(result.getdValue()), redFont);
			} else {
				createCell(wb, rowData, 4, Double.toString(result.getdValue()), font1);
			}


			createCell(wb, rowData, 5, Double.toString(result.getAttendRate()) + "%", font1);
			createCell(wb, rowData, 6, String.valueOf(result.getLateTimes()), font1);


		}
		return wb;
	}

	/**
	 * 创建单元格并设置样式,值
	 *
	 * @param wb
	 * @param row
	 * @param column
	 * @param
	 * @param
	 * @param value
	 */
	public static void createCell(Workbook wb, Row row, int column,
								  String value, Font font) {
		Cell cell = row.createCell(column);
		cell.setCellValue(value);
		CellStyle cellStyle = wb.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_BOTTOM);
		cellStyle.setFont(font);
		cell.setCellStyle(cellStyle);
	}

	/**
	 * 设置字体
	 *
	 * @param wb
	 * @return
	 */
	public static Font createFonts(Workbook wb, short bold, String fontName,
								   boolean isItalic, short hight) {
		Font font = wb.createFont();
		font.setFontName(fontName);
		font.setBoldweight(bold);
		font.setItalic(isItalic);
		font.setFontHeight(hight);
		return font;
	}

	/**
	 * 判断是否为数字
	 *
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		if (str == null || "".equals(str.trim()) || str.length() > 10)
			return false;
		Pattern pattern = Pattern.compile("^0|[1-9]\\d*(\\.\\d+)?$");
		return pattern.matcher(str).matches();
	}
}
