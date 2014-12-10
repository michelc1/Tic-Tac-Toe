import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;


public class Game {

	private char[] templateOfBoard; // our board, TicTacToe field, static
	private char userTurn; // users turn , only one letter, tracks whether it is a X or O 
	private int count; // keeps track of user moves
	final JFrame frame; // our JFrame

	// constructor 
	// want to initialize the variable 
	// here we are stating the board size
	// userTurn starts off at X

	public Game(final JFrame frame) throws BoardErrorException{ // Game takes in the JFrame 

		this.frame = frame;
		count = 0; // number of turns starts at 0;

		setUserTurn('X'); // first turn will always be X
		setTemplateOfBoard(new char[GUI.sizeOfBoard]); // size of the board we are going to make it 

		try{
		for(int spaces=0; spaces<GUI.sizeOfBoard; spaces++){ // size of Board is in the GUI class

			getTemplateOfBoard()[spaces] = ' '; // the board is being created, looping through all rows and col

			//every index of the board not has a char value equal to a space 

			//determine if everything came out correctly 
			//should equal of a total of 9
			// 3x3
		}
		System.out.println("Board template created"); // means the board now has all spaces 
	}
		catch(Exception e){
			System.out.println("Could not initalize the board to empty char");
			e.printStackTrace();
		}
		
	}

	public Game userMove(int moveMade) throws UserMoveErrorExcepion {

		getTemplateOfBoard()[moveMade] = getUserTurn();
		// index of the board, or in simpler terms, where the user
		// inserts there turn i.e X or O, 0-8
		//System.out.println(userMove);

		//boolean statement to determine the turns 
		// So user X starts first
		//if the turn is X, the nextTurn is now O,
	
		if(getUserTurn() == 'X'){
			setUserTurn('O');	
		}
		else {
			setUserTurn('X');
		}

		count++; 
		
		return this; // going to return the userTurn
		// issue actually entering the userTurn is not giving right value, but using 'this' does 
	}


	// for some odd reason the toString is causing some issues, keep getting @hash code
	//saw online to override it like this 
	// will make the board out of emepty strings 
	// going to return a string representation of an object 
	public String toString(){
		return new String(getTemplateOfBoard());
	}

	public void mouseListener(ActionEvent e, int moveMade) throws ButtonsNotMadeException,ButtonCanNotBeClickedException, WinnerErrorException{ 
		// mouse click events 
		// what happens after a button is clicked 

		if(getTemplateOfBoard()[moveMade] == ' '){ // the user can only space a click, so an letter on the field if it is empty 
			((JButton)e.getSource()).setText(Character.toString(getUserTurn())); // when the button is clicked, we want an X placed there 
			if (getUserTurn() == 'X'){
				UIManager.getDefaults().put("Button.disabledText",Color.RED); // when the but gets disabled the test will turn red 		
			}
			else{
				UIManager.getDefaults().put("Button.disabledText",Color.BLUE);
			}
			//calling the method userTurn to determine who goes next
			//problem is that is expects a String
			//going to override the toString method 
			try {
				userMove(moveMade); // calling userMove in moveMade, moveMade is the index at which the user put either an X or a O
				winner(); // we want to check each time to ensure there was/was not a winner 

			} catch (UserMoveErrorExcepion | WinnerErrorException | BoardErrorException | ButtonsNotMadeException | ButtonCanNotBeClickedException e1) {
				e1.printStackTrace();
			}

		}
	}

	public Game winner() throws WinnerErrorException, BoardErrorException, ButtonsNotMadeException, ButtonCanNotBeClickedException { // determines who is the winner


		//list below defines all the possible win combinations 
		// the index of where a X or O can be place
		// placed the locations to a int value 
		int win1 = templateOfBoard[0] + templateOfBoard[1] + templateOfBoard[2];	
		int win2 = templateOfBoard[3] + templateOfBoard[4] + templateOfBoard[5];
		int win3 = templateOfBoard[6] + templateOfBoard[7] + templateOfBoard[8];
		int win4 = templateOfBoard[0] + templateOfBoard[3] + templateOfBoard[6];
		int win5 = templateOfBoard[1] + templateOfBoard[4] + templateOfBoard[7];
		int win6 = templateOfBoard[2] + templateOfBoard[5] + templateOfBoard[8];
		int win7 = templateOfBoard[0] + templateOfBoard[4] + templateOfBoard[8];
		int win8 = templateOfBoard[2] + templateOfBoard[4] + templateOfBoard[6];

		int[] win = new int[]{win1,win2,win3,win4,win5,win6,win7,win8}; 
		// making a array to go through all the possibile wins 
		//possible total of wins is 8


		for(int i = 0;i<win.length;i++){	 
			// looping through the win possibilities 
	
			if(win[i] == 264){ // if one of the the combinations equal 'X','X','X' which equals 264, then there is a winner 
				System.out.println("X is the winner!!!");	
				System.out.println("Game Over!");
				draw(i);
		
				try {
					gameOver("X is the Winner");
				} catch (CouldNotPlayAgainException | NoCancelOption e) {
					e.printStackTrace();
				}
				
				return this; // if statement is true, it will return this(gameOver)
			}
			else if(win[i] == 237 ){ // if one of the the combinations equal 'O','O','O' which equals 234, then there is a winner 
				System.out.println("O is the winner!!!");
				System.out.println("Game Over!");
				draw(i);
				
				try {
					gameOver("O is the Winner");
				} catch (CouldNotPlayAgainException | NoCancelOption e) {
					e.printStackTrace();
				} // if statement is true, it will return this(gameOver)
				//System.out.print("O Hello1");
				return this;

			}
		} 

		//
		if (count == 9) {
			// if none of the statements above are true, it automatically comes done to here
			//so if there is nine moves and no win, it is a draw 
			try {
				gameOver("Draw");
			} catch (CouldNotPlayAgainException | NoCancelOption e) {
				e.printStackTrace();
			}

		}

		return this; 
		// going to return this method ;
	} 

