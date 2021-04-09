package model.plateau.actions;

import model.pieces.cases.Case;
import model.pieces.domino.Domino;
import model.plateau.Grille;

import java.util.HashSet;

public class PutDomino implements IPut {

    private Grille grille;
    private final Domino domino;
    private final String orientation;
    private final int[] position;

    /***
     *
     * @param grille La grille de jeu.
     * @param domino Le domino à ajouter.
     * @param orientation L'orientation dans laquelle on l'ajoute.
     * @param position La position à laquelle on l'ajoute.
     */
    public PutDomino(Grille grille, Domino domino, String orientation, int[] position) {
        this.grille = grille;
        this.domino = domino;
        this.orientation = orientation;
        this.position=position;
    }

    public void setGrille(Grille grille){
        this.grille=grille;
    }

    public Domino getDomino() {
        return domino;
    }

    @Override
    public void put() { // On adapte les extremités du domino en fonction de l'orientation (pour les cas à l'envers).

        if (isValid()) {
            this.domino.setPosition(this.position);
            grille.setDomino(domino, orientation);
            //System.out.println("Domino valide : " + toString());

        }else {
            System.out.println("Domino invalide : " + toString()); // L'action est invalide on remet l'orientation originale du domino (on la remet droit).
        }

    }

    @Override
    public boolean isValid() {
        if ((isDominoAdjacent() && dominoIsNotColliding())) {
            return true;
        }

        return false;
    }

    /***
     *
     * @return Booléen qui confirme l'adjacence ou non d'un domino en fonction des cases voisines trouvées.
     */
    public boolean isDominoAdjacent() {
        HashSet<Case> voisins = verifyAdjacence();
        return voisins.size() > 0;
    }

    /***
     *
     * @return Un ensemble de cases voisines vérifiant l'adjacence des cases du domino.
     */
    public HashSet<Case> verifyAdjacence() {

        int dX = this.position[0];
        int dY = this.position[1];

        HashSet<Case> casesVoisine = new HashSet<>();

        switch (orientation)
        {
            case "horizontal":
                searchAdjacence(dX, dY, casesVoisine, domino.getExtremiteDroite().getPaysage().getName());
                searchAdjacence(dX, dY - 1, casesVoisine, domino.getExtremiteGauche().getPaysage().getName());
                break;
            case "horizontalReversed":
                searchAdjacence(dX, dY, casesVoisine, domino.getExtremiteGauche().getPaysage().getName());
                searchAdjacence(dX, dY - 1, casesVoisine, domino.getExtremiteDroite().getPaysage().getName());
                break;
            case "vertical":
                searchAdjacence(dX, dY, casesVoisine, domino.getExtremiteDroite().getPaysage().getName());
                searchAdjacence(dX-1, dY, casesVoisine, domino.getExtremiteGauche().getPaysage().getName());
                break;
            case "verticalReversed":
                searchAdjacence(dX, dY, casesVoisine, domino.getExtremiteGauche().getPaysage().getName());
                searchAdjacence(dX-1, dY, casesVoisine, domino.getExtremiteDroite().getPaysage().getName());
                break;
        }

        return casesVoisine;
    }

    /***
     *
     * @param dX Index x de la recherche d'adjacence.
     * @param dY Index y de la recherche d'adjacence.
     * @param casesVoisine Cases voisine sauvegardés.
     * @param paysageName Le nom du paysage à vérifier.
     */
    public void searchAdjacence(int dX, int dY, HashSet<Case> casesVoisine, String paysageName) {

        if (!grille.isOutofBound(dX - 1,dY) && dominoIsNotColliding()) {
            if (grille.getCaseBis(dX-1,dY).getPaysage().getName().equals(paysageName) || grille.getCaseBis(dX-1,dY).getPaysage().getName().equals("castle")) {
                casesVoisine.add(grille.getCaseBis(dX-1,dY));
            }
        }
        if (!grille.isOutofBound(dX + 1,dY) && dominoIsNotColliding()) {
            if (grille.getCaseBis(dX+1,dY).getPaysage().getName().equals(paysageName) || grille.getCaseBis(dX+1,dY).getPaysage().getName().equals("castle")) {
                casesVoisine.add(grille.getCaseBis(dX + 1,dY));
            }
        }
        if (!grille.isOutofBound(dX,dY - 1) && dominoIsNotColliding()) {
            if (grille.getCaseBis(dX,dY - 1).getPaysage().getName().equals(paysageName) || grille.getCaseBis(dX,dY-1).getPaysage().getName().equals("castle")) {
                casesVoisine.add(grille.getCaseBis(dX,dY - 1));
            }
        }
        if (!grille.isOutofBound(dX,dY + 1) && dominoIsNotColliding()) {
            if (grille.getCaseBis(dX,dY + 1).getPaysage().getName().equals(paysageName) || grille.getCaseBis(dX,dY+1).getPaysage().getName().equals("castle")) {
                casesVoisine.add(grille.getCaseBis(dX,dY + 1));
            }
        }
    }

    /***
     *
     * @return Booléen qui vérifie que le domino ne dispose pas de collision en fonction de l'orientation.
     */
    public boolean dominoIsNotColliding() {
        int dX = this.position[0];
        int dY = this.position[1];

        switch (orientation) {
            case "horizontal":
            case "horizontalReversed":
                if (!grille.isOutofBound(dX,dY) && !grille.isOutofBound(dX,dY-1))
                    return !grille.getCase(this.position).isOccuped() && !grille.getCase(new int[]{dX,dY-1}).isOccuped();
                return false;
            case "vertical":
            case "verticalReversed":
                if (!grille.isOutofBound(dX,dY) && !grille.isOutofBound(dX - 1,dY))
                    return !grille.getCase(this.position).isOccuped() && !grille.getCase(new int[]{dX-1,dY}).isOccuped();
                return false;
            default:
                return false;
        }
    }

    @Override
    public String toString() {
        return this.domino.toString() + " " +
                this.orientation + " " + this.position[0] + " "
                + this.position[1];
    }
}