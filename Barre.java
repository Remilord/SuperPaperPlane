import java.awt.*;
import javax.swing.*;
import java.io.File;
public class Barre extends GameObject {
	private Image barre;

	public Barre(int x,int y) {
		super(x, y);
		barre = Toolkit.getDefaultToolkit().getImage(".."+File.separator+"image"+File.separator+"Barre.png");
		this.positionX = (int) Math.floor(Math.random() * 250);
		this.positionY = (int) Math.floor(Math.random() * 340)+220;
		this.largeur = getPositionX()+600;
		this.hauteur = getPositionY()+80;
	}
	@Override
	public void paintComponent(Graphics g) {
		Graphics secondpinceau=g.create();
		secondpinceau.drawImage(barre, this.positionX, this.positionY, null);
	}
	@Override
  public Image getImage() {
    return barre;
  }
}
