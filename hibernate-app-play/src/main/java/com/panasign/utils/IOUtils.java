package com.panasign.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: IOUtils
 * @Description: 基本IO操作
 * @Author TangZheng
 * @Date 2013-06-13 上午10:22:21
 * @Copyright: 版权归 HundSun 所有
 */
public class IOUtils extends org.apache.commons.io.IOUtils {

	private static Logger log = LoggerFactory.getLogger(IOUtils.class);

	/**
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	public static byte[] getContent(File file) throws IOException {
		long fileSize = file.length();
		if (fileSize > Integer.MAX_VALUE) {
			log.info("file too big...");
			return null;
		}

		FileInputStream fi = new FileInputStream(file);

		byte[] buffer = new byte[(int) fileSize];

		int offset = 0;

		int numRead = 0;

		while (offset < buffer.length

		&& (numRead = fi.read(buffer, offset, buffer.length - offset)) >= 0) {
			offset += numRead;
		}

		// 确保所有数据均被读取
		if (offset != buffer.length) {
			throw new IOException("Could not completely read file " + file.getName());
		}
		fi.close();

		return buffer;
	}
	
    /**
     * 将byte数组内容写入文件
     * @param filePath
     * @param content
     * @throws IOException
     */
	public static void createFile(String filePath, byte[] content) throws IOException {
		File myFile = new File(filePath);
		if (!myFile.exists()) {
			FileUtils.newFile(filePath);
				//throw new IOException(filePath + " File Parameter does not reference a file that exists");
		} else {
			if (!myFile.isFile()) {
				throw new IOException(filePath + " File Parameter exists, but does not reference a file");
			} else {
				if (!myFile.canWrite()) {
					if (!myFile.setWritable(true))
						throw new IOException(filePath + " File exists and is a file, but cannot be write.");
				}
			}
		}
		FileOutputStream fos = new FileOutputStream(myFile);
	
		fos.write(content);
		fos.close();
	}
	
	/*
	 * 读取指定输入流中数据写到指定目录文件
	 */
	public static void readStreamToFile(InputStream is, String filePath) {
		RandomAccessFile fos = null;
		BufferedInputStream in = null;
		try {
			// 把文件定义成可读写的
			fos = new RandomAccessFile(new File(filePath), "rw");
			in = new BufferedInputStream(is);// 创建使用指定字符集的BufferedInputStream

			// 定义一个大小为1024的字节数组
			byte[] buf = new byte[1024];

			// 从输入流中读出字节到定义的字节数组
			int len;

			// 循环读入字节，然后写到文件输出流中
			while ((len = in.read(buf, 0, buf.length)) != -1) {
				fos.write(buf, 0, len);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 读取指定输入流中数据，生成字符串返回
	 */
	public static String readStreamToString(InputStream is) {
		// InputStreamReader in = new InputStreamReader(con.getInputStream(),"UTF-8");
		// StringBuffer sb = new StringBuffer();
		// int c;
		// while ((c = in.read()) != -1) {
		//     sb.append((char) c);
		// }
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(is, "UTF-8"));// 创建使用指定字符集的InputStreamReader
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String line = null;
		StringBuilder sb = new StringBuilder();
		try {
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return sb.toString();
	}

}
