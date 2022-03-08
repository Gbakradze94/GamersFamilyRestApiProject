package com.gamersfamily.gamersfamily.converter;

import com.gamersfamily.gamersfamily.model.Rate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;

@Converter
public class RateConverter implements AttributeConverter<Rate, Long> {
    @Override
    public Long convertToDatabaseColumn(Rate attribute) {
        return attribute.getRate();
    }

    @Override
    public Rate convertToEntityAttribute(Long dbData) {
        return Arrays.stream(Rate.values()).filter(val -> val.getRate() == dbData).findFirst().orElseThrow(() -> {
            throw new IllegalArgumentException("wrong input data");

        });

    }


}
