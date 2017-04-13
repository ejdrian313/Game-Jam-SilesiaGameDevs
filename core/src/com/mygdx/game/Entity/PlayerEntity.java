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

public class PlayerEntity extends BasicEntity {
    private final float ROTATAION_SPEED = 5;
    private static final int FRAME_COLS = 20;
    Animation<TextureRegion> walkAnimation;
    Texture walkSheet;
    float stateTime;


    public PlayerEntity(String image,float x, float y) {
        super(image, x, y);
        sprite.setOriginCenter();
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
        TextureRegion currentFrame = walkAnimation.getKeyFrame(stateTime, true);
        batch.draw(currentFrame, position.x, position.y, sprite.getWidth()/2, sprite.getHeight()/2, (walkSheet.getWidth() / FRAME_COLS)/3, walkSheet.getHeight()/3, 1, 1, sprite.getRotation()+90);
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    public void handleInput(float delta) {
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            sprite.setRotation(sprite.getRotation() + ROTATAION_SPEED);
        } else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            sprite.setRotation(sprite.getRotation() - ROTATAION_SPEED);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            forward(delta);
        } else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            backward(-delta);
        }
    }
}