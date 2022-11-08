package ru.otus.example.springmongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.github.cloudyrock.spring.v5.EnableMongock;

@EnableMongock
@SpringBootApplication
@EnableConfigurationProperties
public class SpringmongoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringmongoApplication.class, args);
    }

}
