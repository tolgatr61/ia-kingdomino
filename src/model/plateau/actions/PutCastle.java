package model.plateau.actions;

import model.pieces.cases.Castle;
import model.pieces.domino.Domino;
import model.plateau.Grille;

public class PutCastle implements IPut {

    private Grille grille;
    private final Castle castle;
    private int[] position;

    /***
     *
     * @param grille La grille du jeu.
     * @param castle Le château à ajouter.
     * @param position La position sur laquelle on l'ajoute.
     */
    public PutCastle(Grille grille, Castle castle, int[] position) {
        this.grille = grille;
        this.castle = castle;
        this.position = position;
    }

    @Override
    public void put() {
        int indexX = position[0];
        int indexY = position[1];

        if (isValid()) {
            castle.setPosition(new int[]{indexX, indexY});
            grille.setCastle(castle); // set la case du Chateau + on garde en mémoire la chateau
        } else {
            System.out.println("Attention ! aucun château n'existait, un a été mis par défaut au milieu de la grille.");
            position = new int[]{grille.getNbLigne() / 2, grille.getNbColonne() / 2};
            put();
        }
    }

    // Pas besoin de vérifier si un domino est présent, le chateau est la première pièce.
    @Override
    public boolean isValid() {
        return !grille.isOutofBound(position[0], position[1]);
    }

    @Override
    public void setGrille(Grille grille) {
        this.grille = grille;
    }

    @Override
    public Domino getDomino() {
        return null;
    }

    @Override
    public String toString() {
        return this.castle.toString() + " " +
                this.position[0] + " "
                + this.position[1];
    }


}
