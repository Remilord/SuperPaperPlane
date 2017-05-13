package com.mygdx.game;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.badlogic.gdx.graphics.Texture;

public class Record {
	private int record;
	private File fileo;
	private File filet;
	private Texture imagederang;

	public Record() {
		record = 0;
		fileo = new File("save.dat");
		filet = new File("saveinsane.dat");
		imagederang = Niveau.loadBufferedImage(
				"res" + File.separator + "image" + File.separator + "rang" + File.separator + "Bronze.png");
	}

	public int getRecord(int nn) {
		if (nn == 1) {
			try {
				FileInputStream fis = new FileInputStream(this.fileo);
				DataInputStream dis = new DataInputStream(fis);
				this.record = dis.readInt();
				fis.close();
			} catch (FileNotFoundException fnfe) {
				System.err.println("Erreur de lecture");
			} catch (IOException ieo) {
				System.err.println("Erreur de lecture IO");
			}
		}
		if (nn == 2) {
			try {
				FileInputStream fis = new FileInputStream(this.filet);
				DataInputStream dis = new DataInputStream(fis);
				this.record = dis.readInt();
				fis.close();
			} catch (FileNotFoundException fnfe) {
				System.err.println("Erreur de lecture");
			} catch (IOException ieo) {
				System.err.println("Erreur de lecture IO");
			}
		}
		return this.record;
	}

	public boolean setNewRecord(int n, int num) {
		if (n > this.getRecord(num)) {
			this.record = n;
			if (num == 1) {
				try {
					FileOutputStream fos = new FileOutputStream(this.fileo);
					DataOutputStream dos = new DataOutputStream(fos);
					try {
						dos.writeInt(this.record);
					} catch (IOException ioe) {
						System.err.println("Le record n'a pas été écrit");
					}
					fos.close();
				} catch (FileNotFoundException fnfe) {
					System.err.println("Pas de fichier trouvé");
				} catch (IOException ioe) {
					System.err.println("Probleme output");
				}
			} else if (num == 2) {
				try {
					FileOutputStream fos = new FileOutputStream(this.filet);
					DataOutputStream dos = new DataOutputStream(fos);
					try {
						dos.writeInt(this.record);
					} catch (IOException ioe) {
						System.err.println("Le record n'a pas été écrit");
					}
					fos.close();
				} catch (FileNotFoundException fnfe) {
					System.err.println("Pas de fichier trouvé");
				} catch (IOException ioe) {
					System.err.println("Probleme output");
				}
			}
			return true;
		}
		return false;
	}

	public Texture getRankImage() {
		return this.imagederang;
	}

	public String getRank(int i) {
		if (i == 1) {
			this.imagederang = Niveau.loadBufferedImage(
					"res" + File.separator + "image" + File.separator + "rang" + File.separator + "patate.png");
			return "Patate";
		} else if (i == 100) {
			this.imagederang = Niveau.loadBufferedImage(
					"res" + File.separator + "image" + File.separator + "rang" + File.separator + "bb.png");
			return "BigBoss";
		} else if (i == 101) {
			this.imagederang = Niveau.loadBufferedImage(
					"res" + File.separator + "image" + File.separator + "rang" + File.separator + "beat.png");
			return "BeatBox";
		} else if (i == 6) {
			this.imagederang = Niveau.loadBufferedImage(
					"res" + File.separator + "image" + File.separator + "rang" + File.separator + "cisla.png");
			return "Cisla";
		} else if (i == 10) {
			this.imagederang = Niveau.loadBufferedImage(
					"res" + File.separator + "image" + File.separator + "rang" + File.separator + "mario.png");
			return "NON";
		} else if (i == 40) {
			this.imagederang = Niveau.loadBufferedImage(
					"res" + File.separator + "image" + File.separator + "rang" + File.separator + "denis.png");
			return "DenisBrogniart";
		} else if (i == 33) {
			this.imagederang = Niveau.loadBufferedImage(
					"res" + File.separator + "image" + File.separator + "rang" + File.separator + "pacis.png");
			return "PacificSound";
		} else if (i == 55) {
			this.imagederang = Niveau.loadBufferedImage(
					"res" + File.separator + "image" + File.separator + "rang" + File.separator + "eddy.png");
			return "EddyMalou";
		} else if ((i > 1) && (i < 10)) {
			this.imagederang = Niveau.loadBufferedImage(
					"res" + File.separator + "image" + File.separator + "rang" + File.separator + "wat.png");
			return "Tariencompris";
		} else if ((i > 10) && (i < 25)) {
			this.imagederang = Niveau.loadBufferedImage(
					"res" + File.separator + "image" + File.separator + "rang" + File.separator + "bronze.png");
			return "Bronze";
		} else if ((i >= 25) && (i < 50)) {
			this.imagederang = Niveau.loadBufferedImage(
					"res" + File.separator + "image" + File.separator + "rang" + File.separator + "argent.png");
			return "Argent";
		} else if ((i >= 50) && (i < 75)) {
			this.imagederang = Niveau.loadBufferedImage(
					"res" + File.separator + "image" + File.separator + "rang" + File.separator + "or.png");
			return "Or";
		} else if ((i >= 75) && (i < 100)) {
			this.imagederang = Niveau.loadBufferedImage(
					"res" + File.separator + "image" + File.separator + "rang" + File.separator + "platine.png");
			return "Platine";
		}
		return "Pas de rang assigné";
	}
}
