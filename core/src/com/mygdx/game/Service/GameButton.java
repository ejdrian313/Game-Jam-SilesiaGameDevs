package com.mygdx.game.Service;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Entity.Entity;

/**
 * Created by Adrian on 2017-04-16.
 */

public class GameButton extends Entity {

    public GameButton(String name, float x, float y) {
        super(name, x, y);
    }

    public boolean isTouch(Vector2 click) {
        return Collision.overlaps(click, sprite);
    }
}
