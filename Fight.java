/*
Essa tarefa faz o personagem batalhar com um inimigo gerado
*/
package game;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Fight extends charTask {
    
    private Enemy target;
    
    public Fight(Character active){
        super(active);
        target = new Enemy(GameDefault.getPersonName(), active.getBattlePoints());
    }

    @Override
    int act() {
        try {
            Thread.sleep(1000/active.XP);
        } catch (InterruptedException ex) {
            Logger.getLogger(Fight.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        active.attackEnemy(target);
        if(target.getHP() <= 0){
            return 0;
        }
        
        target.attackCharacter(active);
        if(active.getHP() <= 0){
            return -1;
        }
        
        return 1;
    }

    @Override
    void modifyAspects(int c) {
        if(c == 0)
            active.setEnergy(0);
    }

    @Override
    void updateStatus(int c) {
        if(c == 1) status = "Fighting.";
        else if(c == 0) status = "Won the fight.";
        else status = "Dead.";
    }
}
