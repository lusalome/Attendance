package ui;

import java.awt.AWTEvent;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import messagebox.ShowMessageDialog;
import tools.ImageLoader;
import tools.ReadWriteProperties;

/**
 * 考勤管理应用 - 时间设置框
 *
 * @author wanglu lusalome@163.com
 * @version 2012-09-08 21:18
 */
public class TimeSettingPanel extends JDialog{

	private javax.swing.JLabel remark;
	private javax.swing.JTextField setTimeField;
	private javax.swing.JLabel settingLabel;

	// 确认按钮
	ImageIcon ok = ImageLoader.getInstance().getIcon("53.gif");
	JButton okButton = ImageButton.createButton(ok);
	// 取消按钮
	ImageIcon cancel = ImageLoader.getInstance().getIcon("52.gif");
	JButton cancelButton = ImageButton.createButton(cancel);
	public TimeSettingPanel() {
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
		try {
			init();
			setSize(500, 180);
			setLocationRelativeTo(null);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void init() {
		this.setTitle("时间设置");

		// 设置图标
		ImageIcon sysIcon = ImageLoader.getInstance().getIcon("setting.png");
		Image img = sysIcon.getImage();
		this.setIconImage(img);
		settingLabel = new javax.swing.JLabel();
		setTimeField = new javax.swing.JTextField();
		remark = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		settingLabel.setText("本次统计应到小时数:");
		settingLabel.setName("settingLabel"); // NOI18N
		getContentPane().add(settingLabel);
		settingLabel.setBounds(20, 20, 140, 15);

		setTimeField.setName("setTimeField"); // NOI18N
		// 设置初始值（从配置文件中读取）
		setTimeField.setText(ReadWriteProperties.readPropertiesFile("../extra/Setting.properties", "totalTime"));
		getContentPane().add(setTimeField);
		setTimeField.setBounds(170, 11, 320, 30);

		remark.setText("注: 如本次统计一周的考勤，应到小时数填：40.");
		remark.setName("remark"); // NOI18N
		getContentPane().add(remark);
		remark.setBounds(20, 80, 460, 15);

		okButton.setName("okButton"); // NOI18N
		getContentPane().add(okButton);
		// 确定按钮触发函数
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double hours = 0;
				String tempTime = setTimeField.getText();
				if(tempTime == null) {
					JOptionPane.showMessageDialog(null, "必须填写本次统计应到小时数!", "警告",
							JOptionPane.WARNING_MESSAGE,
							ShowMessageDialog.iconWarning);
					// 获得焦点
					setTimeField.requestFocus();
				} else {
					tempTime = tempTime.trim();
					try {
						hours = Double.parseDouble(tempTime);
						if(hours <= 0.0) {
							JOptionPane.showMessageDialog(null, "本次统计应到小时数必须大于0!", "警告",
									JOptionPane.WARNING_MESSAGE,
									ShowMessageDialog.iconWarning);
							// 获得焦点
							setTimeField.requestFocus();
						} else {
							// 保存到properties文件中
							String fileName = "../extra/Setting.properties";
							String property = "totalTime";
							String value = Double.toString(hours);
							ReadWriteProperties.writePropertiesFile(fileName, property, value);
							JOptionPane.showMessageDialog(null, "设置成功!", "成功",
									JOptionPane.OK_OPTION,
									ShowMessageDialog.iconSuccess);
							closeFrame();
						}

					} catch(Exception ex) {
						JOptionPane.showMessageDialog(null, "本次统计应到小时数为数值类型!", "错误",
								JOptionPane.ERROR_MESSAGE,
								ShowMessageDialog.iconError);
						// 获得焦点
						setTimeField.requestFocus();
					}
				}


			}
		});
		okButton.setBounds(280, 110, 57, 23);

		cancelButton.setName("cancelButton"); // NOI18N
		getContentPane().add(cancelButton);
		// 取消按钮触发函数
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeFrame();
			}
		});
		cancelButton.setBounds(390, 110, 57, 23);
	}


	/**
	 *  关闭窗体
	 */
	private void closeFrame()
	{
		this.dispose();
	}
}
