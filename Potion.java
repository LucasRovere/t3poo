/*
Poção é um tipo de item  que recupera/modifica algum atributo do personagem.
É abstrata porque ainda não é definida sua função (metodo use())
*/
package game;
//package item;
//package character;
//package tasks;

import static game.ItemType.*;

public abstract class Potion implements Item {
    
    private int restorepts;
    private String name;
    private ItemType type;
    
    public Potion(String name, int restorepts) {
        this.name = name;
        this.restorepts = restorepts;
        type = potion;
    }

    @Override
    public int getActionPts() {
        return restorepts;
    }

    public abstract void use(Character c);

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ItemType getType() {
        return type;
    }

    @Override
    public double getPrice() {
        return 3 * restorepts / 2;
    }

    @Override
    public double getSellPrice() {
        return 0;
    }
}
