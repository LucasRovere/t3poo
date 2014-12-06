all:
	javac -Xlint Armor.java Character.java Color.java Creature.java Enemy.java Fight.java Game.java GameDefault.java HealthPotion.java Hunt.java HuntType.java Inventory.java Item.java ItemType.java Knight.java ManaPotion.java Meditate.java Mine.java Monster.java Pet.java PetType.java Potion.java RaceType.java Rest.java Tame.java Team.java Thief.java Train.java TrainingType.java Weapon.java Wizard.java charTask.java

run:
	java Game.class

clear:
	rm Armor.class Character.class Color.class Creature.class Enemy.class Fight.class Game.class GameDefault.class HealthPotion.class Hunt.class HuntType.class Inventory.class Item.class ItemType.class Knight.class ManaPotion.class Meditate.class Mine.class Monster.class Pet.class PetType.class Potion.class RaceType.class Rest.class Tame.class Team.class Thief.class Train.class TrainingType.class Weapon.class Wizard.class charTask.class
