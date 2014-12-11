import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;

import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


@SuppressWarnings("unused")
public class GUI implements Runnable {

	private final static int row = 3; // our rows 
	private final static int col = 3; // our col 
	private final Game choice; //instance of Game 
	private static JFrame frame; 
	private final static int sizeOfBoard = row * col; 
	// the size of our board is not going to change so we make it final
	private static JButton[] clickButton;


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public GUI() throws BoardErrorException { //constructor initializing values 

		GUI.frame = new JFrame("TicTacToe GAME");   // initializing the JFrame 
		this.choice = new Game(getFrame());  
		setClickButton(new JButton[9]); //9 different JButton 
	}

	public static void main(String[] args){

		try{
			SwingUtilities.invokeLater(new GUI()); // running our class 
		}
		catch(Exception e){ // wanted to test to ensure that Runnable could be invoked 
			System.out.println("Could not excute Runnable application");
			e.printStackTrace();
		}
	}

	public void run()  { // overriding the run 
		try {
			setup(); // going to run out setup method, what our game is made out of 
		} catch (BoardErrorException e) {
			e.printStackTrace();
		}
	} 

	public void setup() throws BoardErrorException {
		// setting up the Board 
		// board is composed of JButton 
		// and a 3x3 frame 


		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // when the user closes the window JFrame  will exit 

		//going to design the board now 
		//the dimensations of the board = sizeOfBoard

		getFrame().setLayout(new GridLayout(row, col)); // this is the outline rows * col 
		// sizes out row * col based on what we define those numbers as
		//i.e 3x3
		//glass.setLayout(new GridLayout(row, col));

		getFrame().setBounds(0,0,500,500); // location at 0,0, size 500 x 500 
		Border border = new LineBorder(Color.DARK_GRAY, 2); // color of JButton border 

		System.out.println("Your board game is being created!");

		try{
			getFrame().setVisible(true); // shows the board, 
			// this is going to display everything to the screen 
			System.out.println("Board is now visable");
		}
		catch(Exception e){
			System.out.println("Board was not displayed");
		}

		// 9 different buttons, for every index there will be a button 
		for(int i =0; i<getSizeofboard();i++){ // going to fill the board with clickableButtons by looping through every index and placing a button there 
			final int move = i; // want to create a variable equal to i, so we can use it our overridden method below
			//since it has to take final 

			getClickButton()[i] = new JButton(); // at a certain index there is a new button 
			getClickButton()[i].setSize(250,250); // size of each button 
			getClickButton()[i].setBackground(Color.WHITE); // color of the JButton 

			getFrame().add(getClickButton()[i]); // we are going to add the actual the button at that index on the frame 

			getClickButton()[i].setFont(new Font("Arial", Font.BOLD, 70)); // size of the text 
			getClickButton()[i].setBorder(border); // adding border 

			getClickButton()[i].getModel().addChangeListener(new ChangeListener() { //going to overRide what happens when we rollover and press a Butotn 

				public void stateChanged(ChangeEvent e) {
					ButtonModel button = (ButtonModel) e.getSource(); // manages the state of the button, i.e lets me control what happens to the button 

					if(getClickButton()[move] != null){ // if we do not include this argument 
						// the buttons are not made yet on the new game, meaning clickButton[i] = null
						//so boolean(!button.isRollover()) will return true, since on the new game you can not have your mouse hovered over 
						// but when it returns true, it will return a null value, giving a null pointer exception 
						// so best thing to do, is to only run these cases below when the buttons are not null

						if (button.isRollover()) { // when the mouse hovers over the index 
							getClickButton()[move].setBackground(Color.BLACK); // color will equal black 
						}
						else if(!button.isRollover()){ // when the button is not hovered over
							getClickButton()[move].setBackground(Color.WHITE); // color will be whte, just like our background 
						}

					}
				}
			});


			getClickButton()[i].addActionListener(new ActionListener() { // what happens during clicks 

				//our click events, going to override to let it know what we want to happen
				//once we click on the button
				public void actionPerformed(ActionEvent e) {
					try {


						getClickButton()[move].setEnabled(false); //going to disable the button after it is clicked 
						//ORDER: button gets clicked first, then the test is added

						choice.mouseListener(e, move); // our mouseListenerEvent in game class 

						//
					} catch (ButtonsNotMadeException
							| ButtonCanNotBeClickedException
							| WinnerErrorException e1) {

						e1.printStackTrace();
					}
				}
			});			
		}
	}

	public static void playAgain() {

		try{
			System.out.println("NEW GAME");
			SwingUtilities.invokeLater(new GUI());	 // run the run(class) again 
		}
		catch(Exception e){ // wanted to test to ensure that Runnable could be invoked 
			System.out.println("Could not excute Runnable application");
			e.printStackTrace();
		}
	}

	// --------------------------------------------------------

	// getter and setter 

	public static JFrame getFrame() {
		return frame;
	}

	public static int getSizeofboard() {
		return sizeOfBoard;
	}

	public static JButton[] getClickButton() {
		return clickButton;
	}

	public static void setClickButton(JButton[] clickButton) {
		GUI.clickButton = clickButton;
	}

}



