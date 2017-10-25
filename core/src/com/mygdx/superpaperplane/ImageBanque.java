package com.mygdx.superpaperplane;
import java.io.File;

import com.badlogic.gdx.graphics.Texture;

public class ImageBanque {
	private static Texture[] tabimage;
	private static Texture[] tabimageflou;
	private static int value = 0;
	private static boolean flou;

	public static void InitialiserImageBanque() {
		flou = false;
		tabimage = new Texture[57];
		tabimageflou = new Texture[57];
		for (int a = 1; a < 52; a++) {
			if (a <= 18) {
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
    public static boolean isChanged() {
                if(value!=0) {
                    return true;
                }
                return false;
    }
		public static int getValue() {
			return value; // 0 pour normal , 1 pour 911 , 2 pour 21 pilots , 3 pour joint
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
