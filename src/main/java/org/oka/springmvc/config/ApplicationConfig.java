package org.oka.springmvc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import java.util.Map;

import static javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT;

@EnableWebMvc
@EnableAutoConfiguration
@Configuration
@ComponentScan(basePackages = {"org.oka.springmvc"})
public class ApplicationConfig implements WebMvcConfigurer {

    @Autowired
    ApplicationContext applicationContext;

    @Bean
    public ViewResolver thymeleafResolver() {
        ThymeleafViewResolver ivr = new ThymeleafViewResolver();
        ivr.setTemplateEngine(templateEngine());
        ivr.setOrder(0);
        return ivr;
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver srtr = new SpringResourceTemplateResolver();
        srtr.setApplicationContext(applicationContext);
        srtr.setPrefix("classpath:/templates/");
        srtr.setSuffix(".html");
        return srtr;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    @Bean
    public Jaxb2Marshaller jaxb2Marshaller() {
        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
        jaxb2Marshaller.setPackagesToScan("org.oka.springmvc.model");
        jaxb2Marshaller.setMarshallerProperties(Map.of(JAXB_FORMATTED_OUTPUT, true));

        return jaxb2Marshaller;
    }
}