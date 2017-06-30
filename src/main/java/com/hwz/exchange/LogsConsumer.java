package com.hwz.exchange;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * Created by ZhangZaipeng on 2017/6/30 0030.
 */
public class LogsConsumer {
    private static final String EXCHANGE_NAME = "logs" ;

    public static void main(String[] args) throws IOException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("123.206.174.58");
        Connection connection = factory.newConnection();

        // 创建一个通道 (大多数API完成任务)
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        String queueName = channel.queueDeclare().getQueue();

        channel.queueBind(queueName, EXCHANGE_NAME, "");

        System.out.println("queueName --> " + queueName);

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

        channel.basicConsume(queueName,autoAck,consumer);
    }
}
