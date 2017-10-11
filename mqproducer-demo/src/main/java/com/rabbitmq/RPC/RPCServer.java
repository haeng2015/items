package com.rabbitmq.RPC;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Envelope;

/**
 * @创建者:He.hp
 * @创建日期:2017年10月10日
 * @说明：官方rpc生产者。消费者向生产者发送一个数字，生产者获得该数字并计算该数的...出同时发送给消费者。线启动生产者再启动消费者
 */
public class RPCServer {
	
	private static final String RPC_QUEUE_NAME = "rpc_queue";
	
	/**
	 * 计算一个整数的
	 * 
	 * 5 = fib(4)+fib(3) = fib(3)+2fib(2)+1 = fib(2)+1+2*1+0+1 = 1+0+1+2*1+0+1 = 5
	 * 
	 * 10 = fib(9)+fib(8) = fib(8)+2fib(7)+fib(6) = fib(7)+fib(6)+2(fib(6)+fib(5))+fib(5)+fib(4) = 
	 * 	fib(6)+2fib(5)+fib(4)+2(fib(5)+2fib(4)+fib(3))+fib(4)+2fib(3)+fib(2) = 
	 * 	fib(5)+fib(4)+2(fib(4)+fib(3))+fib(3)+fib(2)+2(fib(4)+fib(3)+2(fib(3)+fib(2))+fib(2)+1)+fib(3)+fib(2)+2(fib(2)+1)+1+0
	 * 	= fib(4)+2fib(3)+fib(2)+2(fib(3)+2fib(2)+1)+fib(2)+1+1+0+2(fib(3)+2fib(2)+1+2(fib(2)+1+1+0)+1+0+1)+fib(2)+1+1+0+2(1+0+1)+1+0
	 * 	= fib(3)+fib(2)+2(fib(2)+1)+1+0+2(fib(2)+1+2(1+0)+1)+1+0+1+1+0+2(fib(2)+1+2(1+0)+1+2(1+0+1+1+0)+1+0+1)+1+0+1+1+0+2(1+0+1)+1+0
	 * 	= fib(2)+1+1+0+2(1+0+1)+1+0+2(1+0+1+2(1+0)+1)+1+0+1+1+0+2(1+0+1+2(1+0)+1+2(1+0+1+1+0)+1+0+1)+1+0+1+1+0+2(1+0+1)+1+0
	 * 	= 3+2(2)+1+2(5)+3+2(13)+3+2(2)+1 = 3+4+1+10+3+26+3+4+1 = 55
	 * 4：3
	 * 7:13 = 6+5+4+3
	 * 8:21
	 * 12:144
	 * 15:610
	 */
	private static int fib(int n) {
		if (n == 0) return 0;
		if (n == 1) return 1;
		return fib(n - 1) + fib(n - 2);
	}
	
	public static void main(String[] argv) {
		//新建连接工厂，并传入参数（主机，用户名/密码）
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("192.168.10.140");
		factory.setUsername("admin");
		factory.setPassword("123456");
		
		Connection connection = null;
		try {
			//建立连接和通道
			connection = factory.newConnection();
			final Channel channel = connection.createChannel();
			
			//声明定义队列
			channel.queueDeclare(RPC_QUEUE_NAME, false, false, false, null);
			//同一时间向同一队列只发送一条信息，高效执行消费
			channel.basicQos(1);
			
			System.out.println(" 生产者[x] Awaiting RPC requests......");
			
			//建立队列消费者
			Consumer consumer = new DefaultConsumer(channel) {
				@Override
				public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
					AMQP.BasicProperties replyProps = new AMQP.BasicProperties.Builder().correlationId(properties.getCorrelationId()).build();
					
					String response = "";
					
					try {
						String message = new String(body, "UTF-8");
						int n = Integer.parseInt(message);
						
						System.out.println(" 生产者接受消息[.] fib(" + message + ")");
						response += fib(n);
						System.out.println("生产者计算结果为："+ response);
					} catch (RuntimeException e) {
						System.out.println(" [.] " + e.toString());
					} finally {
						channel.basicPublish("", properties.getReplyTo(), replyProps, response.getBytes("UTF-8"));
						channel.basicAck(envelope.getDeliveryTag(), false);
						// RabbitMq consumer worker thread notifies the RPC server owner thread
						synchronized (this) {
							this.notify();
						}
					}
				}
			};
			
			channel.basicConsume(RPC_QUEUE_NAME, false, consumer);
			// Wait and be prepared to consume the message from RPC client.
			while (true) {
				synchronized (consumer) {
					try {
						consumer.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (IOException | TimeoutException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) try {
				connection.close();
			} catch (IOException _ignore) {
			}
		}
	}
}
