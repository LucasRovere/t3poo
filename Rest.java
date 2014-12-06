/*
Tarefa simples que recupera a energia do personagem.
*/
package game;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Rest extends charTask{
    
    public Rest(Character active){
        super(active);
    }

    @Override
    int act() {
        try {
            Thread.sleep((100 - active.getEnergy())*50);
        } catch (InterruptedException ex) {
            Logger.getLogger(Rest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    void modifyAspects(int c) {
        active.setEnergy(100);
    }

    @Override
    void updateStatus(int c) {
        if(c == 0) status = "Done.";
        else status = "Sleeping";
    }
    
}
