package com.panasign.utils;

import java.text.DecimalFormat;

/**
 * @版权所有：柏年软件
 * @项目名称:柏年云项目第一期
 * @创建者:He.hp
 * @创建日期:2017年9月5日
 * @说明：文件大小单位转换(B KB MB	GB)
 */
public class FileUnitConversionUtils {
	
	public static String getPrintSize(long size) {
		
		DecimalFormat df = new DecimalFormat("####.00"); 
		double fileSize = 0.00;
		
		if (size == 0) {
			return "0B";
		}
		
		// 如果字节数少于1024，则直接以B为单位，否则先除于1024
		if (size < 1024) {
			
			return String.valueOf(size) + "B";
		} else {
			
			fileSize = size / 1024.0;
		}
		
		// 如果原字节数除于1024之后，少于1024，则可以直接以KB作为单位,因为还没有到达要使用另一个单位的时候,接下去以此类推
		if (fileSize < 1024) {
			
			return df.format(fileSize) + "KB";
		} else {
			
			fileSize = fileSize / 1024.0;
		}
		
		if (fileSize < 1024) {
			
			return df.format(fileSize) + "MB";
			
		} else {
			
			fileSize = fileSize / 1024.0;
		}
		
		return df.format(fileSize) + "GB";
	}
	
	
	public static void main(String[] args) {
		System.out.println(getPrintSize(20833001));  //19.8MB
		System.out.println(getPrintSize(3200 ));  //3.12KB
	}
	
	
	
	
	
}
