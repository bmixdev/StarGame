package ru.mbelin.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.mbelin.base.BaseButton;
import ru.mbelin.math.Rect;

public class ButtonExit extends BaseButton {

    public ButtonExit() {
        super(new TextureRegion(new Texture("buttonExit.png")));
    }

    @Override
    public void action() {
        Gdx.app.exit();
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.12f);
        setRight(worldBounds.getRight() - 0.05f);
        setBottom(worldBounds.getBottom() + 0.05f);
    }
}
