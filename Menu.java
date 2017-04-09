import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.event.*;
public class Menu extends JPanel implements ActionListener {
	private int mumenu;

	private BufferedImage menu1;
	private BufferedImage menu2;
	private BufferedImage menu3;
	private BufferedImage menu4;
	private BufferedImage menu5;
	private Font fb;

	private int numero;
	private Record rc;
	private int sc;

	private JTextField ta;


	private String gsk;

		public Menu() {
			this.menu1=Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"fond"+File.separator+"EcranDebutDeJeu.png");
			this.menu2=Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"fond"+File.separator+"ChoixDuNiveau.png");
			this.menu3=Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"fond"+File.separator+"EcranDeCode.png");
			this.menu4=Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"fond"+File.separator+"EcranDeFin.png");
			this.menu5=Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"fond"+File.separator+"EcranDeFinNouveauScore.png");
			this.mumenu=1;
			this.sc=0;
			this.numero=0;
			this.rc=new Record();
			this.fb=new Font("TimesRoman", Font.BOLD, 20);
			this.ta = new JTextField();
			this.gsk=new String("");
		}
		public void SetMenu(int i) {
			this.mumenu=i;
		}
		public void setNumNiv(int nu) {
			this.numero=nu;
		}
		public int getMenuNum() {
			return this.mumenu;
		}
		public void setScoreDeFin(int scr) {
			this.sc=scr;
		}
		public void removeTextField() {
			this.remove(this.ta);
		}
		public String getSkinString() {
			return this.gsk;
		}
	  @Override
	  public void actionPerformed(ActionEvent ae) {
	        String strcmp = new String();
	        strcmp=ae.getActionCommand();
	            if(strcmp.equals("911")||(strcmp.equals("twentyonepilots")||(strcmp.equals("lestacoscbon")))) {
	              this.gsk=strcmp;
	            }
	  }
		@Override
		public void paintComponent(Graphics g) {
			Graphics sec = g.create();
			if(this.mumenu==1) {
			sec.drawImage(menu1, 0, 0, this);
			} else if(this.mumenu==2) {
				sec.drawImage(menu2, 0,0,this);
			}else if(this.mumenu==3) {
				this.ta.setBounds(50,420,400,50);
				sec.drawImage(menu3, 0, 0, this);
				this.ta.addActionListener(this);
				this.add(this.ta);
			}else if(this.mumenu==4) {
				sec.setColor(Color.WHITE);
				sec.setFont(this.fb);
				if(this.rc.setNewRecord(this.sc,this.numero)) {
					sec.drawImage(menu5,0,0,this);
				}else {
				sec.drawImage(menu4,0,0,this);
				sec.drawString(Integer.toString(this.rc.getRecord(this.numero)),240,290);
			}
			  sec.drawString(Integer.toString(this.sc),350,140);
				sec.drawString(this.rc.getRank(this.sc),135,360);
				sec.drawImage(this.rc.getRankImage(),260,310,this);
			}
		}
}
