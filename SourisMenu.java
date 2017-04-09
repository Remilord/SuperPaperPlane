import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
public class SourisMenu implements MouseListener {
	private Menu mt;
	private int activniv;
	private JFrame fenetre;
	private int num;
	public SourisMenu(JFrame jfr,Menu me) {
		this.mt = me;
		this.fenetre=jfr;
		this.activniv=0;
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
		else if(((me.getY()>535)&&(me.getY()<585))&&((me.getX()>175)&&(me.getX()<325))&&(this.mt.getMenuNum()==3)) {
			this.mt.removeTextField();
			this.mt.SetMenu(1);
			this.mt.repaint();
		}
		else if(((me.getY()>265)&&(me.getY()<440))&&(this.mt.getMenuNum()==2)) {
			this.mt.SetMenu(0);
			run(1, this.mt);
			this.activniv=1;
		}
		else if(((me.getY()>550)&&(me.getY()<690))&&(this.mt.getMenuNum()==2)) {
			this.mt.SetMenu(0);
			run(2, this.mt);
			this.activniv=2;
		}else if(((me.getY()>435)&&(me.getY()<555))&&(this.mt.getMenuNum()==4)) {
			this.mt.SetMenu(0);
			run(4, this.mt);
		}
		else if(((me.getY()>635)&&(me.getY()<755))&&(this.mt.getMenuNum()==4)) {
			this.fenetre.dispose();
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

	public void run(int num, Menu fin) {

		fenetre.removeAll();
		fenetre.setFocusable(false);
		if(num==1) {
			NiveauInsane niv = new NiveauInsane(false, fenetre);
			fenetre.getContentPane().add(niv);
			fenetre.revalidate();
			niv.requestFocusInWindow();
			niv.run();
			fin.setScoreDeFin(niv.getScore());
		}else if(num==2) {
			NiveauInsane nivinsane = new NiveauInsane(true, fenetre);
			fenetre.setContentPane(nivinsane);

			nivinsane.requestFocusInWindow();
			nivinsane.revalidate();
			fenetre.revalidate();
			nivinsane.run();

			fin.setScoreDeFin(nivinsane.getScore());


		}
			fin.SetMenu(4);
			fin.setNumNiv(this.num);
			fenetre.getContentPane().removeAll();
			fenetre.setContentPane(fin);
			fenetre.revalidate();
			fenetre.repaint();
	}

}
