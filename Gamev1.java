
/*
Versão principal do jogo.
Feita para rodar com o Jframe e ter uma interface mais amigável.
*/

package game;

import static game.Color.*;
import static game.HuntType.*;
import static game.PetType.*;
import static game.RaceType.*;
import static game.ItemType.*;
import static game.TrainingType.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Gamev2 {
    

    public static JFrame_Principal GameW = new JFrame_Principal();
    public static JFrame_Menus Game = new JFrame_Menus();
    
    public static void main(String[] args) {
              
        GameW.setVisible(true);
        Game.setVisible(true);
        
        GameDefault.setGameDefault();
    
    }
}
