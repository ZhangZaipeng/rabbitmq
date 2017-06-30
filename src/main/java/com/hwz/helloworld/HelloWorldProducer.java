package com.hwz.helloworld;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

/**
 * Created by ZhangZaipeng on 2017/6/30 0030.
 */
public class HelloWorldProducer {

    /** 队列名称 */
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws Exception {

        // 创建一个到服务器的连接
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("123.206.174.58");
        Connection connection = factory.newConnection();

        // 创建一个通道 (大多数API完成任务)
        Channel channel = connection.createChannel();

        String queueName = channel.queueDeclare().getQueue();

        System.out.println(queueName);

        // queueDeclare 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // 发布消息
        for (int i = 1; i < 10; i++) {
            String message = "hello world" + i;

            // 第一个参数是交换的名称
            // 第二个是队列名称
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());

            System.out.println(" [x] Sent '" + message + "'");
        }

        // 关闭通道和连接
        channel.close();
        connection.close();
    }
}
