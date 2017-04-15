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

public abstract class BasicEntity extends Entity {
    protected float SPEED = 200;
    protected static Random generator = new Random();

    public BasicEntity(String name, float x, float y) {
        super(name, x, y);
        width = tex.getWidth()/3;
        height = tex.getHeight()/3;
        sprite.setSize(width, height);
        sprite.setOriginCenter();
    }

    public void dispose () {
        tex.dispose();
    }
}
