/*
A classe thief é um tipo de personagem que tem como especial stealth
*/

package game;
//package item;
//package character;
//package tasks;

public class Thief extends Character {

    protected int stealth;

    //metodos
    public Thief(String name, int stealth) {
        super(name);
        this.stealth = stealth;
    }

    @Override
    public void attackCharacter(Character opponent) {
        double chance, rnd;
        int extradmg, totaldmg = 0;

        rnd = Math.random() * 100;
        chance = 10 / XP;

        if (rnd < chance) {
            return;
        }

        chance = 2 * XP / 2;

        if (rnd > chance) {
            opponent.addHP(0 - opponent.getHP());
            return;
        }

        extradmg = (int) (Math.random() * 10);
        extradmg -= 5;

        totaldmg += opponent.getAttackpoints();
        totaldmg -= this.getDefensepoints();
        totaldmg += extradmg;
        if (totaldmg < 0) {
            totaldmg = 0;
        }
        opponent.addHP(0 - totaldmg);
    }

    @Override
    public void addSpecial(int qtd) {
        stealth += qtd;
    }
    
    @Override
    public int getSpecial(){
        return stealth;
    }

    // Modifica o metodo original da classe pai de acordo com a necessidade dessa classe.
    @Override
    public int getAttackpoints() {
        int points;

        points = super.getAttackpoints() + stealth;

        return points;
    }
    
    @Override
    public void attackMonster(Monster target) {
        target.addHP(target.getDefensepoints() - this.getAttackpoints());
    }

    @Override
    public void attackEnemy(Enemy target) {
        Item drop;
        target.addHP(target.getDefensepoints() - this.getAttackpoints());
        if(myItems.getAvailableSpace() > 0)
            if((drop = target.lootDrop()) != null)
                myItems.insertItem(drop);
    }
}
