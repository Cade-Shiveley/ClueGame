package clueGame;

public class BadConfigFormatException extends Exception {
	public BadConfigFormatException() {
		//returns a default message
		super("Bad error config format ");
	}
	
	public BadConfigFormatException(String test) {
		// returns a message more specific
		super(test);
	}
	
}