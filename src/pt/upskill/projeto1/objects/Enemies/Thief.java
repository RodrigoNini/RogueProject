package pt.upskill.projeto1.objects.Enemies;

import pt.upskill.projeto1.objects.Hero;

import pt.upskill.projeto1.rogue.utils.Position;
import pt.upskill.projeto1.rogue.utils.Vector2D;

import java.util.Random;

public class Thief extends Enemies{

    private Hero hero = Hero.getInstance();

    private Position position;
    public Thief(Position position) {
        super("Thief", position, 5,3);
    }


    @Override
    public void movement() {
        int distance = distanceBetween(this.position, hero.getPosition());
        if (distance <= 3) {
            moveNear();
        } else {
            moveRandom();
        }
    }

    private void moveNear() {
        Position heroPosition = hero.getPosition();
        if (position.getX() < heroPosition.getX()) {
            position = position.plus(new Vector2D(1, 0));
        } else if (position.getX() > heroPosition.getX()) {
            position = position.plus(new Vector2D(-1, 0));
        }

        if (position.getY() < heroPosition.getY()) {
            position = position.plus(new Vector2D(0, 1));
        } else if (position.getY() > heroPosition.getY()) {
            position = position.plus(new Vector2D(0, -1));
        }
    }

    private void moveRandom() {
        //permite movimento na diagonal
        Random random = new Random();
        int dx = random.nextInt(3) - 1;
        int dy = random.nextInt(3) - 1;
        position = position.plus(new Vector2D(dx, dy));
    }

    private int distanceBetween(Position pos1, Position pos2) {
        int distX = pos1.getX() - pos2.getX();
        int distY = pos1.getY() - pos2.getY();
        return distX + distY;
    }

    @Override
    public String getName() {
        return "Thief";
    }

    @Override
    public Position getPosition() {
        return position;
    }


}
