package main;

import javax.swing.*;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.pushingpixels.substance.api.SubstanceLookAndFeel;
import org.pushingpixels.substance.api.SubstanceSkin;
import org.pushingpixels.substance.api.skin.*;

import messagebox.ShowMessageDialog;
import tools.Calculate;
import tools.FileUtil;
import tools.ImageLoader;
import tools.ReadWriteProperties;
import tools.Result;
import tools.WriteExcel;
import ui.AboutBox;
import ui.DirChooser;
import ui.ExcelFileChooser;
import ui.StaffDialog;
import ui.TimeSettingPanel;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 * 考勤管理应用 - 主程序入口
 *
 * @author wanglu lusalome@163.com
 * @version 2012-09-08 21:18
 */
public class MainApp extends JFrame {

    private SubstanceSkin skin;
    JTextArea theArea = null;
    static final String ComboStr[] = {"Times New Roman", "Dialog", "宋体", "黑体", "楷体"};

    /**
     * 构造函数.
     */
    public MainApp() {
        // 标题
        super("考勤管理应用");
        skin = new CremeSkin();
        SubstanceLookAndFeel.setSkin(skin);
        // 设置左上角图标
        ImageIcon sysIcon = ImageLoader.getInstance().getIcon("main.png");
        Image img = sysIcon.getImage();
        this.setIconImage(img);
        // 设置窗口大小并居中
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        this.setSize(screenSize.width / 2, screenSize.height / 2);
        this.setLocationRelativeTo(null);

        theArea = new JTextArea();
        theArea.setEditable(false);
        getContentPane().add(new JScrollPane(theArea));
        JMenuBar MBar = new JMenuBar();
        MBar.setOpaque(true);

        JMenu mfile = buildFileMenu();
        JToolBar theBar = buildToolBar();
        this.getContentPane().add(theBar, BorderLayout.NORTH);
        MBar.add(mfile);
        setJMenuBar(MBar);
    }


    /**
     * 构建下拉菜单项并添加监听
     *
     * @return
     */
    public JMenu buildFileMenu() {
        // 菜单栏目
        JMenu thefile = new JMenu("菜单");
        thefile.setMnemonic('F');

        // 菜单项
        JMenuItem importXLS = new JMenuItem("导入源表", ImageLoader.getInstance().getIcon("import.png"));
        JMenuItem calculateXLS = new JMenuItem("计算结果", ImageLoader.getInstance().getIcon("calculate.png"));
        JMenuItem exportXLS = new JMenuItem("导出结果", ImageLoader.getInstance().getIcon("export.png"));
        JMenuItem quit = new JMenuItem("退出程序", ImageLoader.getInstance().getIcon("lock.png"));

        // 设置键盘助记符和提示
        importXLS.setMnemonic('N');
        calculateXLS.setMnemonic('O');
        exportXLS.setMnemonic('L');
        quit.setMnemonic('X');

        importXLS.setAccelerator(KeyStroke.getKeyStroke('N',
                java.awt.Event.CTRL_MASK, false));
        calculateXLS.setAccelerator(KeyStroke.getKeyStroke('O',
                java.awt.Event.CTRL_MASK, false));
        exportXLS.setAccelerator(KeyStroke.getKeyStroke('L',
                java.awt.Event.CTRL_MASK, false));
        quit.setAccelerator(KeyStroke.getKeyStroke('X',
                java.awt.Event.CTRL_MASK, false));

        // 导入源表按钮的监听事件
        importXLS.setActionCommand("Menu_IMPORT");
        importXLS.addActionListener(new MainApp_MenuItem_actionAdapter(this));
        // 计算结果表按钮的监听事件
        calculateXLS.setActionCommand("Menu_CALCULATE");
        calculateXLS.addActionListener(new MainApp_MenuItem_actionAdapter(this));
        // 导出结果按钮的监听事件
        exportXLS.setActionCommand("Menu_EXPORT");
        exportXLS.addActionListener(new MainApp_MenuItem_actionAdapter(this));
        // 退出程序按钮的监听事件
        quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        thefile.add(importXLS);
        thefile.add(calculateXLS);
        thefile.add(exportXLS);
        thefile.addSeparator();
        thefile.add(quit);

        return thefile;
    }


