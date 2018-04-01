package com.mygdx.game.Items

import com.mygdx.game.Entity.Entity

/**
 * Created by Adrian on 2017-04-09.
 */

class Item(name: String, x: Float, y: Float) : Entity(name, x, y) {

    init {
        entityWidth = (tex.width / 2).toFloat()
        entityHeight = (tex.height / 2).toFloat()
        sprite.setSize(entityWidth, entityHeight)
        sprite.setOriginCenter()
    }
}
