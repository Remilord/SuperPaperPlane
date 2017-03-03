import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class Niveau extends JPanel{
  protected ArrayList<GameObject> objectsArray = new ArrayList<GameObject>();

  public void addNewObject(GameObject object){
  	this.objectsArray.add(object);
  }
  public Niveau(){
    super();
  }
    @Override
    public void paintComponent(Graphics g) {
      Graphics secondpinceau = g.create();

      for(int i = 0; i < 2; i++){
        secondpinceau.drawImage(objectsArray.get(i).getImage(),objectsArray.get(i).getPositionX(), objectsArray.get(i).getPositionY(), this);
        secondpinceau.drawImage(objectsArray.get(i).getImage(), objectsArray.get(i).getPositionX(), objectsArray.get(i).getPositionY(), this);
      }
    }
}
