package ru.mbelin.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.mbelin.base.BaseScreen;
import ru.mbelin.math.Rect;
import ru.mbelin.sprite.Background;
import ru.mbelin.sprite.ButtonExit;
import ru.mbelin.sprite.ButtonPlay;
import ru.mbelin.sprite.Logo;
import ru.mbelin.sprite.Star;

public class MenuScreen extends BaseScreen {

    private final Game game;
    private Texture bg;
    private Background background;

    private Texture lg;
    private Logo logo;
    private Vector2 moveToVector;
    private BitmapFont font;

    private TextureAtlas atlas;
    private ButtonExit buttonExit;
    private ButtonPlay buttonPlay;
    private Star[] stars;

    private Music music;

    public MenuScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        super.show();
        font = new BitmapFont();
        font.setColor(0.4f, 0.5f, 2f, 0.51f);
        moveToVector = new Vector2(0, 0);

        bg = new Texture("textures/bg.png");
        background = new Background(bg);
        atlas = new TextureAtlas("textures/menu/menuAtlas.tpack");
        buttonExit = new ButtonExit(atlas);
        buttonPlay = new ButtonPlay(atlas, game);
        stars = new Star[256];
        for (int i = 0; i < stars.length; i++) {
            stars[i] = new Star(atlas);
        }
        lg = new Texture("textures/stargame.png");
        logo = new Logo(lg);

        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/music.mp3"));
        music.setLooping(true);
        music.play();
    }

    @Override
    public void resize(Rect worldBounds) {
        background.resize(worldBounds);
        logo.resize(worldBounds);
        buttonExit.resize(worldBounds);
        buttonPlay.resize(worldBounds);
        for (Star star : stars) {
            star.resize(worldBounds);
        }
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        draw();
    }

    @Override
    public void dispose() {
        lg.dispose();
        bg.dispose();
        atlas.dispose();
        music.dispose();
        super.dispose();
    }

    private void drawText() {
        CharSequence str = "Screen (Width: " + Gdx.graphics.getWidth()+"; Height: "+Gdx.graphics.getHeight()+";)";
        batch.begin();
        font.draw(batch, str, 10, 20);
        font.draw(batch, "MoveTo("+moveToVector.x+","+moveToVector.y+")", 10, 40);
        batch.end();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        buttonExit.touchDown(touch, pointer, button);
        buttonPlay.touchDown(touch, pointer, button);
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        buttonExit.touchUp(touch, pointer, button);
        buttonPlay.touchUp(touch, pointer, button);
        return false;
    }

    private void update(float delta) {
        for (Star star : stars) {
            star.update(delta);
        }
        buttonPlay.update(delta);
        buttonExit.update(delta);
    }

    private void draw() {
        batch.begin();
        background.draw(batch);
        for (Star star : stars) {
            star.draw(batch);
        }
        logo.draw(batch);
        buttonExit.draw(batch);
        buttonPlay.draw(batch);
        batch.end();
    }

}