package com.vakhnenko.departments.config;

import com.vakhnenko.departments.entity.Department;
import com.vakhnenko.departments.entity.Employee;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan("com.vakhnenko.departments.entity")
@EnableJpaRepositories("com.vakhnenko.departments.dao")
@PropertySource("WEB-INF/properties/database.properties")
public class HibernateConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private Environment env;

    // Declare a JPA Data Source
    @Bean
    public DataSource dataSourceHibernate() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(env.getProperty("jdbc.driver"));
        dataSource.setUrl(env.getProperty("jdbc.url"));
        dataSource.setUsername(env.getProperty("jdbc.username"));
        dataSource.setPassword(env.getProperty("jdbc.password"));

        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSourceHibernate());
        sessionFactory.setPackagesToScan(new String[]{"com.vakhnenko.departments.entity"});
        sessionFactory.setHibernateProperties(hibernateProperties());
        sessionFactory.setAnnotatedClasses(new Class[]{Department.class, Employee.class});

        return sessionFactory;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManagerHibernate(
            SessionFactory sessionFactory) {

        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);

        return txManager;
    }

    Properties hibernateProperties() {
        return new Properties() {
            {
                setProperty("hibernate.hbm2ddl.auto", "update");
                setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
                setProperty("hibernate.globally_quoted_identifiers", "true");
                setProperty("hibernate.show_sql", "true");
                setProperty("hibernate.connection.characterEncoding", "utf8");
            }
        };
    }
}
