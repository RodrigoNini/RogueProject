package pt.upskill.projeto1.objects.Enemies;

import pt.upskill.projeto1.objects.Hero;
import pt.upskill.projeto1.objects.RoomManager;
import pt.upskill.projeto1.rogue.utils.Position;
import pt.upskill.projeto1.rogue.utils.Vector2D;

import java.util.Random;

public class Skeleton extends Enemies {
    private Position position;

    public Skeleton(Position position) {
        super("Skeleton", position, 2, 2);

    }

    @Override
    public String getName() {
        return "Skeleton";
    }

}
