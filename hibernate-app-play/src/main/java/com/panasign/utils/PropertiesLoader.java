package com.panasign.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

/**
 * @ClassName: PropertiesLoader
 * @Description: 
 * @Author TangZheng
 * @Date 2013-12-15 上午09:31:11
 * @Copyright: 版权归 HundSun 所有
 */
public class PropertiesLoader {

	private static Logger log = LoggerFactory.getLogger(PropertiesLoader.class);
	
	private static ResourceLoader resourceLoader = new DefaultResourceLoader();
	
	private static Properties props;

	static {
		loadProperty("classpath:config.properties");
//		loadProperty("META-INF/config.properties");
	}
	
	/**
	 * 配置文件绝对路径或文件相对路径
	 * @param filePath
	 */
	public static synchronized void initProperty(String filePath) {
		FileInputStream fis= null;
		if (props == null) {
			log.info("{}属性配置文件初始化...", filePath);
			props = new Properties();
			try {
				fis = new FileInputStream(new File(filePath));
				props.load(fis);
			} catch (FileNotFoundException e) {
				log.error("{}属性配置文件不存在：{}", filePath, e.getMessage());
			} catch (IOException e) {
				log.error("{}属性配置文件读取失败：{}", filePath, e.getMessage());
			} finally {
				if(fis != null){
					try {
						fis.close();
					} catch (IOException e) {
						log.error("{}属性配置文件流关闭失败：{}", filePath, e.getMessage());
					}
				}
			}
		}
	}

	/**
	 * 配置文件classpath相对路径
	 * @param propsClasspath
	 */
	public static synchronized void loadProperty(String propsClasspath) {
		if (props == null) {
			InputStream is = null;
			props = new Properties();
			try {
				is = resourceLoader.getResource(propsClasspath).getInputStream();
//				is = ClassLoaderUtils.getStream(propsClasspath);
				props.load(is);
			} catch (MalformedURLException e) {
				log.error("{}属性配置文件URL指定错误：{}", propsClasspath, e.getMessage());
			} catch (IOException e) {
				log.error("{}属性配置文件读取失败：{}", propsClasspath, e.getMessage());
			} finally {
				props = null;
			    IOUtils.closeQuietly(is);  
			} 
		}
	}

	/**
	 * 取出Property，但以System的Property优先.
	 * @param key
	 * @return
	 */
	public static String getProperty(String key) {
		String systemProperty = System.getProperty(key);
		return StringUtils.isBlank(systemProperty) ? props.getProperty(key) : systemProperty;
	}
	
	/**
	 * 在控制台上打出，查看一下系统中的Properties属性。
	 * @author: Wu.Liang
	 */
	public void showSystemProperties(){
		Set<Entry<Object, Object>> entrySet = System.getProperties().entrySet();
		for(Entry<Object, Object> entry:entrySet){
			log.info(entry.getKey()+" <----> "+entry.getValue());
		}
	}
}
