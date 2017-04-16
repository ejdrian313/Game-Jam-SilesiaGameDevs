package com.mygdx.game.Service;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
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
        return !(e.position.x > p.position.x + p.width -25|| e.position.x + e.width < p.position.x+25 ||
                e.position.y > p.position.y + p.height -25|| e.position.y + e.height < p.position.y+25);
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
        return (!(b.position.x+35 > e.position.x + e.width || b.position.x + b.width-35  < e.position.x ||
                b.position.y+35 > e.position.y + e.height || b.position.y + b.height-35 < e.position.y));
    }

    public static boolean overlaps(Vector2 touch, Sprite s) {
        return (!(touch.x > s.getX() + s.getWidth() || touch.x  < s.getX() ||
                touch.y > s.getY() + s.getHeight() || touch.y < s.getY()));
    }
}
