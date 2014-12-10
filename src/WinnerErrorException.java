
public class WinnerErrorException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WinnerErrorException(String message){
		super("There was no winner, draw");
		
	}

}
