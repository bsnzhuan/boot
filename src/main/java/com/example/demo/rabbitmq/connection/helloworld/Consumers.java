package com.example.demo.rabbitmq.connection.helloworld;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
/**
 * 单个队列消费者
 * @author LiJun
 *
 */
public class Consumers {
	private final static String QUEUE_NAME ="hello";
	
	public static void main(String[] args) throws IOException, TimeoutException {
		//新建连接工厂
		ConnectionFactory factory = new ConnectionFactory();
		//设置rabbitMQ地址
		factory.setHost("localhost");
		factory.setPort(5679);
		//新建一个连接
		Connection connection = factory.newConnection();
		//创建一个channel
		Channel channel = connection.createChannel();
		//声明要关注的队列
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		System.out.println("C [*] Waiting for messages. To exit press CTRL+C");
		//DefaultConsumer类实现了Consumer接口，
		//通过传入一个频道，告诉服务器需要的频道消息，
		//如果有消息，就会执行回调函数handleDelivery
		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				// TODO Auto-generated method stub
				String message = new String(body,"UTF-8");
				System.out.println("    "+message+"   ");
			}
		};
		//自动回复队列应答  --rabbitMQ中的消息确认机制
		channel.basicConsume(QUEUE_NAME, true,consumer);
	}
}
