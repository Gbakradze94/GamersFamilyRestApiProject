package com.gamersfamily.gamersfamily.converter;

import com.gamersfamily.gamersfamily.model.Rate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;

@Converter(autoApply = true)
public class RateConverter implements AttributeConverter<Rate, Long> {
    @Override
    public Long convertToDatabaseColumn(Rate attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getRate();
    }

    @Override
    public Rate convertToEntityAttribute(Long dbData) {
        if (dbData == null) {
            return null;
        }
        return Arrays.stream(Rate.values()).filter(val -> val.getRate() == dbData).findFirst().orElseThrow(() -> {
            throw new IllegalArgumentException("wrong input data");
        });
    }
}
