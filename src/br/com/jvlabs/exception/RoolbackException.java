package br.com.jvlabs.exception;

public class RoolbackException extends RuntimeException {

	private static final long serialVersionUID = -8487351776701337274L;

	public RoolbackException(String message, Throwable cause) {
		super(message, cause);
	}

	public RoolbackException(String message) {
		super(message);
	}

	public RoolbackException(Throwable cause) {
		super(cause);
	}
	public RoolbackException() {
		super();
	}

}
