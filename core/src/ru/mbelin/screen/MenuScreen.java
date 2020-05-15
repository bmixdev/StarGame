package ru.mbelin.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

import ru.mbelin.base.BaseScreen;
import ru.mbelin.math.Rect;
import ru.mbelin.sprite.ButtonExit;
import ru.mbelin.sprite.ButtonPlay;
import ru.mbelin.sprite.Logo;
import ru.mbelin.sprite.Star;
import ru.mbelin.sprite.model.MrDick;
import ru.mbelin.sprite.model.Unit;
import ru.mbelin.sprite.Background;
import ru.mbelin.utils.Utils;

public class MenuScreen extends BaseScreen {
    private static final int STAR_COUNT = 256;

    private final Game game;

    private BitmapFont font;

 //   int screenWidth, screenHeight;

    private Vector2 moveToVector;
    private List<Unit> unitList;

    private Texture bg;
    private Background background;

    private Texture lg;
    private Logo logo;

    private TextureAtlas atlas;
    private ButtonExit buttonExit;
    private ButtonPlay buttonPlay;

    private Star[] stars;

    public MenuScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        super.show();
        font = new BitmapFont();
        font.setColor(0.4f, 0.5f, 2f, 0.51f);
        moveToVector = new Vector2(0, 0);
        unitList = new ArrayList<>();
       // makeUnits();
        bg = new Texture("textures/background.png");
        background = new Background(bg);
        atlas = new TextureAtlas(Gdx.files.internal("textures/menu/menuAtlas.tpack"));
        lg = new Texture("textures/stargame.png");
        logo = new Logo(lg);


        stars = new Star[24];
        for (int i = 0; i < stars.length; i++) {
            stars[i] = new Star(atlas);
        }

        buttonExit = new ButtonExit(atlas);
        buttonPlay = new ButtonPlay(atlas, game);


    }

    private void makeUnits() {
        //    unitList.add(new MrDick("her.png", new Vector2(20, 20), 1f, 0, 128, 128));
            unitList.add(new MrDick(new TextureRegion(new Texture("textures/her.png")), new Vector2(20, 20), 1f, 0));
         //   unitList.add(new MrDick("her.png", new Vector2(400, 33), 3f , 0.001f, 48, 48));
       //     unitList.add(new MrDick("her.png", new Vector2(700, 400), 5f  , 1, 24, 24));
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
        super.dispose();
    }

    private void drawText() {
        CharSequence str = "Screen (Width: " +Gdx.graphics.getWidth()+"; Height: "+Gdx.graphics.getHeight()+";)";
        batch.begin();
        font.draw(batch, str, 10, 20);
        font.draw(batch, "MoveTo("+moveToVector.x+","+moveToVector.y+")", 10, 40);
        batch.end();
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
        buttonExit.draw(batch);
        buttonPlay.draw(batch);
        logo.draw(batch);
        /*
        for (Unit u:unitList) {
            u.moveToVector(); ((MrDick)u).draw(batch);
        }
         */
        batch.end();
     //   drawText();
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return super.mouseMoved(screenX, screenY);
    }

    private void touchProcess(int x, int y) {
        moveToVector.set(x, y);
        /*
        for (Unit u:unitList) {
            ((MrDick) u).setMoveToVector(moveToVector);
        }
        int size = Utils.getRandomNumberUsingNextInt(48, 128);
       unitList.add(new MrDick( new TextureRegion( new Texture("monsters/"+String.valueOf(Utils.getRandomNumberUsingNextInt(1, 40))+".png")), new Vector2(moveToVector), Utils.getRandomNumberUsingNextInt(1, 5) , 0, size, size));
      */
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touchProcess(screenX, (Gdx.graphics.getHeight() - screenY));
        return super.touchDown(screenX, screenY, pointer, button);
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


}
