/**
 * 
 */
package com.panasign.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @ClassName: ClassLoaderUtils
 * @Description: 用来加载类，classpath下的资源文件，属性文件等。
 *               getExtendResource(StringrelativePath)方法，可以使用../符号来加载classpath外部的资源。
 * @Author TangZheng
 * @Date 2012-2-25 上午09:55:11
 * @Copyright: 版权归 HundSun 所有
 */
public final class ClassLoaderUtils {
	
    /**
     * log
     */
	private static Logger log = LoggerFactory.getLogger(ClassLoaderUtils.class);

	/**
	 * 加载Java类。 使用全限定类名
	 * 
	 * @param className
	 * @return
	 */
    public static Class<?> loadClass(String className) {
		try {
			return getClassLoader().loadClass(className);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("class not found '" + className + "'", e);
		}
	}

	/**
	 * 得到类加载器
	 * 
	 * @return
	 */
	public static ClassLoader getClassLoader() {
	    //return Thread.currentThread().getContextClassLoader();
		return ClassLoaderUtils.class.getClassLoader();
	}

	/**
	 * 提供相对于classpath的资源路径，返回文件的输入流
	 * 
	 * @param relativePath必须传递资源的相对路径。是相对于classpath的路径。如果需要查找classpath外部的资源，需要使用 ../来查找
	 * @return 文件输入流
	 * @throws IOException
	 * @throws MalformedURLException
	 */
	public static InputStream getStream(String relativePath) throws MalformedURLException, IOException {
		if (!relativePath.contains("../")) {
			return getClassLoader().getResourceAsStream(relativePath);
		} else {
			return ClassLoaderUtils.getStreamByExtendResource(relativePath);
		}
	}

	/**
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static InputStream getStream(URL url) throws IOException {
		if (url != null) {
			return url.openStream();
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @param relativePath必须传递资源的相对路径。是相对于classpath的路径。如果需要查找classpath外部的资源，需要使用 ../来查找
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public static InputStream getStreamByExtendResource(String relativePath)
			throws MalformedURLException, IOException {
		return ClassLoaderUtils.getStream(ClassLoaderUtils.getExtendResource(relativePath));
	}

	/**
	 * 提供相对于classpath的资源路径，返回属性对象，它是一个散列表
	 * 
	 * @param resource
	 * @return
	 */
	public static Properties getProperties(String resource) {
		Properties properties = new Properties();
		try {
			properties.load(getStream(resource));
		} catch (IOException e) {
			throw new RuntimeException("couldn't load properties file '"
					+ resource + "'", e);
		}
		return properties;
	}

	/**
	 * 
	 * @param relativePath 必须传递资源的相对路径。是相对于classpath的路径。如果需要查找classpath外部的资源，需要使用../来查找
	 * @return 资源的绝对URL
	 * @throws MalformedURLException
	 */
	public static URL getExtendResource(String relativePath) {
		log.info("传入的相对路径：" + relativePath);
		// log.info(Integer.valueOf(relativePath.indexOf("../")));
		if (!relativePath.contains("../")) {
			return ClassLoaderUtils.getAbsolutePathByResource(relativePath);
		}
		String classPathAbsolutePath = ClassLoaderUtils.getAbsolutePathByResource("").toString();
		if (relativePath.substring(0, 1).equals("/")) {
			relativePath = relativePath.substring(1);
		}
		log.info(Integer.valueOf(relativePath.lastIndexOf("../")).toString());
		String wildcardString = relativePath.substring(0, relativePath.lastIndexOf("../") + 3);
		relativePath = relativePath.substring(relativePath.lastIndexOf("../") + 3);
		int containSum = ClassLoaderUtils.containSum(wildcardString, "../");
		classPathAbsolutePath = ClassLoaderUtils.cutLastString(classPathAbsolutePath, "/", containSum);
		String resourceAbsolutePath = classPathAbsolutePath + relativePath;
		log.info("绝对路径：" + resourceAbsolutePath);
		URL resourceAbsoluteURL = null;
        try
        {
            resourceAbsoluteURL = new URL(resourceAbsolutePath);
        } catch (MalformedURLException e)
        {
            log.error("url地址错误", e);
        }
		return resourceAbsoluteURL;
	}

	/**
	 * 
	 * @param source
	 * @param dest
	 * @return
	 */
	private static int containSum(String source, String dest) {
		int containSum = 0;
		int destLength = dest.length();
		while (source.contains(dest)) {
			containSum = containSum + 1;
			source = source.substring(destLength);
		}
		return containSum;
	}

	/**
	 * 
	 * @param source
	 * @param dest
	 * @param num
	 * @return
	 */
	private static String cutLastString(String source, String dest, int num) {
		for (int i = 0; i < num; i++) {
			source = source.substring(0, source.lastIndexOf(dest, source.length() - 2) + 1);
		}
		return source;
	}

	/**
	 * 将相对于classpath的资源文件路径转化为该文件的url, 若传入""则返回当前Classpath的绝对URL路径
	 *     .toString()或.getPath()或.getFile()返回文件路径
	 * 
	 * @param resClasspath 相对于classpath的资源文件路径
	 * @return
	 */
	public static URL getAbsolutePathByResource(String resClasspath) {
		log.info("传入的相对于classpath的路径：" + resClasspath);
		return getClassLoader().getResource(resClasspath);
	}

}
