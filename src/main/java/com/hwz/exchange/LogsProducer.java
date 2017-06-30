package com.hwz.exchange;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

/**
 * Created by ZhangZaipeng on 2017/6/30 0030.
 */
public class LogsProducer {

    /** 交换机名称 */
    private static final String EXCHANGE_NAME = "logs";

    public static void main(String[] args) throws IOException {
        // 创建一个到服务器的连接
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("123.206.174.58");
        Connection connection = factory.newConnection();

        // 创建一个通道 (大多数API完成任务)
        Channel channel = connection.createChannel();

        // 声明交换机： fanout （将所有收到的消息广播到所有已知的队列）
        // 消息通过路由Key指定的名称路由到队列（如果存在）
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        // 发布消息
        for (int i = 1; i < 10; i++) {
            String message = "hello world exchange" + i;

            // 第一个参数是交换的名称
            // 第二个是队列名称
            channel.basicPublish(EXCHANGE_NAME,"", null, message.getBytes());

            System.out.println(" [x] Sent '" + message + "'");
        }

        // 关闭通道和连接
        channel.close();
        connection.close();
    }
}
