/**
 * 
 */
package com.panasign.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: FileUtils
 * @Description: 封装基本的文件操作
 * @Author TangZheng
 * @Date 2012-2-23 上午09:31:11
 * @Copyright: 版权归 HundSun 所有
 */
public class FileUtils extends org.apache.commons.io.FileUtils {

	/**
	 * log
	 */
	private static final Logger log = LoggerFactory.getLogger(FileUtils.class);

	public static final String FILE_URL_HEADER = "file:///";

	public static final String FILE_SEPARATOR_CHAR = "_";

	/**
	 * 获取文件后缀名
	 * 
	 * @param path
	 * @return
	 */
	public static String getFileExtension(String path) {
		int point = path.lastIndexOf('.');
		int len = path.length();
		if (point == -1 || point == len - 1) {
			return "";
		} else {
			return path.substring(point + 1, len);
		}
	}

	/**
	 * 生产文件 如果文件所在路径不存在则生成路径
	 * 
	 * @param fileName
	 *            文件名 带路径
	 * @param isDirectory
	 *            是否为路径
	 * @return
	 */
	public static File buildFile(String fileName, boolean isDirectory) {
		File target = new File(fileName);
		if (isDirectory) {
			target.mkdirs();
		} else {
			if (!target.getParentFile().exists()) {
				target.getParentFile().mkdirs();
				target = new File(target.getAbsolutePath());
			}
		}
		return target;
	}

	/**
	 * 获取当前classpath的绝对路径
	 * 
	 * @return
	 */
	public static String getAbsoluteClassPath() {
		URL url = Thread.currentThread().getContextClassLoader().getResource("");
		return url.getPath();// .getFile();
	}

	/**
	 * 获取当前web应用的根路径，即相对classpath的绝对路径取父级路径
	 * 
	 * @return
	 */
	public static String getWebAppRootPath() {
		String classPath = getAbsoluteClassPath();
		String webPath = classPath.substring(0, classPath.length() - 17);
		return webPath;
	}

	/**
	 * 新建目录
	 * 
	 * @param folderPath
	 *            String 如 c:/fqf
	 * @return boolean
	 */
	public static void newFolder(String dir) {
		try {
			if(StringUtils.isNotBlank(dir)){
				File myDir = new File(dir);
				if (!myDir.exists()) {
					myDir.mkdirs();
				}
			}
		} catch (Exception e) {
			System.out.println("新建目录操作出错");
			log.error("新建目录操作出错");
		}
	}

	/**
	 * 新建空文件
	 * 
	 * @param filePath
	 *            String 文件路径及名称 如c:/aa/gg/fqf.xml
	 * @return boolean
	 */
	public static void newFile(String filePath) {
		newFile(filePath, "");
	}

	/**
	 * 新建文件
	 * 
	 * @param filePath
	 *            String 文件路径及名称 如c:/aa/gg/fqf.xml
	 * @param fileContent
	 *            String 文件内容
	 * @return boolean
	 */
	public static void newFile(String filePath, String fileContent) {
		try {
			File myFile = new File(filePath);
			if (!myFile.exists()) {
				// String dir = filePath.substring(0, filePath.lastIndexOf("/") + 1);
				// System.out.println(File.separator);
				// System.out.println(dir);
				newFolder(myFile.getParent());
				myFile.createNewFile();
			}
			if (StringUtils.isNotBlank(fileContent)) {
				FileWriter resultFile = new FileWriter(myFile);
				PrintWriter _myFile = new PrintWriter(resultFile);
				_myFile.println(fileContent);
				resultFile.close();
			}
		} catch (Exception e) {
			System.out.println("新建文件操作出错");
			log.error("新建文件操作出错");
		}
	}

	/**
	 * 删除文件
	 * 
	 * @param filePathAndName
	 *            String 文件路径及名称 如c:/fqf.txt
	 * @param fileContent
	 *            String
	 * @return boolean
	 */
	public static void delFile(String filePathAndName) {
		try {
			String filePath = filePathAndName;
			filePath = filePath.toString();
			java.io.File file = new java.io.File(filePath);
			if (file.isFile()) {
				file.delete();
			}
		} catch (Exception e) {
			System.out.println("删除文件操作出错");
			e.printStackTrace();

		}

	}

