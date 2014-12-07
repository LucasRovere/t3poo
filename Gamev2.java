
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

		System.out.println("Lucas Marques Rovere - 8139750");
		System.out.println("Rodrigo das Neves Bernardi - 8066395");
        
        GameDefault.setGameDefault();
        GameDefault.addKnight();
        GameDefault.addThief();
        GameDefault.addWizard();
        
        int choice;
        Scanner scanner = new Scanner(System.in);
        
        //Como tudo de importante para ser feito no jogo está implementado na
        //classe GameDefault, fica para a main a função de rodar o loop
        //principal do jogo e criar uma interface de interação com o usuário.
        //Infelizmente a interação precisou ser feita a base de texto. sdds ICC 1
        while(true){
            System.out.println(GameDefault.playerTeam.toString());
            System.out.println("Seu time tem " + GameDefault.playerTeam.teamSize() + " membros");
            if(GameDefault.currentChar != null) System.out.println("Selecionado: " + GameDefault.currentChar.getName());
            System.out.println("1 Para selecionar personagem.");
            System.out.println("2 Para ver stats dos personagens.");
            System.out.println("30 para mudar nome do time, 31 para mudar cor do time.");
            if(GameDefault.playerTeam.teamSize() < 4)System.out.println("4 para adicionar personagem.");
            choice = scanner.nextInt();
            
            System.out.println();
            
            if(choice == 0) {
                break;
            }
            else if(choice == 1){
                System.out.println("Qual personagem deseja selecionar?");
                choice = scanner.nextInt();
                GameDefault.selectChar(choice);
                System.out.println(GameDefault.getMessage());
            }
            else if(choice == 2){
                GameDefault.currentChar = null;
                for(int i=0; i<GameDefault.playerTeam.teamSize(); i++)System.out.println(GameDefault.playerTeam.getMember(i).getName() + ": Attack: " + GameDefault.playerTeam.getMember(i).getAttackpoints() + " Defense: " + GameDefault.playerTeam.getMember(i).getDefensepoints() + " Free? " + GameDefault.playerTeam.getMember(i).isFree() + " Energia: " + GameDefault.playerTeam.getMember(i).getEnergy());
            }
            else if(choice == 30){
                GameDefault.currentChar = null;
                System.out.println("Digite o novo nome do seu time:");
                String nome = scanner.next();
                GameDefault.playerTeam.setName(nome);
            }
            else if(choice == 31){
                GameDefault.currentChar = null;
                System.out.println("1 azul, 2 vermelho, 3 verde, 4 amarelo, 5 branco, 6 preto");
                choice = scanner.nextInt();
                if(choice == 1) GameDefault.playerTeam.setColor(blue);
                else if(choice == 2) GameDefault.playerTeam.setColor(red);
                else if(choice == 3) GameDefault.playerTeam.setColor(green);
                else if(choice == 4) GameDefault.playerTeam.setColor(yellow);
                else if(choice == 5) GameDefault.playerTeam.setColor(white);
                else if(choice == 6) GameDefault.playerTeam.setColor(black);
            }
            else if(choice == 4){
                GameDefault.currentChar = null;
                System.out.println("1 para Knight, 2 para Thief, 3 para Knight");
                choice = scanner.nextInt();
                if(choice == 1) GameDefault.addKnight();
                else if(choice == 2) GameDefault.addThief();
                else if(choice == 3) GameDefault.addWizard();
            }
            
            if(GameDefault.currentChar != null){
                System.out.println("Digite 0 para informações, 1 para tarefa, 2 para comprar, 3 para vender, 4 para equipar defesa, 5 para equipar arma, 6 para usar poção:");
                choice = scanner.nextInt();
                
                if(choice == 0){
                    System.out.println("Name: " + GameDefault.currentChar.getName());
                    System.out.println("HP: " + GameDefault.currentChar.getHP());
                    System.out.println("Max Stats: " + GameDefault.currentChar.getMaxStats());
                    System.out.println("Energy: " + GameDefault.currentChar.getEnergy());
                    System.out.println("Occupied? " + GameDefault.currentChar.isFree());
                    System.out.println("XP: " + GameDefault.currentChar.getXP());
                    System.out.println("Strenght: " + GameDefault.currentChar.getStrenght());
                    System.out.println("Speed: " + GameDefault.currentChar.getSpeed());
                    System.out.println("Dexterity: " + GameDefault.currentChar.getDexterity());
                    System.out.println("Constitution: " + GameDefault.currentChar.getConstitution());
                    System.out.println("Special: " + GameDefault.currentChar.getSpecial());
                    System.out.println("Attack Points: " + GameDefault.currentChar.getAttackpoints());
                    System.out.println("Defense Points: " + GameDefault.currentChar.getDefensepoints());
                    System.out.println("Equiped: " + GameDefault.currentChar.getEquiped());
                    if(GameDefault.currentChar.getPet() != null) System.out.println("Pet: " + GameDefault.currentChar.getPet().getName());
                }
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
                else if(choice == 4){
                    GameDefault.genArmorList();
                    System.out.println("Opções:");
                    for(int i=0; i<GameDefault.auxItemVector.size(); i++) System.out.println(i + " - " + GameDefault.auxItemVector.get(i).getName() + " : $" + GameDefault.auxItemVector.get(i).getSellPrice());
                    choice = scanner.nextInt();
                    if(choice < GameDefault.auxItemVector.size())
                        GameDefault.currentChar.equipArmor(GameDefault.auxItemVector.get(choice).getName());
                }
                else if(choice == 5){
                    GameDefault.genWeaponList();
                    System.out.println("Opções:");
                    for(int i=0; i<GameDefault.auxItemVector.size(); i++) System.out.println(i + " - " + GameDefault.auxItemVector.get(i).getName() + " : $" + GameDefault.auxItemVector.get(i).getSellPrice());
                    choice = scanner.nextInt();
                    if(choice < GameDefault.auxItemVector.size())
                        GameDefault.currentChar.equipArmor(GameDefault.auxItemVector.get(choice).getName());
                }
            }
            System.out.println();
        }
    
    }
}
