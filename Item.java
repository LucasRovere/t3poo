package game;
//package item;
//package character;
//package tasks;

public interface Item {
    
    public String getName();

    public ItemType getType();

    public double getPrice();
    
    public double getSellPrice();
    
    public int getActionPts();
}
