//A classe game default resume a forma de usar as classes 
//disponíveis, na forma de jogo desejado.
//A ideia é que podem ser criadas várias classes do tipo para
//utilizar a estrutura criada no estilo de jogo desejado.
package game;

import static game.Color.*;
import static game.ItemType.*;
import java.util.ArrayList;

public class GameDefault {

    // O jogador tem um time. No entanto pode manipular um personagem desse time
    //por vez.
    public static Character currentChar;
    
    //A loja do jogo é gerada automaticamete para que nunca faltem ítens
    public static ArrayList<Item> Store;
    
    //Nesse modo de jogo, um time tem até 4 personagens.
    //Isso significa 4 threads rodando ao mesmo tempo.
    public static charTask[] runningTasks = {null, null, null, null};
    
    //O time principal do jogo
    public static Team<Character> playerTeam;

    //A classe GameDefault retorna por meio da string message
    //o que se passa na execução do jogo
    private static String message;
    
    //Em várias situações é necessário um vetor de ítens
    public static ArrayList<Item> auxItemVector;
    
    //Listas de nomes para o jogo
    private static ArrayList<String> armorNames;
    private static ArrayList<String> weaponNames;
    private static ArrayList<String> potionNames;
    private static ArrayList<String> personNames;
    private static ArrayList<String> monsterNames;
    private static ArrayList<String> petNames;

//================================================================\\
    //Bases
    public static void setGameDefault() {
        GameDefault.playerTeam = new Team<Character>("My Team", blue, 4);
        GameDefault.currentChar = null;
        updateMessage("Started");
        auxItemVector = new ArrayList<>();
        generateLists();
        AssembleStore();
    }

    private static void updateMessage(String message) {
        GameDefault.message = message;
    }
    
    public static String getMessage(){
        return message;
    }
    
    private static void AssembleStore(){
        int i;
        for(i=0; i<10; i++)
            Store.add(generateStoreItem(i%4));
    }

    //Botões player
    //buttonN vai de 0 a 3, representando os 4 chars disponíveis
    public static void selectChar(int buttonN) {
        playerTeam.clearDead();
        if (playerTeam.teamSize() <= buttonN) {
            updateMessage("Esse personagem não foi adicionado");
            return;
        }
        currentChar = playerTeam.getMember(buttonN);
        message = "Selecionado personagem " + currentChar.getName();
    }

    //Botão Team
    public static void teamButton() {
    }

//================================================================\\
    //Adicionar personagem
    //add thief
    public static void addThief() {
        playerTeam.clearDead();
        if (playerTeam.teamSize() >= 4) {
            updateMessage("Time cheio");
            return;
        }

        Thief newMember = new Thief(getPersonName(), 0);
        playerTeam.addMember(newMember);

        updateMessage("Adiocionado Ladrao" + newMember.getName());
    }

    //add wizard
    public static void addWizard() {
        playerTeam.clearDead();
        if (playerTeam.teamSize() >= 4) {
            updateMessage("Time cheio");
            return;
        }

        Wizard newMember = new Wizard(getPersonName(), 0);
        playerTeam.addMember(newMember);
        updateMessage("Adiocionado Mago " + newMember.getName());
    }

    //add knight
    public static void addKnight() {
        if (playerTeam.teamSize() >= 4) {
            updateMessage("Time cheio");
            return;
        }

        Knight newMember = new Knight(getPersonName(), 0);
        playerTeam.addMember(newMember);

        updateMessage("Adiocionado Cavaleiro " + newMember.getName());
    }

//================================================================\\
    //Setar cores
    //setar cor
    public static void setBlue() {
        playerTeam.setColor(blue);
        updateMessage("Seu time agora é azul");
    }

    public static void setRed() {
        playerTeam.setColor(red);
        updateMessage("Seu time agora é vermelho");
    }

    public static void setGreen() {
        playerTeam.setColor(green);
        updateMessage("Seu time agora é verde");
    }

    public static void setYellow() {
        playerTeam.setColor(yellow);
        updateMessage("Seu time agora é amarelo");
    }

    public static void setWhite() {
        playerTeam.setColor(white);
        updateMessage("Seu time agora é branco");
    }

    public static void setBlack() {
        playerTeam.setColor(black);
        updateMessage("Seu time agora é preto");
    }

//================================================================\\
    //Criação de listas
    //Criar no menu de vendas uma quantidade fixa de botões (maxInvSize)
    //Para chamar as funções auxiliares (sellItem, buyItem e combItem)
    //gerar array compras possíveis
    //checa se o preço é acessível
    public static void genBuyList() {
        int i;

        auxItemVector.clear();
        
        if(currentChar == null) return;

        if (currentChar.getItemsNumber() == 10) {
            updateMessage("Inventario cheio");
            return;
        }

        for (i = 0; i < Store.size(); i++) {
            if (Store.get(i).getPrice() <= currentChar.getCharacterGold()) {
                auxItemVector.add(Store.get(i));
            }
        }
    }

