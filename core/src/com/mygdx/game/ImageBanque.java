package com.mygdx.game;
import java.io.File;

import com.badlogic.gdx.graphics.Texture;

public class ImageBanque {
	private static Texture[] tabimage;
	private static Texture[] tabimageflou;
	private static int value = 0;
	private static boolean flou;

	public static void InitialiserImageBanque() {
		flou = false;
		tabimage = new Texture[52];
		tabimageflou = new Texture[52];
		for (int a = 1; a < 47; a++) {
			if (a <= 15) {
				tabimage[a] = Niveau.loadBufferedImage("image/normal/image" + value + a + ".png");
				tabimageflou[a] = Niveau.loadBufferedImage(
						"image" + File.separator + "flou" + File.separator + "image" + value + a + ".png");
			} else {
				tabimage[a] = Niveau.loadBufferedImage("image/normal/image0" + a + ".png");
				tabimageflou[a] = Niveau
						.loadBufferedImage("image" + File.separator + "flou" + File.separator + "image0" + a + ".png");
			}
		}

	}


	public static void imageFlou() {
		flou = true;
	}

	public static void setValue(int n) {
		value = n;
	}

	public static Texture getCaseImage(int i) {
		if (flou) {
			return tabimageflou[i];
		} else {
			return tabimage[i];
		}
	}
}
