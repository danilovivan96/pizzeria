package ru.mail.danilov.pizzeria.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource({"classpath:database.properties", "classpath:pagepathes.properties"})
@ComponentScan(basePackages = {
        "ru.mail.danilov.pizzeria.config",
        "ru.mail.danilov.pizzeria.dao",
        "ru.mail.danilov.pizzeria.service",
        "ru.mail.danilov.pizzeria.controllers",
        "ru.mail.danilov.pizzeria.validators"})
public class AppConfig {
    public static PropertySourcesPlaceholderConfigurer configurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
