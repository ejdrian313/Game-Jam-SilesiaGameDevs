package com.mygdx.game.Entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by Adrian on 2017-04-08.
 */

public class BasicEntity extends Sprite {

    public float width;
    public float height;
    public Texture tex;
    public Sprite sprite;
    public Vector2 position;
    protected float SPEED = 200;
    protected static Random generator = new Random();

    public BasicEntity(String name, float x, float y) {
        tex = new Texture(name + ".png");
        sprite = new Sprite(tex);
        position = new Vector2(x, y);
        width = tex.getWidth()/3;
        height = tex.getHeight()/3;
        sprite.setSize(width, height);
        sprite.setOriginCenter();
    }

    public BasicEntity(String name) {
        tex = new Texture(name + ".png");
        sprite = new Sprite(tex);
        position = new Vector2();
        width = tex.getWidth()/3;
        height = tex.getHeight()/3;
        sprite.setSize(width, height);
        sprite.setOriginCenter();
    }

    public void dispose () {
        tex.dispose();
    }

    /**
     *
     * Moving_metods
     */

    protected void forward(float delta) {
        Vector2 w = getMovementVector(sprite.getRotation());

        position.x -= w.x * SPEED * delta;
        position.y -= w.y * SPEED * delta;
    }

    protected void backward(float delta) {
        forward(delta);
    }

    protected float toRad(float degrees)
    {
        return (degrees * MathUtils.PI) / 180.f;
    }

    protected Vector2 getMovementVector(float rotation)
    {
        Vector2 v = new Vector2();
        float rad = toRad(rotation);

        v.x = MathUtils.sin(rad);
        v.y = -MathUtils.cos(rad);

        return v;
    }
}
