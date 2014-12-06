/*
A meditação tem como objetivo aumentar a soma máxima dos atributos.
*/
package game;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Meditate extends charTask {
    
    private int period;
        
    public Meditate(Character active, int period){
        super(active);
        this.period = period;
    }

    @Override
    int act() {
        try {
            Thread.sleep(period*1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Meditate.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    void modifyAspects(int c) {
        active.setEnergy(active.getEnergy() + period);
        active.addMaxPoints(period/2);
    }

    @Override
    void updateStatus(int c) {
        if(c == 0) status = "Done.";
        else status = "Meditating.";
    }
    
}
