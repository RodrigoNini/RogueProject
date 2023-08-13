package pt.upskill.projeto1.objects.Enemies;

import pt.upskill.projeto1.objects.Hero;

import pt.upskill.projeto1.objects.RoomManager;
import pt.upskill.projeto1.rogue.utils.Position;
import pt.upskill.projeto1.rogue.utils.Vector2D;

import java.util.Random;

public class Thief extends Enemies{

    public Thief(Position position) {

        super("Thief", position, 5,3);
    }

    @Override
    public String getName() {
        return "Thief";
    }

}
