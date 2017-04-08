package com.mygdx.game.Service;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.LinkedList;

/**
 * Created by Adrian on 2017-04-03.
 */

public class ParticleSystem {
    public boolean allDead = false;
    private LinkedList<Particle> particles;
    private LinkedList<Particle> particlesToDestroy;


    public ParticleSystem(int howMany, String color, float posX, float posY, float rotation) {
        particles = new LinkedList<>();
        particlesToDestroy = new LinkedList<>();
        for(int i=0; i< howMany; i++) {
            particles.add(new Particle(color, posX, posY, rotation));
        }
    }

    public void update(float deltaTime) {
        for(Particle p : particles) {
            p.update(deltaTime);
        }
        destroy();
    }

    public void draw(SpriteBatch sp, float delta) {
        for(Particle p : particles) {
            p.alpha -= delta;
            if(p.alpha <= 0) {
                p.alpha = 0;
                particlesToDestroy.add(p);
            }
            p.draw(sp, p.alpha);
        }
    }

    public void destroy() {
        for(int i = 0; i < particlesToDestroy.size(); i++) {
            particles.remove(particlesToDestroy.get(i));
        }
        particlesToDestroy.clear();
        if(particles.size() == 0){
            allDead = true;
        }
    }

    public void dispose() {
        for(Particle p : particles) {
            p.dispose();
        }
    }
}