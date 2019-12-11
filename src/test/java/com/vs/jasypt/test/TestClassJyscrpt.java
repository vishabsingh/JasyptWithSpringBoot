package com.vs.jasypt.test;

import encryptionUtils.EncryptionUtil_2;
import com.ulisesbocchio.jasyptspringboot.EncryptablePropertyDetector;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.jasypt.salt.StringFixedSaltGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;


public class TestClassJyscrpt {
    @Autowired
    private EncryptionUtil_2 encryptUtil;

    @Autowired
    private EncryptablePropertyDetector encryptablePropertyDetector;

    public static void main(String[] args) {
        getEncryptionUtil();
    }

    public static EncryptionUtil_2 getEncryptionUtil() {
        EncryptionUtil_2 encryptionUtil = new EncryptionUtil_2();
        StandardPBEStringEncryptor stringEncryptor = new StandardPBEStringEncryptor();
        setField(EncryptionUtil_2.class, encryptionUtil, "applicationDataEncryptor", stringEncryptor);
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword("TestPassword");
        config.setAlgorithm("PBEWITHSHAAND256BITAES-CBC-BC");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("BC");
        config.setSaltGenerator(new StringFixedSaltGenerator("abc233$s74n58S973bs7sn2v1-0dfn^*QW/q"));
        config.setStringOutputType("base64");
        stringEncryptor.setConfig(config);
        System.out.println(" Encryptiin : "+encryptionUtil);
        return encryptionUtil;
    }
    public static void setField(final Class<?> objClass, final Object obj, final String fieldName,
                                final Object fieldValue) {
        Field field = ReflectionUtils.findField(objClass, fieldName);
        ReflectionUtils.makeAccessible(field);
        setFieldModifiers(field);
        ReflectionUtils.setField(field, obj, fieldValue);
    }

    public static void setFieldModifiers(final Field field) {
        try {
            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(field, field.getModifiers() & Modifier.FINAL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public  void testConfig(String value ){

        if (encryptablePropertyDetector.isEncrypted(value)) {
            value = encryptUtil.decryptForKeystore(encryptablePropertyDetector.unwrapEncryptedValue(value));
        }

        value = getDecryptedValue(value);
    }

    private String getDecryptedValue(String value) {
        String decryptedVal = value;
        if (encryptablePropertyDetector.isEncrypted(value)) {
            decryptedVal = encryptUtil.decryptForKeystore(
                    encryptablePropertyDetector.unwrapEncryptedValue(value));
        }
        return decryptedVal;
    }


}
