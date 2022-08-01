package com.mpas.cems.beans.scalars;

import com.mpas.cems.util.DateProcessor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Service
public class StringToDate implements Converter<String, Date> {

    private SimpleDateFormat formatter = new SimpleDateFormat(DateProcessor.DATE_FORMAT);

    @Override
    public Date convert(String source) {
        try {
            return formatter.parse(source);
        } catch (ParseException e) {
            throw new ToDateConversionException(e);
        }
    }
}
