package io.viff.comparator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
@EnableDiscoveryClient
@EnableFeignClients
public class ComparatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ComparatorApplication.class, args);
    }
}
