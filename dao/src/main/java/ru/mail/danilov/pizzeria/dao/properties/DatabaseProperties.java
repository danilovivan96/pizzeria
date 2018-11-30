package ru.mail.danilov.pizzeria.dao.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DatabaseProperties {
    @Autowired
    private Environment environment;

    private String databaseDriverName;
    private String databaseUrl;
    private String databaseUsername;
    private String databasePassword;
    private String hibernateShowSql;
    private String hibernateDialect;
    private String hibernateUseSecondLvlCache;
    private String hibernateCacheRegionFactoryClass;
    private String hibernateHbm2ddlAuto;
    private String maxPoolSize;
    private String dataSourceClass;
    private String cachePreparedStatements;
    private String cachePreparedStatementsSize;
    private String cachePreparedStatementsSQLLimit;
    private String useServerPreparedStatements;


    @PostConstruct
    public void initialize() {
        this.databaseDriverName = environment.getProperty("database.driver.name");
        this.databaseUrl = environment.getProperty("database.url");
        this.databaseUsername = environment.getProperty("database.username");
        this.databasePassword = environment.getProperty("database.password");
        this.hibernateHbm2ddlAuto = environment.getProperty("hibernate.hbm2ddl.auto");
        this.maxPoolSize = environment.getProperty("pool.max.size");
        this.dataSourceClass = environment.getProperty("pool.data.source.class");
        this.cachePreparedStatements = environment.getProperty("pool.cache.prepare.statements");
        this.cachePreparedStatementsSize = environment.getProperty("pool.cache.prepare.statements.size");
        this.cachePreparedStatementsSQLLimit = environment.getProperty("pool.cache.prepare.statements.sql.limit");
        this.useServerPreparedStatements = environment.getProperty("pool.use.server.prepared.statements");
        this.hibernateShowSql = environment.getProperty("hibernate.show_sql");
        this.hibernateDialect = environment.getProperty("hibernate.dialect");
        this.hibernateUseSecondLvlCache = environment.getProperty("hibernate.cache.use_second_level_cache");
        this.hibernateCacheRegionFactoryClass = environment.getProperty("hibernate.cache.region.factory_class");
    }

    public Environment getEnvironment() {
        return environment;
    }

    public String getDatabaseDriverName() {
        return databaseDriverName;
    }

    public String getDatabaseUrl() {
        return databaseUrl;
    }

    public String getDatabaseUsername() {
        return databaseUsername;
    }

    public String getDatabasePassword() {
        return databasePassword;
    }

    public String getHibernateShowSql() {
        return hibernateShowSql;
    }

    public String getHibernateDialect() {
        return hibernateDialect;
    }

    public String getHibernateUseSecondLvlCache() {
        return hibernateUseSecondLvlCache;
    }

    public String getHibernateCacheRegionFactoryClass() {
        return hibernateCacheRegionFactoryClass;
    }

    public String getHibernateHbm2ddlAuto() {
        return hibernateHbm2ddlAuto;
    }

    public String getMaxPoolSize() {
        return maxPoolSize;
    }

    public String getDataSourceClass() {
        return dataSourceClass;
    }

    public String getCachePreparedStatements() {
        return cachePreparedStatements;
    }

    public String getCachePreparedStatementsSize() {
        return cachePreparedStatementsSize;
    }

    public String getCachePreparedStatementsSQLLimit() {
        return cachePreparedStatementsSQLLimit;
    }

    public String getUseServerPreparedStatements() {
        return useServerPreparedStatements;
    }
}
