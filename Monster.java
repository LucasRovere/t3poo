/*
 A classe monster é um tipo de criatura feita para batalhar com o
 personagem.
 */
package game;
//package item;
//package character;
//package tasks;

public class Monster implements Creature {

    private int attackPoints, defensePoints, HP;
    private String name;
    private RaceType race;
    private int ID;

    public Monster(String name, RaceType race, int attackPoints, int defensePoints) {
        this.name = name;
        this.race = race;
        this.attackPoints = attackPoints;
        this.defensePoints = defensePoints;
        ID = -1;
    }

    public Monster(String name, RaceType race, int battlePoints) {
        int attackPoints, defensePoints;

        this.name = name;
        this.race = race;

        attackPoints = (int) ((Math.random() + 1) * battlePoints / 2);
        defensePoints = (int) ((Math.random() + 1) * battlePoints / 2);

        this.attackPoints = attackPoints;
        this.defensePoints = defensePoints;
        ID = -1;
    }

    @Override
    public void setID(int ID) {
        this.ID = ID;
    }
    
    @Override
    public int getID(){
        return ID;
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

    public RaceType getRace() {
        return race;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public void setRace(RaceType race) {
        this.race = race;
    }

    @Override
    public void addHP(int v) {
        HP += v;
    }

    @Override
    public void attackCharacter(Character opponent) {
        double chance;
        chance = 10;
        double rnd;
        int extradmg, totaldmg = 0;

        rnd = Math.random() * 100;

        if (rnd < chance) {
            return;
        }

        chance = 1;

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
}
