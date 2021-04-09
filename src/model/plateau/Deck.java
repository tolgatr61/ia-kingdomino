package model.plateau;

import model.pieces.domino.Domino;
import model.pieces.domino.DominoFactory;

import java.util.ArrayList;
import java.util.Random;

public class Deck {

    private int size;
    private ArrayList<Domino> deck;


    public Deck(int size) {
        this.size = Math.min(size, 48);
        this.deck = new ArrayList<>();
        for (int i = 0; i < this.size; i++) {
            Domino d = DominoFactory.getDomino(i);
            this.deck.add(d);
        }
    }

    public Deck(int size, boolean clear) {
        this(size);
        if (clear) {
            this.deck.clear();
        }
    }

    public int getSize() {
        return size;
    }

    public ArrayList<Domino> getDominos() {
        return this.deck;
    }

    public void addDomino(Domino d) {
        this.deck.add(d);

    }

    public void removeDomino(Domino d) {
        this.deck.remove(d);
    }

    /**
     * Permet de tirer au sort un nombre donné de domino et de les retirer du deck.
     *
     * @param number : nombre de domino pioché
     * @return une liste de domino
     */
    public ArrayList<Domino> pick(int number) throws Exception {
        ArrayList<Domino> picks = new ArrayList<>();
        if (number < 0 || number > this.deck.size()) {
            throw new Exception("number is not a valid number.");
        }
        if (getDominos().size() >= number + 1) {
            for (int i = 0; i < number; i++) {
                Random random = new Random();
                int nb;
                nb = random.nextInt(getDominos().size());
                picks.add(getDominos().get(nb));
                removeDomino(getDominos().get(nb));
            }
        }
        return picks;
    }

}
