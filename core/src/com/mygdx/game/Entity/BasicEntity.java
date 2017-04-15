package com.mygdx.game.Entity;

import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Service.Steer;

import java.util.Random;

/**
 * Created by Adrian on 2017-04-08.
 */

public abstract class BasicEntity extends Sprite implements Steer {

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

    protected void backward(float delta) {
        forward(delta);
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
