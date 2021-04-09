package model.pieces.domino;

import model.pieces.cases.Case;
import model.pieces.cases.ExtremiteDomino;
import model.pieces.cases.Paysage;

import java.util.List;
import java.util.Random;

public class DominoFactory {

    /***
     *
     * @param id Identifiant du domino parser à partir du fichier CSV. (entre 0 et 47)
     * @return Le domino correspodant à l'identifiant.
     */
    public static Domino getDomino(int id) {
        if (id >= 0 && id <= 47) {
            List<String> listOfDomino = CsvParser.parse().get(id);
            ExtremiteDomino case1 = new ExtremiteDomino(new Paysage(listOfDomino.get(1), Integer.parseInt(listOfDomino.get(5))), listOfDomino.get(7).substring(0, 3));
            ExtremiteDomino case2 = new ExtremiteDomino(new Paysage(listOfDomino.get(3), Integer.parseInt(listOfDomino.get(6))), listOfDomino.get(8).substring(0, 3));
            return new Domino(case1, case2, Integer.parseInt(listOfDomino.get(0)));
        }
        System.out.println("Identifiant de domino mauvais, un aléatoire a été généré. ");
        Random random = new Random();
        int randId = random.nextInt(48);
        return getDomino(randId);
    }
}
