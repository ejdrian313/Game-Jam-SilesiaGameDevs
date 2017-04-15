package com.mygdx.game.Items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Service.Steer;

/**
 * Created by Adrian on 2017-04-08.
 */

public class Bullet extends Entity implements Steer {
    private final float SPEED = 800;
    private int power;

    public Bullet(float x, float y, float rotation) {
        super("bullet", x, y);
        sprite.setRotation(rotation);
        alpha = 0.3f;
    }

    public void update(float deltaTime, Bullet b) {
        b.forward(deltaTime);
        alpha += deltaTime;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int weaponPower) {
        power = weaponPower;
    }

    public void dispose () {
        tex.dispose();
    }

    @Override
    public float toRad(float degrees) {
        return (degrees * MathUtils.PI) / 180.f;
    }

    @Override
    public Vector2 getMovementVector(float rotation) {
        Vector2 v = new Vector2();
        float rad = toRad(rotation);

        v.x = MathUtils.sin(rad);
        v.y = -MathUtils.cos(rad);

        return v;
    }

    @Override
    public void forward(float delta) {
        Vector2 w = getMovementVector(sprite.getRotation());

        position.x -= w.x * SPEED * delta;
        position.y -= w.y * SPEED * delta;
    }
}
