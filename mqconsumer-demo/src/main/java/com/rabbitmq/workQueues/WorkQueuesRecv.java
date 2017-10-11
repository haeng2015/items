package com.rabbitmq.workQueues;

import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

/**
 * @创建者:He.hp
 * @创建日期:2017年9月26日
 * @说明：work queues为工作队列模式，其为不同的worker分配task）
 
从结果可知：默认的，RabbitMQ会一个一个的发送信息给下一个消费者(consumer)，而不考虑每个任务的时长等等，且是一次性分配，
并非一个一个分配。平均的每个消费者将会获得相等数量的消息。这样分发消息的方式叫做round-robin。

 */
public class WorkQueuesRecv {
	// 队列名称，由于发送者与接受者的启动顺序无法确定，故都需要指定队列名称，且必须相同
	private final static String QUEUE_NAME = "WorkQueues-queue";
	
	static boolean ack = false ; //打开应答机制 
	static boolean durable = true;  //消息是否持久化（true为持久化）
	static int prefetchCount = 1; //同一时间接受的消息数量为1，防止消费不匀
	
	public static void main(String[] argv) throws java.io.IOException, java.lang.InterruptedException, TimeoutException {
		// 打开连接和创建频道，与发送端一样
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("192.168.10.140");
		factory.setUsername("admin");
		factory.setPassword("123456");
		
		Connection connection = factory.newConnection();
		final Channel channel = connection.createChannel();
		
		// 区分不同工作进程的输出
		int hashCode = WorkQueuesRecv.class.hashCode();
		
		// 声明队列，主要为了防止消息接收者先运行此程序，队列还不存在时创建队列。
		channel.queueDeclare(QUEUE_NAME, durable, false, false, null);
		
		channel.basicQos(prefetchCount); //指定一次只接受一条消息
		System.out.println(hashCode + " [*] Waiting for messages. To exit press CTRL+C");
		
		//创建消费者
		QueueingConsumer consumer = new QueueingConsumer(channel);
		// 指定消费队列
		channel.basicConsume(QUEUE_NAME, ack, consumer);

		while (true) {
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			
			String message = new String(delivery.getBody());
			System.out.println(hashCode + " [x] Received '" + message + "'");
			//每一秒接受一次消息
			receiveMessage(message, hashCode);
			System.out.println(hashCode + " [x] Done");
			
			//需要在每次处理完成一个消息后，手动发送一次应答（应答机制打开时）。 
			channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false); 
		}
		
		// 创建队列消费者，指定消费队列,当为work queues模式时，第二个参数应为false
//		channel.basicConsume(QUEUE_NAME, false, new DefaultConsumer(channel) {
//			// 实现方法handleDelivery，消费消息
//			public void handleDelivery(String consumerTag, com.rabbitmq.client.Envelope envelope, com.rabbitmq.client.AMQP.BasicProperties properties, byte[] body) throws java.io.IOException {
//				String routingKey = envelope.getRoutingKey();
//				String contentType = properties.getContentType();
//				long deliveryTag = envelope.getDeliveryTag();
//				channel.basicAck(deliveryTag, false);
//				System.out.println("queue name is :" + routingKey);
//				System.out.println("the contentType is :" + contentType);
//				try {
//					receiveMessage(new String(body));
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			};
//		});
	}
	
	/**
	 * @author：He.hp
	 * @date：2017年10月9日
	 * @function：TODO 延时，每个点耗时1s 
	 * @param mag
	 * @param hashCode
	 * @throws InterruptedException
	 */
	public static void receiveMessage(String mag, int hashCode) throws InterruptedException {
		for (char ch : mag.toCharArray()) {  
	            if (ch == '.')  
	                Thread.sleep(1000);  // 休眠一秒
	        }  
	}
}
