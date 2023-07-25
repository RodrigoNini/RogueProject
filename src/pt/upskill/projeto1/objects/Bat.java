package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.rogue.utils.Position;

public class Bat extends Adversarios{

    private Position position;

    public Bat(){
        super("Bat", 60, 30, new Position(0,0));
    }

    @Override
    public void movement() {

    }

    @Override
    public void attack() {

    }

    @Override
    public void die() {

    }

    @Override
    public String getName() {
        return "Bat";
    }

    @Override
    public Position getPosition() {
        return position;
    }
}
