package converter;

@SuppressWarnings("serial")
public class InvalidTabFormatException extends Exception {
	InvalidTabFormatException() {
		super();
	}
	
	InvalidTabFormatException(String msg) {
		super(msg);
	}
}
