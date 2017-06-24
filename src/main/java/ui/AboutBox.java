package ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import tools.ImageLoader;

/**
 * 考勤管理应用 - 关于软件版本、所有权等描述信息
 *
 * @author wanglu lusalome@163.com
 * @version 2012-09-08 21:18
 */
public class AboutBox extends JDialog implements ActionListener {

	/**
	 * The Panel 1.
	 */
	JPanel panel1 = new JPanel();
	/**
	 * The Panel 2.
	 */
	JPanel panel2 = new JPanel();
	/**
	 * The Insets panel 1.
	 */
	JPanel insetsPanel1 = new JPanel();
	/**
	 * The Insets panel 3.
	 */
	JPanel insetsPanel3 = new JPanel();
	/**
	 * The Set.
	 */
	// 确认按钮
	ImageIcon set = ImageLoader.getInstance().getIcon("ok_48.png");
	/**
	 * The Button 1.
	 */
	JButton button1 = ImageButton.createButton(set);
	/**
	 * The Label 1.
	 */
	JLabel label1 = new JLabel();
	/**
	 * The Label 2.
	 */
	JLabel label2 = new JLabel();
	/**
	 * The Label 3.
	 */
	JLabel label3 = new JLabel();
	/**
	 * The Image 1.
	 */
	ImageIcon image1 = new ImageIcon();
	/**
	 * The Border layout 1.
	 */
	BorderLayout borderLayout1 = new BorderLayout();
	/**
	 * The Scroll pane.
	 */
	JScrollPane scrollPane = new JScrollPane();
	/**
	 * The Grid bag layout 1.
	 */
	GridBagLayout gridBagLayout1 = new GridBagLayout();
	/**
	 * The Other.
	 */
	JTextArea other = new JTextArea();
	/**
	 * The Grid bag layout 2.
	 */
	GridBagLayout gridBagLayout2 = new GridBagLayout();

	/**
	 * The Icon button.
	 */
	JButton iconButton = new JButton(ImageLoader.getInstance().getIcon(
			"Home.png"));

	/**
	 * Instantiates a new About box.
	 */
	public AboutBox() {
		
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
		try {
			dlgInit();
			
			setSize(800, 400);
			setLocationRelativeTo(null);
			button1.requestFocus();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	// Component initialization
	private void dlgInit() throws Exception {
		
		this.setTitle("关于我们");
		// 设置图标
		ImageIcon sysIcon = ImageLoader.getInstance().getIcon("promotion.png");
		Image img = sysIcon.getImage();
		this.setIconImage(img);
		panel1.setLayout(borderLayout1);
		panel2.setLayout(gridBagLayout1);
		label1.setFont(new Font("微软雅黑", 0, 15));
		label1.setText("考勤管理应用");
		label3.setText(" ");
		insetsPanel3.setLayout(gridBagLayout2);
		insetsPanel3.setBorder(BorderFactory.createEmptyBorder(10, 60, 10, 10));
		
		button1.addActionListener(this);
		other.setBackground(SystemColor.activeCaptionBorder);
		other.setEditable(false);
		other.setText("Copyright (C) 2015 \n\n\n版本V1.0\n\n作者: wanglu    若有问题请联系(lusalome@163.com)\n\n"
				+ "使用步骤：\n  (1)点击第四个按钮进行时间设置。如本次统计一周考勤，输入40；本次统计一个月考勤，输入扣除周末和假期的时间，如114.\n"
				+ "  (2)将从考勤机导出的原始数据另存为一个Excel文件(因为直接导出的源数据文件头不完全)，版本不限制，名称最好为英文或者数字.\n"
				+ "  (3)点击'菜单' -> '导入数据'或者点击按钮'导入数据'，将另存为好的Excel文件导入系统.\n"
				+ "  (4)点击'菜单' -> '计算结果'或者点击按钮'计算结果'，完成计算过程.\n"
				+ "  (5)点击'菜单' -> '导出结果'或者点击按钮'导出结果'，选择保存的路径，得到结果表'考勤结果统计表.xls'.\n"
				+ "  (6)点击'菜单' -> '退出程序'或者关闭按钮退出系统.\n");
		other.setFont(new Font("微软雅黑", 0, 12));
		iconButton.setBorder(null);
		this.getContentPane().add(panel1, null);
		insetsPanel3.add(label1, new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0,
						0, 0, 0), 0, 0));
		insetsPanel3.add(label2, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0,
						0, 0, 0), 0, 0));
		insetsPanel3.add(label3, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0,
						0, 0, 0), 0, 0));
		insetsPanel3.add(iconButton, new GridBagConstraints(0, 0, 1, 3, 0.0,
				0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5), 0, 0));
		panel2.add(scrollPane, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
						10, 10, 10, 10), 0, 0));
		panel2.add(insetsPanel3, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
						0, 0, 0, 0), 0, 0));
		insetsPanel1.add(button1, null);
		panel1.add(insetsPanel1, BorderLayout.SOUTH);
		panel1.add(panel2, BorderLayout.CENTER);
		scrollPane.getViewport().add(other, null);
		setResizable(true);
	}

	// 重写关闭窗口事件
	protected void processWindowEvent(WindowEvent e) {
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			cancel();
		}
		super.processWindowEvent(e);
	}

	/**
	 * Cancel.
	 */
// 关闭对话框
	void cancel() {
		dispose();
	}

	// 点击按钮关闭
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button1) {
			cancel();
		}
	}
}