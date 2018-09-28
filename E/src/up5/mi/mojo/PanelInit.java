package up5.mi.mojo;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class PanelInit extends GridPane {

	public PanelInit(){
		TextField fichier = new TextField();
		
		Button ouvrir = new Button("Ouvrir");
		
		Button compte = new Button("Editer le r�pertoire");
		
		Button quitter = new Button("Quitter");
		
		BorderPane pane = new BorderPane();
		
		pane.setLeft(fichier);
		pane.setRight(ouvrir);
		this.add(pane,1,1);
		this.add(compte,1,2);
		this.add(quitter,1,3);
		
		FileChooser hf = new FileChooser();
		hf.getExtensionFilters().add(new ExtensionFilter("Serializable Files","*.ser"));
		
		ouvrir.setOnAction((event)->{
			File file = hf.showOpenDialog(null);
			
			fichier.setText(file.toString());
		});
		
		compte.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				try{
					File file = new File(fichier.getText());
					RepertoireSD r = RepertoireSD.charger(file);
					final Stage stage = new Stage();
					stage.setTitle("R�pertoire de "+r.getProprietaire());
					Scene scene = new Scene(new PanelRepertoire(r,file));
					stage.setScene(scene);
					stage.show();
				}
				catch(ClassNotFoundException exp) {exp.printStackTrace(); }
				catch(IOException exp) {exp.printStackTrace(); }
			}
		});
		
		quitter.setOnAction((event)->{
			System.exit(0);
		});
	}
}
