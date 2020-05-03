package guessinggame;

import javax.swing.JOptionPane;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import util.StageManager;

/**
 * The controller receives events and input from the UI
 * and updates the UI.
 * When an event occurs, the controller converts UI (field) data 
 * to a form that the model wants and calls methods of the model.
 * It displays info from the model in a way that the UI wants.
 * 
 * A controller has logic for the UI, but not application logic.
 * 
 * In a desktop app, the controller needs a way access some
 * UI components, such as reading text from an input field.
 * In JavaFX, a controller refers to JavaFX components by
 * defining attributes annotated with @FXML.  The names of the
 * attributes must be exactly the same as names of UI components
 * that you assign when you create the UI (use SceneBuilder).
 * 
 * @author Jim
 */
public class GameController {
	/** Reference to the game we are playing. */
	private GuessingGame game;
	@FXML
	Label topMessage;     // message displayed at top of window
	@FXML
	Label promptMessage;  // prompt before the input field
	@FXML
	Label statusMessage;  // for errors, results, or other messages. Shown at bottom.
	@FXML
	TextField inputField; // user input field
	@FXML
	Button submitButton;  // usually the submit button
	@FXML
	Button giveUpButton;
	@FXML
	Menu gameMenu;            // first menu in menubar
	MenuItem menuItem = null;
	
	public GameController( ) {
		// Its bad design for Controller to create Model objects, but
		// JavaFX doesn't give us an easy way to inject a model into the controller.	
		game = new GuessingGame();
	}
	
	/**
	 * JavaFX calls the initialize() method of your controller when
	 * it creates the UI form, after the components have been created
	 * and @FXML annotated attributes have been set.
	 * 
	 * This is a hook to initialize anything your controller or UI needs.
	 */
	@FXML
	public void initialize() {
		// print on console so you can see that JavaFX calls this method
		//System.out.println("GameController initializing");
		promptMessage.setText("Your guess?");
		submitButton.setText("Submit");
		giveUpButton.setText("Give Up");
		topMessage.setText("Guess the Secret Number");
		inputField.setText("");
		// display a hint from the model
		statusMessage.setText(game.getMessage());
		
		// add a menu item to return to Main scene
		if (menuItem == null) {
			menuItem = new MenuItem("Back to Main");
			menuItem.setOnAction( (e) -> StageManager.getInstance().showScene("main") );
			gameMenu.getItems().add(menuItem);
		}
	}
	
	/**
	 * Handle event triggered by button1 press.
	 * @param event a javafx.event.ActionEvent containing info about the event source
	 *     and type of event.
	 */
	public void button1Press(ActionEvent event) {
		// for testing
		statusMessage.setText("");
		
		// what to do depends on your game
		// this is for the guessing game
		String input = inputField.getText().trim();
		inputField.setText("");
		if (input.isEmpty()) {
			int upperBound = game.getUpperBound();
			statusMessage.setText(
				"Please enter a number between 1 and "+upperBound);
			return;
		}
		// give it to the game
		boolean ok = game.guess( input );
		topMessage.setText(game.getMessage());
		if (! ok ) {
			inputField.requestFocus();
			return;
		}
		
		// game is over. What to do now?
		inputField.setDisable(true);  // disable input
		
		int reply = JOptionPane.showConfirmDialog(null, 
				"Right! You guessed the secret number.\nPlay again?", 
				"Play A Guessing Game",
				JOptionPane.YES_NO_OPTION);
		if (reply != JOptionPane.YES_OPTION) Platform.exit();
		
		// create a new game and get hint
		int upperBound = 2 * game.getUpperBound();
		game = new GuessingGame(upperBound);
		inputField.setDisable(false);
		initialize();
	}
	
	/**
	 * Handle event triggered by button2 press.
	 * @param event a javafx.event.ActionEvent containing info about the event source
	 *     and type of event.
	 */
	public void button2Press(ActionEvent event) {
		String msg = String.format("You gave up after %d guesses.", game.getCount());
		statusMessage.setText(msg);
	}
}
