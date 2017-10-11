package com.rabbitmq.publishSubscribe;

import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;

/**
 * @创建者:He.hp
 * @创建日期:2017年9月26日
 * @说明：public/subscribe 发布/订阅模式，一次性向多个消费者发送消息.
 * 随机创建一个队列，然后将队列与转发器绑定，然后将消费者与该队列绑定，然后打印到控制台。
 * 将两个接收端运行，然后运行3次发送端：
 *                     
 */
public class PublicSubscribeRecv02 {
	// 队列名称
	private final static String EXCHANGE_NAME = "PublicSubscribe-exchange";
	
	public static void main(String[] argv) throws java.io.IOException, java.lang.InterruptedException, TimeoutException {
		// 打开连接和创建频道，与发送端一样
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("192.168.10.140");
		factory.setUsername("admin");
		factory.setPassword("123456");
		
		Connection connection = factory.newConnection();
		final Channel channel = connection.createChannel();
		
		// 指定声明exchange
		channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
		// 创建一个非持久的、唯一的且自动删除的临时队列
		String queueName = channel.queueDeclare().getQueue();
		
		// 将queue与exchange绑定
		channel.queueBind(queueName, EXCHANGE_NAME, "");
		
		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
		
		// 创建队列消费者，指定消费队列,切记这里第一个参数是queue，而并非QUEUE_NAME,第二个参数为自动应答，无需手动应答
		channel.basicConsume(queueName, true, new DefaultConsumer(channel) {
			// 实现方法handleDelivery，消费消息
			public void handleDelivery(String consumerTag, com.rabbitmq.client.Envelope envelope, com.rabbitmq.client.AMQP.BasicProperties properties, byte[] body) throws java.io.IOException {
				String routingKey = envelope.getRoutingKey();
				long deliveryTag = envelope.getDeliveryTag();
				channel.basicAck(deliveryTag, true);
				System.out.println("routingKey is :" + routingKey);
				System.out.println(" [x] Received： '" + new String(body) + "'");
			};
		});
	}
}
