package com.mygdx.game.Entity

import java.util.Random

/**
 * Created by Adrian on 2017-04-08.
 */

abstract class BasicEntity(name: String, x: Float, y: Float) : Entity(name, x, y) {
    protected var SPEED = 200f

    init {
        entityWidth = tex.width / 4f
        entityHeight = tex.height / 4f
        sprite.setSize(entityWidth, entityHeight)
        sprite.setOriginCenter()
    }

    open fun dispose() {
        tex.dispose()
    }

    companion object {
        var generator = Random()
    }
}
