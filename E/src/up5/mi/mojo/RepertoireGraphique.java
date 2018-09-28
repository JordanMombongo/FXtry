package up5.mi.mojo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RepertoireGraphique extends Application {
	public void start(Stage stage){
		stage.setTitle("Répertoire");
		
		Scene scene = new Scene(new PanelInit(),200,100);
		
		stage.setScene(scene);
		stage.show();
	}
	public static void main(String[]args){
		launch(args);
	}
}
