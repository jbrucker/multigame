import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.StageManager;

/**
 * Start JavaFX and choose a game to play.
 * 
 * @author jim
 */
public class Main extends javafx.application.Application {
	final String fxmlfile = "main.fxml";

	public void start(Stage stage) throws IOException {
		// StageManager keeps a reference to the stage and switches scenes 
		StageManager stageManager = StageManager.getInstance();
		stageManager.setStage(stage);
		
		Parent root = initComponents();
		Scene scene = new Scene(root);
		// save reference to this scene so we can come back to it later
		stageManager.register("main", scene);
		
		stage.setScene(scene);
		stage.show();
	}
	
	public Parent initComponents() throws IOException {
		URL fxmlurl = this.getClass().getResource(fxmlfile);
		if (fxmlurl == null) {
			System.err.println("Could not load main scene graph "+fxmlfile);
			return null;
		}
		// this is so we can get a reference to the controller
		FXMLLoader loader = new FXMLLoader(fxmlurl);
		Parent root = loader.load();
		//MainController controller = loader.getController();
		return root;
	}

	
	public static void main(String[] args) {
		launch(args);
	}
}
