package guessinggame;

import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class for starting the application.
 * Initialize the application and display first scene of the UI.
 * Use this class to create and initialize any objects your app needs.
 * 
 * @author Jim
 */
public class GuessingGameApp extends Application {
	public static final String UI_FORM = "guessinggame/GameUI.fxml";

	/**
	 * Select the scene to display and start JavaFX.
	 * This is a call-back method invoked by JavaFX.  
	 * Your main method calls Application.launch() and JavaFX
	 * calls this method to show a "scene".
	 * 
	 * @param stage the primary "stage" for showing the scene.
	 */
	@Override
	public void start(Stage stage) throws IOException {
		Parent root = initComponents();
		Scene scene = new Scene(root);
		stage.setTitle("Guessing Game");
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * Create the scene for GuessingGame app.
	 */
	public Parent initComponents() {
		URL form = this.getClass().getClassLoader().getResource(UI_FORM);
		try {
			Parent root = FXMLLoader.load(form);
			return root;
		} catch (IOException e) {
			// TODO write a better catch block
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
