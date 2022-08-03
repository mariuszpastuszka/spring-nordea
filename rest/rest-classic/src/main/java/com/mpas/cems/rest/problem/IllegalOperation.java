
package com.mpas.cems.rest.problem;


public class IllegalOperation extends RuntimeException {
    public IllegalOperation(String message) {
        super(message);
    }

    public IllegalOperation(String message, Throwable cause) {
        super(message, cause);
    }
}
