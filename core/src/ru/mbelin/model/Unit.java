package ru.mbelin.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Unit extends Texture {

    public Unit(String internalPath) {
        super(internalPath);
    }

    public abstract void moveTo(Vector2 v);

    public abstract void draw(SpriteBatch batch);

}
