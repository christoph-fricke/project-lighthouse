package net.lighthouse.controller;

import acm.util.RandomGenerator;
import net.lighthouse.model.BBoss;
import net.lighthouse.model.BLaser;

import java.awt.*;

/**
 * Controls the behaviour of a BBoss.
 *
 * @author Christoph Fricke
 */
class BBossController {

    /**
     * Each BossController has his Boss for operation. So it is good to save the boss as a instance variable.
     */
    private BBoss boss;

    /**
     * Determines how long a hit should be shown through a color change. This needs to be
     * an instance variable since the value has to live longer than a boss move call.
     */
    private int remainingHitFrames;

    /**
     * Creates a new controller.
     *
     * @param boss Boss that should be controlled
     */
    BBossController(BBoss boss) {
        if (boss == null) {
            throw new IllegalArgumentException("There is no possibility to control a null object");
        }
        this.boss = boss;
        this.remainingHitFrames = -1;
    }

    /**
     * Handles action of the BBoss. A boss spawn a new laser when his health is odd.
     *
     * @return A new laser if one is spawned. {@code null} otherwise.
     */
    BLaser playBossMove() {
        if (boss.getHealth() <= 0) {
            throw new IllegalStateException("Boss should be death by now!");
        }

        BLaser laser = null;

        // Changes the color of the boss for one frame if damage was dealt.
        // This it not visible since it is to fast :(
        if (boss.getHealth() != boss.getOldHealth()) {
            // Damage was dealt
            boss.setColor(Color.WHITE);
            boss.evenOldHealth();
            remainingHitFrames = 40;

            RandomGenerator rnd = RandomGenerator.getInstance();
            int[] speed = {rnd.nextInt(-4, 4), rnd.nextInt(4, 6)};

            // Spawns a new laser at the bottom center of the boss
            laser = new BLaser(boss.getX() + boss.getWith() / 2, boss.getY() + boss.getHeight(), speed);
        } else {
            if (remainingHitFrames != -1 && remainingHitFrames == 0) {
                boss.setColor(Color.GREEN);
                remainingHitFrames = -1;
            } else {
                remainingHitFrames--;
            }
        }

        // Indicates that the boss is nearly dead.
        if (boss.getHealth() == 1) {
            boss.setColor(Color.RED);
        }

        return laser;
    }
}
