package com.mygdx.game.Entity

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Vector2

/**
 * Created by Adrian on 2017-04-08.
 */

class EnemyEntity(name: String) : MovableEntity(name, 0f, 0f) {
    private val wall: Int = BasicEntity.generator.nextInt(4)
    private var timer: Float = 0.toFloat()
    var rotate: Float = 0.toFloat()
    var canMoving = true
    internal var walkAnimation: Animation<TextureRegion>
    internal var walkSheet: Texture
    internal var stateTime: Float = 0.toFloat()

    init {
        getRandomPosition()
        SPEED = (BasicEntity.generator.nextInt(60) + 40).toFloat()
        //pb = new ProgressBar(position.x, position.y);

        walkSheet = Texture(Gdx.files.internal("zombiewalk.png"))
        val tmp = TextureRegion.split(walkSheet, walkSheet.width / FRAME_COLS, walkSheet.height)
        val walkFrames = arrayOfNulls<TextureRegion>(FRAME_COLS)

        var index = 0
        for (i in 0 until FRAME_COLS) {
            walkFrames[index] = tmp[0][i]
            index++
        }
        walkAnimation = Animation<TextureRegion>(0.033f, *walkFrames)
        stateTime = 0f
    }

    fun draw(batch: SpriteBatch, delta: Float) {
        stateTime += delta
        if (canMoving) {
            val currentFrame = walkAnimation.getKeyFrame(stateTime, true)
            batch.draw(currentFrame, position.x, position.y, sprite.originX, sprite.originY,
                    (walkSheet.width / FRAME_COLS / 4).toFloat(), (walkSheet.height / 4).toFloat(), 1f, 1f, sprite.rotation + 90)
        } else {
            sprite.setPosition(position.x, position.y)
            sprite.draw(batch)
        }

    }

    fun getRandomPosition() {
        if (wall == 0) {
            position.x = 0f
            position.y = BasicEntity.generator.nextInt(800).toFloat()
            sprite.rotation = 270f
        } else if (wall == 1) {
            position.x = BasicEntity.generator.nextInt(1280).toFloat()
            position.y = 0f
        } else if (wall == 2) {
            position.x = 1280f
            position.y = BasicEntity.generator.nextInt(800).toFloat()
            sprite.rotation = 90f
        } else if (wall == 3) {
            position.x = BasicEntity.generator.nextInt(1280).toFloat()
            position.y = 800f
            sprite.rotation = 180f
        }
    }

    fun update(delta: Float) {
        timer += delta
        if (canMoving) {
            forward(delta)
            sprite.rotation = sprite.rotation + rotate * delta
        }

        if (timer > 2) {
            rotate = (BasicEntity.generator.nextInt(100) - 50).toFloat()
            timer = 0f
        }
    }

    override fun dispose() {
        tex.dispose()
    }

    class ProgressBar(x: Float, y: Float) : Sprite() {
        private val tex: Texture
        internal var progressbar: Sprite
        internal var positionOfBar: Vector2

        init {
            tex = Texture("healthbar.png")
            progressbar = Sprite(tex)
            positionOfBar = Vector2(x, y)
            progressbar.setSize(50f, 5f)
        }

        fun draw(batch: SpriteBatch) {
            progressbar.setPosition(positionOfBar.x, positionOfBar.y)
            progressbar.draw(batch)
        }

        fun reduceBar(howMuch: Int) {
            progressbar.setSize(progressbar.width - howMuch, progressbar.height)
        }

        fun dispose() {
            tex.dispose()
        }
    }

    companion object {
        private val FRAME_COLS = 17
    }
}
