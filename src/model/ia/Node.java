package model.ia;

import model.player.Player;
import model.plateau.Score;

import java.util.HashSet;

public class Node {

    private Player player;
    private State state;
    private HashSet<Node> child;
    private int heuristic;
    private boolean isChance = false;

    public Node(Player player, State state) {
        this.player = player;
        this.state = state;
        this.child = new HashSet<>();
        Score score = new Score(this.getState().getSavesGrid().get(this.player));
        this.heuristic = score.calculateScore();
    }


    public State getState() {
        return state;
    }

    public HashSet<Node> getChild() {
        return child;
    }

    public Player getPlayer() {
        return player;
    }

    public int getHeuristic() {

        Score score = new Score(this.getState().getSavesGrid().get(this.player));
        return score.calculateScore();
    }

    public void addChild(Node child) {
        this.child.add(child);
    }

    public boolean isChance() {
        return isChance;
    }

    public void setChance() {
        this.isChance = true;
        try {
            this.state.setPick(this.state.getDeck().pick(this.state.getGame().getPlayers().size()));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Node)) {
            return false;
        }
        return this.heuristic == ((Node) object).getHeuristic() && this.player == ((Node) object).getPlayer()
                && this.state == ((Node) object).getState();
    }

    public String toString() {
        return "{" +
                "" + (this.getPlayer() == null ? "chance" : this.getPlayer().getId()) + " | " + this.heuristic + "->" + this.getChild()
                + "}";

    }
}