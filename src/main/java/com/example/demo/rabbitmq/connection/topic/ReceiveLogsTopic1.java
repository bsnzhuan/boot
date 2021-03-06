package com.example.demo.rabbitmq.connection.topic;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class ReceiveLogsTopic1 {
	
	private static final String EXCHANGE_NAME = "messages";
	
	public static void main(String[] args) throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("139.196.189.184");
        factory.setVirtualHost("pucheit");
        factory.setUsername("admin");
        factory.setPassword("admin");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		//声明交换器
		channel.exchangeDeclare(EXCHANGE_NAME, "topic");
		String queueName = channel.queueDeclare().getQueue();
		//路由关键字  *模糊匹配一个单词 #表示零个或者多个单词
		String[] routingKeys = new String[] {"#.*"};//{"*.orange.*"};
		//从交换器绑定路由关键字到队列
		/*for (String string : routingKeys) {
			channel.queueBind(queueName, EXCHANGE_NAME, string);
			System.out.println("ReceiveLogsTopic1 exchange : "+EXCHANGE_NAME+" QUEUE: "+queueName+" bindKey:"+string);
		}*/
		channel.queueBind(queueName, EXCHANGE_NAME, "liaochangxing");
		System.out.println("ReceiveLogsTopic1 [*] Waitting for messages. To exit press CTRL+C");
		
		Consumer consumer = new DefaultConsumer(channel) {

			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				// TODO Auto-generated method stub
				String message = new String(body,"UTF-8");
				System.out.println("ReceiveLogsTopic1 [x] Received "+ envelope.getRoutingKey()+"   message "+message);
			}
		};
		channel.basicConsume(queueName, true, consumer);
	}
}
