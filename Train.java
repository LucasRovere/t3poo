
/*
 Train é uma ocupação que permite ao jogador aumentar um dos atributos de seus personagens.
 Serve para qualquer atributo comum aos characters, ou para os específicos das
 classes filhas. Caso a soma dos atributos tenha atingido o nível máximo,
 ao aumentar um, consequentemente o personagem perderá os outros proporcinalmente.
 */
package game;

import static game.TrainingType.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Train extends charTask {

    private int roundCount = 0;
    private int period;
    private TrainingType type;

    public Train(Character active, int period, TrainingType type) {
        super(active);
        this.period = period;
        this.type = type;
    }

    @Override
    int act() {
        try {
            Thread.sleep(5 * active.XP);
        } catch (InterruptedException ex) {
            Logger.getLogger(Train.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (++roundCount > period) {
            return 0;
        }
        if (active.getEnergy() <= 0) {
            return -1;
        }

        return 1;
    }

    @Override
    void modifyAspects(int c) {
        active.setEnergy(active.getEnergy() - active.getXP() / 10 + 10);
        active.addXP(1);
        if (type == run) {
            active.addSpeed(1);
        }
        if (type == lift) {
            active.addStrenght(1);
        }
        if (type == fight) {
            active.addConstitution(1);
        }
        if (type == parkour) {
            active.addDexterity(1);
        }
        if (type == special) {
            active.addSpecial(1);
        }
    }

    @Override
    void updateStatus(int c) {
        if (c <= 0) {
            status = "Done";
        }
        if (c == 1) {
            status = "Training";
        }
    }
}
