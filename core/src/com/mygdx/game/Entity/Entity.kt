package com.mygdx.game.Entity

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2

/**
 * Created by Adrian on 2017-04-15.
 */

open class Entity(name: String, x: Float, y: Float) : Sprite() {
    var sprite: Sprite
    var position: Vector2
    var entityWidth: Float = 0.toFloat()
    var entityHeight: Float = 0.toFloat()
    val tex: Texture = Texture("$name.png")

    init {
        sprite = Sprite(tex)
        position = Vector2(x, y)
        sprite.setSize(entityWidth, entityHeight)
        sprite.setOriginCenter()
    }

    open fun draw(batch: SpriteBatch) {
        sprite.setPosition(position.x, position.y)
        sprite.draw(batch)
    }
}
