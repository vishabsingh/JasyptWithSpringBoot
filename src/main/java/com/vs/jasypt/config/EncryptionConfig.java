package com.vs.jasypt.config;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.jasypt.salt.RandomSaltGenerator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.security.Security;

@Configuration
@ConditionalOnExpression("${app.security.dataEncryptionEnabled}")
public class EncryptionConfig {

	@Autowired
	private Environment environment;

	static {
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
	}

	/*@Bean("jasyptStringEncryptor")
	public StringEncryptor appDataEncrypted() {
		PooledPBEStringEncryptor pooledPBEStringEncryptor = new PooledPBEStringEncryptor();
		SimpleStringPBEConfig stringPBEConfig = new SimpleStringPBEConfig();
		stringPBEConfig.setPassword(environment.getProperty("jasypt.encryptor.password"));
		stringPBEConfig.setProviderName(environment.getProperty("jasypt.encryptor.providerName"));
		stringPBEConfig.setAlgorithm(environment.getProperty("jasypt.encryptor.algorithm"));
		stringPBEConfig.setPoolSize("1");
		stringPBEConfig.setSaltGenerator(new RandomSaltGenerator());
		stringPBEConfig.setStringOutputType("base64");
		stringPBEConfig.setKeyObtentionIterations("1000");
		pooledPBEStringEncryptor.setConfig(stringPBEConfig);
		return pooledPBEStringEncryptor;
	}*/

	@Bean
	public static EnvironmentStringPBEConfig environmentVariablesConfiguration() {
		EnvironmentStringPBEConfig config = new EnvironmentStringPBEConfig();
		config.setPasswordEnvName("APP_KEYSTORE_ENCRYPTOR_PASSWORD");
		config.setAlgorithm("PBEWITHSHAAND256BITAES-CBC-BC");
		config.setKeyObtentionIterations("1000");
		config.setPoolSize("1");
		config.setProviderName("BC");
		config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
		config.setStringOutputType("base64");
		return config;
	}

	@Bean(name="jasyptStringEncryptor")
	public static PooledPBEStringEncryptor stringEncryptor() {
		PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
		encryptor.setConfig(environmentVariablesConfiguration());
		return encryptor;
	}
}
