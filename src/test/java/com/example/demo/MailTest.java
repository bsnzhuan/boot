package com.example.demo;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class MailTest {
    public static void main(String[] args) throws Exception {
        String smtpServer = "smtp.163.com";
        String subject = "邮件测试";
        String body = "authenticator 测试 匿名内部类";

        Properties prop = new Properties();
        prop.setProperty("mail.transport","smtp");
        prop.setProperty("mail.host",smtpServer);
        prop.setProperty("mail.smtp.auth","true");
        //继承Authenticator
        //Authenticator auth = new MyAuthentication("wang_yh0320@163.com","wang1837");
        //Session session = Session.getInstance(prop,auth);
        //匿名内部类
        Session session = Session.getDefaultInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("wang_yh0320@163.com","wang1837");
            }
        });
        session.setDebug(true);

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress("wang_yh0320@163.com"));
        message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("1468712045@qq.com,17638124878@163.com"));
        message.setSentDate(new Date());
        message.setSubject(subject);
        message.setText(body);
        message.saveChanges();

        Transport sender = session.getTransport();
        Transport.send(message);
    }
}
class MyAuthentication extends Authenticator {
    public String username;
    public String password;

    public MyAuthentication(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username,password);
    }
}
