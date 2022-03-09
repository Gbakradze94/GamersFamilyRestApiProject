package com.gamersfamily.gamersfamily.converter;

import com.gamersfamily.gamersfamily.utils.enums.Platform;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;

@Converter(autoApply = true)
public class PlatformConverter implements AttributeConverter<Platform, String> {

    @Override
    public String convertToDatabaseColumn(Platform attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getPlatform();
    }

    @Override
    public Platform convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return Arrays.stream(Platform.values()).filter(val -> val.getPlatform().equals(dbData)).findFirst().orElseThrow(() -> {
            throw new IllegalArgumentException("wrong input data");
        });
    }
}
