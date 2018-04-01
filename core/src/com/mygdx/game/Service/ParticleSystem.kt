package com.mygdx.game.Service

import com.badlogic.gdx.graphics.g2d.SpriteBatch

import java.util.LinkedList

/**
 * Created by Adrian on 2017-04-03.
 */

class ParticleSystem(howMany: Int, color: String, posX: Float, posY: Float, rotation: Float) {
    var allDead = false
    private val particles: LinkedList<Particle> = LinkedList()
    private val particlesToDestroy: LinkedList<Particle> = LinkedList()

    init {
        for (i in 0 until howMany) {
            particles.add(Particle(color, posX, posY, rotation))
        }
    }

    fun update(deltaTime: Float) {
        for (p in particles) {
            p.update(deltaTime)
        }
        destroy()
    }

    fun draw(sp: SpriteBatch, delta: Float) {
        for (p in particles) {
            p.particleAlpha -= delta
            if (p.particleAlpha <= 0) {
                p.particleAlpha = 0f
                particlesToDestroy.add(p)
            }
            p.draw(sp, p.particleAlpha)
        }
    }

    fun destroy() {
        for (i in particlesToDestroy.indices) {
            particles.remove(particlesToDestroy[i])
        }
        particlesToDestroy.clear()
        if (particles.size == 0) {
            allDead = true
        }
    }

    fun dispose() {
        for (p in particles) {
            p.dispose()
        }
    }
}