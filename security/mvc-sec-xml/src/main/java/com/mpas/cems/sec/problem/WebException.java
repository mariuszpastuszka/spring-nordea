
package com.mpas.cems.sec.problem;


public class WebException extends Exception {
    public WebException(String message) {
        super(message);
    }

    public WebException(String message, Throwable cause) {
        super(message, cause);
    }
}
