package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Entity.EnemyEntity;
import com.mygdx.game.Entity.PlayerEntity;
import com.mygdx.game.Items.Bullet;
import com.mygdx.game.Items.Item;
import com.mygdx.game.Service.Collision;
import com.mygdx.game.Service.ParticleSystem;

import java.util.LinkedList;

public class MyGdxGame extends ApplicationAdapter {
    private SpriteBatch batch;
    private int ammo = 500;
    private int counter = 0;
    private float delta;
    private float timer;
    private float timeToReload = 0;
    private float timeToEat = 15;
    private float timeOfGame = 0;
    private boolean lose = false;
    private String hudText;
    private Music music;
    private Music eat;
    private Music dead2;
    private Music dead3;
    private Music dead4;
    private Music playerHit;
    private BitmapFont font;
    private Texture img;
    private Texture text;
	private PlayerEntity player;
    private LinkedList<Item> items;
    private LinkedList<Item> itemsToRemove;
    private LinkedList<Bullet> bullets;
    private LinkedList<Bullet> bulletsToRemove;
    private LinkedList<EnemyEntity> enemyEntities;
    private LinkedList<EnemyEntity> enemyEntitiesToDestroy;
    private LinkedList<ParticleSystem> particleSystems;
    private LinkedList<ParticleSystem> particleSystemsToRemove;


    @Override
	public void create () {
		batch = new SpriteBatch();
        img = new Texture("ground.jpg");
		player = new PlayerEntity("player", 400, 400);
        particleSystems = new LinkedList<>();
        particleSystemsToRemove = new LinkedList<>();
        enemyEntities = new LinkedList<>();
        enemyEntitiesToDestroy = new LinkedList<>();
        bullets = new LinkedList<>();
        bulletsToRemove = new LinkedList<>();
        items = new LinkedList<>();
        itemsToRemove = new LinkedList<>();
        items.add(new Item("precious", 500, 500));
        text = new Texture("lose.png");
        font = new BitmapFont();
        font.getData().setScale(2);
        font.setColor(Color.BLACK);
        music = Gdx.audio.newMusic(Gdx.files.internal("shoot.wav"));
        eat = Gdx.audio.newMusic(Gdx.files.internal("eating.mp3"));
        dead2 = Gdx.audio.newMusic(Gdx.files.internal("dead2.wav"));
        dead3 = Gdx.audio.newMusic(Gdx.files.internal("dead3.wav"));
        dead4 = Gdx.audio.newMusic(Gdx.files.internal("dead4.wav"));
        playerHit = Gdx.audio.newMusic(Gdx.files.internal("playerHit.wav"));
	}

	@Override
	public void render () {
        handleInput(delta);
        update(delta);

        clear();
		batch.begin();
        drawAll(batch);
		batch.end();
        delta = Gdx.graphics.getDeltaTime();
        timeToReload += delta;
        timeOfGame += delta;
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

        checkCollision();
        destroy();
        addEnemy(delta);
        hudText = "Time to eat all the coal: " + timeToEat + "\nYou killed: " + counter + "\nTime of game: " + (int)timeOfGame;
    }

    private void handleInput(float d) {
        player.handleInput(d);

        if(Gdx.input.isKeyPressed(Input.Keys.SPACE) && timeToReload > .5f && ammo > 0) {
            Bullet b1 = new Bullet(player.position.x + player.width/2,
                    player.position.y + player.height/2, player.sprite.getRotation());
            bullets.add(b1);
            timeToReload = 0;
            --ammo;
            if(music.isPlaying()) {
                music.stop();
            }
            music.play();
        }
    }

    private void addEnemy(float delta) {
        timer += delta;
        if(timer > 1.5) {
            enemyEntities.add(new EnemyEntity("zombie"));
            enemyEntities.add(new EnemyEntity("zombie"));
            timer = 0;
        }
    }

    private void drawAll(SpriteBatch batch) {
        batch.draw(img, 0, 0);
        for(Item i : items) {
            i.draw(batch);
        }

        player.draw(batch, delta);

        for(ParticleSystem s : particleSystems) {
            s.draw(batch, delta);
        }
        for(Bullet b : bullets) {
            b.draw(batch);
        }
        for(EnemyEntity e : enemyEntities) {
            e.draw(batch, delta);
        }
        if(lose || timeToEat <= 0) {
            batch.draw(text, 0, 0, 1200, 1200);
        }
        font.draw(batch, hudText, 20, 950);
    }

    private void clear() {
        Gdx.gl.glClearColor(136/255f, 136/255f, 136/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
        font.dispose();
	}

    private void checkCollision() {
        for(Bullet b : bullets) {
            if(Collision.isBulletOnScreen(b)) {
                bulletsToRemove.add(b);
            }
            for(Item i : items) {
                if(Collision.isBulletHitItem(b, i)) {
                    bulletsToRemove.add(b);
                }
            }
            for(EnemyEntity e : enemyEntities) {
                if(Collision.isBulletHitEnemy(b, e)) {
                    counter++;
                    enemyEntitiesToDestroy.add(e);
                    bulletsToRemove.add(b);
                    particleSystems.add(new ParticleSystem(120, "transparentRed", e.position.x +e.width/2,
                            e.position.y + e.height/2, e.sprite.getRotation()));
                    particleSystems.add(new ParticleSystem(30, "red", e.position.x+e.width/2,
                            e.position.y+e.height/2, e.sprite.getRotation()));
                    if(dead2.isPlaying()) {
                        dead2.stop();
                    }
                    dead2.play();
                }
            }
        }

        for(EnemyEntity e : enemyEntities) {
            if(Collision.isPlayerHitEnemy(e, player)) {
                e.canMoving = false;
                //lose = true;
                playerHit.play();
                particleSystems.add(new ParticleSystem(40, "transparentRed",
                        player.position.x, player.position.y, player.sprite.getRotation()));
                particleSystems.add(new ParticleSystem(40, "yellow",
                        player.position.x, player.position.y, player.sprite.getRotation()));
            }
            if(Collision.isEnemyOnScreen(e)) {
                enemyEntitiesToDestroy.add(e);
            }
            for(Item i : items) {
                if(Collision.overlaps(e, i)) {
                    e.canMoving = false;
                    timeToEat -= delta;
                    eat.play();
                }
            }
        }
    }
}