package com.carloscruz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@SpringBootApplication
@EnableAutoConfiguration
public class SelecaoTechneApplication extends SpringBootServletInitializer {
	
private static Class<SelecaoTechneApplication> applicationClass = SelecaoTechneApplication.class;
	
    public static void main(String[] args) {
        SpringApplication.run(SelecaoTechneApplication.class, args);
    }
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }

    
    @Bean(name = "messageSource")
    public ReloadableResourceBundleMessageSource messageSource() {
      ReloadableResourceBundleMessageSource messageBundle = new ReloadableResourceBundleMessageSource();
      messageBundle.setBasename("classpath:static/messages/messages");
      return messageBundle;
    }
}
