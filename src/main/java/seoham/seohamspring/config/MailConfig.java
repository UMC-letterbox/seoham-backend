package seoham.seohamspring.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {
    
    @Bean
    public JavaMailSender javaMailService() {
        JavaMailSender javaMailSender = new JavaMailSenderImpl();

        ((JavaMailSenderImpl) javaMailSender).setHost("smtp.naver.com");
        ((JavaMailSenderImpl) javaMailSender).setUsername("seoham2022@naver.com");
        ((JavaMailSenderImpl) javaMailSender).setPassword("seoham2022!");

        ((JavaMailSenderImpl) javaMailSender).setPort(465);

        ((JavaMailSenderImpl) javaMailSender).setJavaMailProperties(getMailProperties());

        return javaMailSender;
        
    }

    private Properties getMailProperties() {
        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.debug", "true");
        properties.setProperty("mail.smtp.ssl.trust", "smtp.naver.com");
        properties.setProperty("mail.smtp.ssl.enable", "true");

        return properties;
    }
    
}
