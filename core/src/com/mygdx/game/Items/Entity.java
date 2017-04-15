package com.mygdx.game.Items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Adrian on 2017-04-15.
 */

public class Entity extends Sprite {
    public Sprite sprite;
    public Vector2 position;
    public float width;
    public float height;
    protected float alpha = 1f;
    protected Texture tex;

    public Entity(String name, float x, float y) {
        tex = new Texture(name + ".png");
        sprite = new Sprite(tex);
        position = new Vector2(x, y);
        width = tex.getWidth();
        height = tex.getHeight();
        sprite.setSize(width, height);
        sprite.setOriginCenter();
    }

    public void draw(SpriteBatch batch) {
        sprite.setPosition(position.x, position.y);
        sprite.draw(batch, alpha);
    }
}
