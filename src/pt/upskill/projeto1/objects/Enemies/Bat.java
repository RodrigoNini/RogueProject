package pt.upskill.projeto1.objects.Enemies;

import pt.upskill.projeto1.rogue.utils.Position;
import pt.upskill.projeto1.rogue.utils.Vector2D;
import java.util.Random;

public class Bat extends Enemies {

    public Bat(Position position) {
        super("Bat", position, 2, 1);

    }

    @Override
    public Position randomMovement() {
        Random random = new Random();
        int dx = random.nextInt(3) - 1;
        int dy = random.nextInt(3) - 1;

        return super.getPosition().plus(new Vector2D(dx, dy));
    }


    @Override
    public String getName() {
        return "Bat";
    }

}
