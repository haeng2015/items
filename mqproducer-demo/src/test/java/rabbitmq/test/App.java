package rabbitmq.test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

/**
 * ConnectionFactory先填上创建连接要的参数（主机地址、登录账户等），然后就是newConnection()得到Connection，并创建出一个频道Channel。 
从Channel我们可以构造出一个消费者Consumer用来监听收到的消息，也可以使用channel发布消息。
RabbitMQ的消息是byte[]，所以需要将字串转成字节数组才能发送。
 */
public class App {
	private final static String QUEUE_NAME = "hello2";
	static boolean isBreak = false;
	
	public static void main(String[] args) {
		System.out.println("Hello World!");
		
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("192.168.10.140");
		factory.setUsername("admin");
		factory.setPassword("123456");
		
		try {
			Connection connection = factory.newConnection();
			Channel channel = connection.createChannel();
			
			Consumer consumer = new DefaultConsumer(channel) {
				@Override
				public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
					String message = new String(body, "UTF-8");
					System.out.println(" [x] Received '" + message + "'");
					isBreak = true;
				}
			};
			channel.basicConsume(QUEUE_NAME, true, consumer);
			
			channel.queueDeclare(QUEUE_NAME, false, false, false, null);
			String message = "Hello World!";
			channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
			System.out.println(" [x] Sent '" + message + "'");
			
			while (!isBreak) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			channel.close();
			connection.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
