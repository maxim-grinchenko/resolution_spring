package com.resolution.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Locale;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class DatabaseConfig {
    // datasource
    @Value("${h2db.driver}")
    private String driverClassName;
    @Value("${h2db.url}")
    private String h2dbUrl;
    @Value("${h2db.username}")
    private String h2dbUsername;
    @Value("${h2db.password}")
    private String h2dbPassword;

    // hibernate
    @Value("${hibernate.dialect}")
    private String dialect;
    @Value("${hibernate.show.sql}")
    private String showSql;
    @Value("${hibernate.format.sql}")
    private String formatSql;
    @Value("${hibernate.jdbc.batch.size}")
    private String jdbcBatchSize;
    @Value(("${hibernate.hbm2ddl.auto}"))
    private String autoCreateDB;

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        Locale.setDefault(Locale.UK);
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("com.resolution.entity");
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(h2dbUrl);
        dataSource.setUsername(h2dbUsername);
        dataSource.setPassword(h2dbPassword);
        return dataSource;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", dialect);
        properties.put("hibernate.show_sql", showSql);
        properties.put("hibernate.format_sql", formatSql);
        properties.put("hibernate.jdbc.batch_size", jdbcBatchSize);
        properties.put("hibernate.hbm2ddl.auto", autoCreateDB);
        return properties;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);
        return txManager;
    }
}