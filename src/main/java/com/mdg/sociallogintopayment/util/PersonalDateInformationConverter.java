package com.mdg.sociallogintopayment.util;

import java.time.LocalDate;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class PersonalDateInformationConverter implements AttributeConverter<LocalDate, String> {
    @Override
    public String convertToDatabaseColumn(LocalDate attribute) {
        return null;
    }

    @Override
    public LocalDate convertToEntityAttribute(String dbData) {
        return null;
    }
}
