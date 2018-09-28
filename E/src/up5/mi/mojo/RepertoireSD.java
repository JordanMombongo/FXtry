package up5.mi.mojo;

import java.io.*;
import java.util.HashMap;

public class RepertoireSD extends RepertoireS implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 888148742911034246L;
	/**
	 * 
	 */
	
	private String proprietaire;
	private HashMap<String,String> reversed;
	
	public RepertoireSD(String nom){
		super();
		this.proprietaire = nom;
		this.reversed = new HashMap<String,String>();
	}
	
	public void setProprietaire(String nom){
		this.proprietaire = nom;
	}

	public String getProprietaire() {
		return proprietaire;
	}
	
	public static String getTelNorme(String tel){
		if(tel.length()==9) return "33"+tel;
		else if(tel.length()==10) return "33"+tel.substring(1);
		else return null;
	}
	
	public void ajouterEntree(String nom,String tel){
		super.ajouterEntree(nom, getTelNorme(tel));
		this.reversed.put(getTelNorme(tel), nom);
	}
	
	public String getNom(String tel){
		return this.reversed.get(getTelNorme(tel));
	}
	
	public void sauvegarder(File file)throws RepertoireException, IOException{
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(this);
		oos.close();
	}
	
	
	public static RepertoireSD charger(File file) throws ClassNotFoundException, IOException {
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream oi = new ObjectInputStream(fis);
		RepertoireSD res = (RepertoireSD)oi.readObject();
		oi.close();
		return res;
	}
	
	public static void main(String[]args) {
		RepertoireSD r = new RepertoireSD("Byakuya");
		r.ajouterEntree("Renji", "666666666");
		r.ajouterEntree("Kenpachi", "222222222");
		System.out.println(r.getTel("Kenpachi"));
		System.out.println(r.getNom("222222222"));
		File f = new File("rokubantai.ser");
		try {
			r.sauvegarder(f);
		}
		catch(IOException e) { e.printStackTrace(); }
		catch(RepertoireException e) { e.printStackTrace(); }
		try {
			RepertoireSD rep = charger(f);
			System.out.println(rep.equals(r));
			System.out.println(rep.getTel("Kenpachi"));
			System.out.println(rep.getNom("222222222"));
			System.out.println(rep.getProprietaire());
		}
		catch(IOException e) { e.printStackTrace(); }
		catch(ClassNotFoundException e) { e.printStackTrace(); }
	}
}
