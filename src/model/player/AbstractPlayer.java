package model.player;

import model.Kingdomino;
import model.plateau.Grille;
import model.plateau.Score;
import model.plateau.actions.IPut;

public abstract class AbstractPlayer implements Player {

    private int id;
    private Grille plateau;
    private Kingdomino game;

    public AbstractPlayer(Kingdomino game) {
        this.game = game;
        this.plateau = new Grille(5, 5);
    }

    public int getId() {
        return this.id;
    }

    public Grille getPlateau() {
        return plateau;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPlateau(Grille plateau) {
        this.plateau = plateau;
    }

    public Kingdomino getGame() {
        return game;
    }

    public int getScore() {
        Score score = new Score(plateau);
        return score.calculateScore();
    }

    @Override
    public boolean play() throws Exception {
        return false;
    }

}
