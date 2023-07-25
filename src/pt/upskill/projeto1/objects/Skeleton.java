package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.rogue.utils.Position;

public class Skeleton extends Adversarios{
    private String name;
    private int health;

    private int damage;

    private Position position;

    public Skeleton(Position position) {
        super("Skeleton", 40, 30, position);

    }

    @Override
    public void movement() {

    }

    @Override
    public void attack() {
        Hero hero = Hero.getInstance();
        hero.setHealth(hero.getHealth() - damage);
    }

    @Override
    public void die() {
        health = 0;
    }

    @Override
    public String getName() {
        return "Skeleton";
    }

    @Override
    public Position getPosition() {
        return position;
    }


}
