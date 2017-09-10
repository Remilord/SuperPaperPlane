package com.mygdx.superpaperplane;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;

public class Record {
	private int recordnormal;
    private int recordinsane;
	private Texture imagederang;
    private Preferences preferences;

	public Record() {
		recordnormal = 0;
        recordinsane = 0;
        preferences=Gdx.app.getPreferences("Sauvegardes");
	}

    public void initRecord(boolean insane) {
        if (!insane) {
            this.recordnormal = this.preferences.getInteger("recordnormal",0);
        }if (insane) {
            this.recordinsane = this.preferences.getInteger("recordinsane",0);
            }
        }
    public boolean setNewRecord(int n ,boolean insane) {
        if (n > this.recordnormal && !insane) {
            this.recordnormal = n;
            this.preferences.putInteger("recordnormal",this.recordnormal);
			preferences.flush();
            return true;
        }else  if (n > this.recordinsane && insane) {
			this.recordinsane = n;
            this.preferences.putInteger("recordinsane",this.recordinsane);
			preferences.flush();
            return true;
        }
        return false;
    }
        public int getRecord(boolean insane) {
            if(insane) {
                return recordinsane;
            }else {
                return recordnormal;
            }
        }

	public Texture getRank(int i) {
        int num;
        if(i == 0) {
            num=0;
        }else {
            num = (int) Math.floor((double) i / 10.0) * 10;
            if (num == 0) {
                num = 1;
            }else if(num>=110) {
                num=100;
            }
        }
        this.imagederang = Niveau.loadBufferedImage("image/rang/"+num+".png");
        return this.imagederang;
	}

}
