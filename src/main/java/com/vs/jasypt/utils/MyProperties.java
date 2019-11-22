package com.vs.jasypt.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
//@PropertySource("classpath:encrypted.properties")
@ConfigurationProperties
@PropertySource("file:C:\\workspace_github\\JasyptWithSpringBoot\\encrypted.properties")
public class MyProperties {

	String name;
	String test;
	Mail mail;

	public static class Mail {
		String id;
		String host;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getHost() {
			return host;
		}

		public void setHost(String host) {
			this.host = host;
		}

		@Override
		public String toString() {
			return "Mail{" +
				"id='" + id + '\'' +
				", host='" + host + '\'' +
				'}';
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public Mail getMail() {
		return mail;
	}

	public void setMail(Mail mail) {
		this.mail = mail;
	}

	@Override
	public String toString() {
		return "MyProperties{" +
			"name='" + name + '\'' +
			", test='" + test + '\'' +
			", spring=" + mail +
			'}';
	}
}
