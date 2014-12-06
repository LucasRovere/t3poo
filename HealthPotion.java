/*
Recupera o HP do personagem
*/
package game;
//package item;
//package character;
//package tasks;

public class HealthPotion extends Potion {
    
    public HealthPotion(String name, int restorepts) {
        super(name, restorepts);
    }

    @Override
        public void use(Character c) {
        c.addHP(this.getActionPts());
        c.sellItem(getName());
    }
}
