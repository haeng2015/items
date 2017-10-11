/** 基于Dubbo的分布式系统架构视频教程，吴水成，wu-sc@foxmail.com，学习交流QQ群：367211134 **/
package wusc.edu.demo.mqtest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import wusc.edu.demo.mqtest.params.MailParam;

/**
 * 
 * @描述: ActiveMQ生产者测试启动类 .
 * @作者: WuShuicheng .
 * @创建时间: 2015-3-17,上午2:25:20 .
 * @版本号: V1.0 .
 */
public class MQProducerTest {
	private static final Log log = LogFactory.getLog(MQProducerTest.class);

	public static void main(String[] args) {
		try {
			@SuppressWarnings("resource")
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-activemq/spring-context.xml");
			context.start();

			//从spring的bean配置中或@Service("mqProducer")中取得实例化的对象
			MQProducer mqProducer = (MQProducer) context.getBean("mqProducer");
			// 邮件发送
			MailParam mail = new MailParam();
			mail.setTo("haeng2015@163.com");
			mail.setSubject("ActiveMQ测试");
			mail.setContent("通过ActiveMQ异步发送邮件！");

			mqProducer.sendMessage(mail);

			context.stop();
		} catch (Exception e) {
			log.error("==>MQ context start error:", e);
			System.exit(0);
		} finally {
			log.info("===>System.exit");
			System.exit(0);
		}
	}
}
