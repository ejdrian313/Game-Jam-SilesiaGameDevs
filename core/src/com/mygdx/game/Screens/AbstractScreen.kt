package com.mygdx.game.Screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.StretchViewport
import com.mygdx.game.MyGdxGame

/**
 * Created by Adrian on 2017-04-16.
 */

abstract class AbstractScreen(protected var game: MyGdxGame) : Screen {

    protected var stage: Stage
    private var camera: OrthographicCamera? = null
    protected var spriteBatch: SpriteBatch

    init {
        createCamera()
        stage = Stage(StretchViewport(MyGdxGame.WIDTH.toFloat(), MyGdxGame.HEIGHT.toFloat(), camera))
        spriteBatch = SpriteBatch()
        Gdx.input.inputProcessor = stage
        init()
    }

    protected abstract fun init()

    private fun createCamera() {
        camera = OrthographicCamera()
        camera!!.setToOrtho(false, MyGdxGame.WIDTH.toFloat(), MyGdxGame.HEIGHT.toFloat())
        camera!!.update()
    }

    override fun render(delta: Float) {
        clearScreen()
        camera!!.update()
        spriteBatch.projectionMatrix = camera!!.combined
    }

    override fun show() {}

    private fun clearScreen() {
        Gdx.gl.glClearColor(0f, 0f, 0f, 0f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    }

    override fun resume() {
        //game.setPaused(false);
    }

    override fun pause() {
        //game.setPaused(true);
    }

    override fun hide() {}

    override fun dispose() {
        game.dispose()
    }

    override fun resize(width: Int, height: Int) {}

}
