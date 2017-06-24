package ui;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Transparency;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * 考勤管理应用 - 图片按钮
 *
 * @author wanglu lusalome@163.com
 * @version 2012-09-08 21:18
 */
public class ImageButton {
    /**
     * ImageButton的构造函数，声明为Private
     * 使其不能被实例化。
     */
    private ImageButton() {}

    /**
     * 创建带一个图片的按钮
     * @param iconName -
     * @return JButton - 带此图片的按钮
     */
    public static JButton createButton(String iconName) {
        ImageIcon icon = getImageIcon(iconName);
        return createButton(icon);
    }

    /**
     * 创建带一个图片的按钮
     * @param rolloverIcon - 按钮的ImageIcon
     * @return JButton - 带此图片的按钮
     */
    public static JButton createButton(ImageIcon rolloverIcon) {
        return createButton(null,rolloverIcon,0);
    }

    /**
     * 创建带一个图片的按钮并指定透明类型
     * @param rolloverIcon - 按钮的ImageIcon
     * @param alpha - alpha值(按钮的透明形式)，可取本类的常量值
     * @return JButton - 带此图片的按钮
     */
    public static JButton createButton(ImageIcon rolloverIcon,float alpha) {
        return createButton(null,rolloverIcon,alpha);
    }

    /**
     * 创建带两个图片的按钮
     * @param defaultIcon - 按钮的一般ImageIcon
     * @param rolloverIcon - 鼠标滑过按钮时的ImageIcon
     * @param alpha - alpha值(按钮的透明形式)，可取本类的常量值
     * @return - 带此图片的按钮
     */
    public static JButton createButton(ImageIcon defaultIcon,ImageIcon rolloverIcon) {
        return createButton(defaultIcon,rolloverIcon,null,0);
    }

    /**
     * 创建带两个图片的按钮
     * @param defaultIcon - 按钮的一般ImageIcon
     * @param rolloverIcon - 鼠标滑过按钮时的ImageIcon
     * @param alpha - alpha值(按钮的透明形式)，可取本类的常量值
     * @return - 带此图片的按钮
     */
    public static JButton createButton(ImageIcon defaultIcon,ImageIcon rolloverIcon,
                                       float alpha) {
        return createButton(defaultIcon,rolloverIcon,null,alpha);
    }

    /**
     * 创建带的三个图片的按钮
     * @param defaultIcon - 按钮的一般ImageIcon
     * @param rolloverIcon - 鼠标滑过按钮时的ImageIcon
     * @param pressedIcon - 鼠标按下按钮时的ImageIcon
     * @return - 带此图片的按钮
     */
    public static JButton createButton(ImageIcon defaultIcon,ImageIcon rolloverIcon,
                                       ImageIcon pressedIcon) {
        return initButton(defaultIcon,rolloverIcon,pressedIcon,0,null);
    }

    /**
     * 创建带的三个图片的按钮
     * @param defaultIcon - 按钮的一般ImageIcon
     * @param rolloverIcon - 鼠标滑过按钮时的ImageIcon
     * @param pressedIcon - 鼠标按下按钮时的ImageIcon
     * @param alpha - alpha值(按钮的透明形式)，可取本类的常量值
     * @return - 带此图片的按钮
     */
    public static JButton createButton(ImageIcon defaultIcon,ImageIcon rolloverIcon,
                                       ImageIcon pressedIcon,float alpha) {
        return initButton(defaultIcon,rolloverIcon,pressedIcon,alpha,null);
    }

    /**
     * 初始化图片按钮
     */
    private static JButton initButton(ImageIcon defaultIcon,ImageIcon rolloverIcon,
                                      ImageIcon pressedIcon,float alpha,String text) {
        //获取鼠标滑过按钮的宽和高(供转换图片时之用)
        int width = rolloverIcon.getIconWidth();
        int height = rolloverIcon.getIconHeight();

        //如果alpha值为0，则设置alpha值为不透明
        if(alpha == 0)
            alpha = ImageButton.OPACITY;

        //如果一般按钮或鼠标压下按钮的ImageIcon为Null
        //则转换鼠标滑过按钮的图片，赋值给这两个为Null的图片
        if(defaultIcon == null)
            defaultIcon = getImageIcon(rolloverIcon,width,height,0,0,alpha);
        if(pressedIcon == null)
            pressedIcon = getImageIcon(rolloverIcon,width,height,2,2,ImageButton.OPACITY);

        //创建带图片的JButton
        JButton button;
        if(text == null)
            button = new JButton();
        else
            button = new JButton(text);
        button.setIgnoreRepaint(true);          //忽略重绘
        button.setBorder(null);                 //不绘制边框
        button.setContentAreaFilled(false);     //不绘制背景
        button.setIcon(defaultIcon);            //设置按钮默认时的图片
        button.setRolloverIcon(rolloverIcon);   //设置鼠标滑过按钮时的图片
        button.setPressedIcon(pressedIcon);     //设置鼠标按下按钮时的图片

        return button;
    }

    /**
     * 转换图片并返回ImageIcon
     */
    private static ImageIcon getImageIcon(ImageIcon defaultIcon,int width,
                                          int height,int x,int y,float alpha) {
        JFrame frame = new JFrame();
        //得到窗体的图形配置，并创建兼容的图片
        GraphicsConfiguration gc = frame.getGraphicsConfiguration();
        Image image = gc.createCompatibleImage(width,height,Transparency.TRANSLUCENT);

        //转换图片的透明值和位置
        Graphics2D g2D = (Graphics2D)image.getGraphics();
        if(alpha > 0) {
            Composite alphaTemp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alpha);
            g2D.setComposite(alphaTemp);
        }
        g2D.drawImage(defaultIcon.getImage(),x,y,null);
        g2D.dispose();

        return new ImageIcon(image);
    }

    /**
     * 获取自带(各个常量值)的图片并返回ImageIcon
     */
    private static ImageIcon getImageIcon(String fileName) {
        fileName = "images/" + fileName + ".png";
        return new ImageIcon(ImageButton.class.getResource(fileName));
    }


    //ImageButton 的各个常量
    public static final float THIRD_TRANSPARENCY = 0.3f;    //3/1透明
    public static final float TRANSLUCENCE = 0.5f;          //半透明
    public static final float TOWPART_TRANSPARENCY = 0.8f;  //3/2透明
    public static final float OPACITY = -1f;                //不透明
}
