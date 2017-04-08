package com.mygdx.game.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Service.ParticleSystem;

import java.util.LinkedList;

/**
 * Created by Adrian on 2017-04-08.
 */

public class PlayerEntity extends BasicEntity {
    private final float ROTATAION_SPEED = 5;


    public PlayerEntity(String image,float x, float y) {
        super(image, x, y);
    }

    public void draw(SpriteBatch batch, float delta) {
        super.draw(batch);
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    public void handleInput(float delta) {
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }

        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            sprite.setRotation(sprite.getRotation() + ROTATAION_SPEED);
        } else if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            sprite.setRotation(sprite.getRotation() - ROTATAION_SPEED);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            forward(delta);
        } else if(Gdx.input.isKeyPressed(Input.Keys.S)) {
            backward(-delta);
        }
    }
}