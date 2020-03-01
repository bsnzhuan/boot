package com.example.demo.rabbitmq.connection.routing;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 路由模式（direct）
 * @author LiJun
 *
 */
public class RoutingSendDirect {
	private static final String EXCHANGE_NAME = "direct_logs";
	//路由关键字 routing
	private static final String[] routingKeys = new String[] {"info","warning","error"};
	
	public static void main(String[] args) throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		
		channel.exchangeDeclare(EXCHANGE_NAME, "direct");
		
		for (int i = 0; i < routingKeys.length; i++) {
			String message = "Send the message level :"+routingKeys[i];
			channel.basicPublish(EXCHANGE_NAME, routingKeys[i], null, message.getBytes());
			System.out.println(" [x] Sent "+routingKeys[i]+" : "+message);
		}
		
		channel.close();
		connection.close();
	}
}
