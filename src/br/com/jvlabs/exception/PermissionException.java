package br.com.jvlabs.exception;

public class PermissionException extends RuntimeException {

	private static final long serialVersionUID = -8487351776701337274L;

	public PermissionException(String message, Throwable cause) {
		super(message, cause);
	}

	public PermissionException(String message) {
		super(message);
	}

	public PermissionException(Throwable cause) {
		super(cause);
	}

}
