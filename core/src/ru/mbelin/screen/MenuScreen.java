package ru.mbelin.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

import ru.mbelin.base.BaseScreen;
import ru.mbelin.model.MrDick;
import ru.mbelin.model.Unit;
import ru.mbelin.utils.Utils;

public class MenuScreen extends BaseScreen {
    private static final int STAR_COUNT = 256;

    private final Game game;

    private Texture bg;
    private BitmapFont font;

    int screenWidth, screenHeight;

    private Vector2 moveToVector;
    private List<Unit> unitList;

    public MenuScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        super.show();
        bg = new Texture("textures/background.jpg");
        font = new BitmapFont();
        font.setColor(0.4f, 0.5f, 2f, 0.51f);
        moveToVector = new Vector2(0, 0);
        unitList = new ArrayList<>();
        makeUnits();
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
    }

    private void makeUnits() {
        //    unitList.add(new MrDick("her.png", new Vector2(20, 20), 1f, 0, 128, 128));
            unitList.add(new MrDick("her.png", new Vector2(20, 20), 1f, 0));
         //   unitList.add(new MrDick("her.png", new Vector2(400, 33), 3f , 0.001f, 48, 48));
       //     unitList.add(new MrDick("her.png", new Vector2(700, 400), 5f  , 1, 24, 24));
    }

    @Override
    public void render(float delta) {
        draw();
    }

    @Override
    public void dispose() {
        batch.dispose();
        bg.dispose();
        super.dispose();
    }

    private void drawText() {
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
        CharSequence str = "Screen (Width: " +screenWidth+"; Height: "+screenHeight+";)";
        batch.begin();
        font.draw(batch, str, 10, 20);
        font.draw(batch, "MoveTo("+moveToVector.x+","+moveToVector.y+")", 10, 40);
        batch.end();
    }

    private void draw() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(bg, 0 , 0, screenWidth, screenHeight);
        for (Unit u:unitList) {
            u.moveToVector(); u.draw(batch);
        }
        batch.end();
        drawText();
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return super.mouseMoved(screenX, screenY);
    }

    private void touchProcess(int x, int y) {
        moveToVector.set(x, y);
        for (Unit u:unitList) {
            ((MrDick) u).setMoveToVector(moveToVector);
        }
        int size = Utils.getRandomNumberUsingNextInt(48, 128);
        unitList.add(new MrDick("monsters/"+String.valueOf(Utils.getRandomNumberUsingNextInt(1, 40))+".png", new Vector2(moveToVector), Utils.getRandomNumberUsingNextInt(1, 5) , 0, size, size));
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touchProcess(screenX, (Gdx.graphics.getHeight() - screenY));
        return super.touchDown(screenX, screenY, pointer, button);
    }

}
