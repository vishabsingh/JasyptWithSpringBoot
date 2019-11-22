package com.vs.jasypt.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories
public class DatabaseConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseConfig.class);

	@Autowired
	private Environment environment;

	public DataSource dataSource() {
		LOGGER.debug("Environment  : {} ", environment);
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName(environment.getRequiredProperty("spring.datasource.driverClassName"));
		ds.setUrl(environment.getRequiredProperty("spring.datasource.url"));
		ds.setUsername(environment.getRequiredProperty("spring.datasource.username"));
		ds.setPassword(environment.getRequiredProperty("spring.datasource.password"));
		LOGGER.info("DataSource : {} ", ds);
		return ds;
	}
}
