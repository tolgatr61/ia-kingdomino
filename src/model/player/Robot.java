package model.player;

import model.Kingdomino;
import model.ia.*;
import model.plateau.actions.IPut;

public class Robot extends AbstractPlayer implements Player {

    private Strategy strategy;
    private IPut lastAction;

    public Robot(Kingdomino game) {
        super(game);
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public boolean play() throws Exception {
        if (!(this.strategy == null)) {
            if (!(this.getPlateau().actionsPossible(this.getGame().getPick()) == null)
                    && this.getPlateau().actionsPossible(this.getGame().getPick()).size() > 0) {
                IPut action = this.strategy.resolution(this.getPlateau().actionsPossible(this.getGame().getPick()));
                this.lastAction = action;
                getGame().move(action);
                System.out.println("action choisi : " + action);
                return true;
            } else {
                return false;
            }
        } else {
            throw new Exception("Le robot n'a pas de statégie");
        }
    }

    @Override
    public IPut getLastAction() {
        return this.lastAction;
    }

    @Override
    public String toString() {
        return "Robot " + this.getId() + " utilisant une strategie " + this.strategy;
    }
}
