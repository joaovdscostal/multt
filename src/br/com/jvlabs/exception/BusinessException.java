package br.com.jvlabs.exception;

public class BusinessException extends Exception {

	private static final long serialVersionUID = -8487351776701337274L;

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(Throwable cause) {
		super(cause);
	}

}
