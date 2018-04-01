package com.mygdx.game.Service

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2

import java.util.Random

/**
 * Created by Adrian on 2017-04-03.
 */

class Particle(color: String, posx: Float, posy: Float, rotation: Float) : Sprite() {
    var position: Vector2
    var finalPosition: Vector2
    var particleWidth: Float = 0.toFloat()
    var particleHeight: Float = 0.toFloat()
    var particleAlpha = .75f
    private val random: Int
    private val tex: Texture = Texture("$color.png")
    private val sprite: Sprite


    init {
        sprite = Sprite(tex)
        particleWidth = (tex.width / 15).toFloat()
        particleHeight = (tex.height / 15).toFloat()
        sprite.setSize(particleWidth, particleHeight)
        sprite.setOriginCenter()
        sprite.rotation = rotation
        random = generator.nextInt(4) + 1
        position = Vector2(posx, posy)
        finalPosition = Vector2((generator.nextInt(500) - 250).toFloat(), (generator.nextInt(500) - 250).toFloat())
    }

    fun draw(batch: SpriteBatch, alpha: Float) {
        sprite.setPosition(position.x, position.y)
        sprite.setAlpha(alpha)
        sprite.draw(batch)
    }

    fun dispose() {
        tex.dispose()
    }

    fun update(delta: Float) {
        val w = getMovementVector(sprite.rotation)
        w.add(finalPosition)

        position.x -= w.x * (delta / random)
        position.y -= w.y * (delta / random)
    }

    fun toRad(degrees: Float): Float {
        return degrees * MathUtils.PI / 180f
    }

    fun getMovementVector(rotation: Float): Vector2 {
        val v = Vector2()
        val rad = toRad(rotation)

        v.x = MathUtils.sin(rad)
        v.y = -MathUtils.cos(rad)

        return v
    }

    companion object {
        protected var generator = Random()
    }
}