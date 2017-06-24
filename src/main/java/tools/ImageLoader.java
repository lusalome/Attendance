package tools;

import javax.swing.ImageIcon;

/**
 * 考勤管理应用 - 图片加载类:
 * (1)从jar包中images文件夹加载（项目后期）
 * (2)从项目自建images文件夹中加载（项目前期）
 *
 * @author wanglu lusalome@163.com
 * @version 2012-09-08 21:18
 */
public class ImageLoader {

    /**
     * 类的静态实例唯一
     */
    private static ImageLoader imgLoader = null;

    public static ImageLoader getInstance() {
        if (imgLoader == null)
            imgLoader = new ImageLoader();
        return imgLoader;
    }

    /**
     * @param imageName 图片名称
     * @return 图片icon对象
     */
    public final ImageIcon getIcon(String imageName) {
        return new ImageIcon(imgLoader.getClass().getClassLoader().getResource("icons/"+imageName));
    }

    /**
     * MessageBox的图片加载
     *
     * @param imageName 图片名称
     * @return 图片icon对象
     */
    public final ImageIcon getMessageBoxIcon(String imageName) {
        return new ImageIcon(imgLoader.getClass().getClassLoader().getResource("icons/" + imageName));
    }

}