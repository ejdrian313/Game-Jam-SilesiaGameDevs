package com.mygdx.game


import com.badlogic.gdx.Application.ApplicationType
import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.mygdx.game.Service.Game
import com.mygdx.game.Enums.State

class MyGdxGame : ApplicationAdapter() {
    lateinit var game: Game
    lateinit var appType: ApplicationType

    override fun create() {
        appType = Gdx.app.type
        game = Game()
    }

    override fun render() {
        when (state) {
            State.STARTED -> {
                game.start()
                clear()
                game.drawStartScreen()
            }
            State.RUN -> {
                game.update(appType)
                clear()
                game.draw()
            }
            State.PAUSE -> game.paused()
            else -> {
            }
        }
    }

    override fun dispose() {
        game.dispose()
    }

    private fun clear() {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    }

    companion object {
        val GAME_NAME = "Zombie Shooter"
        val WIDTH = 1280
        val HEIGHT = 720
        private var state = State.STARTED

        fun setGameState(s: State) {
            state = s
        }
    }
}