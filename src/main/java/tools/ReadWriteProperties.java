package tools;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * 考勤管理应用 - 读写Properties文件
 *
 * @author wanglu lusalome@163.com
 * @version 2012-09-08 21:18
 */
public class ReadWriteProperties {

	/**
	 *  读取资源文件,并处理中文乱码
	 * @param filename
	 */
	public static void readPropertiesFile(String filename) {
		Properties properties = new Properties();
		try {
			InputStream inputStream = new FileInputStream(filename);
			properties.load(inputStream);
			inputStream.close(); // 关闭流
		} catch (IOException e) {
			e.printStackTrace();
		}
		String username = properties.getProperty("username");
		String passsword = properties.getProperty("password");
		String chinese = properties.getProperty("chinese");
		try {
			chinese = new String(chinese.getBytes("ISO-8859-1"), "GBK"); // 处理中文乱码
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println(username);
		System.out.println(passsword);
		System.out.println(chinese);
	}

	/**
	 *  读取资源文件,并处理中文乱码
	 * @param filename
	 * @return
	 */
	public static String readPropertiesFileChinese(String filename, String key, boolean isChinese) {
		Properties properties = new Properties();
		try {
			InputStream inputStream = new FileInputStream(filename);
			properties.load(inputStream);
			inputStream.close(); // 关闭流
		} catch (IOException e) {
			e.printStackTrace();
		}
		String value = properties.getProperty(key);
		try {
			value = new String(value.getBytes("ISO-8859-1"), "GBK"); // 处理中文乱码
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println(value);
		return value;
	}

	/**
	 * 读取资源文件: 根据key返回value
	 * @param filename
	 * @param key
	 */
	public static String readPropertiesFile(String filename, String key) {
		Properties properties = new Properties();
		try {
			InputStream inputStream = new FileInputStream(filename);
			properties.load(inputStream);
			inputStream.close(); // 关闭流
		} catch (IOException e) {
			e.printStackTrace();
		}
		String value = properties.getProperty(key);
		System.out.println(value);
		return value;
	}

	/**
	 *  读取XML文件,并处理中文乱码
	 * @param filename
	 */
	public static void readPropertiesFileFromXML(String filename) {
		Properties properties = new Properties();
		try {
			InputStream inputStream = new FileInputStream(filename);
			properties.loadFromXML(inputStream);
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String username = properties.getProperty("username");
		String passsword = properties.getProperty("password");
		String chinese = properties.getProperty("chinese"); // XML中的中文不用处理乱码，正常显示
		System.out.println(username);
		System.out.println(passsword);
		System.out.println(chinese);
	}

	/**
	 *  写Properties资源文件，含中文
	 * @param filename
	 */
	public static void writePropertiesFile(String filename) {
		Properties properties = new Properties();
		try {
			OutputStream outputStream = new FileOutputStream(filename);
			properties.setProperty("username", "myname");
			properties.setProperty("password", "mypassword");
			properties.setProperty("chinese", "中文");
			properties.store(outputStream, "author: lusalome@163.com");
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 *  写Properties资源文件，含中文
	 * @param filename
	 * @param property
	 * @param value
	 */
	public static void writePropertiesFile(String filename, String property, String value) {
		Properties properties = new Properties();
		try {
			OutputStream outputStream = new FileOutputStream(filename);
			try {
				value = new String(value.getBytes("GBK"), "ISO-8859-1"); // 处理中文乱码
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			properties.setProperty(property, value);
			properties.store(outputStream, "author: lusalome@163.com)");
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 *  写资源文件到XML文件，含中文
	 * @param filename
	 */
	public static void writePropertiesFileToXML(String filename) {
		Properties properties = new Properties();
		try {
			OutputStream outputStream = new FileOutputStream(filename);
			properties.setProperty("username", "myname");
			properties.setProperty("password", "mypassword");
			properties.setProperty("chinese", "中文");
			properties.storeToXML(outputStream, "author: lusalome@163.com");
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
