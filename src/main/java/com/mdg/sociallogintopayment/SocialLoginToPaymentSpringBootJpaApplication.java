package com.mdg.sociallogintopayment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.mdg")
public class SocialLoginToPaymentSpringBootJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocialLoginToPaymentSpringBootJpaApplication.class, args);
    }

}
