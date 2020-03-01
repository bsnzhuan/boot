package com.example.demo.rabbitmq.connection.queues;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
/**
 * 工作队列分发 fanout
 * @author LiJun
 *
 */
public class NewTask {
	private static final String TASK_QUEUE_NAME = "task_queue";
	
	public static void main(String[] args) throws IOException, TimeoutException {
		//创建连接工厂
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		//新建MQ访问连接
		Connection connection = factory.newConnection();
		//新建队列监听
		Channel channel = connection.createChannel();
		channel.queueDeclare(TASK_QUEUE_NAME,true,false,false,null);
		//消息分发
		for (int i = 0; i < 5; i++) {
			String message = "Hello  World!"+i;
			channel.basicPublish("", TASK_QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes("UTF-8"));
			System.out.println("[x] Sent '"+message+"'");
		}
		channel.close();
		connection.close();
	}
}
