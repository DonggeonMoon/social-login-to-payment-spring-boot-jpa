package com.mdg.sociallogintopayment.util;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class PersonalDateInformationConverter implements AttributeConverter<LocalDate, String> {
    @Value("${crypto.algorithm}")
    private String algorithm;
    @Value("${crypto.key}")
    private String key;
    private final byte[] iv = {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};
    private Cipher cipher;
    private SecretKeySpec secretKeySpec;
    private IvParameterSpec ivParameterSpec;

    @PostConstruct
    public void init() {
        try {
            cipher = Cipher.getInstance(algorithm);
            secretKeySpec = new SecretKeySpec(key.getBytes(), "AES");
            ivParameterSpec = new IvParameterSpec(iv);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String convertToDatabaseColumn(LocalDate attribute) {
        try {
            String convertedDate = String.valueOf(attribute);

            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);

            return Base64.getEncoder().encodeToString(cipher.doFinal(convertedDate.getBytes()));
        } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public LocalDate convertToEntityAttribute(String dbData) {
        try {
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);

            String decryptedDate = new String(cipher.doFinal(Base64.getDecoder().decode(dbData)));

            return LocalDate.parse(decryptedDate);
        } catch (InvalidKeyException | InvalidAlgorithmParameterException | BadPaddingException | IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        }
    }
}