    /**
     * 构建工具栏按钮及监听事件.
     *
     * @return
     */
    public JToolBar buildToolBar() {
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(true);
        ToolBarAction tb_import = new ToolBarAction("导入源表", ImageLoader.getInstance().getIcon("import.png"), this);
        ToolBarAction tb_calculate = new ToolBarAction("计算结果", ImageLoader.getInstance().getIcon("calculate.png"), this);
        ToolBarAction tba_export = new ToolBarAction("导出结果", ImageLoader.getInstance().getIcon("export.png"), this);

        JButton JB;
        JB = toolBar.add(tb_import);
        JB.setActionCommand("TooBar_IMPORT");
        JB.setToolTipText("导入源表");
        JB = toolBar.add(tb_calculate);
        JB.setActionCommand("ToolBar_CALCULATE");
        JB.setToolTipText("计算结果");
        JB = toolBar.add(tba_export);
        JB.setActionCommand("ToolBar_EXPORT");
        JB.setToolTipText("导出结果");

        toolBar.addSeparator();

        ToolBarAction tb_setting = new ToolBarAction("时间设置", ImageLoader.getInstance().getIcon("setting.png"), this);
        ToolBarAction tb_staff = new ToolBarAction("人员列表", ImageLoader.getInstance().getIcon("users.png"), this);
        ToolBarAction tb_about = new ToolBarAction("关于程序", ImageLoader.getInstance().getIcon("promotion.png"), this);
        JB = toolBar.add(tb_setting);
        JB.setToolTipText("时间设置");
        JB.setActionCommand("ToolBar_SETTING");
        JB = toolBar.add(tb_staff);
        JB.setToolTipText("人员列表");
        JB.setActionCommand("ToolBar_STAFF");
        JB = toolBar.add(tb_about);
        JB.setToolTipText("关于程序");
        JB.setActionCommand("ToolBar_ABOUT");

        toolBar.addSeparator();
        return toolBar;
    }

    /**
     * 主程序入口.
     *
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame F = new MainApp();
                F.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                });
                F.pack();
                F.setVisible(true);
            }
        });
    }

    /**
     * @author wanglu
     */
    class ToolBarAction extends AbstractAction {
        MainApp mainApp;

        public ToolBarAction(String name, Icon icon, MainApp mainApp) {
            super(name, icon);
            this.mainApp = mainApp;
        }

