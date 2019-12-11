package com.vs.jasypt.beans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CheckPassword {

    @Value("${encrypted.property}")
    private String encryptedData_ENC;

    @Override
    public String toString() {
        return "CheckPassword{" +
                "encryptedData_ENC='" + encryptedData_ENC + '\'' +
                '}';
    }
}
