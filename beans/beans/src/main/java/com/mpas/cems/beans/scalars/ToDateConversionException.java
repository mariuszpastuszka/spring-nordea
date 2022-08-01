package com.mpas.cems.beans.scalars;


public class ToDateConversionException extends RuntimeException {
    public ToDateConversionException(String message) {
        super(message);
    }

    public ToDateConversionException(Throwable cause) {
        super(cause);
    }
}