        public void actionPerformed(ActionEvent e) {
            // "导入源表"的按钮响应
            if (e.getActionCommand().equals("TooBar_IMPORT")) {
                theArea.append("# " + MainApp.refFormatNowDate() + " ，您点击了'导入源表'的按钮.\n");
                ExcelFileChooser fileChooser = new ExcelFileChooser();
                fileChooser.showOpenDialog(this.mainApp);

                // 只能选择一个文件
                File file = fileChooser.getSelectedFile();
                if (file != null) {
                    if (file.getName().endsWith("xls")
                            || file.getName().endsWith("xlsx")
                            || file.getName().endsWith("XLS")
                            || file.getName().endsWith("XLSX")) {
                        String fileName = MainApp.class.getClassLoader().getResource("extra/Path.properties").getPath();
                        String property = "filePath";
                        String value = file.getAbsolutePath();
                        value = value.replaceAll("\\\\", "/");
                        ReadWriteProperties.writePropertiesFile(fileName, property, value);
                        JOptionPane.showMessageDialog(null,
                                "导入成功!", "成功",
                                JOptionPane.OK_OPTION,
                                ShowMessageDialog.iconSuccess);
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "请选择正确(.xls/.xlsx/.XLS/.XLSX)格式的文件!", "错误",
                                JOptionPane.ERROR_MESSAGE,
                                ShowMessageDialog.iconError);
                    }
                }
            }
            // "计算结果"的按钮响应
            else if (e.getActionCommand().equals("ToolBar_CALCULATE")) {
                theArea.append("# " + MainApp.refFormatNowDate() + " ，您点击了'计算结果'的按钮.\n");
                String fileName = MainApp.class.getClassLoader().getResource("extra/Path.properties").getPath();
                String property = "filePath";
                String value = ReadWriteProperties.readPropertiesFileChinese(fileName, property, true);
                try {
                    List<Result> list = Calculate.calculateExcel(value);
                    try {
                        OutputStream os = new FileOutputStream(new File("a.xls"));
                        HSSFWorkbook workBook = WriteExcel.exportExcel(list);
                        workBook.write(os);
                        os.close();
                        JOptionPane.showMessageDialog(null,
                                "计算完成!", "成功",
                                JOptionPane.OK_OPTION,
                                ShowMessageDialog.iconSuccess);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null,
                                "计算过程中出现错误!", "错误",
                                JOptionPane.ERROR_MESSAGE,
                                ShowMessageDialog.iconError);
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null,
                            "计算过程中出现错误!", "错误",
                            JOptionPane.ERROR_MESSAGE,
                            ShowMessageDialog.iconError);
                }

            }
            // "导出结果"的按钮响应
            else if (e.getActionCommand().equals("ToolBar_EXPORT")) {
                theArea.append("# " + MainApp.refFormatNowDate()
                        + " ，您点击了'导出结果'的按钮.\n");
                JFileChooser chooser = new DirChooser();
                int returnval = chooser.showDialog(this.mainApp, "选择文件夹");
                if (returnval == JFileChooser.APPROVE_OPTION) {
                    String path = chooser.getSelectedFile().getPath();
                    path = path.replaceAll("\\\\", "/");
                    try {
                        File sourceFile = new File("a.xls");
                        File destFile = new File(path + "/考勤结果统计表.xls");
                        FileUtil.copyFile(sourceFile, destFile);
                        JOptionPane.showMessageDialog(null,
                                "保存成功，请见：" + path + " 目录下的考勤结果统计表.xls。\n", "成功",
                                JOptionPane.OK_OPTION,
                                ShowMessageDialog.iconSuccess);
                        if (sourceFile.exists()) {
                            sourceFile.delete();
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(null,
                                "保存过程中出现错误，请关闭所有Excel。\n!", "错误",
                                JOptionPane.ERROR_MESSAGE,
                                ShowMessageDialog.iconError);
                    }
                }

            }
            // "时间设置"的按钮响应
            else if (e.getActionCommand().equals("ToolBar_SETTING")) {
                theArea.append("# " + MainApp.refFormatNowDate() + " ，您点击了'时间设置'的按钮.\n");
                new TimeSettingPanel().setVisible(true);

            }
            // "人员列表"的按钮响应
            else if (e.getActionCommand().equals("ToolBar_STAFF")) {
                theArea.append("# " + MainApp.refFormatNowDate() + " ，您点击了'人员列表'的按钮.\n");
                //getContentPane().add(new JScrollPane(new StaffPanel()));
                new StaffDialog().setVisible(true);
            }
            // "关于我们"的按钮响应
            else if (e.getActionCommand().equals("ToolBar_ABOUT")) {
                theArea.append("# " + MainApp.refFormatNowDate() + " ，您点击了'关于我们'的按钮.\n");
                new AboutBox().setVisible(true);
            }

        }
    }

    /**
     * 获取当前系统时间.
     *
     * @return
     */
    public static String refFormatNowDate() {
        Date nowTime = new Date(System.currentTimeMillis());
        SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String retStrFormatNowDate = sdFormatter.format(nowTime);
        return retStrFormatNowDate;
    }

    public void menuFile_actionPerformed(ActionEvent e) {
        // "导入源表"的菜单响应
        if (e.getActionCommand().equals("Menu_IMPORT")) {
            theArea.append("# " + MainApp.refFormatNowDate() + " ，您点击了'导入源表'菜单.\n");
            ExcelFileChooser fileChooser = new ExcelFileChooser();
            fileChooser.showOpenDialog(this);

            // 只能选择一个文件
            File file = fileChooser.getSelectedFile();
            if (file != null) {
                if (file.getName().endsWith("xls")
                        || file.getName().endsWith("xlsx")
                        || file.getName().endsWith("XLS")
                        || file.getName().endsWith("XLSX")) {
                    String fileName = MainApp.class.getClassLoader().getResource("extra/Path.properties").getPath();
                    String property = "filePath";
                    String value = file.getAbsolutePath();
                    value = value.replaceAll("\\\\", "/");
                    ReadWriteProperties.writePropertiesFile(fileName, property, value);
                    JOptionPane.showMessageDialog(null,
                            "导入成功!", "成功",
                            JOptionPane.OK_OPTION,
                            ShowMessageDialog.iconSuccess);
                } else {
                    JOptionPane.showMessageDialog(null,
                            "请选择正确(.xls/.xlsx/.XLS/.XLSX)格式的文件!", "错误",
                            JOptionPane.ERROR_MESSAGE,
                            ShowMessageDialog.iconError);
                }
            }
        } else if (e.getActionCommand().equals("Menu_CALCULATE")) {
            theArea.append("# " + MainApp.refFormatNowDate() + " ，您点击了'计算结果'菜单.\n");
            String fileName = "extra/Path.properties";
            String property = "filePath";
            String value = ReadWriteProperties.readPropertiesFileChinese(fileName, property, true);
            try {

                List<Result> list = Calculate.calculateExcel(value);
                try {
                    OutputStream os = new FileOutputStream(new File(MainApp.class.getResource("extra/Result.xls").getPath()));
                    HSSFWorkbook workBook = WriteExcel.exportExcel(list);
                    workBook.write(os);
                    os.close();
                    JOptionPane.showMessageDialog(null,
                            "计算完成!", "成功",
                            JOptionPane.OK_OPTION,
                            ShowMessageDialog.iconSuccess);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null,
                            "计算过程中出现错误!", "错误",
                            JOptionPane.ERROR_MESSAGE,
                            ShowMessageDialog.iconError);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null,
                        "计算过程中出现错误!", "错误",
                        JOptionPane.ERROR_MESSAGE,
                        ShowMessageDialog.iconError);
            }
        } else if (e.getActionCommand().equals("Menu_EXPORT")) {
            theArea.append("# " + MainApp.refFormatNowDate() + " ，您点击了'导出结果'菜单.\n");
            JFileChooser chooser = new DirChooser();
            int returnval = chooser.showDialog(this, "选择文件夹");
            if (returnval == JFileChooser.APPROVE_OPTION) {
                String path = chooser.getSelectedFile().getPath();
                path = path.replaceAll("\\\\", "/");
                try {
                    File sourceFile = new File("C:/a.xls");
                    File destFile = new File(path + "/考勤结果统计表.xls");
                    FileUtil.copyFile(sourceFile, destFile);
                    JOptionPane.showMessageDialog(null,
                            "保存成功，请见：" + path + " 目录下的考勤结果统计表.xls。\n", "成功",
                            JOptionPane.OK_OPTION,
                            ShowMessageDialog.iconSuccess);
                    if (sourceFile.exists()) {
                        sourceFile.delete();
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(null,
                            "保存过程中出现错误，请关闭所有Excel。\n!", "错误",
                            JOptionPane.ERROR_MESSAGE,
                            ShowMessageDialog.iconError);
                }
            }
        }
    }
}


