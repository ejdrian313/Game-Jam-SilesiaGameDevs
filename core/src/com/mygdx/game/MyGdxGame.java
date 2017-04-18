package com.mygdx.game;


import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.game.Service.Game;
import com.mygdx.game.Enums.State;

public final class MyGdxGame extends ApplicationAdapter {
    public final static String GAME_NAME = "Zombie Shooter";
    public final static int WIDTH = 1280;
    public final static int HEIGHT = 720;
    private static State state = State.STARTED;
    private Game game;
    private ApplicationType appType;

    @Override
	public void create () {
        appType = Gdx.app.getType();
		game = new Game();
	}

	@Override
	public void render () {
        switch (state)
        {
            case STARTED:
                game.start();
                clear();
                game.drawStartScreen();
                break;
            case RUN:
                game.update(appType);
                clear();
                game.draw();
                break;
            case PAUSE:
                game.paused();
                break;
            default:
                break;
        }
	}

    @Override
    public void dispose () {
        game.dispose();
    }

    public static void setGameState(State s){
        state = s;
    }

    private void clear() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
}