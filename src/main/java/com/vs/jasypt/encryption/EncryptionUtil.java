package com.vs.jasypt.encryption;

import jdk.nashorn.internal.objects.annotations.Setter;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Utility managing {@link StringEncryptor}
 */
@Component
public class EncryptionUtil {

	private StringEncryptor applicationDataEncryptor;

	public EncryptionUtil(@Qualifier("jasyptStringEncryptor") StringEncryptor applicationDataEncryptor) {
		this.applicationDataEncryptor = applicationDataEncryptor;
	}

	public String encryptForApplicationData(String plainText) {
		return applicationDataEncryptor.encrypt(plainText);
	}

	public String decryptForApplicationData(String encryptedText) {
		return applicationDataEncryptor.decrypt(encryptedText);
	}

	/**
	 * Encrypt a clear value. Try to decrypt the value, and if succeed, return the formal parameter without encrypting
	 * again the value.
	 *
	 * @param value
	 *            A potentially raw value to encrypt.
	 * @return The encrypted value, or formal parameter if was already encrypted.
	 */
	public String encryptAsNeeded(final String value) {
		try {
			decryptForApplicationData(value);
			return value;
		} catch (final EncryptionOperationNotPossibleException e) {
			return encryptForApplicationData(value);
		}
	}

	public String decryptAsNeeded(final String value) {
		try {
			return decryptForApplicationData(value);
		} catch (final EncryptionOperationNotPossibleException e) {
			return value;
		}
	}

	public void setApplicationDataEncryptor(StringEncryptor applicationDataEncryptor) {
		this.applicationDataEncryptor = applicationDataEncryptor;
	}
}
