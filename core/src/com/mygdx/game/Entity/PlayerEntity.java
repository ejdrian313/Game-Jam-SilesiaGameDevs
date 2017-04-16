package com.mygdx.game.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


/**
 * Created by Adrian on 2017-04-08.
 */

public class PlayerEntity extends MovableEntity {
    private static final int FRAME_COLS = 20;
    private Animation<TextureRegion> walkAnimation;
    private Texture walkSheet;
    private float stateTime;


    public PlayerEntity(String image,float x, float y) {
        super(image, x, y);
        walkSheet = new Texture(Gdx.files.internal("playerwalk.png"));
        TextureRegion[][] tmp = TextureRegion.split(walkSheet, (walkSheet.getWidth() / FRAME_COLS), walkSheet.getHeight());
        TextureRegion[] walkFrames = new TextureRegion[FRAME_COLS];

        int index = 0;
        for (int i = 0; i < FRAME_COLS; i++) {
            walkFrames[index] = tmp[0][i];
             index++;
        }
        walkAnimation = new Animation<>(0.050f, walkFrames);
        stateTime = 0f;
    }

     public void draw(SpriteBatch batch, float delta) {
        stateTime += delta;
        sprite.setPosition(position.x, position.y);
        TextureRegion currentFrame = walkAnimation.getKeyFrame(stateTime, true);
        batch.draw(currentFrame, position.x, position.y, sprite.getOriginX()+20, sprite.getOriginY()-20,
                (walkSheet.getWidth() / FRAME_COLS)/4, walkSheet.getHeight()/4, 1, 1, sprite.getRotation()+90);
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    public void handleInput(float delta) {
        float ROTATION_SPEED = 6;

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            sprite.setRotation(sprite.getRotation() + ROTATION_SPEED);
        } else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            sprite.setRotation(sprite.getRotation() - ROTATION_SPEED);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
            forward(delta);
        } else if(Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            backward(-delta);
        }
    }
}