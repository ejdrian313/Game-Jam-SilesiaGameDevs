package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Entity.EnemyEntity;
import com.mygdx.game.Entity.PlayerEntity;
import com.mygdx.game.Items.Bullet;
import com.mygdx.game.Service.Collision;
import com.mygdx.game.Service.ParticleSystem;

import java.util.LinkedList;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
    private Texture img;
	private PlayerEntity player;
    private LinkedList<EnemyEntity> enemyEntities;
    private LinkedList<EnemyEntity> enemyEntitiesToDestroy;
    private float delta;
    private float timer;
    private LinkedList<ParticleSystem> particleSystems;
    private LinkedList<ParticleSystem> particleSystemsToRemove;
    private LinkedList<Bullet> bullets;
    private LinkedList<Bullet> bulletsToRemove;

    @Override
	public void create () {
		batch = new SpriteBatch();
        img = new Texture("ground.jpg");
		player = new PlayerEntity("player", 100, 100);
        particleSystems = new LinkedList<>();
        particleSystemsToRemove = new LinkedList<>();
        enemyEntities = new LinkedList<>();
        enemyEntitiesToDestroy = new LinkedList<>();
        bullets = new LinkedList<>();
        bulletsToRemove = new LinkedList<>();
	}

	@Override
	public void render () {
        handleInput(delta);
        update(delta);
        checkCollision();
        destroy();
        addEnemy(delta);

        clear();
		batch.begin();
        drawAll(batch);
		batch.end();
        delta = Gdx.graphics.getDeltaTime();
	}

    private void addEnemy(float delta) {
        timer += delta;
        if(timer > 2) {
            enemyEntities.add(new EnemyEntity("zombie", 0, 0));
            enemyEntities.add(new EnemyEntity("zombie", 0, 0));
            enemyEntities.add(new EnemyEntity("zombie", 0, 0));
            timer = 0;
        }
    }

    private void checkCollision() {
        for(Bullet b : bullets) {
            for(EnemyEntity e : enemyEntities) {
                if(Collision.isBulletHitEnemy(b, e)) {
                    enemyEntitiesToDestroy.add(e);
                    bulletsToRemove.add(b);
                    particleSystems.add(new ParticleSystem(80, "transparentRed", b.position.x,
                            b.position.y, e.sprite.getRotation()));
                }
            }
        }
        for(EnemyEntity e : enemyEntities) {
            if(Collision.isPlayerHitEnemy(e, player)) {
                //@TODO: sad music
                particleSystems.add(new ParticleSystem(40, "red",
                        player.position.x, player.position.y, player.sprite.getRotation()));
                particleSystems.add(new ParticleSystem(40, "transparentRed",
                        player.position.x, player.position.y, player.sprite.getRotation()));
                particleSystems.add(new ParticleSystem(40, "yellow",
                        player.position.x, player.position.y, player.sprite.getRotation()));
            }
        }
    }

    private void drawAll(SpriteBatch batch) {
        batch.draw(img, 0, 0);
        player.draw(batch, delta);
        for(ParticleSystem s : particleSystems) {
            s.draw(batch, delta);
        }
        for(Bullet b : bullets) {
            b.draw(batch);
        }
        for(EnemyEntity e : enemyEntities) {
            e.draw(batch);
        }
    }

    private void update(float delta) {
        for(ParticleSystem s : particleSystems) {
            s.update(delta);
        }
        for(Bullet b : bullets) {
            b.update(delta, b);
        }
        for(EnemyEntity e : enemyEntities) {
            e.update(delta);
        }
    }

    private void destroy() {
        for(int i = 0; i < bulletsToRemove.size(); i++) {
            bullets.remove(bulletsToRemove.get(i));
        }
        for(int i = 0; i < particleSystemsToRemove.size(); i++) {
            particleSystems.remove(particleSystemsToRemove.get(i));
        }
        particleSystemsToRemove.clear();
        for(int i = 0; i < enemyEntitiesToDestroy.size(); i++) {
            enemyEntities.remove(enemyEntitiesToDestroy.get(i));
        }
    }

    private void handleInput(float d) {
        player.handleInput(d);

        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            Bullet b1 = new Bullet(player.position.x + player.width/2,
                    player.position.y + player.height/2, player.sprite.getRotation());
            bullets.add(b1);
        }
    }

    private void clear() {
        Gdx.gl.glClearColor(136/255f, 136/255f, 136/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
	public void dispose () {
        batch.dispose();
        for(ParticleSystem s : particleSystems) {
            s.dispose();
        }
        for(Bullet b : bullets) {
            b.dispose();
        }
        for(EnemyEntity e : enemyEntities) {
            e.dispose();
        }
		player.dispose();
        img.dispose();
	}
}
