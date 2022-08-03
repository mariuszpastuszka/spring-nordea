
package com.mpas.cems.secured.ex;


public class IllegalOperation extends RuntimeException {
    public IllegalOperation(String message) {
        super(message);
    }

    public IllegalOperation(String message, Throwable cause) {
        super(message, cause);
    }
}
