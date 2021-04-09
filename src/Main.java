import model.Kingdomino;
import model.ia.Expectiminimax;
import model.ia.RandomStrategy;
import model.player.Human;
import model.player.Robot;

public class Main {
    public static void main(String[] args) {


        Kingdomino game = new Kingdomino();
        Human human = new Human(game);
        Robot random = new Robot(game);
        random.setStrategy(new RandomStrategy(game));
        Robot random2 = new Robot(game);
        random2.setStrategy(new RandomStrategy(game));
        Robot random3 = new Robot(game);
        random3.setStrategy(new RandomStrategy(game));

        Robot expecti = new Robot(game);
        expecti.setStrategy(new Expectiminimax(expecti, 2, game));


        game.addPlayer(random);
        game.addPlayer(expecti);
        //game.addPlayer(random2);
        //game.addPlayer(human);


        game.start();


    }
}