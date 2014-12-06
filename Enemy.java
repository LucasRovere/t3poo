
/*
 A classe enemy foi criada para que durante a execução do jogo, um
 inimigo simples possa ser criado para batalhar com um personagem.
 Sem necessidade de atributos desnecessários para essa aplicação.
 Isso deixa a classe Character mais direcionada para personagens controlados pelo
 jogador huamano.
 */
package game;
//package item;
//package character;
//package tasks;

public class Enemy implements Creature {

    private int attackPoints, defensePoints, HP;
    private String name;
    private int ID;

    public Enemy(String name, int battlePoints) {
        int attackPoints, defensePoints;

        this.name = name;

        attackPoints = (int) ((Math.random() + 1) * battlePoints / 2);
        defensePoints = (int) ((Math.random() + 1) * battlePoints / 2);

        this.attackPoints = attackPoints;
        this.defensePoints = defensePoints;
        HP = 100;

        ID = -1;
    }

    @Override
    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getAttackpoints() {
        return attackPoints;
    }

    @Override
    public int getDefensepoints() {
        return defensePoints;
    }

    @Override
    public int getHP() {
        return HP;
    }
    
    @Override
    public int getID(){
        return ID;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void addHP(int v) {
        HP += v;
    }

    @Override
    public void attackCharacter(Character opponent) {
        double chance;
        chance = 0.1;
        double rnd;
        int extradmg, totaldmg = 0;

        rnd = Math.random();

        if (rnd < chance) {
            return;
        }

        chance = 0.001;

        if (rnd > chance) {
            opponent.addHP(0 - opponent.getHP());
            return;
        }

        extradmg = (int) (Math.random() * 10);
        extradmg -= 5;

        totaldmg += opponent.getDefensepoints();
        totaldmg -= this.getAttackpoints();
        totaldmg += extradmg;
        if (totaldmg < 0) {
            totaldmg = 0;
        }
        opponent.addHP(totaldmg);
    }

    public Item lootDrop() {
        if (HP > 0) {
            return null;
        }

        return GameDefault.generateStoreItem((int) (Math.random()*4));
    }
}
