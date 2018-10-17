package com.spring.data.rest.handler;

public class InActiveManufacturerException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
    public InActiveManufacturerException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public InActiveManufacturerException(String message) {
        super(message);
    }
    
    public InActiveManufacturerException(Throwable cause) {
        super(cause);
    }
}
