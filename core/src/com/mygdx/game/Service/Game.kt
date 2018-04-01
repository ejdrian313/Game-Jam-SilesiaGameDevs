package com.mygdx.game.Service

import com.badlogic.gdx.Application
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.mygdx.game.Entity.EnemyEntity
import com.mygdx.game.Entity.PlayerEntity
import com.mygdx.game.Items.Bullet
import com.mygdx.game.Items.Item
import com.mygdx.game.MyGdxGame

import java.util.LinkedList

/**
 * Created by Adrian on 2017-04-14.
 */

class Game {
    private val batch: SpriteBatch
    private var ammo = 500
    private var counter = 0
    private var delta: Float = 0.toFloat()
    private var timer: Float = 0.toFloat()
    private var timeToReload = 0f
    private var timeToEat = 15f
    private var timeOfGame = 0f
    private val lose = false
    private var hudText: String? = null
    private val music: Music
    private val eat: Music
    private val dead2: Music
    private val playerHit: Music
    private val font: BitmapFont
    private val img: Texture
    private val text: Texture
    private val startImage: Texture
    private val player: PlayerEntity
//    private val items: LinkedList<Item>
    private val itemsToRemove: LinkedList<Item>
    private val bullets: LinkedList<Bullet>
    private val bulletsToRemove: LinkedList<Bullet>
    private val enemyEntities: LinkedList<EnemyEntity>
    private val enemyEntitiesToDestroy: LinkedList<EnemyEntity>
    private val particleSystems: LinkedList<ParticleSystem>
    private val particleSystemsToRemove: LinkedList<ParticleSystem>

    init {
        batch = SpriteBatch()
        img = Texture("ground.jpg")
        player = PlayerEntity("player", 400f, 400f)
        startImage = Texture("startScreen.png")
        particleSystems = LinkedList()
        particleSystemsToRemove = LinkedList()
        enemyEntities = LinkedList()
        enemyEntitiesToDestroy = LinkedList()
        bullets = LinkedList()
        bulletsToRemove = LinkedList()
        //items = LinkedList()
        itemsToRemove = LinkedList()
        //items.add(Item("precious", 600f, 350f))
        text = Texture("lose.png")
        font = BitmapFont()
        font.color = Color.BLACK
        music = Gdx.audio.newMusic(Gdx.files.internal("shoot.wav"))
        eat = Gdx.audio.newMusic(Gdx.files.internal("eating.mp3"))
        dead2 = Gdx.audio.newMusic(Gdx.files.internal("dead2.wav"))
        playerHit = Gdx.audio.newMusic(Gdx.files.internal("playerHit.wav"))
        music.volume = 0.2f
        eat.volume = 0.2f
        dead2.volume = 0.2f
        playerHit.volume = 0.2f
    }

    fun update(appType: Application.ApplicationType) {
        if (appType == Application.ApplicationType.Android) {
            handleInputOnAndroid(delta)
        } else {
            handleInput(delta)
        }
        updateGame(delta)
        updateTime()
    }

    private fun updateTime() {
        delta = Gdx.graphics.deltaTime
        timeToReload += delta
        timeOfGame += delta
    }

    fun draw() {
        batch.begin()
        drawAll(batch)
        batch.end()
    }

    fun dispose() {
        batch.dispose()
        for (s in particleSystems) {
            s.dispose()
        }
        for (b in bullets) {
            b.dispose()
        }
        for (e in enemyEntities) {
            e.dispose()
        }
        player.dispose()
        img.dispose()
        font.dispose()
    }

    private fun updateGame(delta: Float) {
        for (s in particleSystems) {
            s.update(delta)
        }
        for (b in bullets) {
            b.update(delta, b)
        }
        for (e in enemyEntities) {
            e.update(delta)
        }

        checkCollision()
        destroy()
        addEnemy(delta)
        hudText = "Ammo: " + ammo + "\nYou killed: " + counter + "\nTime of game: " + timeOfGame.toInt()
    }

