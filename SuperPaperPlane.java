
import java.awt.*;
import java.awt.BorderLayout;
import javax.swing.*;
public class SuperPaperPlane{

	public static void main(String[] args){
		JFrame fenetre = new JFrame();
		Niveau niveau = new Niveau();
		fenetre.setSize(515,800);
    fenetre.setLocation(0, 0);
    fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setVisible(true);
		fenetre.add(niveau);

		niveau.run();
	}
}
