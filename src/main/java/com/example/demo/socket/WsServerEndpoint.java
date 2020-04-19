package com.example.demo.socket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.socket.WebSocketSession;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(value="/api/websocket/{sid}")
@Component
@Slf4j
public class WsServerEndpoint {

    private Session session;    //客户端连接会话

    private volatile  int onlineCount = 0;  //记录当前连接数量
    //存放客户端对应的WsServerEndpoint对象
    private static CopyOnWriteArraySet<WsServerEndpoint> set = new CopyOnWriteArraySet<WsServerEndpoint>();

    private String sid = "";    //接收sid
    @OnOpen
    public void onOpen(Session session, @PathParam("sid") String sid){
        this.session = session;
        //加入set
        set.add(this);
        //在线人数加一
        onlineCount += 1;
        log.info("socket创建连接，当前用户："+sid+",当前在线人数："+onlineCount);
        this.sid = sid;
        System.out.println("==========onOpen==========");
        try {
            sendMessage("连接成功");
        } catch (Exception e) {
            log.error("websocket IO异常");
        }
    }

    @OnClose
    public void onClose(Session session){
        System.out.println("==========onClose==========");
        //从set中删除
        set.remove(this);
        //在线数减1
        onlineCount -= 1;
        log.info("有一连接"+sid+"关闭！当前在线人数为" + onlineCount);
    }

    @OnMessage
    public void onMessage(String message,Session session) throws Exception{
        log.info("收到来自窗口"+sid+"的信息:"+message);
        //群发消息
        for (WsServerEndpoint item : set) {
            try {
                item.sendMessage(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessage(String message) throws Exception {
        this.session.getBasicRemote().sendText(message);
    }

    @OnError
    public void onErroe(Session session,Throwable e){
        log.error("发生错误");
        e.printStackTrace();
    }


}
