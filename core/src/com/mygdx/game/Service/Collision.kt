package com.mygdx.game.Service

import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.Entity.BasicEntity
import com.mygdx.game.Entity.EnemyEntity
import com.mygdx.game.Items.Bullet
import com.mygdx.game.Items.Item

/**
 * Created by Adrian on 2017-04-09.
 */

object Collision {

    fun isBulletHitEnemy(b: Bullet, e: EnemyEntity): Boolean {
        return !(b.position.x > e.position.x + e.entityWidth || b.position.x + b.entityWidth < e.position.x ||
                b.position.y > e.position.y + e.entityHeight || b.position.y + b.entityHeight < e.position.y)
    }

    fun isBulletHitItem(b: Bullet, e: Item): Boolean {
        return !(b.position.x > e.position.x + e.entityWidth - 10 || b.position.x + b.entityWidth < e.position.x + 10 ||
                b.position.y > e.position.y + e.entityHeight - 10 || b.position.y + b.entityHeight < e.position.y + 10)
    }

    fun isPlayerHitEnemy(e: BasicEntity, p: BasicEntity): Boolean {
        return !(e.position.x > p.position.x + p.entityWidth - 25 || e.position.x + e.entityWidth < p.position.x + 25 ||
                e.position.y > p.position.y + p.entityHeight - 25 || e.position.y + e.entityHeight < p.position.y + 25)
    }

    fun isEnemyOnScreen(e: EnemyEntity): Boolean {
        return e.position.x > 1500 || e.position.x < -150 ||
                e.position.y > 1200 || e.position.y < -150
    }

    fun isBulletOnScreen(b: Bullet): Boolean {
        return b.position.x > 1500 || b.position.x < -150 ||
                b.position.y > 1200 || b.position.y < -150
    }

    fun isBasicOnPlatform(player: BasicEntity, box: Item): Boolean {
        return (player.position.x + player.entityWidth - 2 >= box.position.x && player.position.x + 2 <= box.position.x + box.entityWidth
                && player.position.y <= box.position.y + box.entityHeight + 2f && player.position.y >= box.position.y + box.entityHeight - 2)
    }

    fun isBasicHitLeft(player: BasicEntity, box: Item): Boolean {
        return (player.position.x + player.entityWidth >= box.position.x && player.position.x + player.entityWidth < box.position.x + box.entityWidth
                && player.position.y < box.position.y + box.entityHeight && player.position.y + player.entityHeight > box.position.y)
    }

    fun isBasicHitRight(player: BasicEntity, box: Item): Boolean {
        return (player.position.x <= box.position.x + box.entityWidth && player.position.x > box.position.x
                && player.position.y < box.position.y + box.entityHeight && player.position.y + player.entityHeight > box.position.y)
    }

    fun isBasicHitBottom(player: BasicEntity, box: Item): Boolean {
        return (player.position.y + player.entityHeight > box.position.y && player.position.y + player.entityHeight < box.position.y + box.entityHeight
                && player.position.x + 2 < box.position.x + box.entityWidth && player.position.x + player.entityWidth - 2 > box.position.x)
    }

    fun overlaps(b: BasicEntity, e: Item): Boolean {
        return !(b.position.x + 35 > e.position.x + e.entityWidth || b.position.x + b.entityWidth - 35 < e.position.x ||
                b.position.y + 35 > e.position.y + e.entityHeight || b.position.y + b.entityHeight - 35 < e.position.y)
    }

    fun overlaps(touch: Vector2, s: Sprite): Boolean {
        return !(touch.x > s.x + s.width || touch.x < s.x ||
                touch.y > s.y + s.height || touch.y < s.y)
    }
}
