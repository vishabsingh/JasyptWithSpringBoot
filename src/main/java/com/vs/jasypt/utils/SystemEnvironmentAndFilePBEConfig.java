package com.vs.jasypt.utils;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.jasypt.encryption.pbe.config.SimplePBEConfig;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.function.Function;

/**
 * Fail safe password configuration using in this order :
 * <ul>
 * <li>System property</li>
 * <li>Environment variable</li>
 * <li>Password file content (system then environment file name)</li>
 * </ul>
 */
@Slf4j
public class SystemEnvironmentAndFilePBEConfig<pipe> extends SimplePBEConfig {

	@Setter
	private String passwordEnvName;
	@Setter
	private String passwordSysPropertyName;
	@Setter
	private String passwordFileEnvName;
	@Setter
	private String passwordFilePropertyName;

	public String getPasswordFromFile(String fileName) {
		// Read password from file
		try {
			return StringUtils.trimToNull(FileUtils.readFileToString(new File(fileName), StandardCharsets.UTF_8.name()));
		}catch (Exception e){
			log.warn(" Unable to read file ");
		}

		// Read password from classpath
		try {
			return StringUtils.trimToNull(IOUtils.toString(new ClassPathResource(fileName).getInputStream(),StandardCharsets.UTF_8.name()));
		} catch (IOException e) {
			log.warn(" Unable to read file from class path ");
		}

		return null;
	}

	@Override
	public String getPassword() {
		return new String(getPasswordCharArray());
	}

	@Override
	public char[] getPasswordCharArray() {
		// Raw value providers
		String password = pipe(passwordSysPropertyName, System::getProperty);
		if (password == null) {
			password = pipe(passwordSysPropertyName, GlobalPropertyUtils::getProperty);
		}
		if (password == null) {
			password = pipe(passwordEnvName, System::getenv);
		}

		// File providers
		if (password == null) {
			password = pipe(passwordFilePropertyName, System::getProperty, this::getPasswordFromFile);
		}
		if (password == null) {
			password = pipe(passwordFilePropertyName, GlobalPropertyUtils::getProperty, this::getPasswordFromFile);
		}
		if (password == null) {
			password = pipe(passwordFileEnvName, System::getenv, this::getPasswordFromFile);
		}
		setPassword(password);
		return super.getPasswordCharArray();
	}
	@SafeVarargs
	private final String pipe(String property, Function<String,String>... piperReaders){
		String value = property;
		for (Function<String,String> reader: piperReaders) {
			if(StringUtils.isNotBlank(value)){
				value = reader.apply(value);
			}
		}
		return value;
	}


}
