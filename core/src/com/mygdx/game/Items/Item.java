package com.mygdx.game.Items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Adrian on 2017-04-09.
 */

public class Item extends Sprite {
    private Texture tex;
    public Sprite sprite;
    public Vector2 position;
    public float width;
    public float height;

    public Item(String name, float x, float y) {
        tex = new Texture(name + ".png");
        sprite = new Sprite(tex);
        position = new Vector2(x, y);
        width = tex.getWidth()/2;
        height = tex.getHeight()/2;
        sprite.setSize(width, height);
        sprite.setOriginCenter();
    }

    public void draw(SpriteBatch batch) {
        sprite.setPosition(position.x, position.y);
        sprite.draw(batch);
    }
}
