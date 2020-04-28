package ru.mbelin.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class MrDick extends Unit {
    private Vector2 positionVector;
    private Vector2 velocityVector;
    private Vector2 gravityVector;
    private Vector2 distanceVector;
    private Vector2 directionVector;
    private float velocity;
    private float gravity;
    private int heigth = 64;
    private int width = 64;

    public MrDick(String internalPath, Vector2 position, float velocity, float gravity) {
        super(internalPath);
        this.positionVector = position;
        this.velocity = velocity;
        this.gravity = gravity;
        this.velocityVector = new Vector2();
        this.gravityVector = new Vector2(0, 1);
        this.distanceVector = new Vector2();
        this.directionVector = new Vector2();
    }

    public MrDick(String internalPath, Vector2 position, float velocity, float gravity, int heigth, int width) {
        super(internalPath);
        this.heigth = heigth;
        this.width = width;
        this.positionVector = position;
        this.velocity = velocity;
        this.gravity = gravity;
        this.velocityVector = new Vector2();
        this.gravityVector = new Vector2(0, 1);
        this.distanceVector = new Vector2();
        this.directionVector = new Vector2();
    }

    private void setCenter(Vector2 v){
        v.sub(this.width/2, this.heigth/ 2);
    }

    public void moveTo(Vector2 v) {
        // вычисляю дистанцию
        this.distanceVector.set(v).sub(this.positionVector);
        // центрирую
        setCenter(this.distanceVector);
        // если до дистанции меньше 1, то ставлю в конечную точку
        if (this.distanceVector.len() < 1) {
            this.positionVector.set(v);
            setCenter(this.positionVector);
        }
        else {
            // вычисляю направление
            this.directionVector.set(this.distanceVector).nor();
            // добавляю скорость
            this.velocityVector.set(this.directionVector).scl(velocity);
            // добавляю гравитацию
            this.gravityVector.set(0, this.directionVector.y).scl(gravity);
            //меняю позицию
            this.positionVector.add(this.velocityVector);
            this.positionVector.add(gravityVector);
        }
    }

    public void draw(SpriteBatch batch) {
        batch.draw(this, this.positionVector.x, this.positionVector.y, heigth, width);
    }

    @Override
    public String toString() {
        return "MrDick{" +
                "positionVector=" + positionVector +
                ", velocityVector=" + velocityVector +
                ", gravityVector=" + gravityVector +
                ", distanceVector=" + distanceVector +
                ", directionVector=" + directionVector +
                ", velocity=" + velocity +
                ", gravity=" + gravity +
                ", heigth=" + heigth +
                ", width=" + width +
                '}';
    }
}
