package ui;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;

/**
 * 考勤管理应用 - Excel选择框
 *
 * @author wanglu lusalome@163.com
 * @version 2012-09-08 21:18
 */
public class ExcelFileChooser extends JFileChooser{

	public ExcelFileChooser(){
		try {
			//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());// 当前系统风格
		} catch (Exception e) {
			e.printStackTrace();
		}
		initGUI();
		/* 左上角图标与Frame的图标相同 */
	}

	public void initGUI(){
		// 设置当前目录
		this.setCurrentDirectory(new File("."));
		this.setAcceptAllFileFilterUsed(false);
		this.setMultiSelectionEnabled(false);//不可以选择多个文件
		final String[][] fileENames = { { ".XLSX", "XLSX 文件(*.XLSX)" },
				{ ".XLS", "XLS 文件(*.XLS)" }, { ".xlsx", "xlsx 文件(*.xlsx)" },
				{ ".xls", "xls 文件(*.xls)" } };
		// 显示所有文件
		this.addChoosableFileFilter(new FileFilter() {
			public boolean accept(File file) {
				return true;
			}
			public String getDescription() {
				return "所有文件(*.*)";
			}
		});
		// 循环添加需要显示的文件
		for (final String[] fileEName : fileENames) {
			this.setFileFilter(new javax.swing.filechooser.FileFilter() {
				public boolean accept(File file) {
					if (file.getName().endsWith(fileEName[0])
							|| file.isDirectory()) {
						return true;
					}
					return false;
				}
				public String getDescription() {
					return fileEName[1];
				}
			});
		}
	}
}
