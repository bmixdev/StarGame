package ru.mbelin.base;


import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.mbelin.math.Rect;
import ru.mbelin.math.Rnd;

public abstract class BaseButton extends Sprite {

    private static final float SCALE = 0.9f;

    private int pointer;
    private boolean pressed;

    private float animateTimer;
    private float animateInterval;


    public BaseButton(TextureRegion region) {
        super(region);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        if (pressed || !isMe(touch)) {
            return false;
        }
        this.pointer = pointer;
        pressed = true;
        scale = SCALE;
        return false;
    }


    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        if (this.pointer != pointer || !pressed) {
            return false;
        }
        if (isMe(touch)) {
            action();
        }
        pressed = false;
        scale = 1f;
        return false;
    }


    @Override
    public void resize(Rect worldBounds) {
        animateInterval = Rnd.nextFloat(0.5f, 4f);
    }


    @Override
    public void update(float delta) {
        setScale(getScale() + 0.0018f);
        animateTimer += delta;
        if (animateTimer >= animateInterval) {
            setScale(1f);
            animateTimer = 0f;
        }
    }

    public abstract void action();
}