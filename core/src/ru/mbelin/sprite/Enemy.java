package ru.mbelin.sprite;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.time.temporal.ValueRange;

import ru.mbelin.base.Ship;
import ru.mbelin.math.Rect;
import ru.mbelin.pool.BulletPool;
import ru.mbelin.pool.ExplosionPool;

public class Enemy extends Ship {

    private static final float V_Y = -0.3f;
    private boolean visible;

    public Enemy(BulletPool bulletPool, ExplosionPool explosionPool, Rect worldBounds, Sound sound) {
        super(bulletPool, explosionPool, worldBounds, sound);
        visible = false;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if (getBottom() <= worldBounds.getBottom()) {
            destroy();
        }
        if (getTop() < worldBounds.getTop()) {
            v.set(v0);
            bulletPos.set(pos.x, pos.y - getHalfHeight());
            autoShoot(delta);
        }
        else if ( !visible) {
            visible = true;
            shoot();
            reloadTimer = 0f;
        }
    }

    public void set(
            TextureRegion[] regions,
            Vector2 v0,
            TextureRegion bulletRegion,
            float bulletHeight,
            float bulletVY,
            int damage,
            float reloadInterval,
            int hp,
            float height
    ) {
        this.visible = false;
        this.regions = regions;
        this.v0.set(v0);
        this.bulletRegion = bulletRegion;
        this.bulletHeight = bulletHeight;
        this.bulletV.set(0, bulletVY);
        this.damage = damage;
        this.reloadInterval = reloadInterval;
        this.reloadTimer = reloadInterval;
        this.hp = hp;
        setHeightProportion(height);
        this.v.set(0, V_Y);
    }
}