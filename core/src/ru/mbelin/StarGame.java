package ru.mbelin;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class StarGame extends ApplicationAdapter {
	Texture img;
	float x, y;
	private SpriteBatch spriteBatch;
	private BitmapFont font;
	int screenWidth, screenHeight;

	@Override
	public void create () {
		img = new Texture("background.jpg");
		x = y = 0;
		spriteBatch = new SpriteBatch();
		font = new BitmapFont();
	}

	private void drawText() {

		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();

		CharSequence str = "Screen (Width: " +screenWidth+"; Height: "+screenHeight+";)";
		spriteBatch.begin();
		font.draw(spriteBatch, str, 10, 20);
		spriteBatch.end();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		spriteBatch.begin();
		spriteBatch.draw(img, 0 , 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		spriteBatch.end();

		drawText();
	}
	
	@Override
	public void dispose () {
		spriteBatch.dispose();
		img.dispose();
	}
}
