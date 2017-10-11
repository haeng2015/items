package com.rabbitmq.routing;

import java.util.Random;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

/**
 * @创建者:He.hp
 * @创建日期:2017年9月26日
 * @说明：routing模式，有选择性的接受消息（按照一定的路由规则）。 让日志接收者能够订阅部分消息。
 */
public class RoutingRecv {
	
	private final static String EXCHANGE_NAME = "Routing-exchange";
	private static final String[] SEVERITIES = { "info", "warning", "error" };
	
	public static void main(String[] argv) throws java.io.IOException, java.lang.InterruptedException, TimeoutException {
		// 打开连接和创建频道，与发送端一样
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("192.168.10.140");
		factory.setUsername("admin");
		factory.setPassword("123456");
		
		Connection connection = factory.newConnection();
		final Channel channel = connection.createChannel();
		
		// 指定声明direct类型的转发器
		channel.exchangeDeclare(EXCHANGE_NAME, "direct");
		
		//随机得到一个临时的队列，退出时即可销毁
		String queueName = channel.queueDeclare().getQueue();
		
		// 获得routingkey
		String severity = getSeverity();
		// 将queue与exchange绑定,获得指定binding_key
		channel.queueBind(queueName, EXCHANGE_NAME, severity);
		System.out.println(" [*] Waiting for " + severity + " logs. To exit press CTRL+C");
		
		// 创建消费者，方法一
		QueueingConsumer consumer = new QueueingConsumer(channel);
		channel.basicConsume(queueName, true, consumer);
		
		while (true) {
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			String message = new String(delivery.getBody());
			
			System.out.println(" [x] Received '" + message + "'");
		}
		// 创建消费者，方法二（创建队列消费者并将获得的队列名注入）
		// Consumer consumer = new DefaultConsumer(channel) {
		// // 实现方法handleDelivery，消费消息
		// @Override
		// public void handleDelivery(String consumerTag, Envelope
		// envelope, AMQP.BasicProperties properties, byte[] body)
		// throws IOException {
		// String message = new String(body, "UTF-8");
		// System.out.println(" [x] Received '" +
		// envelope.getRoutingKey() + "':'" + message + "'");
		// }
		// };
		// channel.basicConsume(queueName, true, consumer);
	}
	
	/**
	 * 随机产生一种日志类型
	 * 
	 * @return
	 */
	private static String getSeverity() {
		Random random = new Random();
		int ranVal = random.nextInt(SEVERITIES.length);
		return SEVERITIES[ranVal];
	}
}
