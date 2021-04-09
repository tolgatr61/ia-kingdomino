package model.pieces.cases;

public class Paysage {

    private final String name;
    private final int nbCouronnes;

    /***
     *
     * @param name Nom du paysage.
     * @param nbCouronnes Nombre de couronnes lié au paysage.
     */
    public Paysage(String name, int nbCouronnes) {
        this.name = name;
        this.nbCouronnes = nbCouronnes;
    }

    /***
     * Getters
     */
    public String getName() {
        return this.name;
    }

    public int getNbCouronnes() {
        return this.nbCouronnes;
    }

}
