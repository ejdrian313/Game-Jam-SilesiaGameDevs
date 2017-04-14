package com.mygdx.game;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.game.Service.Game;
import com.mygdx.game.Service.State;

public class MyGdxGame extends ApplicationAdapter {
    public static State state = State.RUN;
    private Game game;

    @Override
	public void create () {
		game = new Game();
	}

	@Override
	public void render () {
        switch (state)
        {
            case RUN:
                game.update(state);
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
        Gdx.gl.glClearColor(136/255f, 136/255f, 136/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
}
