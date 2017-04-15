package com.mygdx.game.Entity;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Adrian on 2017-04-15.
 */

public abstract class MovableEntity extends BasicEntity {

    public MovableEntity(String name, float x, float y) {
        super(name, x, y);
    }

    protected void backward(float delta) {
        forward(delta);
    }

    protected float toRad(float degrees) {
        return (degrees * MathUtils.PI) / 180.f;
    }

    protected Vector2 getMovementVector(float rotation) {
        Vector2 v = new Vector2();
        float rad = toRad(rotation);

        v.x = MathUtils.sin(rad);
        v.y = -MathUtils.cos(rad);

        return v;
    }

    protected void forward(float delta) {
        Vector2 w = getMovementVector(sprite.getRotation());

        position.x -= w.x * SPEED * delta;
        position.y -= w.y * SPEED * delta;
    }
}
