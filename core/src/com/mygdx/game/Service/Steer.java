package com.mygdx.game.Service;


import com.badlogic.gdx.math.Vector2;

/**
 * Created by Adrian on 2017-04-15.
 */

public interface Steer {
    float toRad(float degrees);

    Vector2 getMovementVector(float rotation);

    void forward(float delta);
}
