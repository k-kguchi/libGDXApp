package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.LibGdxsample;
import com.mygdx.game.musicplay.Setting;

public class DifficultySelectDisplay extends MyScreenAdapter {
	private static final String LOG_TAG = DifficultySelectDisplay.class.getSimpleName();
    SpriteBatch batch;
    Texture img1;
    Texture img2;
    Texture img3;
    Texture img4;
    Texture img5;
    Texture img6;
    Texture img7;
    private OrthographicCamera cam;
	private Viewport viewport;

    BitmapFont font;
    ShapeRenderer renderer;

    public DifficultySelectDisplay(LibGdxsample game) {
        super(game);
        img1 = new Texture("res/hidari.png");
        img2 = new Texture("res/mae.png");
        img3 = new Texture("res/migi.png");
        img4 = new Texture("res/usiro.png");
        img5 = new Texture("res/easy10.png");
        img6 = new Texture("res/normal.png");
        img7 = new Texture("res/hard.png");
        batch = new SpriteBatch();
        font = new BitmapFont();
        renderer = new ShapeRenderer();
	}

    @Override
    public void resize(int width, int height) {
		viewport.update(width, height);
	}

    @Override
    public void show () {
    	Gdx.app.log(LOG_TAG, "show");
    	this.cam = new OrthographicCamera(Setting.LOGICAL_WIDTH, Setting.LOGICAL_HEIGHT);
		this.cam.position.set(Setting.LOGICAL_WIDTH/2, Setting.LOGICAL_HEIGHT/2, 0);
		viewport = new FitViewport(Setting.LOGICAL_WIDTH, Setting.LOGICAL_HEIGHT, cam);
    }

    @Override
    public void render (float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.cam.update();
    	batch.setProjectionMatrix(cam.combined);
        batch.begin();
        batch.draw(img1, 220, 300);
        batch.draw(img2, 320, 300);
        batch.draw(img3, 420, 300);
        batch.draw(img4, 520, 300);
        batch.draw(img5, 300, 210);
        batch.draw(img6, 300, 150);
        batch.draw(img7, 300, 90);
        font.draw(batch, "back", 35, 565);
        batch.end();

    	renderer.setProjectionMatrix(cam.combined);
        renderer.begin(ShapeRenderer.ShapeType.Line);

        renderer.rect(10, 540, 80,40);

        renderer.end();
        if (Gdx.input.justTouched()) {
        	float x = Gdx.input.getX();
		    float y = Gdx.input.getY();
		    Vector3 touchPoint = new Vector3();
		    viewport.unproject(touchPoint.set(x, y, 0));
		    if(10<touchPoint.x && touchPoint.x<80){
				if(465>touchPoint.y && 430<touchPoint.y){
					System.out.println("back");
					game.setScreen(new SongSelectDisplay(game));
				}
			}
			if(300<touchPoint.x && touchPoint.x<500){
				if(210>touchPoint.y && 165<touchPoint.y){
					game.setScreen(new Result(game));
				}
			}
			if(300<touchPoint.x && touchPoint.x<500){
				if(160>touchPoint.y && 115<touchPoint.y){
				}
			}
			if(300<touchPoint.x && touchPoint.x<500){
				if(110>touchPoint.y && 65<touchPoint.y){
				}
			}
		}
    }

    @Override
    public void hide() {
        Gdx.app.log(LOG_TAG, "hide");
        dispose();
    }

    @Override
    public void dispose() {
    	 Gdx.app.log(LOG_TAG, "hide");
    	 font.dispose();
    	 batch.dispose();
    	 renderer.dispose();
    }

}
