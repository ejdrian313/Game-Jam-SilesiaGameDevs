package com.mygdx.game.Items

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.mygdx.game.Entity.MovableEntity

/**
 * Created by Adrian on 2017-04-08.
 */

class Bullet(x: Float, y: Float, rotation: Float) : MovableEntity("bullet", x, y) {
    private var bulletAlpha = 0.3f
    var power: Int = 0

    init {
        SPEED = 800f
        entityWidth = tex.width.toFloat()
        entityHeight = tex.height.toFloat()
        sprite.setSize(entityWidth, entityHeight)
        sprite.setOriginCenter()
        sprite.rotation = rotation
    }

    fun update(deltaTime: Float, b: Bullet) {
        b.forward(deltaTime)
        bulletAlpha += deltaTime
    }

    override fun draw(batch: SpriteBatch) {
        sprite.setPosition(position.x, position.y)
        sprite.draw(batch, bulletAlpha)
    }

    override fun dispose() {
        tex.dispose()
    }
}