    //gerar array vendas
    public static void genSellList() {
        int i;

        auxItemVector.clear();
        
        if(currentChar == null) return;

        for (i = 0; i < currentChar.getItemsNumber(); i++) {
            auxItemVector.add(currentChar.getItem(i));
        }
    }
    
    //Gera array com defesas disponíveis no inventário do personagem selecionado
    //para que o jogador possa escolher equipar um deles
    public static void genArmorList() {
        int i;

        auxItemVector.clear();
        
        if(currentChar == null) return;

        for (i = 0; i < currentChar.getItemsNumber(); i++) {
            if(currentChar.getItem(i).getType() == armor)
                auxItemVector.add(currentChar.getItem(i));
        }
    }
    
    //Mesma ideia do anterior
    public static void genWeaponList() {
        int i;

        auxItemVector.clear();
        
        if(currentChar == null) return;

        for (i = 0; i < currentChar.getItemsNumber(); i++) {
            if(currentChar.getItem(i).getType() == weapon)
                auxItemVector.add(currentChar.getItem(i));
        }
    }
    
    //Mesma ideia do anterior. Para poções
    public static void genUseList() {
        int i;

        auxItemVector.clear();
        
        if(currentChar == null) return;

        for (i = 0; i < currentChar.getItemsNumber(); i++) {
            if((currentChar.getItem(i).getType() == potion))
                auxItemVector.add(currentChar.getItem(i));
        }
    }

    //gerar arry combos
    //Caso uma combinação seja possível, adiciona no ArrayList
    //E o player tenha dinheiro para compra-la
    //Primeiro o combiando, depois os dois originais

    public static void genComboList() {
        updateMessage("Ainda não disponível ç.ç");
    }

//================================================================\\
    //Base compras e vendas
    //a classe que cuida do Jframe deve conseguir processar internamente
    //a qual item o botão clicado corresponde.
    //vender
    public static void sellItem(int itemN) {
        if(auxItemVector.size() <= itemN) return;
        if(currentChar == null) return;

        currentChar.sellItem(auxItemVector.get(itemN).getName());

        updateMessage("Item: " + auxItemVector.get(itemN).getName() + " vendido");
    }

    //comprar

    public static void buyItem(int itemN) {
        if(auxItemVector.size() <= itemN) return;
        if(currentChar == null) return;

        currentChar.buyItem(auxItemVector.get(itemN));
        Store.remove(auxItemVector.get(itemN).getName());
        
        Store.add(generateStoreItem((int) (Math.random()*3.9)));

        updateMessage("Item: " + auxItemVector.get(itemN).getName() + " comprado");
    }

    /*combinar

    public static void combItem(int itemN) {
        Item originalA, originalB, combo;
        combo = auxItemVector.get(itemN * 3);
        originalA = auxItemVector.get(itemN * 3 + 1);
        originalB = auxItemVector.get(itemN * 3 + 2);

        currentChar.sellItem(originalA.getName());
        currentChar.sellItem(originalB.getName());
        currentChar.buyItem(combo);

        updateMessage("Items: " + originalA.getName() + " e " + originalB.getName() + " combinados");
    }*/

//================================================================\\
    //Tarefas
    //caçar
    public static void goHunt(HuntType type) {
        if(currentChar == null){
            updateMessage("Selecione um personagem.");
            return;
        }
        
        Hunt nTask = new Hunt(currentChar, type);

        runningTasks[currentChar.getID()] = nTask;

        updateMessage("Personagem " + currentChar.getName() + " foi caçar");
        currentChar = null;

        nTask.start();
    }

    //lutar

    public static void goFight() {
        if(currentChar == null){
            updateMessage("Selecione um personagem.");
            return;
        }
        
        Fight nTask = new Fight(currentChar);

        runningTasks[currentChar.getID()] = nTask;

        updateMessage("Personagem " + currentChar.getName() + " foi lutar");
        currentChar = null;

        nTask.start();
    }

    //meditar

    public static void goMeditate() {
        if(currentChar == null){
            updateMessage("Selecione um personagem.");
            return;
        }
        
        int period = 100;

        Meditate nTask = new Meditate(currentChar, period);

        runningTasks[currentChar.getID()] = nTask;

        updateMessage("Personagem " + currentChar.getName() + " foi meditar");
        currentChar = null;

        nTask.start();
    }

    //minerar

    public static void goMine() {
        if(currentChar == null){
            updateMessage("Selecione um personagem.");
            return;
        }
        
        int period = 100;

        Mine nTask = new Mine(currentChar, period);

        runningTasks[currentChar.getID()] = nTask;

        updateMessage("Personagem " + currentChar.getName() + " foi minerar");
        currentChar = null;

        nTask.start();
    }

