
/*
 A ocupação mine fará que o personagem gaste energia para conseguir
 aumentar a quantidade de gold disponível.
 */
//package item;
//package character;
//package tasks;
package game;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Mine extends charTask {

    private int roundCount = 0;
    private int period;

    public Mine(Character active, int period) {
        super(active);
        this.period = period;
    }

    @Override
    int act() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(Mine.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (++roundCount > period) {
            return 1;
        }
        if (active.getEnergy() <= 0) {
            return -1;
        } else {
            return 1;
        }
    }

    @Override
    void modifyAspects(int c) {
        active.setEnergy(active.getEnergy() - 10);
        active.earnCharacterGold(10 + active.getXP() / 500);
        active.addXP(1);
    }

    @Override
    void updateStatus(int c) {
        if (c <= 0) {
            status = "Done";
        }
        if (c == 1) {
            status = "Mining";
        }
    }
}
