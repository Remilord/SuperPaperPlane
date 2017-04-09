
import java.awt.*;
import java.awt.BorderLayout;
import javax.swing.*;
import java.io.File;
public class SuperPaperPlane{

	public static void main(String[] args){
		JFrame fenetre = new JFrame("Super Paper Plane");
		fenetre.setResizable(false);
		ImageIcon ii = new ImageIcon("res"+File.separator+"image"+File.separator+"avion"+File.separator+"PionPlane.png");
		fenetre.setIconImage(ii.getImage());
		//Menu md = new Menu();
		//SourisMenu sm = new SourisMenu(fenetre,md);
		Niveau niveauInsane = new Niveau(true, fenetre);
		fenetre.add(niveauInsane);
		fenetre.setSize(505,800);
    fenetre.setLocation(0, 0);
    fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  //  fenetre.addMouseListener(sm);
		fenetre.setVisible(true);
		niveauInsane.run();

	}
}
