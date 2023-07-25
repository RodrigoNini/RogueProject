package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.rogue.utils.Position;

public class BadGuy extends Adversarios{

    private Position position;

    public BadGuy() {
        super("BadGuy", 80, 60, new Position(0,0));
    }

    @Override
    public void movement() {
        Hero hero = Hero.getInstance();
    }

    @Override
    public void attack() {

    }

    @Override
    public void die() {

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
