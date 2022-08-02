
package com.mpas.cems.web.problem;


public class WebException extends Exception {
    public WebException(String message) {
        super(message);
    }

    public WebException(String message, Throwable cause) {
        super(message, cause);
    }
}
