package com.rabbitmq.RPC;

import java.io.IOException;
import java.util.UUID;
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
public class RPCRecv {
	
	static String QUEUE_NAME = "rpc-queue";
	
	public static void main(String[] args) throws IOException, ShutdownSignalException, ConsumerCancelledException, InterruptedException, TimeoutException {
		
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
		
		// 响应QueueName ，服务端将会把要返回的信息发送到该Queue
		String responseQueue = channel.queueDeclare().getQueue();
		
		String correlationId = UUID.randomUUID().toString();
		
		BasicProperties props = new BasicProperties.Builder().replyTo(responseQueue).correlationId(correlationId).build();
		
		String message = "is_zhoufeng";
		channel.basicPublish("", QUEUE_NAME, props, message.getBytes("UTF-8"));
		
		System.out.println("消费者等待消息......");
		
		QueueingConsumer consumer = new QueueingConsumer(channel);
		//为消费者指定消费队列
		channel.basicConsume(responseQueue, true, consumer);
		
		while (true) {
			
			Delivery delivery = consumer.nextDelivery();
			
			if (delivery.getProperties().getCorrelationId().equals(correlationId)) {
				String result = new String(delivery.getBody());
				System.out.println("消费者接受消息：" + result);
			}
			
		}
	}
	
}