    private fun handleInput(d: Float) {
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            MyGdxGame.setGameState(com.mygdx.game.Enums.State.PAUSE)
        }
        player.handleInput(d)
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && timeToReload > .1f && ammo > 0) {
            val b1 = Bullet(player.position.x + player.entityWidth / 2,
                    player.position.y + player.entityHeight / 2, player.sprite.rotation)
            bullets.add(b1)
            timeToReload = 0f
            --ammo
            if (music.isPlaying) {
                music.stop()
            }
            music.play()
        }
    }

    private fun handleInputOnAndroid(delta: Float) {

    }

    private fun addEnemy(delta: Float) {
        timer += delta
        if (timer > 1.5 && enemyEntities.size < 50) {
            enemyEntities.add(EnemyEntity("zombie"))
            enemyEntities.add(EnemyEntity("zombie"))
            timer = 0f
        }
    }

    private fun drawAll(batch: SpriteBatch) {
        batch.draw(img, 0f, 0f)
//        for (i in items) {
//            i.draw(batch)
//        }
        player.draw(batch, delta)

        for (s in particleSystems) {
            s.draw(batch, delta)
        }
        for (b in bullets) {
            b.draw(batch)
        }
        for (e in enemyEntities) {
            e.draw(batch, delta)
        }
        if (lose || timeToEat <= 0) {
            batch.draw(text, 0f, 0f, 1200f, 1200f)
        }
        font.draw(batch, hudText, 20f, 700f)
    }

    private fun destroy() {
        for (i in bulletsToRemove.indices) {
            bullets.remove(bulletsToRemove[i])
        }
        bulletsToRemove.clear()
        for (i in particleSystemsToRemove.indices) {
            particleSystems.remove(particleSystemsToRemove[i])
        }
        particleSystemsToRemove.clear()
        for (i in enemyEntitiesToDestroy.indices) {
            enemyEntities.remove(enemyEntitiesToDestroy[i])
        }
        enemyEntitiesToDestroy.clear()
//        for (i in itemsToRemove.indices) {
//            items.remove(itemsToRemove[i])
//        }
//        itemsToRemove.clear()
    }

    fun paused() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            MyGdxGame.setGameState(com.mygdx.game.Enums.State.RUN)
        }
    }

    private fun checkCollision() {
        for (b in bullets) {
            if (Collision.isBulletOnScreen(b)) {
                bulletsToRemove.add(b)
            }
//            for (i in items) {
//                if (Collision.isBulletHitItem(b, i)) {
//                    bulletsToRemove.add(b)
//                }
//            }
            for (e in enemyEntities) {
                if (Collision.isBulletHitEnemy(b, e)) {
                    counter++
                    enemyEntitiesToDestroy.add(e)
                    bulletsToRemove.add(b)
                    particleSystems.add(ParticleSystem(120, "transparentRed", e.position.x + e.entityWidth / 2,
                            e.position.y + e.entityHeight / 2, e.sprite.rotation))
                    particleSystems.add(ParticleSystem(30, "red", e.position.x + e.entityWidth / 2,
                            e.position.y + e.entityHeight / 2, e.sprite.rotation))
                    if (dead2.isPlaying) {
                        dead2.stop()
                    }
                    dead2.play()
                }
            }
        }

        for (e in enemyEntities) {
            if (Collision.isPlayerHitEnemy(e, player)) {
                e.canMoving = false
                //lose = true;
                playerHit.play()
                particleSystems.add(ParticleSystem(40, "transparentRed",
                        player.position.x + player.entityWidth / 2,
                        player.position.y + player.entityHeight / 2, player.sprite.rotation))
                particleSystems.add(ParticleSystem(40, "yellow",
                        player.position.x + player.entityWidth / 2,
                        player.position.y + player.entityHeight / 2, player.sprite.rotation))
            }
            if (Collision.isEnemyOnScreen(e)) {
                enemyEntitiesToDestroy.add(e)
            }
//            for (i in items) {
//                if (Collision.overlaps(e, i)) {
//                    e.canMoving = false
//                    timeToEat -= delta
//                    eat.play()
//                }
//            }
        }
    }

    fun start() {
        if (Gdx.input.isTouched) {
            MyGdxGame.setGameState(com.mygdx.game.Enums.State.RUN)
        }

    }

    fun drawStartScreen() {
        batch.begin()
        batch.draw(startImage, 0f, 0f)
        batch.end()
    }
}
