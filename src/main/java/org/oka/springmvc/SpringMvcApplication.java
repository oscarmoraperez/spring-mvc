package org.oka.springmvc;

import org.oka.springmvc.config.ApplicationInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point of the application
 */
@SpringBootApplication
public class SpringMvcApplication {
    public static void main(String[] args) {
        SpringApplication.run(new Class[]{SpringMvcApplication.class, ApplicationInitializer.class}, args);
    }
}
