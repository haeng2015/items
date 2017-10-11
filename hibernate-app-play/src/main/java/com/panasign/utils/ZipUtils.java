package com.panasign.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 压缩工具类
 * @copyright：柏年软件
 * @author： 吴樑
 */
public class ZipUtils {
	/**
	 * 创建成功
	 */
	 public static final String RESULT_SUCCESS = "文件打包完毕！";
	 /**
	  * 文件存在
	  */
	 public static final String RESULT_EXISTS = "相关文件已存在！";
	 /**
	  * 文件不存在
	  */
	 public static final String RESULT_NOT_EXISTS = "找不到相关文件！";
	 /**
	  * 文件不是目录
	  */
	 public static final String RESULT_NOT_DIR = "该文件不是目录！";
	 /**
	  * 没有子文件
	  */
	 public static final String RESULT_NO_CHILDREN = "该目录无任何文件！";
	
	/**
	 * 压缩指定文件夹下所有文件，打包成zip包，zip包名为此文件夹名，zip包位置在与此文件夹相同目录下
	 * （此方法压缩的文件列表如果有空文件夹，会自动过滤掉，不会添加进zip中）
	 * @param dirPath
	 * @param cover 如果zip压缩文件已存在，是否覆盖它：true【覆盖】，false【不覆盖】
	 * @return
	 * @throws IOException 
	 */
	public synchronized static String directoryFilesToZip(String dirPath, boolean cover) throws IOException{
		File dir = new File(dirPath);
		
		//文件不存在
		if(!dir.exists()){
			return RESULT_NOT_EXISTS;
		}
		
		//不是目录
		if(!dir.isDirectory()){
			return RESULT_NOT_DIR;
		}
		
		File[] files = dir.listFiles();
		if(files.length<1){
			return RESULT_NO_CHILDREN;
		}
		
		
		//创建目标压缩文件
		File zipFile = new File(dir.getAbsolutePath().replace(dir.getName(), "")+dir.getName()+".zip");
		if(zipFile.exists() && !cover){
			return RESULT_EXISTS;
		}else{
			//用zip格式流连接到zip文件准备输出
			ZipOutputStream zout = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipFile)));
			
			for(File file : files){
				//将要压缩的文件读入内存，再由zout格式流写入到zip压缩文件中
				if(file.isDirectory()){
					intoZip(zout, file, file.getName()+File.separator);
				}else{
					intoZip(zout, file, "");
				}
			}
			//关闭zip流
			if(zout!=null){
				zout.close();
			}
			
			return RESULT_SUCCESS;
		}
	}
	
	/**
	 * 将文件写入zip文件中
	 * @param zout	zip输出流
	 * @param file	要压缩的文件
	 * @param dirName zip文件中如果有文件夹，此项为文件夹名，如果dirName为空，则不是目录
	 * @throws IOException
	 */
	private synchronized static void intoZip(ZipOutputStream zout, File file, String dirName) throws IOException{
		if(file.isDirectory()){
			File[] children = file.listFiles();
			for(File child : children){
				intoZip(zout, child, dirName);
			}
		}else{
			//将要压缩的目标文件，加入 zip目录
			ZipEntry zip = new ZipEntry(dirName+file.getName());
			zout.putNextEntry(zip);
			
			FileInputStream fis = new FileInputStream(file);
			
			byte[] b = new byte[1024*10];
			int len = 0;
			while((len=fis.read(b))!=-1){
				zout.write(b, 0, len);
			}
			if(fis!=null){	//关闭文件流
				fis.close();
			}
		}
	}
}
