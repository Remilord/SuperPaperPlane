import javax.swing.*;
import java.awt.*;
public class ThreadGame extends Thread {
	private JFrame fenetre;
	private int num;
			public ThreadGame(JFrame jf,int n) {
				super();
				this.fenetre=jf;
				this.num=n;
			}
			public void run() {
				this.fenetre.getContentPane().removeAll();
				this.fenetre.setFocusable(false);
				if(this.num==1) {
				Niveau niv = new Niveau();	
				this.fenetre.getContentPane().add(niv);
				this.fenetre.revalidate();
				niv.requestFocusInWindow();
				niv.run();
				}else if(this.num==2) {
					NiveauInsane nivinsane = new NiveauInsane();
					this.fenetre.getContentPane().add(nivinsane);
					this.fenetre.revalidate();
					nivinsane.requestFocusInWindow();
					nivinsane.run();
				}
				
			}
}
