package com.mygdx.game.Items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Adrian on 2017-04-09.
 */

public class Item extends Entity {

    public Item(String name, float x, float y) {
        super(name, x, y);
        width = tex.getWidth()/2;
        height = tex.getHeight()/2;
        sprite.setSize(width, height);
        sprite.setOriginCenter();
    }
}
