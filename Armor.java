/*
 Armor é um tipo de item que aumenta a defesa do personagem.
 */
package game;
//package item;

import static game.ItemType.*;

public class Armor implements Item {

    private String name;
    private int defensepts;
    private double weight;
    private ItemType type;

    public Armor(String name, double weight, int defensepts) {
        this.name = name;
        this.weight = weight;
        this.defensepts = defensepts;

        if (defensepts > 20) {
            defensepts = 20;
        }
        if (defensepts < 1) {
            defensepts = 1;
        }
        if (weight > 20) {
            weight = 20;
        }
        if (weight < 1) {
            weight = 1;
        }
        this.weight = weight;
        this.defensepts = defensepts;
        type = armor;
    }

    public double getWeight() {
        return weight;
    }

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
        return 2 * defensepts - weight;
    }

    @Override
    public double getSellPrice() {
        return getPrice() / 2;
    }

    @Override
    public int getActionPts() {
        return defensepts;
    }
}
