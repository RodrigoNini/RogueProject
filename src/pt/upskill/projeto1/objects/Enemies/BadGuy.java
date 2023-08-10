package pt.upskill.projeto1.objects.Enemies;

import pt.upskill.projeto1.objects.Enemies.Enemies;
import pt.upskill.projeto1.rogue.utils.Position;

public class BadGuy extends Enemies {

    private String name;
    private int health;
    private int damage;
    private Position position;

    public BadGuy(Position position) {
        this.name = "BadGuy";
        this.health = 70;
        this.damage = 50;
        this.position = position;
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
        return name;
    }


    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth() {

    }
    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition() {

    }
}
