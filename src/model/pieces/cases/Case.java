package model.pieces.cases;

public interface Case {

    /***
     * @return Le paysage d'une case.
     */
    public Paysage getPaysage();

    /***
     * @return Le symbole servant à l'affichage.
     */
    public String getSymbole();

    /***
     * @return Si une case est occupé (non vide) ou non (dans le cas vide).
     */
    public boolean isOccuped();

    /***
     *
     * @param position Position d'une case.
     */
    public void setPosition(int[] position);

    /***
     *
     * @return Position d'une case.
     */
    public int[] getPosition();
}
