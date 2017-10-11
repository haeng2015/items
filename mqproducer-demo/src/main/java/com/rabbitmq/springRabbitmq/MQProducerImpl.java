package com.rabbitmq.springRabbitmq;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @创建者:He.hp
 * @创建日期:2017年9月27日
 * @说明：convertAndSend：将Java对象转换为消息发送到匹配Key的交换机中Exchange，由于配置了JSON转换，这里是将Java对象转换成JSON字符串的形式。 
 */
@Service("mqProducer")
public class MQProducerImpl implements MQProducer {
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	private final static Logger LOGGER = Logger.getLogger(MQProducerImpl.class);
	
	@Override
	public void sendDataToQueue(String queueKey, Object object) {
		try {
			System.out.println("生产者发送消息("+object.toString()+")到队列！！");
			amqpTemplate.convertAndSend(queueKey, object);
		} catch (Exception e) {
			LOGGER.error(e);
		}
	}
}
