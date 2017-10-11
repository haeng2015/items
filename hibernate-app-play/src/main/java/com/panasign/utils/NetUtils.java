package com.panasign.utils;
///**
// * 
// */
//package com.panasign.common.util;
//
//import java.io.IOException;
//import java.net.InetAddress;
//import java.net.UnknownHostException;
//import java.util.regex.Pattern;
//
//import org.jsoup.Connection;
//import org.jsoup.Jsoup;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
///**
// * @ClassName: NetUtils
// * @Description:
// * @Author TangZheng
// * @Date 2013-11-15 上午11:19:25
// * @Copyright: 版权归 HundSun 所有
// */
//@SuppressWarnings("unused")
//public class NetUtils {
//
//	/**
//	 * log
//	 */
//	private static final Logger log = LoggerFactory.getLogger(NetUtils.class);
//	
//	/**
//	 * boolean b = Pattern.matches(IP_REG, "112.234.1.1");
//	 */
//	public static final String IP_REG = "((25[0-5])|(2[0-4]\\d)|(1\\d\\d)|([1-9]\\d)|\\d)(\\.((25[0-5])|(2[0-4]\\d)|(1\\d\\d)|([1-9]\\d)|\\d)){3}";
//	
//	/**
//	 * boolean b = Pattern.matches(DOMAIN_REG, "www.baidu.com");
//	 */
//	public static final String DOMAIN_REG = "[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(\\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+\\.?";
//
//	/**
//	 * 
//	 * 获得本机的所有网络地址
//	 * 
//	 * @return
//	 */
//	public static InetAddress[] getLocalhostAddress() {
//		InetAddress[] address = null;
//		try {
////			return InetAddress.getLocalHost();
//			address = getInetAddressByDomain(InetAddress.getLocalHost().getHostName());
//
//		} catch (UnknownHostException e) {
//			log.error("本机地址解析失败:", e.getCause());
//		}
//		return address;
//	}
//
//	/**
//	 * 
//	 * 获得某域名下的所有网络地址
//	 * 	for(InetAddress addr: address){
//	 *		System.out.println(addr.getHostName()+"======"+addr.getHostAddress());
//	 *	}
//	 * @param domain
//	 *            域名
//	 * 
//	 * @return
//	 */
//	public static InetAddress[] getInetAddressByDomain(String domain) {
//		InetAddress[] address = null;
//		try {
//
//			address = InetAddress.getAllByName(domain);
//
//		} catch (UnknownHostException e) {
//			log.error("{}网络域名地址解析失败:", domain, e.getCause());
//		}
//		return address;
//
//	}
//	
//	/**
//	 * 通过JSoup访问www.ip138.com, 从页面中提取出本机的公网ip
//	 * 得到本机的外网ip, 出现异常时返回空串""
//	 * @return
//	 */
//	public static String getPublicIP() {
//		String ip = "";
//		
//		org.jsoup.nodes.Document doc = null;
//		Connection con = null;
//
//		// 连接 www.ip138.com 网页
//		con = Jsoup.connect("http://iframe.ip138.com/ic.asp").timeout(10000); 
//
//		try {
//			doc = con.get();
//			
//			// 获得包含本机ip的文本串：您的IP是：[xxx.xxx.xxx.xxx] 来自：中国
//			org.jsoup.select.Elements els = doc.body().select("center");
//			for (org.jsoup.nodes.Element el : els) {
//				ip = el.text(); 
//			}
//			
//			// 从文本串过滤出ip，用正则表达式将非数字和.替换成空串""
//			ip = ip.replaceAll("[^0-9.]", ""); 
//		} catch (IOException e) {
//			e.printStackTrace();
//			return ip;
//		}
//		
//		return ip;
//	}
//
//	public static void main(String[] args) {
//		String host = "www.21mhealth.cn";
//
////		InetAddress address = null;
////
////		try {
////
////			address = InetAddress.getByName(host);
////
////		} catch (UnknownHostException e) {
////			System.out.println("Unknown host");
////			System.exit(0);
////		}
////
////		byte[] ip = address.getAddress();
////
////		for (int i = 0; i < ip.length; i++) {
////			if (i > 0) System.out.print(".");
////			System.out.print(ip[i] & 0xff);
////		}
////
////		System.out.println();
//		
//		
//		InetAddress[] address = getLocalhostAddress();
//		for(InetAddress addr: address){
//			System.out.println(addr.getCanonicalHostName()+"======"+addr.getHostAddress());
//		}
//		
////		address = getInetAddressByDomain(host);
////		for(InetAddress addr: address){
////			System.out.println(addr.getHostName()+"======"+addr.getHostAddress());
////		}
////		
////		System.out.println(Pattern.matches(IP_REG, "223.6.249.180"));
////		
////		System.out.println(Pattern.matches(DOMAIN_REG, "www.21mhealth.com"));
//		
//		
//		System.out.println(getPublicIP());
//		
//	}
//
//}
