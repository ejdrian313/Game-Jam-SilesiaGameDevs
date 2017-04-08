package com.mygdx.game.Service;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by Adrian on 2017-04-03.
 */

public class Particle extends Sprite {
    public Vector2 position;
    public Vector2 finalPosition;
    public float width;
    public float height;
    public float alpha = .75f;
    private int random;
    private Texture tex;
    private Sprite sprite;
    protected static Random generator = new Random();


    public Particle(String color, float posx, float posy, float rotation) {
        tex = new Texture(color + ".png");
        sprite = new Sprite(tex);
        width = tex.getWidth()/15;
        height = tex.getHeight()/15;
        sprite.setSize(width, height);
        sprite.setOriginCenter();
        sprite.setRotation(rotation);
        random = generator.nextInt(4)+1;
        position = new Vector2(posx, posy);
        finalPosition = new Vector2(generator.nextInt(500) - 250, generator.nextInt(500) - 250);
    }

    public void draw(SpriteBatch batch, float alpha) {
        sprite.setPosition(position.x, position.y);
        sprite.setAlpha(alpha);
        sprite.draw(batch);
    }

    public void dispose() {
        tex.dispose();
    }

    protected void update(float delta) {
        Vector2 w = getMovementVector(sprite.getRotation());
        w.add(finalPosition);

        position.x -= w.x  * (delta / random);
        position.y -= w.y  * (delta / random);
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