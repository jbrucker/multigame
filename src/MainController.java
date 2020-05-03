import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import util.StageManager;

/**
 * Main controller changes the scene or quits.
 * 
 * @author jim
 *
 */
public class MainController {
	@FXML
	MenuBar menubar;
	@FXML
	Menu gameMenu;
	
	public MainController() {
		// nothing to do 
	}
	
	@FXML
	public void initialize() {
		
		// add games to the menu
		MenuItem game1 = new MenuItem("TicTacToe");
		game1.setOnAction( (e) -> showScene("tictactoe") );
		MenuItem game2 = new MenuItem("Guessing Game");
		game2.setOnAction( (e) -> showScene("guessinggame"));
		MenuItem exit = new MenuItem("Exit");
		// anything to clean up before exit?
		exit.setOnAction((e) -> System.exit(0));
		gameMenu.getItems().addAll(game1, game2, new SeparatorMenuItem(), exit);
	}
	
	/**
	 * Change the scene.
	 * Let StageManager do it, so we don't need a reference to the Stage.
	 */
	public void showScene(String sceneName) {
		StageManager.getInstance().showScene(sceneName);
	}
}
