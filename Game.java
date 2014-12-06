
package game;

import static game.Color.*;
import static game.RaceType.*;
import static game.HuntType.*;
import static game.ItemType.*;
import static game.PetType.*;
import static game.TrainingType.*;
import java.util.ArrayList;

public class Game {
    

    public static void main(String[] args) {
    	
    	setGameDefault();

    	//Exemplo de execucao sem intereacao do usuario.

    	GameDefault.addWizard();
    	GameDefault.addKnight();
    	GameDefault.addTheif();

    	GameDefault.selectcChar(0);
    	GameDeafult.goHunt(animal);

    	GameDefault.selectChar(1);
    	GameDefault.goMine();

		GameDefault.selectChar(2);
    	GameDefault.goTame();

    	GameDefault.selectChar(1);

    	GameDefault.genBuyList();
    	GameDefault.buyItem(0);

    	GameDefault.goFight();
    }
}
