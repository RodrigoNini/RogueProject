package pt.upskill.projeto1.objects.Enemies;

import pt.upskill.projeto1.rogue.utils.Position;

public class BadGuy extends Enemies {
    private Position position;

    public BadGuy(Position position) {
        super("BadGuy", position, 6,2);
    }

    @Override
    public String getName() {
        return "BadGuy";
    }

}
