
/*
 Como há vários tipos de criaturas no jogo, com
 aplicações muito diferentes; a interface creature é
 simples e exige apenas estruturas muito básicas de uma criatura.
 Serve principalmente para generalizar a classe Team, que
 pode ser uma grupo de qualquer criatura do jogo.
 */
package game;
//package item;
//package character;
//package tasks;

public interface Creature {

    String getName();

    int getAttackpoints();

    int getDefensepoints();

    int getHP();

    void setName(String name);

    void addHP(int qtd);

    //Uma forma mais fácil de saber de qual criatura se trata
    void setID(int ID);

    void attackCharacter(Character opponent);
    
    int getID();

}
