package com.mygdx.game;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.game.Service.Game;

public class MyGdxGame extends ApplicationAdapter {
    private Game game;

    @Override
	public void create () {
		game = new Game();
        game.init();
	}

	@Override
	public void render () {
        game.update();
        clear();
		game.draw();
	}

    @Override
    public void dispose () {
        game.dispose();
    }

    private void clear() {
        Gdx.gl.glClearColor(136/255f, 136/255f, 136/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
}
