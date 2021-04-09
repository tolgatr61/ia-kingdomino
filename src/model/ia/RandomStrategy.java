package model.ia;

import model.Kingdomino;
import model.plateau.actions.IPut;

import java.util.ArrayList;


public class RandomStrategy implements Strategy {

    private Kingdomino game;

    public RandomStrategy(Kingdomino game) {
        this.game = game;
    }


    @Override
    public IPut resolution(ArrayList<IPut> actions) {
        return actions.get((int) (Math.random() * actions.size()));
    }


    @Override
    public String toString() {
        return "Random";
    }
}
