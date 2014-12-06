/*
 Ajuda o personagem em ataques. E é muito fofinho :3
 */
package game;
//package item;
//package character;
//package tasks;

public class Pet implements Creature {

    private int attackPoints, defensePoints, HP;
    private String name;
    private PetType race;
    private int ID;

    public Pet(String name, PetType race, int attackPoints, int defensePoints) {
        this.name = name;
        this.race = race;
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

    public PetType getRace() {
        return race;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public void setRace(PetType race) {
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

    public void attackMonster(Monster opponent) {
        opponent.addHP(opponent.getDefensepoints() - this.getAttackpoints());
    }
}
