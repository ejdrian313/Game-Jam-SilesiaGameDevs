package com.mygdx.game.Items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Adrian on 2017-04-08.
 */

public class Bullet extends Sprite {
    public float width;
    public float height;
    public Vector2 position;
    private Texture tex;
    private Sprite sprite;
    private final float SPEED = 800;
    private static int power;
    private float alpha = 0.3f;


    public Bullet(float x, float y, float rotation) {
        tex = new Texture("bullet.png");
        sprite = new Sprite(tex);
        position = new Vector2(x, y);
        width = tex.getWidth();
        height = tex.getHeight();
        sprite.setSize(width, height);
        sprite.setRotation(rotation);
        sprite.setOriginCenter();
    }

    public void draw(SpriteBatch batch) {
        sprite.setPosition(position.x, position.y);
        sprite.draw(batch, alpha);
    }

    public void update(float deltaTime, Bullet b) {
        b.forward(deltaTime);
        alpha += deltaTime;
    }

    private float toRad(float degrees)
    {
        return (degrees * MathUtils.PI) / 180.f;
    }

    public Vector2 getMovementVector(float rotation)
    {
        Vector2 v = new Vector2();
        float rad = toRad(rotation);

        v.x = MathUtils.sin(rad);
        v.y = -MathUtils.cos(rad);

        return v;
    }

    private void forward(float delta) {
        Vector2 w = getMovementVector(sprite.getRotation());

        position.x -= w.x * SPEED * delta;
        position.y -= w.y * SPEED * delta;
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
