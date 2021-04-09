package model.plateau.actions;

import model.pieces.domino.Domino;
import model.plateau.Grille;

public interface IPut {

    /***
     * put Mettre une pièce.
     */
    public void put();

    /***
     *
     * @return Valide la possibilité de mettre une pièce.
     */
    public boolean isValid();

    void setGrille(Grille grille);

    Domino getDomino();
}
