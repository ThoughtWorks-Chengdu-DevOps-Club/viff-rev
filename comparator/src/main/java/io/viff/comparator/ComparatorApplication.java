package io.viff.comparator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class ComparatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComparatorApplication.class, args);
	}
}
