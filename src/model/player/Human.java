package model.player;

import model.Kingdomino;
import control.InputHuman;
import model.plateau.actions.IPut;

public class Human extends AbstractPlayer implements Player {

    private IPut lastAction;

    public Human(Kingdomino game) {
        super(game);
    }


    //-> ne pas modifier des choses dans games
    public boolean play() {
        IPut action = InputHuman.chooseAction(this);
        this.lastAction = action;
        getGame().move(action);
        return true;
    }

    @Override
    public IPut getLastAction() {
        return this.lastAction;
    }

    @Override
    public String toString() {
        return "Humain " + this.getId();
    }
}
