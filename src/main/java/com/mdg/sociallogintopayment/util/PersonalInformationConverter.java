package com.mdg.sociallogintopayment.util;

import jakarta.persistence.AttributeConverter;

public class PersonalInformationConverter implements AttributeConverter<String, String> {
    @Override
    public String convertToDatabaseColumn(String attribute) {
        return null;
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return null;
    }
}
