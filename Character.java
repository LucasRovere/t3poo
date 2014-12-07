/*
 A classe character é uma classe abstrata que contem a maior parte dos
 metodos necessários para um personagem do jogo.
 No entanto, foi decidido que personagens não poderiam ser genericos.
 Por isso não é possível instanciar um objeto character, sendo necessário escolher
 entre uma de suas especializações (classes filhas).
 */
package game;
//package character;

import static java.lang.Math.*;
import java.util.Iterator;

public abstract class Character implements Creature {

    private String alias;
    private int HP;
    private int MP;
    private int maxPoints;
    private int energy;
    private Pet pet;
    private boolean taskFree;
    private int ID;

    protected Inventory myItems;
    protected int XP;
    protected int strenght;
    protected int speed;
    protected int dexterity;
    protected int constitution;
    protected int realspeed;

    public Character(String alias) {
        this.alias = alias;
        HP = 100;
        XP = 1;
        MP = 0;
        strenght = 10;
        speed = 10;
        dexterity = 10;
        constitution = 10;
        realspeed = speed;
        maxPoints = 100;
        pet = null;
        energy = 100;
        taskFree = true;
        ID = -1;
        myItems = new Inventory();
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }
    
    public int getMaxStats(){
        return maxPoints;
    }
    
    public int getStrenght(){
        return strenght;
    }
    
    public int getSpeed(){
        return speed;
    }
    
    public int getConstitution(){
        return constitution;
    }
    
    public int getDexterity(){
        return dexterity;
    }
    
    //Retorna uma string com os nomes dos ítens equipados. A ideia
    //principal seria para interagir mais facilmente com um usuário
    public String getEquiped(){
        String equiped = "";
        if(myItems.getEquipedWeapon1() != null) equiped += myItems.getEquipedWeapon1().getName() + " ";
        if(myItems.getEquipedWeapon2() != null) equiped += myItems.getEquipedWeapon2().getName() + " ";
        if(myItems.getEquipedArmor() != null) equiped += myItems.getEquipedArmor().getName();
        
        return equiped;
    }

    public void addSpeed(int qtd) {
        speed += qtd;
        normalizeAspects();
    }

    public void addStrenght(int qtd) {
        strenght += qtd;
        normalizeAspects();
    }

    public void addDexterity(int qtd) {
        dexterity += qtd;
        normalizeAspects();
    }

    public void addConstitution(int qtd) {
        constitution += qtd;
        normalizeAspects();
    }

    public void addMaxPoints(int qtd) {
        maxPoints += qtd;
    }

    public void setEnergy(int v) {
        energy = v;
    }

    public void changePet(Pet newPet) {
        pet = newPet;
    }

    public int getEnergy() {
        return energy;
    }

    public int getXP() {
        return XP;
    }

    @Override
    public String getName() {
        return alias;
    }

    @Override
    public int getHP() {
        return HP;
    }

    public int getItemsNumber() {
        return myItems.getUsedspaces();
    }

    public Item getItem(int pos) {
        return myItems.searchItem(pos);
    }

    //Calcula uma parcela dos pontos de ataque de um personagem.
    //Algumas classes que herdam de Character podem modificar de alguma
    //forma essa conta. No entanto, em geral, esse método é a base.
    @Override
    public int getAttackpoints() {
        int i, points = 0;
        Weapon current;
        Armor current2;

        normalizeAspects();

        if ((current2 = myItems.getEquipedArmor()) != null) {
            realspeed = (int) (speed * sqrt(current2.getWeight()) / 10);
        }

        points += strenght * 5;
        points += dexterity * 3;
        points += realspeed * 2;

        points = points / 10;

        if ((current = myItems.getEquipedWeapon1()) != null) {
            points += current.getActionPts();
        }
        if ((current = myItems.getEquipedWeapon2()) != null) {
            points += current.getActionPts();
        }

        points = (points * XP) / 2;

        return points;
    }

