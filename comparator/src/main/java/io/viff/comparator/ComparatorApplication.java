package io.viff.comparator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
@EnableEurekaClient
public class ComparatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ComparatorApplication.class, args);
    }
}
