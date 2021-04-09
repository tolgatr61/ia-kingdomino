package model.plateau;

import model.pieces.cases.Case;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Score {

    private Grille grille;

    public Score(Grille grille) {
        this.grille = grille;
    }

    public static String getTheMostPaysage(Grille grille) {
        HashMap<String, Integer> paysageCounter = new HashMap<>();
        for (int i = 0; i < grille.getNbLigne(); i++) {
            for (int j = 0; j < grille.getNbColonne(); j++) {
                paysageCounter.put(grille.getGrille()[i][j].getPaysage().getName(), 0);
            }
        }
        for (int i = 0; i < grille.getNbLigne(); i++) {
            for (int j = 0; j < grille.getNbColonne(); j++) {
                paysageCounter.put(grille.getGrille()[i][j].getPaysage().getName(), paysageCounter.get(grille.getGrille()[i][j].getPaysage().getName()) + 1);
            }
        }
        String most = "null";
        int plus = 0;
        for (Map.Entry<String, Integer> entry : paysageCounter.entrySet()) {
            if (!entry.getKey().equals("vide")) {
                if (entry.getValue() > plus) {
                    plus = entry.getValue();
                    most = entry.getKey();
                }
            }
        }
        return most;
    }

    /***
     *
     * @param x Index x (ligne)
     * @param y Index y (colonne)
     * @param verifiedCases La liste de cases déjà verifiées.
     * @param area La liste de cases composant une zone.
     * @return Score de la zone calculé récursivement.
     */
    public int calculateAreaScore(int x, int y, ArrayList<Case> verifiedCases, ArrayList<Case> area) {
        Case c = grille.getCaseBis(x, y);
        verifiedCases.add(c);
        area.add(c);

        Case caseAbove = (x > 0) ? grille.getCaseBis(x - 1, y) : null;
        if (caseAbove != null && !verifiedCases.contains(caseAbove)) {
            if (caseAbove.getPaysage().getName().equals(c.getPaysage().getName()))
                calculateAreaScore(x - 1, y, verifiedCases, area);
        }

        Case caseUnder = (x < grille.getNbLigne() - 1) ? grille.getCaseBis(x + 1, y) : null;
        if (caseUnder != null && !verifiedCases.contains(caseUnder)) {
            if (caseUnder.getPaysage().getName().equals(c.getPaysage().getName()))
                calculateAreaScore(x + 1, y, verifiedCases, area);
        }

        Case caseLeft = (y > 0) ? grille.getCaseBis(x, y - 1) : null;
        if (caseLeft != null && !verifiedCases.contains(caseLeft)) {
            if (caseLeft.getPaysage().getName().equals(c.getPaysage().getName()))
                calculateAreaScore(x, y - 1, verifiedCases, area);
        }

        Case caseRight = (y < grille.getNbColonne() - 1) ? grille.getCaseBis(x, y + 1) : null;
        if (caseRight != null && !verifiedCases.contains(caseRight)) {
            if (caseRight.getPaysage().getName().equals(c.getPaysage().getName()))
                calculateAreaScore(x, y + 1, verifiedCases, area);
        }

        int nbCrowns = 0;
        for (Case c0 : area)
            nbCrowns += c0.getPaysage().getNbCouronnes();

        return nbCrowns * area.size();
    }

    /***
     *
     * @param verifiedCases Liste de cases vérifiées.
     * @return On retourne la somme des scores.
     */
    public int calculateVerifiedCases(ArrayList<Case> verifiedCases) {
        int score = 0;
        if (grille == null) {
            return 0;
        }
        for (int i = 0; i < grille.getNbLigne(); i++)
            for (int j = 0; j < grille.getNbColonne(); j++) {
                Case c = this.grille.getCaseBis(i, j);

                if (!c.getPaysage().getName().equals("castle") && !verifiedCases.contains(c)) {
                    ArrayList<Case> area = new ArrayList<Case>();
                    score += calculateAreaScore(i, j, verifiedCases, area);
                }
            }

        return score;
    }

    /***
     *
     * @return Calcule le score final.
     */
    public int calculateScore() {
        ArrayList<Case> verifiedCases = new ArrayList<>();
        return calculateVerifiedCases(verifiedCases) + pointBonus();
    }

    /***
     * Vérifie que toute la grille est complète.
     */
    public boolean checkPointBonus() {
        boolean checker = true;
        if (grille == null) {
            return false;
        }
        for (int i = 0; i < grille.getNbLigne(); i++) {
            for (int j = 0; j < grille.getNbColonne(); j++) {
                if (grille.getCaseBis(i, j).isOccuped() == false) {
                    checker = false;
                }
            }
        }
        return checker;
    }

    /***
     *
     * @return 5 points si la grille est complète sinon rien.
     */
    public int pointBonus() {
        return checkPointBonus() ? 5 : 0;
    }
}
