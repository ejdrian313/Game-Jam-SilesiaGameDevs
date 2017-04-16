package com.mygdx.game.Items;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Entity.MovableEntity;

/**
 * Created by Adrian on 2017-04-08.
 */

public class Bullet extends MovableEntity {
    private float alpha = 0.3f;
    private int power;

    public Bullet(float x, float y, float rotation) {
        super("bullet", x, y);
        SPEED = 800;
        width = tex.getWidth();
        height = tex.getHeight();
        sprite.setSize(width, height);
        sprite.setOriginCenter();
        sprite.setRotation(rotation);
    }

    public void update(float deltaTime, Bullet b) {
        b.forward(deltaTime);
        alpha += deltaTime;
    }

    @Override
    public void draw(SpriteBatch batch) {
        sprite.setPosition(position.x, position.y);
        sprite.draw(batch, alpha);
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
}
