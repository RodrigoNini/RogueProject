package pt.upskill.projeto1.objects.Enemies;

import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.objects.Hero;
import pt.upskill.projeto1.rogue.utils.Position;

public abstract class Enemies implements ImageTile {

    private Position position;
    private int health;
    private int damage;
    public Enemies() {

    }

    public abstract void movement();

    public abstract void attack();

    public abstract void die();

    public void takeDamage(int damage){
        health = health - damage;
    }


     public abstract String getName();

    public abstract int getHealth();

    public abstract void setHealth();

    public abstract int getDamage();

    public abstract Position getPosition();

    public abstract void setPosition();
}
