package com.example.post.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    private final String url;
    private final String login;
    private final String password;

    public Config(@Value("db.url") String url, @Value("db.login")String login, @Value("db.password")String password) {
        this.url = url;
        this.login = login;
        this.password = password;
    }

    @Bean("dataSource")
    HikariDataSource getDataSource(){

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(url);
        hikariConfig.setUsername(login);
        hikariConfig.setPassword(password);

        return new HikariDataSource(hikariConfig);
    }
}