    //A mesma ideia dos pontos de ataque
    @Override
    public int getDefensepoints() {
        int i, points = 0;
        Armor current;

        normalizeAspects();

        if ((current = myItems.getEquipedArmor()) != null) {
            realspeed = (int) (speed * sqrt(current.getWeight()) / 10);
        }

        points += constitution * 5;
        points += dexterity * 3;
        points += realspeed * 2;

        points = points / 10;

        if ((current = myItems.getEquipedArmor()) != null) {
            points += current.getActionPts();
        }

        points = (points * XP) / 3;

        return points;
    }

    //Para gerar inimigos mais ao nível do personagem,
    //retorna-se os battlepoints. Que dão uma base para gerar
    //enemies e monsters. (Ataque + Defesa (sem ítens e pets))
    public int getBattlePoints() {
        int points;
        points = 0;

        points += constitution * 5;
        points += dexterity * 6;
        points += speed * 4;
        points += strenght * 5;

        return points * XP / 60;
    }

    //Awn :3
    //Além disso eles podem ajudar o personagem num ataque
    public Pet getPet() {
        return pet;
    }

    @Override
    public void setName(String name) {
        this.alias = name;
    }

    @Override
    public abstract void attackCharacter(Character opponent);

    public abstract void attackMonster(Monster target);

    public abstract void attackEnemy(Enemy target);

    public abstract void addSpecial(int qtd);

    public abstract int getSpecial();

    public void addXP(int qtd) {
        XP += qtd;
    }

    @Override
    public void addHP(int qtd) {
        HP += qtd;
        if (HP < 0) {
            HP = 0;
        }
    }

    public void addMP(int qtd) {
        MP += qtd;
    }

    //Esse método facilita o acesso correto ao inventário.
    //Só é possível adicionar um ítem comprando-o.
    //Melhora, também, o encapsulamento do inventário.
    public void buyItem(Item it) {
        double price;

        price = it.getPrice();
        if (price > myItems.getTotalGold()) {
            return;
        }

        myItems.insertItem(it);
        myItems.spendGold(price);
    }

    public Item getInvpos(int pos) {
        return myItems.searchItem(pos);
    }

    public void sellItem(String name) {
        Item rem = myItems.searchItem(name);
        if (rem == null) {
            return;
        }

        myItems.earnGold(rem.getPrice());
        myItems.removeItem(name);
    }

    public double getCharacterGold() {
        return myItems.getTotalGold();
    }

    public void earnCharacterGold(double qtd) {
        myItems.earnGold(qtd);
    }

    public void equipArmor(String name) {
        myItems.equipArmor(name);
    }

    public void equipWeapons(String name1, String name2) {
        myItems.equipWeapons(name1, name2);
    }

    public void equipWeapon(String name) {
        myItems.equipWeapons(name, "");
    }

    //A soma de alguns atributos não pode exceder max points. No entanto
    //esse método permite que eles sejam modificados, e acerta o valor
    //de todos para manter a proporção desejada.
    private void normalizeAspects() {
        double fator = 0;
        double Dstrenght = strenght, Dspeed = speed, Ddexterity = dexterity, Dconstitution = constitution;

        fator += speed;
        fator += strenght;
        fator += dexterity;
        fator += constitution;

        if (fator <= maxPoints) {
            return;
        }

        fator = maxPoints / fator;

        Dspeed *= fator;
        Dstrenght *= fator;
        Ddexterity *= fator;
        Dconstitution *= fator;

        speed = (int) (Dspeed + 0.5);
        strenght = (int) (Dstrenght + 0.5);
        dexterity = (int) (Ddexterity + 0.5);
        constitution = (int) (Dconstitution + 0.5);
    }

    //Um personagem só pode fazer uma coisa de cada vez.
    void assignTask() {
        taskFree = false;
    }

    void stopTask() {
        taskFree = true;
    }

    boolean isFree() {
        return taskFree;
    }
}
