package ru.vsu.archive;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("ru.vsu.archive")
@PropertySource("classpath:application.properties")
public class SpringConfiguration {

}
