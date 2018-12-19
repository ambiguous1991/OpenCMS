package com.jba.runner;

import com.jba.configuration.OpenJMSConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication(scanBasePackages = "com.jba")
@PropertySource({"classpath:application.properties"})
@Import({OpenJMSConfig.class})
public class OpenJMS {
    public static void main(String[] args) {
        SpringApplication.run(OpenJMS.class, args);
    }
}
