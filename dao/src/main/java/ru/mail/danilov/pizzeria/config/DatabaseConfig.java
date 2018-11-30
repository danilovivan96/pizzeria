package ru.mail.danilov.pizzeria.config;

import com.zaxxer.hikari.HikariDataSource;
import liquibase.integration.spring.SpringLiquibase;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.mail.danilov.pizzeria.dao.model.*;
import ru.mail.danilov.pizzeria.dao.properties.DatabaseProperties;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class DatabaseConfig {

    private final DatabaseProperties databaseProperties;

    @Autowired
    public DatabaseConfig(DatabaseProperties databaseProperties) {
        this.databaseProperties = databaseProperties;
    }

    @Bean
    public DataSource dataSource() {
        final HikariDataSource dSource = new HikariDataSource();
        dSource.setPoolName("Pizzeria connection pool");
        dSource.setMaximumPoolSize(Integer.parseInt(databaseProperties.getMaxPoolSize()));
        dSource.setDataSourceClassName(databaseProperties.getDataSourceClass());
        dSource.addDataSourceProperty("url", databaseProperties.getDatabaseUrl());
        dSource.addDataSourceProperty("user", databaseProperties.getDatabaseUsername());
        dSource.addDataSourceProperty("password", databaseProperties.getDatabasePassword());
        dSource.addDataSourceProperty("cachePrepStmts", databaseProperties.getCachePreparedStatements());
        dSource.addDataSourceProperty("prepStmtCacheSize", databaseProperties.getCachePreparedStatementsSize());
        dSource.addDataSourceProperty("prepStmtCacheSqlLimit", databaseProperties.getCachePreparedStatementsSQLLimit());
        dSource.addDataSourceProperty("useServerPrepStmts", databaseProperties.getUseServerPreparedStatements());
        return dSource;
    }

    @Bean
    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        return transactionManager;
    }

    @Bean
    public SpringLiquibase springLiquibase(DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog("classpath:migration/database.changelog.xml");
        return liquibase;
    }

    @Bean
    @DependsOn("springLiquibase")
    public LocalSessionFactoryBean getSessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        Properties properties = new Properties();
        properties.put(Environment.DIALECT, databaseProperties.getHibernateDialect());
        properties.put(Environment.SHOW_SQL, databaseProperties.getHibernateShowSql());
        properties.put(Environment.HBM2DDL_AUTO, databaseProperties.getHibernateHbm2ddlAuto());
        properties.put(Environment.USE_SECOND_LEVEL_CACHE, databaseProperties.getHibernateUseSecondLvlCache());
        properties.put(Environment.CACHE_REGION_FACTORY, databaseProperties.getHibernateCacheRegionFactoryClass());
        sessionFactoryBean.setHibernateProperties(properties);
        sessionFactoryBean.setAnnotatedClasses(
                Audit.class,
                User.class,
                Role.class,
                News.class,
                Profile.class,
                Item.class,
                Comment.class,
                Order.class,
                Permission.class,
                OrderPosition.class,
                RolePermission.class);
        return sessionFactoryBean;
    }
}
