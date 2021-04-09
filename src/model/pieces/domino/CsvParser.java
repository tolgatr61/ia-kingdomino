package model.pieces.domino;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvParser {

    /***
     *
     * @return Liste de liste des données parser à partir du fichier "kingdomino.csv"
     */
    public static List<List<String>> parse() {
        List<List<String>> parsedLists = new ArrayList<List<String>>();
        try (CSVReader csvReader = new CSVReader(new FileReader("kingdomino.csv"));) {
            String[] values = null;
            while ((values = csvReader.readNext()) != null) {
                parsedLists.add(Arrays.asList(values));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parsedLists;
    }

    /***
     *
     * @param newList Liste de liste des données parser à afficher
     */
    public static void afficheListe(List<List<String>> newList) {
        for (List<String> cs : newList) {
            if (!cs.isEmpty()) {
                for (int i = 0; i < cs.size(); i++) {
                    System.out.print(cs.get(i) + " | ");
                }
            }
            System.out.print("\n");
        }
    }

}
