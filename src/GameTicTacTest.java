import static org.junit.Assert.*; // external jar, used for failed cases 
import javax.swing.JButton;
import javax.swing.JFrame;

import org.junit.Test;


public class GameTicTacTest {

	//testing class, going to test out constructor first 
	@Test
	public void test() throws BoardErrorException {


		JFrame frame = null;
		Game choice = new Game(frame); //object of the Game board 


		assertEquals('X', choice.getUserTurn()); // we can use this method for writing test 
		//only the failed test are recorded 
		// here we are testing the users turn , marked by the char " x " 

		assertEquals("         ", choice.toString()); // testing the board 
		//3x3 board 
		// what the board should look like, should be a blank board at this point 

	}

	@Test
	public void testUserMove() throws UserMoveErrorExcepion{

		Game choice = null;
		try {
			choice = new Game(null).userMove(1);
		} catch (BoardErrorException e) {
			e.printStackTrace();
		} // after the first move, i.e X just went 
		assertEquals('O', choice.getUserTurn()); // next Turn should not be a O, once the user X goes 
		assertEquals(" X       ", choice.toString()); // what the board looks like now after an X has been inserted and now it is 
		// O turn
		// note to self, every move must have a space after quote
		// look at the JNIT failure test to determine where the 'actual' is at 

	}


	@SuppressWarnings({ "unused", "static-access" })
	@Test
	public void testWinner() throws WinnerErrorException, BoardErrorException, ButtonsNotMadeException, ButtonCanNotBeClickedException{ // testing to ensure there can be a winner

		JFrame frame = new JFrame("TicTacToe GAME");
		Game choice = new Game(frame); //object of the Game board    
		GUI gui = new GUI();
		GUI.getFrame().setVisible(true);

		// assertEquals("XXX      ",choice.winner() ); // should return a winner
		//testing the boardtemplateOfBoard[0] = 'X';

		choice.getTemplateOfBoard()[0] = 'X';
		choice.getTemplateOfBoard()[1] = 'X';
		choice.getTemplateOfBoard()[2] = 'X';
		choice.getTemplateOfBoard()[3] = 'O';
		choice.getTemplateOfBoard()[4] = 'X';
		choice.getTemplateOfBoard()[5] = 'X';
		choice.getTemplateOfBoard()[6] = 'O';
		choice.getTemplateOfBoard()[7] = 'X';
		choice.getTemplateOfBoard()[8] = 'O';  
		
		for(int i =0; i<gui.sizeOfBoard;i++){ // going to fill the board with clickableButtons by looping through every index and placing a button there 
			final int move = i;

			gui.clickButton[i] = new JButton(); // at a certain index there is a new button 
		}
		
		 choice.winner(); //the ensure that winner works
	}
}




