package game;

import static game.ItemType.*;

public interface Item {
    
    public String getName();

    public ItemType getType();

    public double getPrice();
    
    public double getSellPrice();
    
    public int getActionPts();
}
