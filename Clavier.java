import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.*;
import java.awt.event.*;
public class Clavier extends KeyAdapter implements KeyListener{
  private Niveau niveau;
  public Clavier(Niveau n){
    super();
    this.niveau = n;
  }


  @Override
  public void keyTyped(KeyEvent e) {   }

  @Override
  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
      this.niveau.setEntreeUtilisateur(1);
    }
    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
      this.niveau.setEntreeUtilisateur(2);
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    this.niveau.setEntreeUtilisateur(0);
  }
}
