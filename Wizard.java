/*
 A classe wizard é um tipo de personagem que tem como especial wisdom.
 */
package game;
//package item;
//package character;
//package tasks;

public class Wizard extends Character {

    protected int wisdom;

    public Wizard(String name, int wisdom) {
        super(name);
        this.wisdom = wisdom;
    }

    @Override
    public void attackCharacter(Character opponent) {
        double chance, rnd;
        int extradmg, totaldmg = 0;

        rnd = Math.random() * 100;
        chance = 10 / XP;

        // O ataque pode falhar
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

        // Os pontos totais de ataque sao somados
        totaldmg += opponent.getAttackpoints();
        totaldmg -= this.getDefensepoints();
        totaldmg += extradmg;
        if (totaldmg < 0) {
            totaldmg = 0;
        }
        opponent.addHP(0 - totaldmg);
    }

    public void addSpecial(int qtd) {
        wisdom += qtd;
    }

    public int getSpecial() {
        return wisdom;
    }

    // Modifica o metodo original da classe pai de acordo com a necessidade dessa classe.
    @Override
    public int getDefensepoints() {
        int points;

        points = super.getDefensepoints() + wisdom / 2;

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
