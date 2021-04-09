package model.pieces.cases;

public abstract class CaseImpl implements Case {

    private final Paysage paysage;
    private final String symbole;
    private final boolean occupe;
    private int[] position;

    /***
     * @param paysage Paysage d'une case.
     * @param symbole Symbole d'une case.
     */
    public CaseImpl(Paysage paysage, String symbole) {
        this.paysage = paysage;
        this.symbole = symbole;
        this.occupe = paysage.getName().equals("vide") ? false : true;
    }

    /***
     * @return Le paysage d'une case.
     */
    @Override
    public Paysage getPaysage() {
        return paysage;
    }

    /***
     * @return Le symbole d'une case.
     */
    @Override
    public String getSymbole() {
        return symbole;
    }

    /***
     *
     * @return Variable booléenne qui indique si une case est occupé.
     */
    @Override
    public boolean isOccuped() {
        return occupe;
    }

    /***
     *
     * @param position La position à set d'une case.
     */
    @Override
    public void setPosition(int[] position) {
        this.position = position;
    }

    /***
     *
     * @return La position d'une case.
     */
    @Override
    public int[] getPosition() {
        return this.position;
    }

}
