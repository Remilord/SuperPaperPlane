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
			
}
