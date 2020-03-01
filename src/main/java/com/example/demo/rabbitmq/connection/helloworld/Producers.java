package com.example.demo.rabbitmq.connection.helloworld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
/**
 * 单个队列生产者
 * @author LiJun
 *
 */
public class Producers {
	private final static String QUEUE_NAME = "hello";
	
	public static void main(String[] args) throws IOException, TimeoutException {
		//创建连接工厂
		ConnectionFactory factory = new ConnectionFactory();
		//设置rabbitMQ地址
		factory.setHost("localhost");
		factory.setPort(5679);
		//创建新链接
		Connection connection = factory.newConnection();
		//新建channel
		Channel channel = connection.createChannel();
		//声明一个队列
		//队列声明是幂等性的（多次执行产生的影响均与一次执行的影响相同）
		//即是如果不存在则创建，反之，不会对已存在的队列产生影响
		channel.queueDeclare(QUEUE_NAME,false,false,false,null);
		String message = "Hello World!";
		//发送消息到队列中
		channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
		System.out.println("p [X] Sent "+message);
		//关闭channel以及connection
		channel.close();
		connection.close();
	}
}
