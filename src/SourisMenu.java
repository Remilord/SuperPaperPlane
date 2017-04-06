import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
public class SourisMenu implements MouseListener {
	private Menu mt;
	private JFrame fenetre;
	public SourisMenu(JFrame jfr) {
		this.mt = new Menu();
		this.fenetre=jfr;
		this.fenetre.getContentPane().add(this.mt);
	}
	@Override
	public void mouseClicked(MouseEvent	me) {
		if(((me.getY()>373)&&(me.getY()<490))&&(this.mt.getMenuNum()==1)) {
			this.mt.SetMenu(2);
			this.mt.repaint();
		}
		else if(((me.getY()>500)&&(me.getY()<630))&&(this.mt.getMenuNum()==1)) {
			this.mt.SetMenu(3);
			this.mt.repaint();
		}
		else if(((me.getY()>660)&&(me.getY()<750))&&(this.mt.getMenuNum()==1)) {
			this.fenetre.dispose();
		}
		else if(((me.getY()>265)&&(me.getY()<440))&&(this.mt.getMenuNum()==2)) {
			this.fenetre.removeMouseListener(this);
			ThreadGame tg = new ThreadGame(this.fenetre,1);
			tg.start();
		}
		else if(((me.getY()>550)&&(me.getY()<690))&&(this.mt.getMenuNum()==2)) {
			this.fenetre.removeMouseListener(this);
			ThreadGame tg = new ThreadGame(this.fenetre,2);
			tg.start();
	}
	}

	@Override
	public void mouseEntered(MouseEvent me) {
		//Inutile
	}

	@Override
	public void mouseExited(MouseEvent me) {
	//Inutile
	}

	@Override
	public void mousePressed(MouseEvent me) {
		//Inutile
	}

	@Override
	public void mouseReleased(MouseEvent me) {
		//Inutile
	}

}
