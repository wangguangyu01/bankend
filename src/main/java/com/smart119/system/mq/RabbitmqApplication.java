package com.smart119.system.mq;


import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;


/**
 * @Authr : Edwin
 * @Date : 2018/8/21
 * @Description :
 */
@SpringBootApplication
public class RabbitmqApplication {

    @Bean
    public Queue testQueue(){
        return new Queue("comp");
    }

    @PostConstruct
    public void init(){
    }
}
