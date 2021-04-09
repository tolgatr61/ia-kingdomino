package model.plateau.actions;

import model.pieces.domino.Domino;
import model.plateau.Grille;

public class PutSkipped implements IPut {
    @Override
    public void put() {

    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public void setGrille(Grille grille) {

    }

    @Override
    public Domino getDomino() {
        return null;
    }

    @Override
    public String toString() {
        return "[action skipped]";
    }
}
