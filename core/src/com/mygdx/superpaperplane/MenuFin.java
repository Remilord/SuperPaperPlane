package com.mygdx.superpaperplane;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;


public class MenuFin implements Screen {
    private Texture ecranfin;
    private Texture rangdefin;
    private Record rec;
    private boolean record;
    private String bssString;
    private Niveau game;
    private SpriteBatch sbatch;
    private OrthographicCamera camera;
    private BitmapFont bitmapFont;

    public MenuFin(Niveau g) {
                this.game=g;
        ecranfin = Niveau.loadBufferedImage("image/ecran/EcranDeFin.png");
        rec = new Record();
        rec.initRecord(this.game.getInsane());
        bssString = Integer.toString(this.rec.getRecord(this.game.getInsane()));
        if(rec.setNewRecord(this.game.getScore(),this.game.getInsane())) {
            ecranfin = Niveau.loadBufferedImage("image/ecran/EcranDeFinNouveauScore.png");
            record=true;
        }else {
            ecranfin = Niveau.loadBufferedImage("image/ecran/EcranDeFin.png");
            record=false;
        }
        this.rangdefin=rec.getRank(this.game.getScore());
        BitmapFont font = new BitmapFont();
        font.getData().setScale(3);
        bitmapFont = font;
        sbatch = new SpriteBatch();
        camera = new OrthographicCamera(500, 800);
        camera.position.set(500 / 2, 800 / 2, 0);
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        camera.update();
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.sbatch.setProjectionMatrix(camera.combined);
        this.sbatch.begin();
        if(Gdx.input.getY() >  Gdx.app.getGraphics().getHeight() / 2 && Gdx.input.getY() <  Gdx.app.getGraphics().getHeight()/1.6 && Gdx.input.justTouched()) {
            this.game.resetGame();
            this.game.setScreen(this.game);
            this.game.activeListener();
		} else if (Gdx.input.getY() > Gdx.app.getGraphics().getHeight() / 1.3
				&& Gdx.input.getY() < Gdx.app.getGraphics().getHeight() /1.1 && Gdx.input.justTouched() ) {
                    System.exit(0);
        }
        this.sbatch.draw(ecranfin,0,0);
        bitmapFont.setColor(Color.WHITE);
        String scoreString = Integer.toString(this.game.getScore());
        Matrix4 normalProjection = new Matrix4().setToOrtho2D(0, 0, Gdx.graphics.getWidth(),  Gdx.graphics.getHeight());
        this.sbatch.setProjectionMatrix(normalProjection);
        bitmapFont.draw(sbatch,scoreString, (float) (Gdx.app.getGraphics().getWidth()/1.4),Gdx.app.getGraphics().getHeight()-(Gdx.app.getGraphics().getHeight()/8));
        if(!record) {
            bitmapFont.draw(sbatch, bssString, (float) (Gdx.app.getGraphics().getWidth() / 2), (float) (Gdx.app.getGraphics().getHeight() - (Gdx.app.getGraphics().getHeight() / 2.7)));
        }
        this.sbatch.draw(rangdefin,(float)(Gdx.graphics.getWidth()/4.6),(float)(Gdx.app.getGraphics().getHeight() / 1.89));
        this.sbatch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
