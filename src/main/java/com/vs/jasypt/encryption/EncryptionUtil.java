package com.vs.jasypt.encryption;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EncryptionUtil {

	private StringEncryptor applicationDataEncryptor;

	@Autowired
	public EncryptionUtil(@Qualifier("jasyptStringEncryptor") StringEncryptor applicationDataEncryptor) {
		this.applicationDataEncryptor = applicationDataEncryptor;
	}

	public String encryptForApplicationData(String plainText) {
		return applicationDataEncryptor.encrypt(plainText);
	}

	public String decryptForApplicationData(String encryptedText) {
		return applicationDataEncryptor.decrypt(encryptedText);
	}
}
