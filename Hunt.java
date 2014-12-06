/*
 Basicamente serve para aumentar alguns atributos e experiência do personagem.
 Pode ser feita de forma garantida e segura, com animais, ou de forma perigosa
 mas com maior recompensa, com monstros.
 */
package game;

import static game.HuntType.*;
import static game.RaceType.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Hunt extends charTask {

    private HuntType type;
    private Monster target;

    public Hunt(Character active, HuntType type) {
        super(active);
        this.type = type;
        target = new Monster(GameDefault.getMonsterName(), orc, active.getBattlePoints());
    }

    @Override
    int act() {
        try {
            Thread.sleep(10000 / active.XP);
        } catch (InterruptedException ex) {
            Logger.getLogger(Hunt.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (type == animal) {
            return -2;
        }

        target.attackCharacter(active);
        if (active.getHP() <= 0) {
            return -3;
        }
        active.attackMonster(target);
        if (active.getPet() != null) {
            active.getPet().attackMonster(target);
        }

        if (target.getHP() > 0) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    void modifyAspects(int c) {
        if (c == 0) {
            active.addXP(50);
            active.addStrenght(5);
            active.addConstitution(5);
        } else if (c == -1) {
            active.addXP(25);
        } else if (c == -2) {
            active.addXP(10);
        }

        active.setEnergy(active.getEnergy() - 10);
    }

    @Override
    void updateStatus(int c) {
        if (c == 0) {
            status = "Killed a monster.";
        } else if (c == -1) {
            status = "Injured a monster.";
        } else if (c == -2) {
            status = "Killed an animal.";
        } else {
            status = "Dead.";
        }
    }

}
