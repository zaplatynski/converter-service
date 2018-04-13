package com.github.zaplatynski;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;


@SpringBootApplication
public class Application {

    public static void main(String... args) {
        final SpringApplication application = new SpringApplication(Application.class);
        application.addListeners(new ApplicationPidFileWriter());
        application.run(args);
    }

    @Bean
    @Primary
    public static ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

    @Bean(name="yamlMapper")
    public static YAMLMapper yamlMapper(){
        return new YAMLMapper();
    }
}