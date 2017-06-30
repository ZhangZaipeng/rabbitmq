package com.hwz.helloworld;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * Created by ZhangZaipeng on 2017/6/30 0030.
 */
public class HelloWorldConsumer {

    /** 队列名称 */
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws Exception {

        // 创建一个到服务器的连接
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("123.206.174.58");
        Connection connection = factory.newConnection();

        // 创建一个通道 (大多数API完成任务)
        Channel channel = connection.createChannel();

        // queueDeclare 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // DefaultConsumer
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body,"UTF-8");
                System.out.println("[x] Received '" + message + "'");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        boolean autoAck = true ; //确认如下

        channel.basicConsume(QUEUE_NAME,autoAck,consumer);
    }

}

