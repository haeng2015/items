package com.rabbitmq;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rabbitmq.springRabbitmq.MQProducer;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring-rabbitmq/spring-context.xml" })
public class TestQueue {
	@Autowired
	MQProducer mqProducer;
	
	final String queue_key = "rabbit-exchange-key";
	String message = "hello,rabbmitmq! this is my test.";
	
	@Test
	public void send() {
		Map<String, Object> msg = new HashMap<String, Object>();
		msg.put("data", message);
		mqProducer.sendDataToQueue(queue_key, msg);
	}
}
