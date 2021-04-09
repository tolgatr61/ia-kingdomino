package model.pieces.cases;

public class Castle extends CaseImpl {

    public Castle() {
        super(new Paysage("castle", 0), "cas");
    }

    @Override
    public String toString() {
        return "[ castle ]";
    }
}
