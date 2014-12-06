/*
Uma coleção de criaturas. Com metodos basicos para sua manipulação.
*/
package game;
//package item;
//package character;
//package tasks;

//import characters.Character;

import static game.Color.*;
import java.util.ArrayList;

class Team <T extends Creature> {

    private String name;
    private Color color;
    private int win;
    private int lose;
    private int draw;
    private ArrayList<T> members;
    private ArrayList<T> deadMembers;
    private ArrayList<Integer> freeID;
    private int size;
    private int maxSize;

    public Team(String name, Color color, int maxSize) {
        this.name = name;
        this.color = color;
        win = lose = draw = 0;
        size = 0;
        this.maxSize = maxSize;
        members = new ArrayList<>();
        deadMembers = new ArrayList<>();
        freeID = new ArrayList<>();
        for(int i=0; i<this.maxSize; i++) freeID.add(i);
    }
    
    void setColor(Color color){
        this.color = color;
    }
    
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        String cor = null;
        if (color == blue) {
            cor = " - blue";
        } else if (color == red) {
            cor = " - red";
        } else if (color == green) {
            cor = " - green";
        } else if (color == yellow) {
            cor = " - yellow";
        } else if (color == white) {
            cor = " - white";
        } else if (color == black) {
            cor = " - black";
        }

        return (name + cor);
    }

    public void resolveBattle(Team opteam) {
        if (getPoints() > opteam.getPoints()) {
            win++;
        } else if (getPoints() < opteam.getPoints()) {
            lose++;
        } else {
            draw++;
        }
    }

    public void addMember(T new_member) {
        if(size >= maxSize) return;
        members.add(new_member);
        new_member.setID(freeID.get(0));
        size++;
        freeID.remove(0);
    }

    public void removeMember(String name) {
        if(memberPosition(name) == -1) return;
        freeID.add(members.get(memberPosition(name)).getID());
        members.remove(memberPosition(name));
        size--;
    }

    private int memberPosition(String nome) {
        int i;
        for (i = 0; i < members.size(); i++) {
            if (nome.equals((members.get(i)).getName())) {
                return i;
            }
            return -1;
        }
        
        return -1;
    }

    public int getPoints() {
        int i;
        int points = 0;

        for (i = 0; i < members.size(); i++) {
            points += (members.get(i)).getHP();
        }

        points = points / (members.size() + clearDead());

        return points;
    }

    public int clearDead(){
        int i, dead;
        
        dead = 0;
        
        for(i = 0; i < members.size(); i++){
            if(members.get(i).getHP() <= 0){
                members.remove(i);
                dead++;
                size--;
            }
        }
        
        return dead;
    }
    
    public T getMember(int pos){
        if(pos>members.size()) return null;
        return members.get(pos);
    }
    
    public int teamSize(){
        return size;
    }
}
