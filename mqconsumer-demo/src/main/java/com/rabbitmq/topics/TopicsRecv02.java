package com.rabbitmq.topics;

import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

/**
 * @创建者:He.hp
 * @创建日期:2017年10月9日
 * @说明：toupics 基于一定的pattern匹配的接受消息
 */
public class TopicsRecv02 {
	// 交换器名称
	private final static String EXCHANGE_NAME = "Topics-exchange";
	static String[] routing_keys = new String[] { "kernal.info", "cron.warning", "auth.info", "kernel.critical" };
	
	public static void main(String[] argv) throws java.io.IOException, java.lang.InterruptedException, TimeoutException {
		// 打开连接和创建频道，与发送端一样
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("192.168.10.140");
		factory.setUsername("admin");
		factory.setPassword("123456");
		
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		
		// 指定声明topic类型的exchange
		channel.exchangeDeclare(EXCHANGE_NAME, "topic");
		
		// 随机得到一个临时的队列，退出时即可销毁
		String queueName = channel.queueDeclare().getQueue();
		
		// 将queue与exchange绑定,限定只接收所有与kernel相关的消息
		channel.queueBind(queueName, EXCHANGE_NAME, "*.info");
		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
		
		// 创建队列消费者并将获得的队列名注入
		QueueingConsumer consumer = new QueueingConsumer(channel);
		channel.basicConsume(queueName, true, consumer);
		
		while (true) {
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			String message = new String(delivery.getBody());
			String routingKey = delivery.getEnvelope().getRoutingKey();
			
			System.out.println(" [x] Received routingKey = " + routingKey + ",msg = " + message + ".");
		}
	}
}
