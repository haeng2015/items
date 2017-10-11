package com.rabbitmq.topics;

import java.util.UUID;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @创建者:He.hp
 * @创建日期:2017年9月26日
 * @说明：toupics 基于一定的pattern匹配的接受消息

运行：先启动两个消费者，在启动生产者。

主题转发（Topic Exchange）
发往主题类型的转发器的消息不能随意的设置选择键（routing_key），必须是由点隔开的一系列的标识符组成。
标识符可以是任何东西，但是一般都与消息的某些特性相关。一些合法的选择键的例子："stock.usd.nyse", "nyse.vmw","quick.orange.rabbit".
你可以定义任何数量的标识符，上限为255个字节。
绑定键和选择键的形式一样。主题类型的转发器背后的逻辑和直接类型的转发器很类似：一个附带特殊的选择键将会被转发到绑定键与之匹配的队列中。
需要注意的是：关于绑定键有两种特殊的情况。  *可以匹配一个标识符。   #可以匹配0个或多个标识符。
 */
public class TopicsSend {
	// 交换器名称
	private final static String EXCHANGE_NAME = "Topics-exchange";
	static String[] routing_keys = new String[] { "kernal.info", "cron.warning", "auth.info", "kernel.critical" };
	
	/**
	 * 创建连接连接到MabbitMQ
	 */
	public static void main(String[] argv) throws java.io.IOException {
		try {
			ConnectionFactory factory = new ConnectionFactory();
			// 设置MabbitMQ所在主机ip或者主机名
			factory.setHost("192.168.10.140");
			factory.setUsername("admin");
			factory.setPassword("123456");
			
			// 创建一个连接
			Connection connection = factory.newConnection();
			// 创建一个频道
			Channel channel = connection.createChannel();
			
			// 指定声明topic类型的exchange
			channel.exchangeDeclare(EXCHANGE_NAME, "topic");
			
			for (String routing_key : routing_keys) {
				// 发送的消息
				String message = "hello Topics! '" + routing_key + "':" + UUID.randomUUID().toString();
				// 往交换器中发出一条消息
				channel.basicPublish(EXCHANGE_NAME, routing_key, null, message.getBytes());
				System.out.println(" [x] Sent routingKey = " + routing_key + " ,message = " + message + ".");
			}
			
			// 关闭频道和连接
			channel.close();
			connection.close();
			
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
	}
}
