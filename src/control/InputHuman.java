package control;

import model.pieces.cases.Castle;
import model.pieces.domino.Domino;
import model.plateau.actions.IPut;
import model.plateau.actions.PutCastle;
import model.player.Human;
import model.player.Player;

import java.util.ArrayList;
import java.util.Scanner;

public class InputHuman {


    public static IPut chooseAction(Human player){
        int j=0;
        if(player.getPlateau().getCastle()==null){
            System.out.println("Veuillez placer votre chateau 22 pour x=2 et y=2");
            Scanner myObj1 = new Scanner(System.in);
            String position = myObj1.nextLine();
            Castle castle = new Castle();
            return new PutCastle(player.getPlateau(), castle, new int[]{Integer.parseInt(""+position.charAt(0)), Integer.parseInt(position.charAt(1)+"")});

        }
        System.out.println("votre grille : ");
        player.getPlateau().afficheGrille();
        System.out.println();
        for(Domino d : player.getGame().getPick()){
            System.out.print(j+" "+d+"|");
            j=j+1;
        }

        System.out.println("\nVeuillez choisir un domino");
        Scanner myObj = new Scanner(System.in);
        int idDomino = myObj.nextInt();
        Domino domino = player.getGame().getPick().get(idDomino);
        ArrayList<Domino> justTest = new ArrayList<>();
        justTest.add(domino);
        System.out.println("Veuillez choisir une action");
        int i=0;
        for (IPut action: player.getPlateau().actionsPossible(justTest)){
            System.out.print(i+" "+action+" | ");
            if (i%2==0){
                System.out.println();
            }
            i=i+1;
        }
        System.out.println();
        Scanner myObj2 = new Scanner(System.in);
        int position2 = myObj2.nextInt();
        return player.getPlateau().actionsPossible(justTest).get(position2);
    }

}
