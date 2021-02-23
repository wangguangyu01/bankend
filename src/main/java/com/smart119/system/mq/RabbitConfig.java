package com.smart119.system.mq;

import com.rabbitmq.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application-dev.yml")
@ConfigurationProperties(prefix = "rabbitmq")
public class RabbitConfig {

    @Value("${host}")
    private String serverIp;

    @Value("${mq.port}")
    private int port;

    @Value("${mq.username}")
    private String username;

    @Value("${mq.password}")
    private String password;


    public ConnectionFactory getConnection(){
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(serverIp);
        factory.setPort(port);
        factory.setUsername(username);
        factory.setPassword(password);
        return factory;
    }
}
