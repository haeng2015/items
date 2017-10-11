package com.rabbitmq;
import org.springframework.amqp.core.AmqpTemplate;  
import org.springframework.amqp.rabbit.core.RabbitTemplate;  
import org.springframework.context.ApplicationContext;  
import org.springframework.context.support.ClassPathXmlApplicationContext;  

import com.rabbitmq.springRabbitmq.User;
  
/**
 * @创建者:He.hp
 * @创建日期:2017年9月27日
 * @说明：生产者启动类
 */
public class ProducerMain {  
      
    public static void main(String[] args) {  
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-rabbitmq/spring-context.xml");    
        AmqpTemplate amqpTemplate = context.getBean(RabbitTemplate.class);     
        User user = new User();  
        user.setName("niuniu");  
        amqpTemplate.convertAndSend(user);  
    }  
}  