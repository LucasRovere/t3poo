
/*
Versão alternativa e simplificada do jogo.
Feita para rodar no terminal.
Apesar de simplificada, tem a mesma mecânica da versão 1
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
    

    public static void main(String[] args) {
        
        GameDefault.setGameDefault();
        GameDefault.addKnight();
        GameDefault.addThief();
        GameDefault.addWizard();
        int choice;
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("Seu time tem 1 mago, 1 cavaleiro e 1 ladrão.");
            System.out.println("Para selecionar o personagem:");
            System.out.println("10 cavaleiro, 11 ladrão, 12 mago, 0 para sair.");
            System.out.println("2 Para ver stats dos personagens.");
            choice = scanner.nextInt();
            
            if(choice == 0) break;
            else if(choice == 10){
                GameDefault.selectChar(0);
            }
            else if(choice == 11){
                GameDefault.selectChar(1);
            }
            else if(choice == 12){
                GameDefault.selectChar(2);
            }
            else if(choice == 2){
                System.out.println(GameDefault.playerTeam.getMember(0).getName() + ": Attack: " + GameDefault.playerTeam.getMember(0).getAttackpoints() + " Defense: " + GameDefault.playerTeam.getMember(0).getDefensepoints() + " Free? " + GameDefault.playerTeam.getMember(0).isFree() + " Energia: " + GameDefault.playerTeam.getMember(0).getEnergy());
                System.out.println(GameDefault.playerTeam.getMember(1).getName() + ": Attack: " + GameDefault.playerTeam.getMember(1).getAttackpoints() + " Defense: " + GameDefault.playerTeam.getMember(1).getDefensepoints() + " Free? " + GameDefault.playerTeam.getMember(1).isFree() + " Energia: " + GameDefault.playerTeam.getMember(1).getEnergy());
                System.out.println(GameDefault.playerTeam.getMember(2).getName() + ": Attack: " + GameDefault.playerTeam.getMember(2).getAttackpoints() + " Defense: " + GameDefault.playerTeam.getMember(2).getDefensepoints() + " Free? " + GameDefault.playerTeam.getMember(2).isFree() + " Energia: " + GameDefault.playerTeam.getMember(2).getEnergy());
            }
            
            if(GameDefault.currentChar != null){
                System.out.println("Digite 1 para tarefa, 2 para comprar, 3 para vender:");
                choice = scanner.nextInt();
                
                if(choice == 0) break;
                else if(choice == 1){
                    System.out.println("Digite:");
                    System.out.println("1 lutar, 2 caçar, 3 meditar, 4 minerar, 5 descançar, 6 domar, 7 treinar");
                    choice = scanner.nextInt();
                    
                    if(choice == 0) break;
                    else if(choice == 1){
                        GameDefault.goFight();
                    }
                    else if(choice == 2){
                        GameDefault.goHunt(animal);
                    }
                    else if(choice == 3){
                        GameDefault.goMeditate();
                    }
                    else if(choice == 4){
                        GameDefault.goMine();
                    }
                    else if(choice == 5){
                        GameDefault.goRest();
                    }
                    else if(choice == 6){
                        GameDefault.goTame(wolf);
                    }
                    else if(choice == 7){
                        GameDefault.goTrain(run);
                    }
                }
                else if(choice == 2){
                    GameDefault.genBuyList();
                    System.out.println("Loja:");
                    for(int i=0; i<GameDefault.auxItemVector.size(); i++) System.out.println(i + " - " + GameDefault.auxItemVector.get(i).getName() + " : $" + GameDefault.auxItemVector.get(i).getPrice());
                    choice = scanner.nextInt();
                    GameDefault.buyItem(choice);
                }
                else if(choice == 3){
                    GameDefault.genSellList();
                    System.out.println("Inventário:");
                    for(int i=0; i<GameDefault.auxItemVector.size(); i++) System.out.println(i + " - " + GameDefault.auxItemVector.get(i).getName() + " : $" + GameDefault.auxItemVector.get(i).getSellPrice());
                    choice = scanner.nextInt();
                    GameDefault.sellItem(choice);
                }
            }
        }
    
    }
}
