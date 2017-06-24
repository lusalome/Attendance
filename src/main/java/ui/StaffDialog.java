package ui;

import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import tools.ImageLoader;
import tools.ReadWriteTxt;

/**
 * 考勤管理应用 - 时间设置框
 *
 * @author wanglu lusalome@163.com
 * @version 2012-09-08 21:18
 */
public class StaffDialog extends JDialog{

	public StaffDialog() {
		initGUI();
	}

	private void initGUI() {
		this.setTitle("时间设置");
		this.setSize(400, 300);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		// 设置图标
		ImageIcon sysIcon = ImageLoader.getInstance().getIcon("users.png");
		Image img = sysIcon.getImage();
		this.setIconImage(img);
		// 初始化测试数据
		String[] tableTitleArray = { "编号", "姓名"};

		ReadWriteTxt myFile = new ReadWriteTxt();
		myFile.setPath(this.getClass().getClassLoader().getResource("extra").getPath());
		myFile.setFilenameTemp(myFile.getPath() + "/" + "Staff.txt");
		String str = myFile.readDate();
		String[] split = str.split("\r\n");
		Object[][] body = new Object[split.length][tableTitleArray.length];
		for (int i = 0; i < split.length; i++) {
			String[] sp = split[i].split("\t");
			body[i][0] = sp[0];
			body[i][1] = sp[1];
		}
		JTabelRender table = new JTabelRender(new DefaultTableModel(body,
				tableTitleArray));
		this.add(new JScrollPane(table), BorderLayout.CENTER);

	}
}
