import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
public class Menu extends JPanel {
	private int mumenu;
	private BufferedImage menu1;
	private BufferedImage menu2;
	private BufferedImage menu3;
		public Menu() {
			this.menu1=Niveau.loadBufferedImage("D:/Dossier perso/Programmation/Java/SuperPaperPlane/image/EcranDebutDeJeu.png");
			this.menu2=Niveau.loadBufferedImage("D:/Dossier perso/Programmation/Java/SuperPaperPlane/image/ChoixDuNiveau.png");
			this.menu3=Niveau.loadBufferedImage("D:/Dossier perso/Programmation/Java/SuperPaperPlane/image/EcranDeCode.png");
			this.mumenu=1;
		}
		public void SetMenu(int i) {
			this.mumenu=i;
		}
		public int getMenuNum() {
			return this.mumenu;
		}
		@Override
		public void paintComponent(Graphics g) {
			Graphics sec = g.create();
			if(this.mumenu==1) {
			sec.drawImage(menu1, 0, 0, this);
			} else if(this.mumenu==2) {
				sec.drawImage(menu2, 0,0,this);
			}else if(this.mumenu==3) {
				JTextArea ta = new JTextArea();
				ta.setBounds(50,380,400,100);
				sec.drawImage(menu3, 0, 0, this);
				this.add(ta);
			}
		}
}
