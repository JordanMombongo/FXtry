package up5.mi.mojo;

import java.io.File;
import java.io.IOException;

public class Serialization {
	public static void main(String[]args) {
		RepertoireSD r = new RepertoireSD("Byakuya");
		r.ajouterEntree("Renji", "666666666");
		r.ajouterEntree("Kenpachi", "222222222");
		System.out.println(r.getTel("Kenpachi"));
		System.out.println(r.getNom("222222222"));
		File f = new File("rokubantai.ser");
		File g = new File("test.ser");
		try {
			r.sauvegarder(f);
		}
		catch(IOException e) { e.printStackTrace(); }
		catch(RepertoireException e) { e.printStackTrace(); }
		try {
			RepertoireSD rep = RepertoireSD.charger(f);
			System.out.println(rep.equals(r));
			System.out.println(rep.getTel("Kenpachi"));
			System.out.println(rep.getNom("222222222"));
			System.out.println(rep.getProprietaire());
		}
		catch(IOException e) { e.printStackTrace(); }
		catch(ClassNotFoundException e) { e.printStackTrace(); }
	}
}
