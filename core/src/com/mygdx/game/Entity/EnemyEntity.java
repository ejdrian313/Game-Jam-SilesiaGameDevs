package com.mygdx.game.Entity;

import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.Random;

/**
 * Created by Adrian on 2017-04-08.
 */

public class EnemyEntity extends BasicEntity {
    private int wall;
    private float timer;

    public EnemyEntity(String name, float x, float y) {
        super(name, x, y);
        wall = generator.nextInt(4);
        getRandomPosition();
        SPEED = 100;
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
        forward(delta);
        if(timer > 2){
            sprite.setRotation(sprite.getRotation() + generator.nextInt(80)-40);
            timer = 0;
        }
    }
}
