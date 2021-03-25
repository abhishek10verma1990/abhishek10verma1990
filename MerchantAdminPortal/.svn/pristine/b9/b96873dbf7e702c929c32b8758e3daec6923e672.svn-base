package com.npst.upi.portal.merchant.config;

import java.util.Properties;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
/**
 * 
 * @author Rahul Chaudhary
 *
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		  entityManagerFactoryRef = "entityManagerFactory",
		  transactionManagerRef = "transactionManager",
		  basePackages = { "com.npst.upi.portal.merchant.repo"}
		)
public class PrimaryDatabaseConfig {
	private static final Logger log = LoggerFactory.getLogger(PrimaryDatabaseConfig.class);
	@Autowired
	private Environment env;
	@Autowired
	@Qualifier(value="dataSource")
	private DataSource dataSource;
	@Autowired
	@Qualifier(value="entityManagerFactory")
	private LocalContainerEntityManagerFactoryBean entityManagerFactory;

	@Primary
	@Bean(name = "dataSource")
	DataSource dataSource() {
		log.trace("Creating HikariCP data source", null, null);
		HikariConfig dataSourceConfig = new HikariConfig();
		dataSourceConfig.setDriverClassName(env.getRequiredProperty("db.driver"));
		dataSourceConfig.setJdbcUrl(env.getRequiredProperty("db.url"));
		dataSourceConfig.setUsername(env.getRequiredProperty("db.username"));
		dataSourceConfig.setPassword(env.getRequiredProperty("db.password"));
		dataSourceConfig.setConnectionTimeout(env.getRequiredProperty("db.connectiontimeout", Long.class));
		dataSourceConfig.setIdleTimeout(env.getRequiredProperty("db.idletimeout", Long.class));
		dataSourceConfig.setMaximumPoolSize(env.getRequiredProperty("db.maxpoolsize", Integer.class));
		dataSourceConfig.setMaxLifetime(env.getRequiredProperty("db.maxlifetime", Long.class));
		dataSourceConfig.setMinimumIdle(env.getRequiredProperty("db.minidleconnection", Integer.class));
		dataSourceConfig.addDataSourceProperty("cachePrepStmts", env.getRequiredProperty("db.cachePrepStmts"));
		dataSourceConfig.addDataSourceProperty("prepStmtCacheSize", env.getRequiredProperty("db.prepStmtCacheSize"));
		dataSourceConfig.addDataSourceProperty("prepStmtCacheSqlLimit",
				env.getRequiredProperty("db.prepStmtCacheSqlLimit"));
		log.info("HikariCP data source created successfully", getConfigDetailsForLogging(dataSourceConfig), null);
		return new HikariDataSource(dataSourceConfig);
	}

	@Primary
	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		log.trace("Entry inside entity manager factor for HikariCP", null, null);
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setDataSource(dataSource);
		entityManagerFactory.setPackagesToScan(env.getProperty("entitymanager.packagesToScan"));
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		entityManagerFactory.setJpaVendorAdapter(vendorAdapter);
		Properties additionalProperties = new Properties();
		additionalProperties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
		additionalProperties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		if(env.getProperty("hibernate.hbm2ddl.auto")!=null){
			additionalProperties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		}
		entityManagerFactory.setJpaProperties(additionalProperties);
		log.info("Exit from entity manager factor for HikariCP", null, null);
		return entityManagerFactory;
	}

	@Primary
	@Bean(name = "transactionManager")
	public JpaTransactionManager transactionManager() {
		log.info("Entry inside transaction manager for HikariCP", null, null);
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory.getObject());
		log.info("Exit from transaction manager for HikariCP", null, null);
		return transactionManager;
	}

	@Primary
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	private String getConfigDetailsForLogging(final HikariConfig dataSourceConfig) {
		Properties prop = dataSourceConfig.getDataSourceProperties();
		return "DatabaseConfig [Connection Timeout=" + dataSourceConfig.getConnectionTimeout() + ", Idle Timeout="
				+ dataSourceConfig.getIdleTimeout() + ", Max Pool Size=" + dataSourceConfig.getMaximumPoolSize()
				+ ", Max Lifetime=" + dataSourceConfig.getMaxLifetime() + ", Min Idle Time="
				+ dataSourceConfig.getMinimumIdle() + ", cachePrepStmts=" + prop.getProperty("cachePrepStmts")
				+ ", prepStmtCacheSize=" + prop.getProperty("prepStmtCacheSize") + ", prepStmtCacheSqlLimit="
				+ prop.getProperty("prepStmtCacheSqlLimit") + "]";
	}
}