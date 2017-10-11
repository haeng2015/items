package com.rabbitmq.routing;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @创建者:He.hp
 * @创建日期:2017年9月26日
 * @说明：routing模式，有选择性的接受消息（按照一定的路由规则）。 

需求：接收端随机设置一个日志严重级别（binding_key） 比如开启了3个接收端程序，两个准备接收error类型日志，一个接收info类型日志，然后运行发送端程序
运行：启了3个接收端程序，两个准备接收error类型日志，一个接收info类型日志，然后运行发送端程序

一、绑定（Bindings）
绑定表示转发器与队列之间的关系。即队列对该转发器上的消息感兴趣。
绑定可以附带一个额外的参数routingKey。为了与避免basicPublish方法（发布消息的方法）的参数混淆，我们准备把它称作绑定键（binding key）。
如：channel.queueBind(queueName, EXCHANGE_NAME, "black"); 绑定键的意义依赖于转发器的类型。对于fanout类型，忽略此参数。

二、直接转发（Direct exchange）
根据日志的严重性进行过滤日志。例如：我们可能希望把致命类型的错误写入硬盘，而不把硬盘空间浪费在警告或者消息类型的日志上。
我们将会使用direct类型的转发器。direct类型的转发器背后的路由转发算法很简单：消息会被推送至绑定键（binding key）和消息发布附带的选择键（routing key）完全匹配的队列。
除此绑定键之外，所有的其他的消息将会被丢弃。

三、多重绑定（multiple bindings）
使用一个绑定键（binding key）绑定多个队列是完全合法的。一个附带选择键（routing key）的消息将会被转发到两个队列中。

四、发送日志（Emittinglogs）
将这种模式用于我们的日志系统。我们将消息发送到direct类型的转发器。把日志的严重性作为选择键（routing key）。
创建一个转发器：channel.exchangeDeclare(EXCHANGE_NAME,"direct");
发送一条消息：channel.basicPublish(EXCHANGE_NAME,severity, null, message.getBytes());
假定‘severity’是‘info’，‘warning’，‘error’中的一个。

五、订阅
接收消息的代码和前面的消费者模式类似，只有一点不同：给所感兴趣的严重性类型的日志创建一个绑定。
StringqueueName = channel.queueDeclare().getQueue();
for(String severity : argv){
	channel.queueBind(queueName, EXCHANGE_NAME, severity);
}

 */
public class RoutingSend {
	// 交换机名称
	private final static String EXCHANGE_NAME = "Routing-exchange";
	private static final String[] SEVERITIES = { "info", "warning", "error" }; //routingkey
	
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
			
			// 指定声明一个direct类型的交换器EXCHANGE_NAME
			channel.exchangeDeclare(EXCHANGE_NAME, "direct");
			
			// 发送6条消息
			for (int i = 0; i < 6; i++) {
				String severity = getSeverity(); // 静态方法中不能调用非静态方法
				String message = severity + "_log :" + UUID.randomUUID().toString();
				// 发布消息至转发器，指定routingkey
				channel.basicPublish(EXCHANGE_NAME, severity, null, message.getBytes());
				System.out.println(" [x] Sent '" + message + "'");
			}
			
			// 关闭频道和连接
			channel.close();
			connection.close();
			
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
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
