package ui;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

/**
 * 考勤管理应用 - 目录选择框
 *
 * @author wanglu lusalome@163.com
 * @version 2012-09-08 21:18
 */
public class DirChooser extends JFileChooser{

	public DirChooser() {
		initGUI();
	}

	public void initGUI(){
		// 设置当前目录
		this.setCurrentDirectory(new File("."));
		this.setAcceptAllFileFilterUsed(false);
		// 只能选择目录
		this.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		this.setMultiSelectionEnabled(false);//不可以选择多个文件

		// 显示所有文件
		this.addChoosableFileFilter(new FileFilter() {
			public boolean accept(File file) {
				return true;
			}
			public String getDescription() {
				return "所有文件(*.*)";
			}
		});

	}
}