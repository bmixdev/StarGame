package ru.mbelin.base;


import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.mbelin.math.Rect;
import ru.mbelin.math.Rnd;

public abstract class BaseButton extends Sprite {

    private int pointer;
    private boolean pressed;

    private float animateTimer;
    private float animateInterval;


    public BaseButton(TextureRegion region) {
        super(region);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        if (pressed || !isMe(touch)) {
            return false;
        }
        pressed = true;
        scale = 0.8f;
        this.pointer = pointer;
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        if (this.pointer != pointer || !pressed) {
            return false;
        }
        if (isMe(touch)) {
            action();
        }
        pressed = false;
        scale = 1;
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