	private void gameOver(String message) throws BoardErrorException, ButtonsNotMadeException, ButtonCanNotBeClickedException, 
	                                             WinnerErrorException, CouldNotPlayAgainException,NoCancelOption{

		JOptionPane.showMessageDialog(null, message, "Game Over", JOptionPane.YES_NO_OPTION); // gives a popup window at the end of the game 

		int playAgain = JOptionPane.showConfirmDialog(null, "Do you want to play another game?", 
				"Play Again", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
		//going to ask user if he wants to play agin, while still seeing the board 

		if (playAgain == JOptionPane.OK_OPTION) { 

			frame.dispose(); // going to dispose of our frame// if user hit ok 
			GUI.playAgain(); // play the game again

		}
	    if(playAgain == JOptionPane.CANCEL_OPTION){
			secondPlayAgin(); // give user second chance 
		}	
		
	}

	private void secondPlayAgin() throws CouldNotPlayAgainException,NoCancelOption {

		int secondChoice = JOptionPane.showConfirmDialog(null, "PLEASE PLAY ME AGAIN", 
				"Play Again", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

		if (secondChoice == JOptionPane.OK_OPTION) { 
			frame.dispose(); // going to dispose of our frame// if user hit ok 
			GUI.playAgain();

		}
		else if(secondChoice == JOptionPane.CANCEL_OPTION){
			JOptionPane.showMessageDialog(null, "You are entering View Mode Only", "View Only Mode", JOptionPane.YES_NO_OPTION);
			
			//disabling all JButton until new game is started 
		 try{			 
			for(int i=0;i<9;i++){

				 GUI.clickButton[i].setEnabled(false);
				//System.out.println("Buttons Disabled");
			}
			System.out.println("VIEW MODE ONLY");
		 }
		 catch(Exception e){
			 System.out.println("Could not disable the button");
			 e.printStackTrace();
		 }
		}
	}
	
	private void draw(int win){

		Graphics2D g1 = (Graphics2D) frame.getGraphics();
		if(win == 0){
			g1.setStroke(new BasicStroke(8));
			g1.drawLine(0,104,500,104);
		}
		else if(win == 1){
			g1.setStroke(new BasicStroke(8));
			g1.drawLine(0,257,500,257);
		}
		else if(win == 2){
			g1.setStroke(new BasicStroke(8));
			g1.drawLine(0,411,500,411);	
		}
		else if(win == 3){
			g1.setStroke(new BasicStroke(8));
			g1.drawLine(88,0,88,500);	
		}
		else if(win == 4){
			g1.setStroke(new BasicStroke(8));
			g1.drawLine(250,0,250,500);
		}
		else if(win == 5){
			g1.setStroke(new BasicStroke(8));
			g1.drawLine(411,0,411,500);
		}
		else if(win == 6){
			g1.setStroke(new BasicStroke(8));
			g1.drawLine(-22,0,500,500);
			
		}
		else if(win == 7){
			g1.setStroke(new BasicStroke(8));
			g1.drawLine(520,0,0,500);
		}
	}
	
    // want to be able to access the private variables 
	//so we will make getter and setter methods for the ones that we need
	public char getUserTurn() { // getter method for userTurn
		return userTurn;
	}

	public void setUserTurn(char userTurn) { // setter method 
		this.userTurn = userTurn;
	}

	public char[] getTemplateOfBoard() { //getter method 
		return templateOfBoard;
	}

	public void setTemplateOfBoard(char[] templateOfBoard) { // setter method 
		this.templateOfBoard = templateOfBoard;
	}

}
