package com.mygdx.game.Entity

import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2

/**
 * Created by Adrian on 2017-04-15.
 */

abstract class MovableEntity(name: String, x: Float, y: Float) : BasicEntity(name, x, y) {

    protected fun backward(delta: Float) {
        forward(delta)
    }

    protected fun toRad(degrees: Float): Float {
        return degrees * MathUtils.PI / 180f
    }

    protected fun getMovementVector(rotation: Float): Vector2 {
        val v = Vector2()
        val rad = toRad(rotation)

        v.x = MathUtils.sin(rad)
        v.y = -MathUtils.cos(rad)

        return v
    }

    protected fun forward(delta: Float) {
        val w = getMovementVector(sprite.rotation)

        position.x -= w.x * SPEED * delta
        position.y -= w.y * SPEED * delta
    }
}
