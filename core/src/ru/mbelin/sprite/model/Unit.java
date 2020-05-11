package ru.mbelin.sprite.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.mbelin.base.Sprite;

public abstract class Unit extends Sprite {

    public Unit(TextureRegion textureRegion) {
        super(textureRegion);
    }

    public abstract void moveToVector();

}
