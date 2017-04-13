package com.mygdx.game.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Adrian on 2017-04-08.
 */

public class EnemyEntity extends BasicEntity {
    private int wall;
    private float timer;
    public float rotate;
    public boolean canMoving = true;
    private static final int FRAME_COLS = 17;
    Animation<TextureRegion> walkAnimation;
    Texture walkSheet;
    float stateTime;

    public EnemyEntity(String name) {
        super(name);
        wall = generator.nextInt(4);
        getRandomPosition();
        SPEED = 100;
        //pb = new ProgressBar(position.x, position.y);

        walkSheet = new Texture(Gdx.files.internal("zombiewalk.png"));
        TextureRegion[][] tmp = TextureRegion.split(walkSheet, (walkSheet.getWidth() / FRAME_COLS), walkSheet.getHeight());
        TextureRegion[] walkFrames = new TextureRegion[FRAME_COLS];

        int index = 0;
        for (int i = 0; i < FRAME_COLS; i++) {
            walkFrames[index] = tmp[0][i];
            index++;
        }
        walkAnimation = new Animation<>(0.033f, walkFrames);
        stateTime = 0f;
    }

    public void draw(SpriteBatch batch, float delta) {
        stateTime += delta;
        if(canMoving) {
            TextureRegion currentFrame = walkAnimation.getKeyFrame(stateTime, true);
            batch.draw(currentFrame,position.x, position.y, sprite.getOriginX(), sprite.getOriginY(), (walkSheet.getWidth() / FRAME_COLS)/3, walkSheet.getHeight()/3, 1, 1, sprite.getRotation()+90);
        } else {
            sprite.setPosition(position.x, position.y);
            sprite.draw(batch);
        }

    }

    public void getRandomPosition() {
        if(wall == 0) {
            position.x = 0;
            position.y = generator.nextInt(1000);
            sprite.setRotation(270);
        } else if (wall == 1) {
            position.x = generator.nextInt(1150);
            position.y = 0;
        } else if (wall == 2) {
            position.x = 1150;
            position.y = generator.nextInt(900);
            sprite.setRotation(90);
        } else  if (wall == 3) {
            position.x = generator.nextInt(1100);
            position.y = 1000;
            sprite.setRotation(180);
        }
    }

    public void update(float delta) {
        timer += delta;
        if(canMoving) {
            forward(delta);
            sprite.setRotation(sprite.getRotation() + rotate * delta);
        }

        if(timer > 2){
            rotate = generator.nextInt(100)-50;
            timer = 0;
        }
    }

    @Override
    public void dispose () {
        tex.dispose();
    }

    public static class ProgressBar extends Sprite {
        private Texture tex;
        Sprite progressbar;
        Vector2 positionOfBar;

        public ProgressBar(float x, float y){
            tex = new Texture("healthbar.png");
            progressbar = new Sprite(tex);
            positionOfBar = new Vector2(x, y);
            progressbar.setSize(50, 5);
        }

        public void draw(SpriteBatch batch) {
            progressbar.setPosition(positionOfBar.x, positionOfBar.y);
            progressbar.draw(batch);
        }

        public void reduceBar(int howMuch) {
            progressbar.setSize(progressbar.getWidth() - howMuch, progressbar.getHeight());
        }

        public void dispose() {
            tex.dispose();
        }
    }
}
