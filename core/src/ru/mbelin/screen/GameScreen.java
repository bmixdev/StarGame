package ru.mbelin.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.mbelin.base.BaseScreen;
import ru.mbelin.base.SpritesPool;
import ru.mbelin.math.Rect;

import ru.mbelin.math.Rnd;
import ru.mbelin.pool.BulletPool;
import ru.mbelin.sprite.Background;
import ru.mbelin.sprite.EnemyShip;
import ru.mbelin.sprite.MainShip;
import ru.mbelin.sprite.Star;
import ru.mbelin.utils.Utils;

public class GameScreen extends BaseScreen {

    private Texture bg;
    private Background background;
    private TextureAtlas atlas;
    private Star[] stars;
    private MainShip mainShip;
    private BulletPool bulletPool;
    private EnemyShip enemyShip;
    private SpritesPool<EnemyShip> enemyShipPool;
    private Rect _worldBounds;

    @Override
    public void show() {
        super.show();
        bg = new Texture("textures/background.png");
        background = new Background(bg);
        atlas = new TextureAtlas("textures/mainAtlas.tpack");
        stars = new Star[64];
        for (int i = 0; i < stars.length; i++) {
            stars[i] = new Star(atlas);
        }
        bulletPool = new BulletPool();
        mainShip = new MainShip(atlas, bulletPool);
        enemyShipPool = new SpritesPool<EnemyShip>() {
            @Override
            protected EnemyShip newObject() {
                return new EnemyShip(atlas);
            }
        };
        for (int i = 0 ; i < 2; i ++) {
            EnemyShip enemyShip = enemyShipPool.obtain();
        }

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        free();
        draw();
    }

    @Override
    public void resize(Rect worldBounds) {
        background.resize(worldBounds);
        for (Star star : stars) {
            star.resize(worldBounds);
        }
        mainShip.resize(worldBounds);
        enemyShipPool.resize(worldBounds);
        _worldBounds = worldBounds;

    }

    @Override
    public void dispose() {
        bg.dispose();
        atlas.dispose();
        bulletPool.dispose();
        enemyShipPool.dispose();
        super.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        mainShip.keyDown(keycode);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        mainShip.keyUp(keycode);
        return false;
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        mainShip.touchDown(touch, pointer, button);
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        mainShip.touchUp(touch, pointer, button);
        return false;
    }

    private void update(float delta) {
        for (Star star : stars) {
            star.update(delta);
        }
        bulletPool.updateActiveSprites(delta);
        if (enemyShipPool.getActiveObjects().size()!= 0) {
            enemyShipPool.updateActiveSprites(delta);
        }
        else {

            for (int i =0; i < Utils.getRandomInt(1, 10); i++) {
                EnemyShip enemyShip = enemyShipPool.obtain();

                enemyShip.set(new Vector2(0, 0), new Vector2(0, (-1) * Rnd.nextFloat(0.1f, 1f)), 0.01f, _worldBounds);
            }
        }
        mainShip.update(delta);
    }

    private void free() {
        bulletPool.freeAllDestroyed();
        enemyShipPool.freeAllDestroyed();
    }

    private void draw() {
        batch.begin();
        background.draw(batch);
        for (Star star : stars) {
            star.draw(batch);
        }
        bulletPool.drawActiveSprites(batch);
        mainShip.draw(batch);
        enemyShipPool.drawActiveSprites(batch);
        batch.end();
    }
}
