package com.mygdx.game.Service

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.Entity.Entity

/**
 * Created by Adrian on 2017-04-16.
 */

class GameButton(name: String, x: Float, y: Float) : Entity(name, x, y) {

    fun isTouch(click: Vector2): Boolean {
        return Collision.overlaps(click, sprite)
    }
}
