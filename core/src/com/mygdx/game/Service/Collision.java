package com.mygdx.game.Service;

import com.mygdx.game.Entity.BasicEntity;
import com.mygdx.game.Entity.EnemyEntity;
import com.mygdx.game.Items.Bullet;
import com.mygdx.game.Items.Item;

/**
 * Created by Adrian on 2017-04-09.
 */

public abstract class Collision {
    public static boolean isBulletHitEnemy(Bullet b, EnemyEntity e) {
        return !(b.position.x > e.position.x + e.width || b.position.x + b.width < e.position.x ||
                b.position.y > e.position.y + e.height || b.position.y + b.height < e.position.y);
    }

    public static boolean isBulletHitItem(Bullet b, Item e) {
        return !(b.position.x > e.position.x + e.width-10 || b.position.x + b.width < e.position.x +10||
                b.position.y > e.position.y + e.height-10 || b.position.y + b.height < e.position.y+10);
    }

    public static boolean isPlayerHitEnemy(BasicEntity e, BasicEntity p) {
        return !(e.position.x > p.position.x + p.width -60|| e.position.x + e.width < p.position.x+60 ||
                e.position.y > p.position.y + p.height -60|| e.position.y + e.height < p.position.y+60);
    }

    public static boolean isEnemyOnScreen(EnemyEntity e) {
        return (e.position.x > 1500 || e.position.x < -150 ||
                e.position.y > 1200 || e.position.y < -150);
    }

    public static boolean isBulletOnScreen(Bullet b) {
        return (b.position.x > 1500 || b.position.x < -150 ||
                b.position.y > 1200 || b.position.y < -150);
    }

    public static boolean isBasicOnPlatform(BasicEntity player, Item box) {
        return  ((player.position.x + player.width - 2 >= box.position.x) && (player.position.x + 2 <= box.position.x + box.width)
                && (player.position.y <= box.position.y + box.height + 2) && (player.position.y >= box.position.y + box.height - 2));
    }

    public static boolean isBasicHitLeft(BasicEntity player, Item box) {
        return ((player.position.x + player.width >= box.position.x) && ((player.position.x + player.width) < (box.position.x + box.width))
                && (player.position.y < (box.position.y + box.height)) && ((player.position.y + player.height) > box.position.y));
    }

    public static boolean isBasicHitRight(BasicEntity player, Item box) {
        return ((player.position.x <= box.position.x + box.width) && (player.position.x > box.position.x)
                && (player.position.y < box.position.y + box.height) && (player.position.y + player.height > box.position.y));
    }

    public static boolean isBasicHitBottom(BasicEntity player, Item box) {
        return ((player.position.y + player.height > box.position.y) && (player.position.y + player.height < box.position.y + box.height)
                && (player.position.x + 2 < box.position.x + box.width) && (player.position.x + player.width - 2 > box.position.x));
    }

    public static boolean overlaps(BasicEntity b, Item e) {
        if(!(b.position.x+50 > e.position.x + e.width || b.position.x + b.width-50  < e.position.x ||
                b.position.y+50 > e.position.y + e.height || b.position.y + b.height-50 < e.position.y)) {
            return true;
        } else return false;
    }
}
