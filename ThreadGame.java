import javax.swing.*;
import java.awt.*;
public class ThreadGame extends Thread {
	private JFrame fenetre;
	private int num;
	private Menu fin;
			public ThreadGame(JFrame jf,int n,Menu mj) {
				super();
				this.fenetre=jf;
				this.num=n;
				this.fin=mj;
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
				this.fin.setScoreDeFin(niv.getScore());
				}else if(this.num==2) {
					NiveauInsane nivinsane = new NiveauInsane();
					this.fenetre.getContentPane().add(nivinsane);
					this.fenetre.revalidate();
					nivinsane.requestFocusInWindow();
					nivinsane.run();
					this.fin.setScoreDeFin(nivinsane.getScore());
				}
				this.fin.SetMenu(4);
				this.fin.setNumNiv(this.num);
				this.fenetre.getContentPane().removeAll();
				this.fenetre.getContentPane().add(this.fin);
				this.fenetre.revalidate();
				this.fenetre.repaint();
			}
}
