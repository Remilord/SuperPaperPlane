import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.*;
import java.awt.event.*;
public class ClavierInsane extends KeyAdapter implements KeyListener{
  private Niveau niveau;
  private boolean inversed;
  public ClavierInsane(Niveau n){
    super();
    this.niveau = n;
  }

public void setInversed(boolean on) {
		this.inversed=on;
}
  @Override
  public void keyTyped(KeyEvent e) {   }

  @Override
  public void keyPressed(KeyEvent e) {
	  if(inversed) {
    if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
      this.niveau.setEntreeUtilisateur(2);
    }
    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
      this.niveau.setEntreeUtilisateur(1);
    }
	  }else {
    	    if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
    	      this.niveau.setEntreeUtilisateur(1);
    	    }
    	    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
    	      this.niveau.setEntreeUtilisateur(2);
    	    }
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    this.niveau.setEntreeUtilisateur(0);
  }
}