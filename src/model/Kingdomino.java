package model;

import model.pieces.domino.Domino;
import model.plateau.Deck;
import model.plateau.actions.IPut;
import model.player.Player;

import java.util.*;

public class Kingdomino {

    private Deck deck;
    private ArrayList<Player> players;
    private ArrayList<Domino> pick;
    private boolean terminate = false;

    public Kingdomino() {
        this.deck = new Deck(48);
        this.players = new ArrayList<>();
        this.pick = new ArrayList<>();

    }

/*
---------------------------------------------------------------------------------------------------------------------
                                    GETTER/SETTER
---------------------------------------------------------------------------------------------------------------------
*/

    public ArrayList<Player> getPlayers() {
        return players;
    }


    public Deck getDeck() {
        return this.deck;
    }

    public ArrayList<Domino> getPick() {
        return pick;
    }



/*
---------------------------------------------------------------------------------------------------------------------
                                    METHODES
---------------------------------------------------------------------------------------------------------------------
*/


    public void move(IPut action) {
        action.put();
    }

    public void addPlayer(Player player) {
        player.setId(this.players.size());
        players.add(player);
    }

    // Boucle de jeu
    public void start() {
        System.out.println("Debut du jeu");
        int tourNumber = 1;
        while (!this.terminate) {
            System.out.println("=========================================== TOUR " + tourNumber + " ===========================================");
            if (this.deck.getDominos().size() == 0) {
                this.terminate = true;
            }
            if (tourNumber != 1) {
                System.out.println("Tirage de la pioches");
                try {
                    this.pick = this.deck.pick(this.players.size());
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
                System.out.println(this.pick);
                if (this.pick.isEmpty()) {
                    this.terminate = true;
                }
            }
            for (Player p : this.players) {
                System.out.println();
                System.out.println(p.getId() + "-------- TOUR du joueur : " + p + " --------");

                double startTime = System.currentTimeMillis();
                try{
                    if (!p.play()) {
                        return;
                    }
                }catch (Exception e){
                    System.err.println(e.getMessage());
                }
                long endTime = System.currentTimeMillis();

                this.pick.remove(p.getLastAction().getDomino());
                p.getPlateau().afficheGrille();
                System.out.println("Le score du joueur est :" + p.getScore());
                System.out.println("temps exe : " + ((endTime - startTime) / 1000) + "s");
                System.out.println();
            }
            tourNumber = tourNumber + 1;
        }
    }


}