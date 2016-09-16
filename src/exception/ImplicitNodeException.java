package exception;

public class ImplicitNodeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ImplicitNodeException() {
		super();
	}

	public ImplicitNodeException(String message) {
		super(message);
	}

	public ImplicitNodeException(String message, Throwable cause) {
		super(message, cause);
	}

	public ImplicitNodeException(Throwable cause) {
		super(cause);
	}
}
