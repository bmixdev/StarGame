package ru.mbelin.pool;

import ru.mbelin.base.SpritesPool;
import ru.mbelin.sprite.Bullet;

public class BulletPool extends SpritesPool<Bullet> {
    @Override
    protected Bullet newObject() {
        return new Bullet();
    }
}
