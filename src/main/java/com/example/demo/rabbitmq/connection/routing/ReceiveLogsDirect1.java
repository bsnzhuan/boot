package com.example.demo.rabbitmq.connection.routing;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class ReceiveLogsDirect1 {
	private static final String EXCHANGE_NAME = "direct_logs";
	//路由关键字 routing
	private static final String[] routingKeys = new String[] {"info","warning","error"};
	
	public static void main(String[] args) throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		
		channel.exchangeDeclare(EXCHANGE_NAME, "direct");
		String queueName = channel.queueDeclare().getQueue();
		for (String str : routingKeys) {
			channel.queueBind(queueName, EXCHANGE_NAME, str);
			System.out.println(" ReceiveLogsDirect1 exchange "+EXCHANGE_NAME+"  QUEUE "+queueName+" rountKeys "+str);
		}
		System.out.println("ReceiveLogsDirect1 [*] Waiting for message to exit CTRL+C");
		Consumer consumer = new DefaultConsumer(channel) {

			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				// TODO Auto-generated method stub
				String message = new String(body,"UTF-8");
				System.out.println(" ReceiveLogsDirect1 received : "+message);
			}
		};
		//第二个参数用于是否启用AUTOACK机制
		channel.basicConsume(queueName,false,consumer);
	}
}
