
import java.awt.*;
import java.awt.BorderLayout;
import javax.swing.*;
public class SuperPaperPlane{

	public static void main(String[] args){
		JFrame fenetre = new JFrame();
		Niveau niveau = new Niveau();
		fenetre.setSize(505,820);
    fenetre.setLocation(0, 0);
    fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Avion avion = new Avion(50, 50);
		Barre barre = new Barre(250, 50);
		niveau.addNewObject(avion);
		niveau.addNewObject(barre);
		fenetre.add(niveau);
    fenetre.setVisible(true);
	}
}
