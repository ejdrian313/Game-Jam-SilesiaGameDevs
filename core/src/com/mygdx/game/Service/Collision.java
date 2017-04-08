package com.mygdx.game.Service;

import com.mygdx.game.Entity.BasicEntity;
import com.mygdx.game.Entity.EnemyEntity;
import com.mygdx.game.Items.Bullet;

/**
 * Created by Adrian on 2017-04-09.
 */

public abstract class Collision {
    public static boolean isBulletHitEnemy(Bullet b, EnemyEntity e) {
        return !(b.position.x > e.position.x + e.width || b.position.x + b.width < e.position.x ||
                b.position.y > e.position.y + e.height || b.position.y + b.height < e.position.y);
    }

    public static boolean isPlayerHitEnemy(BasicEntity e, BasicEntity p) {
        return !(e.position.x > p.position.x + p.width || e.position.x + e.width < p.position.x ||
                e.position.y > p.position.y + p.height || e.position.y + e.height < p.position.y);
    }
}
