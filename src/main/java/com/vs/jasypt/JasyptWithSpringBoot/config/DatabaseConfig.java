package com.vs.jasypt.JasyptWithSpringBoot.config;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.DriverManager;

@Configuration
@EnableJpaRepositories
@EnableEncryptableProperties
public class DatabaseConfig {

	@Autowired
	private Environment environment;

	public DataSource dataSource(){
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName(environment.getRequiredProperty("spring.datasource.driverClassName"));
		ds.setUrl(environment.getRequiredProperty("spring.datasource.url"));
		ds.setUsername(environment.getRequiredProperty("spring.datasource.username"));
		ds.setPassword(environment.getRequiredProperty("spring.datasource.password"));
		return ds;
	}
}
