/*
 Classe abstrata que generaliza e define o funcionamento das ocupações dos personagens.
 Basicamente, uma ocupação tem sempre que funcionar da mesma forma:
 A função act() contém toda semântica da tarefa. Ela retorna o status da execução
 na forma de um inteiro (condition).
 As funções modifyAspects e updateStatus devem ser capazes de interpretar essa
 condição e fazer o necessário.
 Condições > 0 servem para definir o que está se passando na execução da tarefa.
 Condições <= 0 servem para definir que a tarefa acabou, e o motivo de ter acabado.

Ps: Peço desculpas se algumas tarefas estão desproporcionalmente vantajosas ou desvantajosas.
Para conseguir acertar tudo, realmente não deu tempo de deixar o jogo "justo"
 */
package game;
//package item;
//package character;

import java.util.logging.Level;
import java.util.logging.Logger;

//package tasks;
public abstract class charTask extends Thread {

    protected String status;
    protected Character active;
    private int position;

    public charTask(Character active) {
        this.active = active;
        active.assignTask();
        this.position = position;
    }

    @Override
    public void run() {
        int condition;
        condition = 1;

        updateStatus(1);

        while (condition > 0) {
            condition = act();
            modifyAspects(condition);
            updateStatus(condition);
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(charTask.class.getName()).log(Level.SEVERE, null, ex);
        }

        active.stopTask();

    }

    abstract int act();

    abstract void modifyAspects(int c);

    abstract void updateStatus(int c);

    public String getStatus() {
        return status;
    }
}
