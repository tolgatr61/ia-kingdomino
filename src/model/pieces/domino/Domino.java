package model.pieces.domino;

import model.pieces.cases.Case;
import model.pieces.cases.ExtremiteDomino;

public class Domino {

    private final ExtremiteDomino extremiteGauche;
    private final ExtremiteDomino extremiteDroite;
    private boolean estPoser;
    private final int numeroDomino;
    private int[] position;

    /*** +
     *
     * @param extremiteGauche Extremité gauche du Domino.
     * @param extremiteDroite Extremité droite du Domino.
     * @param numeroDomino Le numéro du domino.
     */
    public Domino(ExtremiteDomino extremiteGauche, ExtremiteDomino extremiteDroite, int numeroDomino) {
        this.extremiteGauche = extremiteGauche;
        this.extremiteDroite = extremiteDroite;
        this.numeroDomino = numeroDomino;
        this.estPoser = false;
    }

    /***
     * Getters
     */
    public ExtremiteDomino getExtremiteGauche() {
        return extremiteGauche;
    }

    public ExtremiteDomino getExtremiteDroite() {
        return extremiteDroite;
    }

    public int getNumeroDomino() {
        return numeroDomino;
    }

    public int[] getPosition() {
        return position;
    }

    public boolean estPoser() {
        return estPoser;
    }

    /**
     * Setters
     */
    public void setEstPoser(boolean etat) {
        estPoser = etat;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "[" + this.getExtremiteGauche().getPaysage().getName() + " " +
                this.getExtremiteGauche().getPaysage().getNbCouronnes() +
                "|" + this.getExtremiteDroite().getPaysage().getName() + " " +
                this.getExtremiteDroite().getPaysage().getNbCouronnes() + "]";
    }

}