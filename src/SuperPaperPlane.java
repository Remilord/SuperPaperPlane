
import java.awt.*;
import java.awt.BorderLayout;
import javax.swing.*;
public class SuperPaperPlane{

	public static void main(String[] args){
		JFrame fenetre = new JFrame("Super Paper Plane");
		SourisMenu sm = new SourisMenu(fenetre);
		fenetre.setSize(515,800);
    fenetre.setLocation(0, 0);
    fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    fenetre.addMouseListener(sm);
		fenetre.setVisible(true);
	}
}
