package cn.bronzeware.muppet.websiterelease;

public class ExceptionTest extends RuntimeException {

	public ExceptionTest(String message) {
		super(message);
	}

	public ExceptionTest(String message, Throwable cause) {
		super(message, cause);
	}

	public ExceptionTest(Throwable cause) {
		super(cause);
	}

	protected ExceptionTest(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
