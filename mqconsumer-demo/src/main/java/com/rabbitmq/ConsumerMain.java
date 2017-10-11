package com.rabbitmq;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @创建者:He.hp
 * @创建日期:2017年9月27日
 * @说明：消费者启动类
 */
public class ConsumerMain {
	
	/**
	 * @author：He.hp
	 * @date：2017年9月30日
	 * @function：TODO 先启动消费者，监听接收消息，再启动生产者发送消息。
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			new ClassPathXmlApplicationContext("classpath:/spring-rabbitmq/spring-context.xml");
			System.out.println("消费者启动，处于监听状态......");
		} catch (Exception e) {
			System.out.println("【启动异常】：" + e.getMessage());
		}
	}
	
}
