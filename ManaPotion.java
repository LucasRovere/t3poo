/*
Poção que aumenta a mana do personagem
*/
package game;
//package item;
//package character;
//package tasks;

public class ManaPotion extends Potion {

    public ManaPotion(String name, int restorepts) {
        super(name, restorepts);
    }

    @Override
    public void use(Character c) {
        c.addHP(getActionPts());
        c.sellItem(getName());
    }
}
