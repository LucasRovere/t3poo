package game;
//package item;
//package character;
//package tasks;

import static game.ItemType.*;

public class Weapon implements Item {
    
    protected int attackpts;
    protected double range;
    private final String name;
    private ItemType type;

    public Weapon(String name, double range, int attackpts) {
        this.name = name;

        if (attackpts > 9) {
            attackpts = 9;
        }
        if (attackpts < 1) {
            attackpts = 1;
        }
        this.attackpts = attackpts;
        this.range = range;
        type = weapon;
    }

    @Override
    public int getActionPts() {
        return attackpts;
    }

    public double getRange() {
        return range;
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
        return attackpts + range;
    }

    @Override
    public double getSellPrice() {
        return getPrice() / 2;
    }
}