package com.vs.jasypt;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import com.vs.jasypt.beans.CheckPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEncryptableProperties
public class JasyptSpringBootMain {

    @Value("${encrypted.property.test}")
    private String encryptedData_ENC;

    public static void main(String[] args) {
        SpringApplication.run(JasyptSpringBootMain.class,args);
    }

    @Bean
    public CommandLineRunner getData(@Autowired CheckPassword checkPassword){
        return args -> {
            System.out.println("Encrypted Data from encrypted.properties = " + encryptedData_ENC);
            System.out.println("====checkPassword==== " + checkPassword );
        };
    }


}
