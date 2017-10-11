package com.rabbitmq.springRabbitmq;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Service;

/**
 * @创建者:He.hp
 * @创建日期:2017年9月27日
 * @说明：定义监听器(在rabbitmq调用该监听器时，必须要求spring版本4及以上，因为需要BackOff,另外不能少了spring-expression包)
 */
@Service("queueListenter")
public class QueueListenter implements MessageListener {
	
	@Override
	public void onMessage(Message msg) {
		try {
			System.out.println("消费者开始监听消息：" + new String(msg.getBody(), "UTF-8"));
			JSONObject json = JSONObject.fromObject(new String(msg.getBody(), "UTF-8"));
			System.out.println("消费者监听到的消息（JSONObject）：" + json.get("data").toString());
			
			JSONArray jSONArray = JSONArray.fromObject(json.get("items"));
			System.out.println("消费者监听到的一组消息（JSONArray）：" + jSONArray.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
