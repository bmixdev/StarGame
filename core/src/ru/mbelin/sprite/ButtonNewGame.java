package ru.mbelin.sprite;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.mbelin.base.BaseButton;
import ru.mbelin.math.Rect;
import ru.mbelin.screen.GameScreen;

public class ButtonNewGame extends BaseButton {

    private GameScreen gameScreen;

    private static final float MARGIN = 0.05f;

    public ButtonNewGame(TextureAtlas atlas, GameScreen gameScreen) {
        super(atlas.findRegion("button_new_game"));
        this.gameScreen  = gameScreen;
    }

    @Override
    public void action() {
      gameScreen.show();
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.12f);
        setBottom(worldBounds.getBottom() + MARGIN);
        setLeft(worldBounds.getLeft() + MARGIN);
        super.resize(worldBounds);
    }
}
