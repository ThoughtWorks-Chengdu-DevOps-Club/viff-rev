package io.viff.storage.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

@Configuration
public class FileUploadConfig {

    @Value("${application.files.maxSize}")
    private String maxFileSize;

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(maxFileSize);
        factory.setMaxRequestSize(maxFileSize);
        return factory.createMultipartConfig();
    }
}
