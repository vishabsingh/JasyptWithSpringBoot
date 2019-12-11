package encryptionUtils;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

public class EncryptionUtil_2 {
    @Autowired
    @Qualifier("lazyJasyptStringEncryptor")
    private StringEncryptor jasyptStringEncryptor;

    @Autowired
    @Qualifier("applicationDataEncryptor")
    private StringEncryptor applicationDataEncryptor;

    @Value("${app.log.encryption.enabled:true}")
    private boolean logEncryptionEnabled;


    public String encryptForKeystore(String plainText) {
        return jasyptStringEncryptor.encrypt(plainText);
    }

    public String decryptForKeystore(String encryptedText) {
        return jasyptStringEncryptor.decrypt(encryptedText);
    }

    public String encryptForApplicationData(String plainText) {
        return applicationDataEncryptor.encrypt(plainText);
    }

    public String decryptForApplicationData(String encryptedText) {
        return applicationDataEncryptor.decrypt(encryptedText);
    }

    public boolean isLogEncryptionEnabled() {
        return logEncryptionEnabled;
    }

    public void setLogEncryptionEnabled(boolean logEncryptionEnabled) {
        this.logEncryptionEnabled = logEncryptionEnabled;
    }

    @Override
    public String toString() {
        return "EncryptionUtil{" +
                "jasyptStringEncryptor=" + jasyptStringEncryptor +
                ", applicationDataEncryptor=" + applicationDataEncryptor +
                ", logEncryptionEnabled=" + logEncryptionEnabled +
                '}';
    }
}