	/**
	 * 删除文件夹
	 * 
	 * @param filePathAndName
	 *            String 文件夹路径及名称 如c:/fqf
	 * @param fileContent
	 *            String
	 * @return boolean
	 */
	public static void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); // 删除完里面所有内容
			String filePath = folderPath;
			filePath = filePath.toString();
			java.io.File myFilePath = new java.io.File(filePath);
			myFilePath.delete(); // 删除空文件夹

		} catch (Exception e) {
			System.out.println("删除文件夹操作出错");
			e.printStackTrace();
		}
	}

	/**
	 * 删除文件夹里面的所有文件,保留空文件夹
	 * 
	 * @param path
	 *            String 文件夹路径 如 c:/fqf
	 */
	public static void delAllFile(String path) {
		File file = new File(path);
		if (!file.exists()) {
			return;
		}
		if (!file.isDirectory()) {
			return;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
				// delFolder(path + "/" + tempList[i]);// 再删除空文件夹
			}
		}
	}

	/**
	 * 复制单个文件
	 * 
	 * @param oldPath
	 *            String 原文件路径 如：c:/fqf.txt
	 * @param newPath
	 *            String 复制后路径 如：f:/fqf.txt
	 * @return boolean
	 */
	@SuppressWarnings("unused")
	public static void copyFile(String oldPath, String newPath) {
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) { // 文件存在时
				InputStream inStream = new FileInputStream(oldPath); // 读入原文件
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
				int length;
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; // 字节数 文件大小
					// System.out.println(bytesum);
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
				fs.close();
			}
		} catch (Exception e) {
			System.out.println("复制单个文件操作出错");
			e.printStackTrace();

		}

	}

	/**
	 * 复制整个文件夹内容
	 * 
	 * @param oldPath
	 *            String 原文件路径 如：c:/fqf
	 * @param newPath
	 *            String 复制后路径 如：f:/fqf/ff
	 * @return boolean
	 */
	public static void copyFolder(String oldPath, String newPath) {

		try {
			(new File(newPath)).mkdirs(); // 如果文件夹不存在 则建立新文件夹
			File a = new File(oldPath);
			String[] file = a.list();
			File temp = null;
			for (int i = 0; i < file.length; i++) {
				if (oldPath.endsWith(File.separator)) {
					temp = new File(oldPath + file[i]);
				} else {
					temp = new File(oldPath + File.separator + file[i]);
				}

				if (temp.isFile()) {
					FileInputStream input = new FileInputStream(temp);
					FileOutputStream output = new FileOutputStream(newPath + "/" + (temp.getName()).toString());
					byte[] b = new byte[1024 * 5];
					int len;
					while ((len = input.read(b)) != -1) {
						output.write(b, 0, len);
					}
					output.flush();
					output.close();
					input.close();
				}
				if (temp.isDirectory()) {// 如果是子文件夹
					copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
				}
			}
		} catch (Exception e) {
			System.out.println("复制整个文件夹内容操作出错");
			e.printStackTrace();
		}
	}

	/**
	 * 移动文件到指定目录
	 * 
	 * @param oldPath
	 *            String 如：c:/fqf.txt
	 * @param newPath
	 *            String 如：d:/fqf.txt
	 */
	public static void moveFile(String oldPath, String newPath) {
		copyFile(oldPath, newPath);
		delFile(oldPath);
	}

	/**
	 * 移动文件夹到指定目录
	 * 
	 * @param oldPath
	 *            String 如：c:/fqf.txt
	 * @param newPath
	 *            String 如：d:/fqf.txt
	 */
	public static void moveFolder(String oldPath, String newPath) {
		copyFolder(oldPath, newPath);
		delFolder(oldPath);
	}

}
