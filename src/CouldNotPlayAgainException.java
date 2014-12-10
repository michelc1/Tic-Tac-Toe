
public class CouldNotPlayAgainException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CouldNotPlayAgainException(String message){
		message = "Could not start a new Game, So Sorry";
	}

}
