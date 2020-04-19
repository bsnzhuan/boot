package com.example.demo.config;

import com.example.demo.socket.WsHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
@EnableWebSocket
public class WebSocketConfig/* implements WebSocketConfigurer*/ {
    /*  WsHandler
    @Bean
    public WebSocketHandler wsHandler(){
        System.out.println("WebSocketConfig----wsHandler");
        return new WsHandler();
    }

    *//**
     * 设置项目socket链接地址
     * @param
     *//*
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        System.out.println("WebSocketConfig----registerWebSocketHandlers");
        registry.addHandler(new WsHandler(),"/api/websocket")
            .setAllowedOrigins("*");
    }*/

    @Bean
    public ServerEndpointExporter serverEndpoint(){
        return new ServerEndpointExporter();
    }
}