class MainApp_MenuItem_actionAdapter implements ActionListener {
    MainApp adaptee;

    MainApp_MenuItem_actionAdapter(MainApp adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.menuFile_actionPerformed(e);
    }
}

/**
 * 考勤管理应用 - Swing
 *
 * @author wanglu lusalome@163.com
 * @version 2012-09-08 21:18
 */
class SwingUtil {
    public static final void setLookAndFeel() {
        try {
            Font font = new Font("JFrame", Font.PLAIN, 12);
            Enumeration keys = UIManager.getLookAndFeelDefaults().keys();

            while (keys.hasMoreElements()) {
                Object key = keys.nextElement();
                if (UIManager.get(key) instanceof Font) {
                    UIManager.put(key, font);
                }
            }
            SubstanceLookAndFeel.setSkin(new OfficeBlack2007Skin());

        } catch (Exception e) {

        }
    }
}
//Substance总共定义了27种皮肤，分别是：
//org.pushingpixels.substance.api.skin.AutumnSkin,  
//org.pushingpixels.substance.api.skin.BusinessSkin,  
//org.pushingpixels.substance.api.skin.BusinessBlackSteelSkin,  
//org.pushingpixels.substance.api.skin.BusinessBlueSteelSkin,  
//org.pushingpixels.substance.api.skin.ChallengerDeepSkin,  
//org.pushingpixels.substance.api.skin.CremeSkin,  
//org.pushingpixels.substance.api.skin.CremeCoffeeSkin,  
//org.pushingpixels.substance.api.skin.DustSkin,  
//org.pushingpixels.substance.api.skin.DustCoffeeSkin,  
//org.pushingpixels.substance.api.skin.EmeraldDuskSkin,  
//org.pushingpixels.substance.api.skin.GeminiSkin,  
//org.pushingpixels.substance.api.skin.GraphiteSkin,  
//org.pushingpixels.substance.api.skin.GraphiteAquaSkin,  
//org.pushingpixels.substance.api.skin.GraphiteGlassSkin,  
//org.pushingpixels.substance.api.skin.MagellanSkin,  
//org.pushingpixels.substance.api.skin.MarinerSkin,  
//org.pushingpixels.substance.api.skin.MistAquaSkin,  
//org.pushingpixels.substance.api.skin.MistSilverSkin,  
//org.pushingpixels.substance.api.skin.ModerateSkin,  
//org.pushingpixels.substance.api.skin.NebulaSkin,  
//org.pushingpixels.substance.api.skin.NebulaBrickWallSkin,  
//org.pushingpixels.substance.api.skin.OfficeBlack2007Skin,  
//org.pushingpixels.substance.api.skin.OfficeBlue2007Skin,  
//org.pushingpixels.substance.api.skin.OfficeSilver2007Skin,  
//org.pushingpixels.substance.api.skin.RavenSkin,  
//org.pushingpixels.substance.api.skin.SaharaSkin  
