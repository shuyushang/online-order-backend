package com.laioffer.onlineOrder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableWebMvc
//spring运行时候需要的class
//provide dependency to connect DB
//package com.laioffer.onlineOrder.entity;
//package com.laioffer.onlineOrder.entity;
//@Bean
public class ApplicationConfig {
    @Bean(name = "sessionFactory")
    //建立一个bean对象，在spring里让他知道有且能创建sessionFactory的对象, 方便DI，@autowired
    public LocalSessionFactoryBean sessionFactory() {
        String PACKAGE_NAME = "com.laioffer.onlineOrder.entity"; //entity 去哪找
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(PACKAGE_NAME);
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    @Bean(name = "dataSource")
    public DataSource dataSource() {
        String RDS_ENDPOINT = "laiproject-instance.cji8vpnc9z5h.us-east-2.rds.amazonaws.com";
        String USERNAME = "admin";
        String PASSWORD = "ssyclm1234";
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://" + RDS_ENDPOINT + ":3306/onlineOrder?createDatabaseIfNotExist=true&serverTimezone=UTC");
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);

        return dataSource;
    }

    private final Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
        hibernateProperties.setProperty("hibernate.show_sql", "true");
        return hibernateProperties;
    }



}
