package com.hwz.routing;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * Created by Administrator on 2017/6/30 0030.
 */
public class EmitLogDirect {
    private static final String EXCHANGE_NAME = "direct_logs";

    public static void main(String[] argv)
            throws java.io.IOException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("123.206.174.58");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");

        String severity = "error";
        String message = "info1 msg 12312321";

        channel.basicPublish(EXCHANGE_NAME, severity, null, message.getBytes());
        System.out.println(" [x] Sent '" + severity + "':'" + message + "'");

        channel.close();
        connection.close();
    }
}
