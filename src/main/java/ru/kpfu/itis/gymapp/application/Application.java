package ru.kpfu.itis.gymapp.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 09.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
@SpringBootApplication
@ComponentScan("ru.kpfu.itis.gymapp")
@EnableJpaRepositories(basePackages = "ru.kpfu.itis.gymapp.repositories")
@EntityScan(basePackages = {"ru.kpfu.itis.gymapp.models"})
@PropertySource("classpath:sms.properties")
public class Application {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}