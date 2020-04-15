package org.computerShop.config;

import org.computerShop.email.SendEmail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(ignoreResourceNotFound = true, value = "classpath:application-email.properties")
public class EmailConfig {
    @Value("${user.email.login}")
    private String username;

    @Value("${user.email.password}")
    private String password;


    @Bean
    public SendEmail sendEmail(){
        SendEmail sendEmail = new SendEmail();
        sendEmail.setUsername(username);
        sendEmail.setPassword(password);
        return sendEmail;
    }

}
