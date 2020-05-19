package ru.mbelin.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.mbelin.base.Sprite;
import ru.mbelin.math.Rect;
import ru.mbelin.math.Rnd;
import ru.mbelin.pool.BulletPool;

public class EnemyShip extends Sprite {

    private static final float SIZE = 0.15f;
    private static final float MARGIN = 0.05f;
    private static final int INVALID_POINTER = -1;

    private  Vector2 velocity;

    private Rect worldBounds;

    private float moveTimer;
    private float moverTimerInterval =  Rnd.nextFloat(0.1f, 1f);

    public EnemyShip(TextureAtlas atlas) {
        super(atlas.findRegion("enemy0"), 1, 2, 2);
        velocity = new Vector2(0f, (-1f) * moverTimerInterval);
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        setHeightProportion(SIZE);
        setTop(worldBounds.getTop() + MARGIN);
        pos.x = Rnd.nextFloat(worldBounds.getLeft(), worldBounds.getRight());
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(velocity, delta);
        if (isOutside(worldBounds)) {
            destroy();
        }
    }

    private void stop() {
        pos.set(new Vector2(pos.x, worldBounds.getBottom() + this.getTop()));
    }


    public void set(
            Vector2 pos0,
            Vector2 v0,
            float height,
            Rect worldBounds
    ) {
        this.pos.set(pos0);
        this.velocity.set(v0);
        setHeightProportion(height);
        this.worldBounds = worldBounds;
        resize(worldBounds);
    }

}
