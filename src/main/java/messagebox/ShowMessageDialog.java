package messagebox;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import tools.ImageLoader;

/**
 * 考勤管理应用 - 显示对话框
 * 描述：弹出MessageDialog(5种)示例
 * 显示一个模式对话框的一个按钮，这是标有“确定”（或本地化的等效）。 您可以轻松地指定的消息，图标和标题对话框显示
 * <p>
 * 默认图标如下：messageType
 * QUESTION_MESSAGE（问题）
 * INFORMATION_MESSAGE（信息）
 * WARNING_MESSAGE（警告）
 * ERROR_MESSAGE（错误）
 * PLAIN_MESSAGE（无图标）
 * Success （自定义成功图标）
 *
 * @author wanglu lusalome@163.com
 * @version 2012-09-08 21:18
 */
public class ShowMessageDialog {

    //成功对话框
    public static Icon iconSuccess = ImageLoader.getInstance().getMessageBoxIcon("accept.png");

    //问题对话框
    public static Icon iconQuestion = ImageLoader.getInstance().getMessageBoxIcon("help.png");

    // 信息对话框
    public static Icon iconInformation = ImageLoader.getInstance().getMessageBoxIcon("info.png");

    // 警告对话框
    public static Icon iconWarning = ImageLoader.getInstance().getMessageBoxIcon("warning.png");

    // 错误对话框
    public static Icon iconError = ImageLoader.getInstance().getMessageBoxIcon("delete.png");

    // 禁止对话框
    public static Icon iconForbid = ImageLoader.getInstance().getMessageBoxIcon("cancel_48.png");

    /**
     * 问题对话框（图标是问号，一个确定按钮）
     * question dialog
     */
    public void showQuestionDialog() {
        JOptionPane.showMessageDialog(null, "问题消息！", "问题", JOptionPane.QUESTION_MESSAGE);
    }

    /**
     * 信息对话框（图标是像i，一个确定按钮）
     * information dialog
     */
    public void showInformationDialog() {
        JOptionPane.showMessageDialog(null, "信息消息！", "信息", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * 警告对话框（图标是三角形包围的感叹号，一个确定按钮）
     * warning dialog
     */
    public void showWarningDialog() {
        JOptionPane.showMessageDialog(null, "警告消息！", "警告", JOptionPane.WARNING_MESSAGE);
    }


    /**
     * 错误对话框（图标是红叉，一个确定按钮）
     * error dialog
     */
    public void showErrorDialog() {
        JOptionPane.showMessageDialog(null, "信息消息！", "信息", JOptionPane.ERROR_MESSAGE);
    }


    /**
     * （图标是无，一个确定按钮）
     * error dialog
     */
    public void showPlainDialog() {
        JOptionPane.showMessageDialog(null, "空！", "空", JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * 自定义对话框
     * success dialog
     */
    public void showSuccessnDialog() {
        //成功对话框
        Icon iconSuccess = new ImageIcon("resources/images/accept.png");
        JOptionPane.showMessageDialog(null, "成功消息！", "成功", JOptionPane.INFORMATION_MESSAGE, iconSuccess);

        //问题对话框
        Icon iconQuestion = new ImageIcon("resources/images/help.png");
        JOptionPane.showMessageDialog(null, "问题主体！", "问题", JOptionPane.INFORMATION_MESSAGE, iconQuestion);

        //信息对话框
        Icon iconInformation = new ImageIcon("resources/images/info.png");
        //Icon iconInformation = new ImageIcon("resources/images/comment.png");
        JOptionPane.showMessageDialog(null, "信息主体！", "信息", JOptionPane.INFORMATION_MESSAGE, iconInformation);

        //警告对话框
        Icon iconWarning = new ImageIcon("resources/images/warning.png");
        JOptionPane.showMessageDialog(null, "警告主体！", "警告", JOptionPane.INFORMATION_MESSAGE, iconWarning);

        //错误对话框
        Icon iconError = new ImageIcon("resources/images/delete.png");
        JOptionPane.showMessageDialog(null, "错误主体！", "错误", JOptionPane.INFORMATION_MESSAGE, iconError);

        //禁止对话框
        Icon iconForbid = new ImageIcon("resources/images/block.png");
        JOptionPane.showMessageDialog(null, "禁止主体！", "禁止", JOptionPane.INFORMATION_MESSAGE, iconForbid);

    }


    public Icon getIconSuccess() {
        return iconSuccess;
    }

    public void setIconSuccess(Icon iconSuccess) {
        this.iconSuccess = iconSuccess;
    }

    public Icon getIconQuestion() {
        return iconQuestion;
    }

    public void setIconQuestion(Icon iconQuestion) {
        this.iconQuestion = iconQuestion;
    }

    public Icon getIconInformation() {
        return iconInformation;
    }

    public void setIconInformation(Icon iconInformation) {
        this.iconInformation = iconInformation;
    }

    public Icon getIconWarning() {
        return iconWarning;
    }

    public void setIconWarning(Icon iconWarning) {
        this.iconWarning = iconWarning;
    }

    public Icon getIconError() {
        return iconError;
    }

    public void setIconError(Icon iconError) {
        this.iconError = iconError;
    }

    public Icon getIconForbid() {
        return iconForbid;
    }

    public void setIconForbid(Icon iconForbid) {
        this.iconForbid = iconForbid;
    }
}
