package util;

import java.util.HashMap;
import java.util.Map;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/**
 * A singleton class to manager showing scenes on a Stage.
 * The main class must give StageManager a reference to the Stage.
 * 
 * Other scenes can either register themselves or be created
 * by StageManager.
 * 
 * This class solves the problem of how to show different scenes
 * since a scene needs a reference to the Stage.
 */
public class StageManager {
	private static StageManager instance;
	private Stage stage;
	private Map<String,Scene> scenes;
	
	/* prevent object creation */
	private StageManager() { 
		scenes = new HashMap<>();
	}
	
	/**
	 * Get the singleton instance of StageManager
	 */
	public static StageManager getInstance() {
		if (instance == null) {
			synchronized (StageManager.class) {
				if (instance==null) instance = new StageManager();
			}
		}
		return instance;
	}
	
	/**
	 * Set the stage to use.
	 */
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	/**
	 * Register a scene by name.
	 * @param sceneName the name to use in the showScene(scenename) method
	 * @param scene a Scene graph
	 */
	public void register(String sceneName, Scene scene) {
		scenes.put(sceneName.toLowerCase(), scene);
	}
	
	public void showScene(String sceneName) {
		sceneName = sceneName.toLowerCase();
		Scene scene = scenes.get(sceneName);
		if (scene == null) {
			scene = makeScene(sceneName);
			scenes.put(sceneName, scene);
		}
		stage.setScene(scene);
		stage.sizeToScene();
		stage.show();
	}
		
	private Scene makeScene(String sceneName) {
		switch(sceneName) {
		case "guessinggame":
			Parent root = new guessinggame.GuessingGameApp().initComponents();
			return new Scene(root);
		case "tictactoe":
			Parent root2 = new tictactoe.TicTacToeApp().initComponents();
			return new Scene(root2);
		default:
			FlowPane root3 = new FlowPane();
			Label label = new Label("Unknown scenename "+sceneName);
			root3.getChildren().add(label);
			root3.setAlignment(Pos.CENTER);
			return new Scene(root3);
		}
	}

}
