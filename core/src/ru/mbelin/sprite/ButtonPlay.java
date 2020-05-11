package ru.mbelin.sprite;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.mbelin.base.BaseButton;
import ru.mbelin.math.Rect;
import ru.mbelin.screen.GameScreen;

public class ButtonPlay extends BaseButton {

    private Game game;

    public ButtonPlay(Game game) {
        super(new TextureRegion(new Texture("buttonPlay.jpg")));
        this.game  = game;
    }

    @Override
    public void action() {
        game.setScreen(new GameScreen(game));
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.1f);
        setRight(worldBounds.getLeft() + 0.2f);
        setBottom(worldBounds.getBottom() + 0.05f);
    }
}