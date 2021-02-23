package com.smart119;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhangshunhua
 */
@EnableAutoConfiguration(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
})
@EnableTransactionManagement
@ServletComponentScan
@MapperScan("com.smart119.*.dao")
@SpringBootApplication
@EnableCaching
public class Smart119ApplicationBackend {
    @Bean
    public RestTemplate restTemplate(){
        
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(Smart119ApplicationBackend.class, args);
        System.out.println("\n" +
                " _______  _______  _______  _______ _________   __    __     _____  \n" +
                "(  ____ \\(       )(  ___  )(  ____ )\\__   __/  /  \\  /  \\   / ___ \\ \n" +
                "| (    \\/| () () || (   ) || (    )|   ) (     \\/) ) \\/) ) ( (   ) )\n" +
                "| (_____ | || || || (___) || (____)|   | |       | |   | | ( (___) |\n" +
                "(_____  )| |(_)| ||  ___  ||     __)   | |       | |   | |  \\____  |\n" +
                "      ) || |   | || (   ) || (\\ (      | |       | |   | |       ) |\n" +
                "/\\____) || )   ( || )   ( || ) \\ \\__   | |     __) (___) (_/\\____) )\n" +
                "\\_______)|/     \\||/     \\||/   \\__/   )_(     \\____/\\____/\\______/  ");
    }
}
