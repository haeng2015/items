package com.rabbitmq.publishSubscribe;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

/**
 * @创建者:He.hp
 * @创建日期:2017年9月26日
 * @说明：public/subscribe 发布/订阅模式，一次性向多个消费者发送消息.
 * 随机创建一个队列，然后将队列与转发器绑定，然后将消费者与该队列绑定，然后写入日志文件。
 * 将两个接收端运行，然后运行3次发送端：
 */
public class PublicSubscribeRecv01 {
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
		
		// 创建队列消费者，指定消费队列,切记这里第一个参数是queue，而并非QUEUE_NAME
		QueueingConsumer consumer = new QueueingConsumer(channel);
		// 为队列指定接收者，第二个参数为自动应答，无需手动应答
		channel.basicConsume(queueName, true, consumer);
		
		while (true) {
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			String message = new String(delivery.getBody());
			
			print2File(message);
			System.out.println(" [x] Received： '" + message + "'");
		}
	}
	
	/**
	 * @author：He.hp
	 * @date：2017年10月9日
	 * @function：TODO 打印消息到日志文件
	 * @param msg
	 */
	private static void print2File(String msg) {
		try {
			//G:/projects/dubboProjects/edu-demo-mqconsumer/target/classes/
			String dir = PublicSubscribeRecv01.class.getClassLoader().getResource("").getPath(); 
			String logFileName = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			File file = new File(dir, logFileName + ".txt");
			FileOutputStream fos = new FileOutputStream(file, true);
			fos.write((msg + "\r\n").getBytes());
			fos.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
