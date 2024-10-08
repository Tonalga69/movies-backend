package com.example.demo.config.dotenv;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DotEnvConfig {

    @Bean
    public Dotenv dotEnv(){
        return Dotenv.configure().ignoreIfMissing().load();
    }
}
