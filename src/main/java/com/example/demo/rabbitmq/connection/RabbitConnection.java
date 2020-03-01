package com.example.demo.rabbitmq.connection;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitConnection {
	private static Connection connection;
	
	private static Connection getConnection() {
		if(connection == null) {
			ConnectionFactory factory = new ConnectionFactory();
			
		}
		return connection;
	}
}
