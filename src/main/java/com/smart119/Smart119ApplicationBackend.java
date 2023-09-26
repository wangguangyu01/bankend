package com.smart119;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

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
@EnableScheduling
public class Smart119ApplicationBackend {

    @Bean(name = "restTemplate")
    public RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate(getFactory());
        restTemplate.setMessageConverters(getConverts());
        return restTemplate;
    }


    private SimpleClientHttpRequestFactory getFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        return factory;
    }

    private List<HttpMessageConverter<?>> getConverts() {
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        // String杞崲鍣?/span>
        StringHttpMessageConverter stringConvert = new StringHttpMessageConverter();
        List<MediaType> stringMediaTypes = new ArrayList<MediaType>() {{
            //娣诲姞鍝嶅簲鏁版嵁鏍煎紡锛屼笉鍖归厤浼氭姤401
            add(MediaType.TEXT_PLAIN);
            add(MediaType.TEXT_XML);
            add(MediaType.APPLICATION_JSON);
        }};
        stringConvert.setSupportedMediaTypes(stringMediaTypes);
        messageConverters.add(stringConvert);
        return messageConverters;
    }


    @Bean(name = "restTemplateImag")
    public RestTemplate restTemplateImag(){
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
