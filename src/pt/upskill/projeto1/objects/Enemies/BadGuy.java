package pt.upskill.projeto1.objects.Enemies;

import pt.upskill.projeto1.objects.Enemies.Enemies;
import pt.upskill.projeto1.rogue.utils.Position;

public class BadGuy extends Enemies {
    private Position position;

    public BadGuy(Position position) {
        super("BadGuy", position, 10,4);
    }

    @Override
    public void movement() {
    }

    @Override
    public String getName() {
        return "BadGuy";
    }
    @Override
    public Position getPosition() {
        return position;
    }


}
