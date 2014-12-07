package game;

import static game.ItemType.*;

public interface Item {
    
    //Deve retornar o nome do ítem. Todo ítem precisa ter um nome.
    public String getName();

    //Para saber em tempo de execução com qual tipo de ítem estamos lidando,
    //existe o enumerador ItemType
    public ItemType getType();

    //O preço do ítem é gerado por essa função com base em seus atributos.
    //Sendo assim, para gerar ítens automaticamente durante a execução,
    //não há preocupação se o preço é aceitável.
    public double getPrice();
    
    //Os ítens podem ser vendidos.
    //Para deixar mais "realista", essa função calcula um valor
    //depreciado dos ítens usados.
    public double getSellPrice();
    
    //Cada ítem tem um atributo especial diferente.
    //Action points é o nome genérico dado para melhorar
    //o polimorfismo.
    public int getActionPts();
}
