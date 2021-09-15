package com.iryna.config;

import com.iryna.entity.Session;
import com.iryna.entity.User;
import com.iryna.security.SecurityService;
import lombok.extern.slf4j.Slf4j;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@EnableScheduling
@org.springframework.context.annotation.Configuration
@EnableCaching
@Slf4j
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.iryna")
public class Configuration {

    @Value("${db.url}")
    private String dbUrl;

    @Value("${db.user}")
    private String dbUser;

    @Value("${db.password}")
    private String dbPassword;

    private SecurityService securityService;

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("genres");
    }

    @Bean
    public PGSimpleDataSource dataSource() {
        PGSimpleDataSource pgSimpleDataSource = new PGSimpleDataSource();
        pgSimpleDataSource.setUrl(dbUrl);
        pgSimpleDataSource.setPassword(dbPassword);
        pgSimpleDataSource.setUser(dbUser);
        return pgSimpleDataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(PGSimpleDataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        return jdbcTemplate;
    }

    @Bean
    public SecurityService securityService() {
        securityService = new SecurityService();
        return securityService;
    }

    @CacheEvict(cacheNames = {"genres"})
    @Scheduled(fixedDelay = 14400000)
    public void cacheEvict() {
        log.info("Cache evicted");
    }

    @Scheduled(fixedDelay = 18000)
    public void removeExpiredTokens() {
        securityService.clearExpiredTokens();
        log.info("expired tokens removed");
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        propertySourcesPlaceholderConfigurer.setLocation(new ClassPathResource("application.properties"));
        propertySourcesPlaceholderConfigurer.setIgnoreUnresolvablePlaceholders(true);
        return propertySourcesPlaceholderConfigurer;
    }

    @Bean
    public Map<String, Session> getMap() {
        return Collections.synchronizedMap(new HashMap<>());
    }
}
