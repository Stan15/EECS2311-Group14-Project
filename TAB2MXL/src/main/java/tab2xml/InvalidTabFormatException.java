package tab2xml;

public class InvalidTabFormatException extends Exception {
	InvalidTabFormatException() {
		super();
	}
	
	InvalidTabFormatException(String msg) {
		super(msg);
	}
}
