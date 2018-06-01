package com.resolution.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

//import org.springframework.context.annotation.PropertySource;


@Configuration
@EnableWebMvc
@ComponentScan("com.resolution.*")
@PropertySource(value = {"classpath:application.properties", "classpath:db.properties"})
public class AppConfig {
}