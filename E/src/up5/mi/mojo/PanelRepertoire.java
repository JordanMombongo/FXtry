package up5.mi.mojo;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.io.File;


public class PanelRepertoire extends BorderPane {
	private RepertoireSD repertoire;
	private File file;
	
	private TextField printer = new TextField();
	private TextField control = new TextField();
	private Button save = new Button("Sauvegarder");
	private Button nom = new Button("Obtenir nom");
	private Button num = new Button("Obtenir num�ro");
	private Button add = new Button("Nouveau contact");
	private boolean addin = false;
	private boolean addsteptwo = false;
	private String n;
	private String t;
	
	public PanelRepertoire(RepertoireSD repertoire,File file){
		this.repertoire = repertoire;
		this.file = file;
		
		this.printer.setEditable(false);
		
		this.setTop(printer);
		this.setLeft(control);
		
		GridPane grid = new GridPane();
		grid.add(save, 1, 2);
		grid.add(num,2,1);
		grid.add(nom, 1, 1);
		grid.add(add, 2, 2);
		
		this.setRight(grid);
		
		nom.setOnAction((event)->{
			printer.setText(repertoire.getNom(control.getText())+" : "+RepertoireSD.getTelNorme(control.getText()));
			control.clear();
		});
		num.setOnAction((event)->{
			printer.setText(control.getText()+" : "+repertoire.getTel(control.getText()));
			control.clear();
		});
		add.setOnAction((event)->{
			printer.setText("Entrer le nom :");
			addin = true;
		});
	
		control.setOnAction((event)->{
			if(addsteptwo){
				t = control.getText();
				control.clear();
				addsteptwo = false;
				repertoire.ajouterEntree(n, t);
				printer.setText("Nouveau contact ajout�");
			}
			if(addin){
				n = control.getText();
				control.clear();
				addsteptwo = true;
				addin = false;
				printer.setText("Entrer son num�ro :");
			}
		});
		
		save.setOnAction((event)->{
			try {
				repertoire.sauvegarder(file);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			printer.setText("R�pertoire mis � jour");
		});
	}
}
