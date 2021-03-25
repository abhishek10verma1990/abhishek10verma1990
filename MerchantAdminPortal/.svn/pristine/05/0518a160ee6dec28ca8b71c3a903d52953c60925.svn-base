/*package com.npst.upi.portal.merchant.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
*//**
 * 
 * @author Rahul Chaudhary
 *
 *//*
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		  entityManagerFactoryRef = "canEntityManagerFactory",
		  transactionManagerRef = "canTransactionManager",
		  basePackages = { "com.npst.portal.repo" }
		)
public class SecondaryDatabaseConfig {

	private static final Logger log = LoggerFactory.getLogger(SecondaryDatabaseConfig.class);
	@Autowired
	private Environment env;
	@Autowired
	@Qualifier(value="canDataSource")
	private DataSource canDataSource;
	@Autowired
	@Qualifier(value="canEntityManagerFactory")
	private LocalContainerEntityManagerFactoryBean canEntityManagerFactory;

	@Bean(name = "canDataSource")
	DataSource canDataSource() {
		log.trace("Creating HikariCP data source", null, null);
		HikariConfig dataSourceConfig = new HikariConfig();
		dataSourceConfig.setDriverClassName(env.getRequiredProperty("can.db.driver"));
		dataSourceConfig.setJdbcUrl(env.getRequiredProperty("can.db.url"));
		dataSourceConfig.setUsername(env.getRequiredProperty("can.db.username"));
		dataSourceConfig.setPassword(env.getRequiredProperty("can.db.password"));
		dataSourceConfig.setConnectionTimeout(env.getRequiredProperty("can.db.connectiontimeout", Long.class));
		dataSourceConfig.setIdleTimeout(env.getRequiredProperty("can.db.idletimeout", Long.class));
		dataSourceConfig.setMaximumPoolSize(env.getRequiredProperty("can.db.maxpoolsize", Integer.class));
		dataSourceConfig.setMaxLifetime(env.getRequiredProperty("can.db.maxlifetime", Long.class));
		dataSourceConfig.setMinimumIdle(env.getRequiredProperty("can.db.minidleconnection", Integer.class));
		dataSourceConfig.addDataSourceProperty("cachePrepStmts", env.getRequiredProperty("can.db.cachePrepStmts"));
		dataSourceConfig.addDataSourceProperty("prepStmtCacheSize", env.getRequiredProperty("can.db.prepStmtCacheSize"));
		dataSourceConfig.addDataSourceProperty("prepStmtCacheSqlLimit",
				env.getRequiredProperty("can.db.prepStmtCacheSqlLimit"));
		log.info("HikariCP data source created successfully", getCANConfigDetailsForLogging(dataSourceConfig), null);
		return new HikariDataSource(dataSourceConfig);
	}

	@Bean(name = "canEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean canEntityManagerFactory() {
		log.trace("Entry inside entity manager factor for HikariCP", null, null);
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setDataSource(canDataSource);
		entityManagerFactory.setPackagesToScan(env.getProperty("can.entitymanager.packagesToScan"));
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		entityManagerFactory.setJpaVendorAdapter(vendorAdapter);
		Properties additionalProperties = new Properties();
		additionalProperties.put("hibernate.dialect", env.getProperty("can.hibernate.dialect"));
		additionalProperties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		if(env.getProperty("hibernate.hbm2ddl.auto")!=null){
			additionalProperties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		}
		entityManagerFactory.setJpaProperties(additionalProperties);
		log.info("Exit from entity manager factor for HikariCP", null, null);
		return entityManagerFactory;
	}

	@Bean(name = "canTransactionManager")
	public JpaTransactionManager canTransactionManager() {
		log.info("Entry inside transaction manager for HikariCP", null, null);
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(canEntityManagerFactory.getObject());
		log.info("Exit from transaction manager for HikariCP", null, null);
		return transactionManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor canExceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	private String getCANConfigDetailsForLogging(final HikariConfig dataSourceConfig) {
		Properties prop = dataSourceConfig.getDataSourceProperties();
		return "CapexCANDatabaseConfig [Connection Timeout=" + dataSourceConfig.getConnectionTimeout() + ", Idle Timeout="
				+ dataSourceConfig.getIdleTimeout() + ", Max Pool Size=" + dataSourceConfig.getMaximumPoolSize()
				+ ", Max Lifetime=" + dataSourceConfig.getMaxLifetime() + ", Min Idle Time="
				+ dataSourceConfig.getMinimumIdle() + ", cachePrepStmts=" + prop.getProperty("cachePrepStmts")
				+ ", prepStmtCacheSize=" + prop.getProperty("prepStmtCacheSize") + ", prepStmtCacheSqlLimit="
				+ prop.getProperty("prepStmtCacheSqlLimit") + "]";
	}

}
*/