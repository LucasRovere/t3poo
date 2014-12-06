
/*
 A classe Tame é uma ocupação para o personagem.
 A ideia é que o personagem saia para tentar domar algum pet para ajuda-lo em batalhas.
 Ele gasta energia de qualquer forma, mas sua chance de conseguir alguma coisa é proporcional a sua experiencia
 */
package game;
//package item;
//package character;

import java.util.logging.Level;
import java.util.logging.Logger;

//package tasks;
public class Tame extends charTask {

    private int roundCount = 0;
    private int period;
    private PetType type;

    public Tame(Character active, int period, PetType type) {
        super(active);
        this.period = period;
        this.type = type;
    }

    //Enquanto durar o período e a energia do personagem
    //a função act escolhe aleatoriamente o resultado da tentativa
    @Override
    int act() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Tame.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (active.getEnergy() <= 0) {
            return -1;
        }

        if (Math.random() < 1 / (10 + active.getXP())) {
            return 0;
        }
        if (++roundCount > period) {
            return -1;
        }

        return 1;
    }

    @Override
    void modifyAspects(int c) {
        active.setEnergy(active.getEnergy() - 3);
        active.addXP(1);
        if (c != 0) {
            return;
        }

        active.changePet(new Pet(GameDefault.getPetName(), type, (int) (Math.random() * active.getXP()), (int) (Math.random() * active.getXP())));
    }

    @Override
    void updateStatus(int c) {
        if (c == 1) {
            this.status = "Searching";
        } else if (c == 0) {
            this.status = "Tamming";
        } else if (c == -1) {
            this.status = "Unsuccesfull...";
        } else {
            this.status = "Done";
        }
    }

}