    //descansar

    public static void goRest() {
        if(currentChar == null){
            updateMessage("Selecione um personagem.");
            return;
        }
        
        Rest nTask = new Rest(currentChar);

        runningTasks[currentChar.getID()] = nTask;

        updateMessage("Personagem " + currentChar.getName() + " foi descançar");
        currentChar = null;

        nTask.start();
    }

    //domar

    public static void goTame(PetType type) {
        if(currentChar == null){
            updateMessage("Selecione um personagem.");
            return;
        }
        
        int period = 100;

        Tame nTask = new Tame(currentChar, period, type);

        runningTasks[currentChar.getID()] = nTask;

        updateMessage("Personagem " + currentChar.getName() + " foi domar animais");
        currentChar = null;

        nTask.start();
    }

    //treinar

    public static void goTrain(TrainingType type) {
        if(currentChar == null){
            updateMessage("Selecione um personagem.");
            return;
        }
        
        int period = 100;

        Train nTask = new Train(currentChar, period, type);

        runningTasks[currentChar.getID()] = nTask;

        updateMessage("Personagem " + currentChar.getName() + " foi treinar");
        currentChar = null;

        nTask.start();
    }

    public static void stopTask(int pos) {

    }

//================================================================\\
    //Listas
    
    //Manipuladores.
    //Essas funções retornam o primeiro elemento da fila, e
    //rodam-na. Assim, tendo n elementos, as primeiras n chamadas das
    //funções terão retorno diferente
    
    public static String getArmorName(){
        String name;
        
        name = armorNames.get(0);
        armorNames.remove(0);
        armorNames.add(name);
        
        return name;
    }
    
    public static String getWeaponName(){
        String name;
        
        name = weaponNames.get(0);
        weaponNames.remove(0);
        weaponNames.add(name);
        
        return name;
    }
    
    public static String getPotionName(){
        String name;
        
        name = potionNames.get(0);
        potionNames.remove(0);
        potionNames.add(name);
        
        return name;
    }
    
    public static String getPersonName(){
        String name;
        
        name = personNames.get(0);
        personNames.remove(0);
        personNames.add(name);
        
        return name;
    }
    
    public static String getMonsterName(){
        String name;
        
        name = monsterNames.get(0);
        monsterNames.remove(0);
        monsterNames.add(name);
        
        return name;
    }
    
    public static String getPetName(){
        String name;
        
        name = petNames.get(0);
        petNames.remove(0);
        petNames.add(name);
        
        return name;
    }
    
    //Função usada para repor os ítens da loja conforme são comprados.
    //Também usada para gerar os drops aleatórios dos enemies.
    public static Item generateStoreItem(int type){
        if(type == 0) return new Armor(getArmorName(), Math.random(), (int) (Math.random()*20));
        else if(type == 1) return new Weapon(getWeaponName(), Math.random(), (int) (Math.random()*20));
        else if(type == 2) return new ManaPotion(getPotionName(), (int) (Math.random()*20));
        else if(type == 3) return new HealthPotion(getPotionName(), (int) (Math.random()*20));
        
        return null;
    }
    
    //Criatividade não é o forte
    private static void generateLists(){
        Store = new ArrayList<>();
        armorNames = new ArrayList<>();
        weaponNames = new ArrayList<>();
        potionNames = new ArrayList<>();
        personNames = new ArrayList<>();
        monsterNames = new ArrayList<>();
        petNames = new ArrayList<>();
        
        armorNames.add("Escudo do capeta");
        armorNames.add("Capacete de ouro");
        armorNames.add("Botas estilozas");
        armorNames.add("Protetor solar");
        armorNames.add("Caneleiras profissionais");
        
        weaponNames.add("Espada flamejante");
        weaponNames.add("Lança envenenada");
        weaponNames.add("Flecha de aço");
        weaponNames.add("Cajado mágico");
        weaponNames.add("Machete");
        weaponNames.add("Espingarda");
        
        potionNames.add("Caldo de morcego");
        potionNames.add("Fermentado de testículo de boi");
        potionNames.add("Creme de cobra");
        potionNames.add("Café");
        potionNames.add("Leite com manga");
        
        personNames.add("João Alfredo");
        personNames.add("Seu Irineu");
        personNames.add("Chico");
        personNames.add("Adenilson");
        personNames.add("Ana");
        
        monsterNames.add("Destruidor de ossos");
        monsterNames.add("Coisa ruim");
        monsterNames.add("Frank");
        monsterNames.add("Kpta");
        monsterNames.add("Nosferatu");
        
        petNames.add("Rex");
        petNames.add("Lua");
        petNames.add("Peppa");
        petNames.add("Dogs");
        petNames.add("Nick");
        }
}
