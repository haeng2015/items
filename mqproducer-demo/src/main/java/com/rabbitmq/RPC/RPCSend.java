package com.rabbitmq.RPC;

import java.io.IOException;  
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.QueueingConsumer.Delivery;
import com.rabbitmq.client.ShutdownSignalException;

/**
 * @创建者:He.hp
 * @创建日期:2017年10月10日
 * @说明：远程调用模式。
 * 
 *             应用场景：当客户端想要调用服务器的某个方法来完成某项功能时，就可以使用rabbitMQ支持的PRC服务。
 */
public class RPCSend {
	
	static String QUEUE_NAME = "rpc-queue";
	
	public static void main(String[] args) throws IOException, TimeoutException, ShutdownSignalException, ConsumerCancelledException, InterruptedException {
		// 创建连接工厂
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("192.168.10.140");
		factory.setUsername("admin");
		factory.setPassword("123456");
		factory.setVirtualHost("/");
		
		// 获得连接
		Connection connection = factory.newConnection();
		
		// 获得通道
		Channel channel = connection.createChannel(4);
		
		// 声明定义队列
		channel.queueDeclare(QUEUE_NAME, true, false, false, null);
		
		// 创建队列消费者
		QueueingConsumer consumer = new QueueingConsumer(channel);
		
		// 指定消费队列
		channel.basicConsume(QUEUE_NAME, true, consumer);
		
		// 开始接受并消费消息
		while (true) {
			
			System.out.println("服务端等待接收消息..");
			Delivery delivery = consumer.nextDelivery();
			System.out.println("服务端成功收到消息..");
			
			BasicProperties properties = delivery.getProperties();
			
			String message = new String(delivery.getBody(), "UTF-8");
			
			String responseMessage = sayHello(message);
			
			BasicProperties responseProps = new BasicProperties.Builder().correlationId(properties.getCorrelationId()).build();
			
			// 将结果返回到客户端Queue
			channel.basicPublish("", properties.getReplyTo(), responseProps, responseMessage.getBytes("UTF-8"));
			
			// 向客户端确认消息
			channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
			System.out.println("服务端返回消息完成..");
			
		}
		
	}
	
	public static String sayHello(String name) {
		return "hello " + name;
	}
}
