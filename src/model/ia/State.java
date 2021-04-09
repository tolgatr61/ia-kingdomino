package model.ia;

import model.Kingdomino;
import model.player.Player;
import model.pieces.domino.Domino;
import model.plateau.Deck;
import model.plateau.Grille;

import java.util.ArrayList;
import java.util.HashMap;

public class State {

    private Kingdomino game;
    private HashMap<Player, Grille> savesGrid;
    private Deck deck;
    private ArrayList<Domino> pick;
    private Player currentPlayer;

    public State(Kingdomino game, Player player) {
        this.game = game;
        this.savesGrid = new HashMap<>();
        this.deck = new Deck(game.getDeck().getSize(), true);
        this.currentPlayer = player;
        for (Player p : game.getPlayers()) {
            Grille grille = new Grille(p.getPlateau().getNbLigne(), p.getPlateau().getNbColonne());
            for (int i = 0; i < p.getPlateau().getNbLigne(); i++) {
                for (int j = 0; j < p.getPlateau().getNbColonne(); j++) {
                    grille.getGrille()[i][j] = p.getPlateau().getCase(new int[]{i, j});
                }
            }
            this.savesGrid.put(p, grille);
        }

        for (Domino d : game.getDeck().getDominos()) {
            this.deck.addDomino(d);
        }
        this.pick = this.game.getPick();

    }

    public ArrayList<Domino> getPick() {
        return pick;
    }

    public void setPick(ArrayList<Domino> pick) {
        this.pick = pick;
    }

    public Player nextPlayer() {
        int idNextPlayer = this.currentPlayer == null ? 0 : this.currentPlayer.getId() + 1;
        if (idNextPlayer >= this.game.getPlayers().size()) {
            this.currentPlayer = null;
        } else {
            this.currentPlayer = this.game.getPlayers().get(idNextPlayer);
        }
        return this.currentPlayer;
    }

    public Kingdomino getGame() {
        return game;
    }

    public HashMap<Player, Grille> getSavesGrid() {
        return savesGrid;
    }

    public Deck getDeck() {
        return deck;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof State)) {
            return false;
        }
        return this.getCurrentPlayer() == ((State) object).getCurrentPlayer() && this.getSavesGrid() == ((State) object).getSavesGrid();
    }

}