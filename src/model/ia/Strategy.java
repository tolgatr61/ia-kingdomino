package model.ia;

import model.plateau.actions.IPut;

import java.util.ArrayList;

public interface Strategy {


    IPut resolution(ArrayList<IPut> actions);


}
