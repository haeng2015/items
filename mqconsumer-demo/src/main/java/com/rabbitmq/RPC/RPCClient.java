package com.rabbitmq.RPC;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

/**
 * @创建者:He.hp
 * @创建日期:2017年10月10日
 * @说明：官方rpc消费者.消费者向生产者发送一个数字，生产者获得该数字并计算该数的...出同时发送给消费者。线启动生产者再启动消费者
 */
public class RPCClient {
	
	private Connection connection;
	private Channel channel;
	private String requestQueueName = "rpc_queue";
	private String replyQueueName;
	
	public RPCClient() throws IOException, TimeoutException {
		//新建连接工厂，并传入参数（主机，用户名/密码）
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("192.168.10.140");
		factory.setUsername("admin");
		factory.setPassword("123456");
		
		//建立连接和通道
		connection = factory.newConnection();
		channel = connection.createChannel();
		
		//随机获得队列名（多个队列），退出时销毁
		replyQueueName = channel.queueDeclare().getQueue();
	}
	
	public String call(String message) throws IOException, InterruptedException {
		final String corrId = UUID.randomUUID().toString();
		
		AMQP.BasicProperties props = new AMQP.BasicProperties.Builder().correlationId(corrId).replyTo(replyQueueName).build();
		
		channel.basicPublish("", requestQueueName, props, message.getBytes("UTF-8"));
		
		final BlockingQueue<String> response = new ArrayBlockingQueue<String>(1);
		
		channel.basicConsume(replyQueueName, true, new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
				if (properties.getCorrelationId().equals(corrId)) {
					response.offer(new String(body, "UTF-8"));
				}
			}
		});
		return response.take();
	}
	
	public void close() throws IOException {
		connection.close();
	}
	
	public static void main(String[] argv) {
		RPCClient fibonacciRpc = null;
		String response = null;
		String request = "8";  //消费者要请求生产者计算的数字
		try {
			fibonacciRpc = new RPCClient();
			
			System.out.println(" 消费者请求数据[x] Requesting fib("+ request +")......");
			response = fibonacciRpc.call(request);
			System.out.println(" 消费者接收结果[.] Got '" + response + "'");
		} catch (IOException | TimeoutException | InterruptedException e) {
			e.printStackTrace();
		} finally {
			if (fibonacciRpc != null) {
				try {
					fibonacciRpc.close();
				} catch (IOException _ignore) {
				}
			}
		}
	}
}
