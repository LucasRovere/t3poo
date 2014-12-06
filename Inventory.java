/*
Um inventário é uma estrutura que guarda itens para um personagem.
*/
package game;
//package item;
//package character;
//package tasks;

import java.util.ArrayList;
import java.util.Iterator;

public class Inventory {

    private int spaces;
    private double gold;
    private ArrayList<Item> items;
    private ArrayList<Integer> itemstat;
    private int equipedArmor, equipedWeapon1, equipedWeapon2;

    // Construtor
    public Inventory() {
        spaces = 5;
        gold = 0;
        equipedArmor = -1;
        equipedWeapon1 = -1;
        equipedWeapon2 = -1;
        items = new ArrayList<>();
        itemstat = new ArrayList<>();
    }
    
    public Inventory(int spaces) {
        gold = 0;
        equipedArmor = -1;
        equipedWeapon1 = -1;
        equipedWeapon2 = -1;
        items = new ArrayList<>();
        itemstat = new ArrayList<>();
        this.spaces = spaces;
    }

    public double getTotalGold() {
        return gold;
    }

    // Retorna a quantidade de espacos disponiveis nesse inventario
    public int getAvailableSpace() {
        if(items == null) return spaces;
        return spaces - items.size();
    }
    
    public Iterator getIterator(){
        return items.iterator();
    }

    public void spendGold(double qnt) {
        gold -= qnt;
    }

    public void earnGold(double qnt) {
        gold += qnt;
    }

    public void setSpaces(int qnt) {
        if (qnt > items.size()) {
            spaces = qnt;
        }
    }

    // Busca e retorna o item com base no nome
    public Item searchItem(String nome) {
        int i;
        for (i = 0; i < items.size(); i++) {
            if (nome == null ? (items.get(i)).getName() == null : nome.equals((items.get(i)).getName())) {
                return items.get(i);
            }
        }

        return null;
    }

    // Busca e retorna o item com base na posicao
    public Item searchItem(int pos) {
        if (items.size() > pos) {
            return items.get(pos);
        }
        return null;
    }

    // Adiciona um item no final do iventario
    public void insertItem(Item novo) {
        if (this.getAvailableSpace() == 0) {
            return;
        }

        if (novo != null) {
            items.add(novo);
            itemstat.add(0);
        }
    }

    // Remove um item com base no nome, caso ele exista
    public void removeItem(String nome) {
        int i;
        for (i = 0; i < items.size(); i++) {
            if (nome.equals(items.get(i).getName())) {
                items.remove(i);
                itemstat.remove(i);
                if (i == equipedArmor) {
                    equipedArmor = -1;
                } else if (i == equipedWeapon1) {
                    equipedWeapon1 = -1;
                } else if (i == equipedWeapon2) {
                    equipedWeapon2 = -1;
                }
                break;
            }
        }
    }

    // Remove um item com base na sua posicao
    public void removeItem(int pos) {
        if (pos > items.size()) {
            return;
        }

        if (pos == equipedArmor) {
            equipedArmor = -1;
        } else if (pos == equipedWeapon1) {
            equipedWeapon1 = -1;
        } else if (pos == equipedWeapon1) {
            equipedWeapon1 = -1;
        }

        items.remove(pos - 1);
        itemstat.remove(pos - 1);
    }

    public int getUsedspaces() {
        return items.size();
    }

    public void equipArmor(String name) {
        int i;
        for (i = 0; i < items.size(); i++) {
            if (name.equals((items.get(i)).getName())) {
                equipedArmor = i;
                itemstat.set(equipedArmor, 1);
                break;
            }
        }
    }

    public void equipWeapons(String name1, String name2) {
        int i;
        for (i = 0; i < items.size(); i++) {
            if (name1.equals((items.get(i)).getName())) {
                equipedWeapon1 = i;
                itemstat.set(equipedWeapon1, 1);
                break;
            }
        }

        for (i = 0; i < items.size(); i++) {
            if (name2.equals((items.get(i)).getName())) {
                equipedWeapon2 = i;
                itemstat.set(equipedWeapon2, 1);
                break;
            }
        }
    }

    public Armor getEquipedArmor() {
        if (equipedArmor == -1) {
            return null;
        }
        return (Armor) items.get(equipedArmor);
    }

    public Weapon getEquipedWeapon1() {
        if (equipedWeapon1 == -1) {
            return null;
        }
        return (Weapon) items.get(equipedWeapon1);
    }

    public Weapon getEquipedWeapon2() {
        if (equipedWeapon2 == -1) {
            return null;
        }
        return (Weapon) items.get(equipedWeapon2);
    }
}
