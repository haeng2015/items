package com.rabbitmq.workQueues;

import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

/**
 * @创建者:He.hp
 * @创建日期:2017年9月26日
 * @说明：work queues为工作队列模式，其为不同的worker分配task,先打开多个接收者，再为他们发送消息，结果他们平均获得消息
 
应用场景：使用任务队列的好处是能够很容易的并行工作。如果我们积压了很多工作，我们仅仅通过增加更多的工作者就可以解决问题，使系统的伸缩性更加容易。
运行方式：先运行3个接收端WorkQueuesRecv实例，然后运行WorkQueuesSend，3个工作者实例都会得到信息。

一、消息应答（message acknowledgments）：
如果杀死正在执行任务的某个工作者，我们会丢失它正在处理的信息。我们也会丢失已经转发给这个工作者且它还未执行的消息。
而实际中，当某个工作者（接收者）被杀死时，我们希望将任务传递给另一个工作者。为了保证消息永远不会丢失，RabbitMQ支持
消息应答（message acknowledgments）。消费者发送应答给RabbitMQ，告诉它信息已经被接收和处理，然后RabbitMQ可以自由的进行信息删除。
如果消费者被杀死而没有发送应答，RabbitMQ会认为该信息没有被完全的处理，然后将会重新转发给别的消费者。通过这种方式，你可以确认信息不会被丢失，即使消者偶尔被杀死。
这种机制并没有超时时间这么一说，RabbitMQ只有在消费者连接断开时重新转发此信息。如果消费者处理一个信息需要耗费特别特别长的时间是允许的。

消息应答默认是打开的。在消费者代码中设置channel.basicConsume(QUEUE_NAME, false, consumer)第二个参数为false即可打开。
另外需要在每次处理完成一个消息后，手动发送一次应答。 消费端添加代码： channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);  

二、消息持久化（Message durability）：
在一般情况下，如果RabbitMQ服务被停止，或者当RabbitMQ退出或者异常退出，将会丢失所有的队列和信息。因此我们需要给所有的队列和消息设置持久化的标志。
第一， 我们需要确认RabbitMQ永远不会丢失我们的队列。需要声明它为持久化的。设置channel.queueDeclare(QUEUE_NAME, true, false, false, null);第二个参数为true。
第二， 我们需要标识我们的信息为持久化的。通过设置MessageProperties（implements BasicProperties）值为PERSISTENT_TEXT_PLAIN。如：
	channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
注：RabbitMQ不允许使用不同的参数重新定义一个队列，所以已经存在的队列，我们无法修改其属性。

三、公平转发（Fair dispatch）：
例如，有这样一种情况，对于两个消费者，有一系列的任务，奇数任务特别耗时，而偶数任务却很轻松，这样造成一个消费者一直繁忙，
另一个消费者却很快执行完任务后等待。造成这样的原因是因为RabbitMQ仅仅是当消息到达队列进行转发消息。
并不在乎有多少任务消费者并未传递一个应答给RabbitMQ。仅仅盲目转发所有的奇数给一个消费者，偶数给另一个消费者。
为了解决这样的问题，我们可以使用basicQos方法，传递参数为prefetchCount = 1。这样告诉RabbitMQ不要在同一时间给一个消费者超过一条消息。
换句话说，只有在消费者空闲的时候会发送下一条信息。
注：如果所有的工作者都处于繁忙状态，你的队列有可能被填充满。你可能会观察队列的使用情况，然后增加工作者，或者使用别的什么策略。
这种模式下支持动态增加消费者，因为消息并没有发送出去，动态增加了消费者马上投入工作。
而默认的转发机制会造成，即使动态增加了消费者，此时的消息已经分配完毕，无法立即加入工作，即使有很多未完成的任务。

 */
public class WorkQueuesSend {
	// 队列名称
	private final static String QUEUE_NAME = "WorkQueues-queue";
	
	static boolean durable = true;  //消息是否持久化（true为持久化）

	
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
			factory.setVirtualHost("/");
			
			// 创建一个连接
			Connection connection = factory.newConnection();
			// 创建一个频道
			Channel channel = connection.createChannel();
			
			// 指定声明一个队列
			channel.queueDeclare(QUEUE_NAME, durable, false, false, null);
			
			// 往队列中发出10条消息，依次在消息后面附加1-10个点 
			for (int i = 0; i < 10; i++) {
				 String dots = "";  
			            for (int j = 0; j <= i; j++) {  
			                dots += ".";  
			            }  

				// 发送的消息
				String message = "WorkQueues-" + dots + dots.length();
				//发送消息，并指定key为队列名，该模式没有交换机，打开消息持久化设置第三个参数为MessageProperties.PERSISTENT_TEXT_PLAIN，否则为null
				channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
				System.out.println(" [x] Sent '" + message + "'");
			}
			
			// 关闭频道和连接
			channel.close();
			connection.close();
			
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
	}
}
