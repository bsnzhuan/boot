package com.example.demo.rabbitmq.connection.publish;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
/**
 * 声明交换器logs绑定队列  交换器消息传递到绑定的队列
 * @author LiJun
 *
 */
public class EmitLog {
	private static final String EXCHANGE_NAME = "logs";
	
	public static void main(String[] args) throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		//定义交换器
		channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
		//分发消息
		for (int i = 0; i < 5; i++) {
			String message = "Hello World! "+i;
			channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
			System.out.println("[x] Sent '"+message+"'");
		}
		channel.close();
		connection.close();
	}
}
