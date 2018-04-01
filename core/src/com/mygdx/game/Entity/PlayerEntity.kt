package com.mygdx.game.Entity

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion


/**
 * Created by Adrian on 2017-04-08.
 */

class PlayerEntity(image: String, x: Float, y: Float) : MovableEntity(image, x, y) {
    private val walkAnimation: Animation<TextureRegion>
    private val walkSheet: Texture
    private var stateTime: Float = 0.toFloat()


    init {
        walkSheet = Texture(Gdx.files.internal("playerwalk.png"))
        val tmp = TextureRegion.split(walkSheet, walkSheet.width / FRAME_COLS, walkSheet.height)
        val walkFrames = arrayOfNulls<TextureRegion>(FRAME_COLS)

        var index = 0
        for (i in 0 until FRAME_COLS) {
            walkFrames[index] = tmp[0][i]
            index++
        }
        walkAnimation = Animation<TextureRegion>(0.050f, *walkFrames)
        stateTime = 0f
    }

    fun draw(batch: SpriteBatch, delta: Float) {
        stateTime += delta
        sprite.setPosition(position.x, position.y)
        val currentFrame = walkAnimation.getKeyFrame(stateTime, true)
        batch.draw(currentFrame, position.x, position.y, sprite.originX + 20, sprite.originY - 20,
                (walkSheet.width / FRAME_COLS / 4).toFloat(), (walkSheet.height / 4).toFloat(), 1f, 1f, sprite.rotation + 90)
    }

    override fun dispose() {
        super.dispose()
    }

    fun handleInput(delta: Float) {
        val ROTATION_SPEED = 6f

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            sprite.rotation = sprite.rotation + ROTATION_SPEED
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            sprite.rotation = sprite.rotation - ROTATION_SPEED
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
            forward(delta)
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            backward(-delta)
        }
    }

    companion object {
        private val FRAME_COLS = 20
    }
}