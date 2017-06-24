package tools;

/**
 * 考勤管理应用 - 工具类
 *
 * @author wanglu lusalome@163.com
 * @version 2012-09-08 21:18
 */
class ExcelUtils {

	/**
	 * 是否是2003的excel，返回true是2003
	 * @param filePath 文件完整路径
	 * @return
	 */
	public static boolean isExcel2003(String filePath) {

		return filePath.matches("^.+\\.(?i)(xls)$");

	}

	/**
	 * 是否是2007的excel，返回true是2007
	 * @param filePath 文件完整路径
	 * @return
	 */
	public static boolean isExcel2007(String filePath) {

		return filePath.matches("^.+\\.(?i)(xlsx)$");

	}
}
