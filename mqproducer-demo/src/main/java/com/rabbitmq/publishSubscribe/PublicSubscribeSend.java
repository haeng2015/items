package com.rabbitmq.publishSubscribe;

import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

/**
 * @创建者:He.hp
 * @创建日期:2017年9月26日
 * @说明：public/subscribe 发布/订阅模式，一次性向多个消费者发送消息（类似观察者模式）。

需求：构建一个简单的日志系统。这个系统包含两类程序，一类程序发动日志，另一类程序接收和处理日志。
每一个运行的接收者程序都会收到日志。然后我们实现，一个接收者将接收到的数据写到硬盘上，与此同时，另一个接收者把接收到的消息展现在屏幕上。
本质上来说，就是发布的日志消息会转发给所有的接收者。

实现：声明指定exchange（channel.exchangeDeclare(QUEUE_NAME,"fanout")）。
实践：将两个接收端运行，然后运行3次发送端
	
一、转发器（Exchanges）
RabbitMQ消息模型的核心理念是生产者永远不会直接发送任何消息给队列，一般的情况生产者甚至不知道消息应该发送到哪些队列。
相反的，生产者只能发送消息给转发器（Exchange）。转发器一边接收从生产者发来的消息，另一边把消息推送到队列中。
转发器必须清楚的知道如何处理它收到的每一条消息。是否应该追加到一个指定的队列？是否应该追加到多个队列？或者是否应该丢弃？这些规则通过转发器的类型进行定义。
fanout类型转发器特别简单，把所有它介绍到的消息，广播到所有它所知道的队列。不过这正是我们前述的日志系统所需要的。

二、匿名转发器（nameless exchange）
前面的两种模式中并没有使用到转发器，我们仍然可以发送和接收消息。这是因为我们使用了一个默认的转发器，它的标识符为””，如：
	channel.basicPublish("", QUEUE_NAME,MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
第一个参数为转发器的名称，我们设置为”” ，如果存在routingKey（第二个参数），消息由routingKey决定发送到哪个队列。

三、临时队列（Temporary queues）
前面的模式中都为队列指定了一个特定的名称。能够为队列命名是很关键的，我们需要指定消费者为某个队列。当我们希望在生产者和消费者间共享队列时，为队列命名是很重要的。
不过，对于我们的日志系统我们并不关心队列的名称。我们想要接收到所有的消息，而且我们也只对当前正在传递的数据的感兴趣。为了满足我们的需求，需要做两件事：
第一， 无论什么时间连接到Rabbit我们都需要一个新的空的队列。为了实现，我们可以使用随机数创建队列，或者更好的，让服务器给我们提供一个随机的名称。
第二， 一旦消费者与Rabbit断开，消费者所接收的那个队列应该被自动删除。
Java中我们可以使用queueDeclare()方法，不传递任何参数，来创建一个非持久的、唯一的、自动删除的队列且队列名称由服务器随机产生。
String queueName = channel.queueDeclare().getQueue();
一般情况这个名称与amq.gen-JzTY20BRgKO-HjmUJj0wLg 类似。

四、绑定（Bindings）
已经创建了一个fanout转发器和队列，我们现在需要通过binding告诉转发器把消息发送给我们的队列。
channel.queueBind(queueName, “logs”, ””)参数1：队列名称 ；参数2：转发器名称

 */
public class PublicSubscribeSend {
	private final static String EXCHANGE_NAME = "PublicSubscribe-exchange";
	
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
			
			// 指定声明fanout类型的exchange
			channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
	
			// 发送的消息
			String message = "PublicSubscribe hello world!";
			// 向转发器中发一条消息
			channel.basicPublish(EXCHANGE_NAME, "", MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
			System.out.println(" [x] Sent '" + message + "'");
			
			// 关闭频道和连接
			channel.close();
			connection.close();
			
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
	}
}
