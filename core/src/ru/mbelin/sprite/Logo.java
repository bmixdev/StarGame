package ru.mbelin.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.mbelin.base.Sprite;
import ru.mbelin.math.Rect;

public class Logo extends Sprite {

    public Logo(Texture texture) {
        super(new TextureRegion(texture));
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.15f);
        //this.pos.set(worldBounds.pos);
        setBottom(worldBounds.getTop() - 0.17f);
    }

    @Override
    public void draw(SpriteBatch batch) {

        if (getBottom() < 0.1f)
            setBottom(getBottom() + 0.1f);
        else
            setBottom(getBottom() - 0.001f);

        super.draw(batch);
    }
}