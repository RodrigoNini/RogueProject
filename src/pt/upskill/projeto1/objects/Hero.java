package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.rogue.utils.Direction;
import pt.upskill.projeto1.rogue.utils.Position;

public class Hero implements ImageTile {

    private int health;
    private Position position;
    private static final Hero INSTANCE = new Hero(new Position(4, 3));

    private Hero(Position position) {
        this.position = position;
    }

    @Override
    public String getName() {
        return "Hero";
    }

    @Override
    public Position getPosition() {
        return position;
    }

    public static Hero getInstance() {
        return INSTANCE;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setPosition(Direction direction) {
        Position newPosition = this.getPosition().plus(direction.asVector());
        this.position = newPosition;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
