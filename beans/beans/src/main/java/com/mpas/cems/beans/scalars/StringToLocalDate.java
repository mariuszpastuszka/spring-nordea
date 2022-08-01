package com.mpas.cems.beans.scalars;

import com.mpas.cems.util.DateProcessor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Component
public class StringToLocalDate implements Converter<String, LocalDateTime> {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateProcessor.DATE_FORMAT);

    @Override
    public LocalDateTime convert(String source) {
        return LocalDateTime.parse(source, formatter);
    }
}
