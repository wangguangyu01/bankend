package com.smart119.system.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @Authr : Edwin
 * @Date : 2018/8/21
 * @Description :
 */
@Component
public class RabbitMQClient {

    protected static Logger logger = LoggerFactory.getLogger(RabbitMQClient.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RabbitConfig rabbitConfig;

    public void sendMessageToQueue(String quequName,String message) {
        rabbitTemplate.convertAndSend(quequName, message);
    }


    public void sendMessageToExchange(String exchangeName,String message) {
        Connection connection = null;
        Channel channel = null;
        try{
            ConnectionFactory factory = rabbitConfig.getConnection();
            connection = factory.newConnection();
            if(connection != null){
                channel = connection.createChannel();
                if(channel != null){
                    channel.basicPublish(exchangeName,"",null,message.getBytes("UTF-8"));
                }else{
                    connection.close();
                }
            }
        }catch (Exception e){
            logger.error("Create Channel Error!");
        }finally {
            try{
                if(channel != null){
                    channel.close();
                }
                if(connection != null){
                    connection.close();
                }
            }catch (Exception e){
                logger.error(e.toString());
            }
        }
    }
}
